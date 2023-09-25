# Gestione prenotazioni
## Struttura
Per una migliore gestione e modularità del codice si è divisa la logica relativa al modello, alla gestione ed alla visualizzazione dei dati relativi alle prenotazioni in file distinti.

#### Modello
- Prenotazione.java: rappresenta il modello della singola prenotazione nell'applicazione
#### DAO (Data Access Object)
- PrenotazioneDAO.java : contiene le funzioni che operano con il database SQLite per quanto riguarda gli oggetti *Prenotazione*
#### Servlet
- PrenotazioneServlet.java: gestisce le richieste HTTP ed agisce da tramite tra frontend e database richiamando le funzioni contenute nel **DAO** per recuperare le prenotazioni salvate nel database
- PrenotazioneSingolaServlet.java: gestisce la richiesta HTTP relativa alla visualizzazione di una singola prenotazione richiamando tramite una funzione specifica nel **DAO** i dati della prenotazione richiesta
#### JSP (frontend)
- prenotazioni.jsp: mostra la lista delle prenotazioni disponibili nel database
- prenotazione.jsp: mostra i dati relativi alla prenotazione prescelta

## File
### Prenotazione
Il file relativo al modello contiene la creazione dell'oggetto *Prenotazione* ed i metodi **getter** e **setter**, utili a recuperare ed impostare le sue proprietà in maniera rapida e modulare.

```
private int id;
private int idCliente;
private int idCamera;
private Date checkIn;
private Date checkOut;
private int notti;
private double totale;

private String clienteNome;
private String clienteCognome;
private String clienteDocumento;
private String clienteEmail;
private String clienteTelefono;

private int cameraNumero;
private String cameraTipologia;
```

Come si vede dal codice, il modello contiene anche variabili relativi ai clienti ed alle camere, in quanto la tabella *prenotazioni* del database contiene anche i dati relativi a chi effettua la prenotazione e quale camera è stata prenotata.

### PrenotazioneDAO
Il file contiene tre metodi principali, create basandosi sull'esigenza dell'utente che utilizzerà l'app:
- **getAllPrenotazioni**: recupera tutte le *Prenotazioni* presenti nel database
- **getPrenotazioniByFilter**: recupera le *Prenotazioni* presenti nel database secondo dei parametri specifici 
- **getPrenotazioneById**: recupera i dati di una singola *Prenotazione* specifica

Al fine di rendere il codice più modulare ed evitare inutili ridondanze, si è scelto di scrivere a parte le variabili e le funzioni utilizzate da tutte e tre i metodi principali.

```
String sql = "SELECT prenotazioni.*, " +
    "clienti.nome AS clienteNome, clienti.cognome AS clienteCognome, clienti.carta_id AS clienteDocumento, clienti.email AS clienteEmail, clienti.telefono AS clienteTelefono, " +
    "camere.id AS cameraNumero, camere.tipologia AS cameraTipologia " +
    "FROM prenotazioni " +
    "JOIN clienti ON prenotazioni.id_cliente = clienti.id " +
    "JOIN camere ON prenotazioni.id_camera = camere.id";
```
Questa è la query di richiesta al database dei dati presenti nella tabella *prenotazioni* comune ai tre metodi principali.

```
public Prenotazione setAllData(ResultSet rs) throws SQLException {
    Prenotazione p = new Prenotazione();

    p.setId(rs.getInt("id"));
    p.setIdCliente(rs.getInt("id_cliente"));
    p.setIdCamera(rs.getInt("id_camera"));
    p.setNotti(rs.getInt("notti"));
    p.setCheckIn(rs.getDate("check_in"));
    p.setCheckOut(rs.getDate("check_out"));
    p.setTotale(rs.getDouble("totale"));
    p.setClienteNome(rs.getString("clienteNome"));
    p.setClienteCognome(rs.getString("clienteCognome"));
    p.setClienteDocumento(rs.getString("clienteDocumento"));
    p.setClienteEmail(rs.getString("clienteEmail"));
    p.setClienteTelefono(rs.getString("clienteTelefono"));
    p.setCameraNumero(rs.getInt("cameraNumero"));
    p.setCameraTipologia(rs.getString("cameraTipologia"));

    return p;
}
```
Questo è invece un metodo che si occupa di ordinare i dati ricevuti dal database dopo la richiesta, in maniera tale da renderli utilizzabili da Java inserendoli in un'istanza *p* del modello *Prenotazione*.

#### getAllPrenotazioni
```
public List<Prenotazione> getAllPrenotazioni(String sql) {
    List<Prenotazione> prenotazioni = new ArrayList<>();

    try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Prenotazione p = setAllData(rs);
            prenotazioni.add(p);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return prenotazioni;
}
```
Recupera tutte le *Prenotazioni* presenti nel database sfruttando la query **sql** salvata a parte in precedenza e passata alla funzione come variabile.

La funzione si occupa di creare una **List** di oggetti **Prenotazione** vuota. Successivamente fa una richiesta al database e crea un'oggetto **Prenotazione** per ogni riga trovata, impostandone le singole proprietà tramite la funzione **setAllData** salvata precedentemente.

#### getPrenotazioniByFilter
```
public List<Prenotazione> getPrenotazioniByFilter(String sql, String tipologiaCamera, String checkInDateOrder) {
    List<Prenotazione> prenotazioni = new ArrayList<>();

    boolean filterByTipologia = tipologiaCamera != null && !tipologiaCamera.isEmpty();
    boolean orderByCheckIn = checkInDateOrder != null && !checkInDateOrder.isEmpty();

    if (filterByTipologia) {
        sql += " WHERE";
        if (filterByTipologia) {
            sql += " camere.tipologia = ?";

            if (orderByCheckIn) {
                sql += " ORDER BY prenotazioni.check_in";
                sql += " " + checkInDateOrder;
            }
        }
            
    } else {
        if (orderByCheckIn) {
            sql += " ORDER BY prenotazioni.check_in";
            sql += " " + checkInDateOrder;
        }
    }

    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        int paramIndex = 1;

        if (filterByTipologia) {
            stmt.setString(paramIndex++, tipologiaCamera);
        }

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Prenotazione p = setAllData(rs);
            prenotazioni.add(p);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return prenotazioni;
}
```
Recupera le *Prenotazioni* presenti nel database secondo dei parametri specifici passati come variabili della funzione; in questo caso **tipologiaCamera** e **checkInDateOrder** visto che nella pagina del frontend è possibile filtrare le prenotazioni secondo la tipologia della camera e ordinarle in maniera crescente o decrescente secondo la data del check-in.

Il metodo sfrutta la variabile **sql** salvata a parte per fare la richiesta di query al database dei dati dalla tabella *prenotazioni*, poi la prolunga con  nuovi parametri a seconda della richiesta effettuata.
```
if(filterByTipologia) {
    sql+=" WHERE";
    
    if(filterByTipologia) {
        sql+=" camere.tipologia = ?";

        if(orderByCheckIn) {
            sql+=" ORDER BY prenotazioni.check_in";
            sql+=" " + checkInDateOrder;
        }
    }

} else {
    if(orderByCheckIn) {
        sql+=" ORDER BY prenotazioni.check_in";
        sql+=" " + checkInDateOrder;
    }
}
```
Questa è la sezione in cui il metodo prolunga la query **sql** con dei parametri in maniera tale da filtrare i dati secondo la richiesta.

#### getPrenotazioneById
```
public Prenotazione getPrenotazioneById(String sql, int id) {
    Prenotazione p = null;

    try {
        sql += " WHERE prenotazioni.id = " + id + ";";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            p = setAllData(rs);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return p;
}
```
Recupera i dati di una singola *Prenotazione* specifica.

Come il metodo precedente, sfrutta la variabile **sql** contenente la query che recupera dal database tutte le prenotazioni, salvata a parte, e la prolunga richiedendo solo la prenotazione con il parametro **id** specifico.
```
sql += " WHERE prenotazioni.id = " + id + ";";
```

### Servlet
#### PrenotazioneServlet
Come impostato nel file **web.xml** questa servlet entra in gioco quando un utente fa una richiesta di **GET** alla pagina, ovvero la carica e visualizza.
```
<servlet>
    <servlet-name>PrenotazioneServlet</servlet-name>
    <servlet-class>PrenotazioneServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>PrenotazioneServlet</servlet-name>
    <url-pattern>/prenotazioni</url-pattern>
</servlet-mapping>
```

La servlet sfrutta i metodi contenuti nella libreria **javax.servlet** per gestire le richieste HTTP.
```
public class PrenotazioneServlet extends HttpServlet {
    
// codice

}
```

Successivamente gestisce le operazioni da fare quando viene fatta la richiesta di **GET** alla pagina */prenotazioni*.
```
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Camera> camere = CameraDAO.getAllCamere();    
    request.setAttribute("camere", camere);
    List<Prenotazione> prenotazioni;

    // Controlla se i filtri sono applicati o meno
    String tipologiaCamera = request.getParameter("tipologia-camera");
    String checkInDateOrder = request.getParameter("ordina-data-check-in");

    if (tipologiaCamera != null && !tipologiaCamera.isEmpty() || checkInDateOrder != null && !checkInDateOrder.isEmpty()) {
        prenotazioni = PrenotazioneDAO.getPrenotazioniByFilter(PrenotazioneDAO.sql, tipologiaCamera, checkInDateOrder);
    } else {
        prenotazioni = PrenotazioneDAO.getAllPrenotazioni(PrenotazioneDAO.sql);
    }

    request.setAttribute("prenotazioni", prenotazioni);

    // Invio delle richieste e redirect
    RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni.jsp");
    dispatcher.forward(request, response);
}
```
La servlet richiama tutte le prenotazioni con il metodo **getAllPrenotazioni** o solo quelle filtrate con il metodo **getPrenotazioniByFilter**, a seconda che nella richiesta di **GET** siano presenti o meno dei parametri.
```
if (tipologiaCamera != null && !tipologiaCamera.isEmpty() || checkInDateOrder != null && !checkInDateOrder.isEmpty()) {

    // codice

}
```

I possibili parametri per il filtraggio arrivano dalla pagina stessa all'interno della richiesta di **GET** e vengono filtrati e salvati qui:
```
String tipologiaCamera = request.getParameter("tipologia-camera");
String checkInDateOrder = request.getParameter("ordina-data-check-in");
```

Dopo aver creato la lista delle prenotazioni la servlet si occupa di inviare questo parametro alla pagina tramite:
```
request.setAttribute("prenotazioni", prenotazioni);
```
ed infine invio al broswer dell'utente la pagina:
```
RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni.jsp");
dispatcher.forward(request, response);
```

#### PrenotazioneSingolaServlet
Sfruttando le stesse premesse relative all'utilizzo della libreria **javax.servlet** per gestire le richieste HTTP, si occupa di quelle inviate all'indirizzo */prenotazioni/id*, dove **id** viene sostituito dall'id della prenotazione salvata nel database.
```
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    String[] parts = requestURI.split("/");
    
    if (parts.length > 0) {
        String parametro = parts[parts.length - 1];

        try {
            int idPrenotazione = Integer.parseInt(parametro);
            Prenotazione prenotazione = PrenotazioneDAO.getPrenotazioneById(PrenotazioneDAO.sql, idPrenotazione);
            if (prenotazione == null) {
                response.sendRedirect("/prenotazioni");
                return;
            }
            
            request.setAttribute("prenotazione", prenotazione);
        } catch (NumberFormatException e) {
            response.sendRedirect("/prenotazioni");
            return;
        }            
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("/prenotazione.jsp");
    dispatcher.forward(request, response);
}
```

La prima parte si occupa di recuperare qualunque dato appaia dopo */prenotazioni/* nella richiesta HTTP e controllare se è o meno un id valido, ovvero associato ad una riga nella tabella *prenotazioni* del database.

Per essere sicuri che l'utente non richiami pagine inesistenti 
```
String requestURI = request.getRequestURI();
String[] parts = requestURI.split("/");

if (parts.length > 0) {
    String parametro = parts[parts.length - 1];

    try {
        int idPrenotazione = Integer.parseInt(parametro);
        Prenotazione prenotazione = PrenotazioneDAO.getPrenotazioneById(PrenotazioneDAO.sql, idPrenotazione);

        if (prenotazione == null) {
            response.sendRedirect("/prenotazioni");
            return;
        }
        
        request.setAttribute("prenotazione", prenotazione);

    } catch (NumberFormatException e) {
        response.sendRedirect("/prenotazioni");
        return;
    }            
}
```