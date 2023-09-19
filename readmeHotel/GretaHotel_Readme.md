# GretaHotel

GretaHotel è un'applicazione di database management che permette una facile gestione dei dati di un ipotetico Hotel online trattando in particolare le sezioni camere, clienti e prenotazioni.

Le tecnologie con cui è stata creata sono:

- Java
- SQLite
- Maven
- Java Server Page
- Bootstrap



## Backend

Per lavorare in maniera efficace e ordinata abbiamo compartimentanto il programma in tre tipi di classi Java:

- Modello di Dati
- DAO (Databse Access Object)
- Servlet


## Modello di Dati

Per rappresentare le tabelle Camere, Clienti e Prenotazioni, abbiamo creato delle classi Java corrispondenti; questi tipi di classe possiedono solo i campi che corrispondono alle colonne nelle tabelle del database.Ad esempio, la classe Prodotto ha un campo id, un campo nome, un campo prezzo, un campo immagine e un campo descrizione che corrispondono ad ogni colonna della tabella Camere.
```java
public class Camera{

    private int id;
    private String tipologia;
    private String descrizione;
    private Double prezzo;
    private String immagine;
    private boolean disponibilita;


```

Le classi poi sono dotate di costruttori per inizializzare i campi, così come Getter e Setter per accedere e modificare i valori dei campi.

```java
    public Camera(){
    }

    public Camera(int id, String tipologia, String descrizione, Double prezzo, String immagine, boolean disponibilita){
        this.id = id;
        this.tipologia = tipologia;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.immagine = immagine;
        this.disponibilita = disponibilita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipologia(){
        return tipologia;
    }

    public void setTipologia(String tipologia){
        this.tipologia = tipologia;
    }

    public String getDescrizione(){
        return descrizione;
    }

    public void setDescrizione (String descrizione){
        this.descrizione = descrizione;
    }

    public Double getPrezzo (){
        return prezzo;
    }
    public void setPrezzo(Double prezzo){
        this.prezzo = prezzo;
    }

    public String getImmagine(){
        return immagine;
    }
    public void setImmagine(String immagine){
        this.immagine = immagine;
    }

    public boolean getDisponibilita (){
        return disponibilita;
    }
    public void setDisponibilita(boolean disponibilita){
        this.disponibilita = disponibilita;
    }
}
```

## DAO (Data Access Object)

Abbiamo separato la gestione dei database in delle classi apposite, ovvero delle classi di tipo Data Access Object.
Queste classi includono metodi per salvare, recuperare, aggiornare e eliminare dati dal database.

Riportiamo la classe CameraDAO.java come esempio
```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CameraDAO {
    private Connection conn; //connessione

    public CameraDAO(Connection conn) { //costruttore di classe
        this.conn = conn;
    }

    public List<Camera> getAllCamere() { //metodo che restituisce una lista di oggetti Camera
        List<Camera> camere = new ArrayList<>(); //creazione della lista

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM camere")) {

            while(rs.next()) {
                Camera p = new Camera(); //creazione oggetto Camera
                //utilizzo il metodo di Camera p per assegnare il valore all'id del Camera,
                //leggendolo dal valore della colonna della riga corrente del ResultSet
                p.setId(rs.getInt("id"));
                p.setTipologia(rs.getString("tipologia"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setImmagine(rs.getString("immagine"));
                p.setDisponibilita(rs.getBoolean("disponibilita"));
                camere.add(p);
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return camere; //riporta la lista di Prodotti
    }

    public Camera getCameraById(int id) { //metodo di selezione Camera in base all'id (in input) che restituisce un oggetto Camera
        Camera p = null; //creazione oggetto Camera vuoto

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM camere WHERE id = ?")) { //selezione dalla tabella prodotti in base all'id

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) { // assegnazione dei valori all'oggetto Camera in base alla riga dell'id fornito
                    p = new Camera();
                    p.setId(rs.getInt("id"));
                    p.setTipologia(rs.getString("tipologia"));
                    p.setDescrizione(rs.getString("descrizione"));
                    p.setPrezzo(rs.getDouble("prezzo"));
                    p.setImmagine(rs.getString("immagine"));
                    p.setDisponibilita(rs.getBoolean("disponibilita"));
                }

            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return p; //riporta il Camera completo
    }

    public void insertCamera(Camera p) { //metodo di inserimento che prende in input un Camera (p)
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO camere (tipologia, descrizione, prezzo, immagine, disponibilita) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, p.getTipologia()); //riporto i valori nella riga della tabella prodotti
            stmt.setString(2, p.getDescrizione());
            stmt.setDouble(3, p.getPrezzo());
            stmt.setString(4, p.getImmagine());
            stmt.setBoolean(5, p.getDisponibilita());
            stmt.executeUpdate();
        }catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        
    }

    public void cancellaCamera(int id){ //metodo per cancellare un Camera che prende in input l'id
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM camere WHERE id = ?")){
            stmt.setInt(1, id); // Cancella il Camera in base all'id
            stmt.executeUpdate();
        }catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }

    private static void camereDisponibili(Connection conn) throws SQLException {
        String selezionaCamereDisponibili = "SELECT * FROM camere WHERE disponibilita = 0";
        
        try (PreparedStatement stmt = conn.prepareStatement(selezionaCamereDisponibili)) {
            ResultSet rs = stmt.executeQuery();
            while (resultSet.next()) {
                rs.getId();
                rs.getTipologia();
                rs.getDescrizione();
                rs.getPrezzo();
                rs.getImmagine();
                rs.getDisponibilita();
            }
        }
    }

    private static void updateDisponibilita(Connection conn, int idCamera, boolean occupata) throws SQLException {
        String updateDisponibilitaCamera = "UPDATE camere SET disponibilita = ? WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(updateDisponibilitaCamera)) {
            stmt.setBoolean(1, occupata);
            stmt.setInt(2, idCamera);
            stmt.executeUpdate();
        }
    }
}

```





## Servlet

Una servlet è una componente fondamentale nella creazione di applicazioni web in Java. Offre un modo per gestire le richieste e le risposte HTTP in modo dinamico, consentendo lo sviluppo di applicazioni web interattive e personalizzate.

```java
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager; // Importa DriverManager da java.sql
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CameraServlet extends HttpServlet {
    private CameraDAO CameraDAO;

    public void init() {
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database.", e);
        }
        CameraDAO = new CameraDAO(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Camera> camere = CameraDAO.getAllCamere();
        request.setAttribute("camere", camere);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("camere.jsp");
        dispatcher.forward(request, response);
    }
}
```
In sostanza, questa servlet si occupa di ottenere dati dalle Prenotazioni dal database, memorizzarli come attributo della richiesta e inoltrare la richiesta a una pagina JSP per la visualizzazione
****


## Intestazione e menù principale condivisi tra le pagine Camere clienti ed ordini

Per condividere un'intestazione e un menu principale tra le pagine di Camere, clienti ed ordini, puoi creare una struttura di layout comune che includa queste parti e quindi includerla nelle diverse pagine JSP. Ecco come farlo:

**1. Creazione di una struttura di layout comune**

Crea una nuova pagina JSP chiamata layout.jsp che conterrà l'intestazione e il menu principale condivisi. Questa pagina sarà il tuo layout di base che verrà incluso nelle altre pagine.

```html

<!DOCTYPE html>
<html>
<head>
    <title>Il Tuo Sito Web</title>
    <!-- Aggiungi qui i tuoi collegamenti CSS e script JavaScript comuni -->
</head>
<body>
    <header>
        <!-- Includi l'intestazione qui -->
        <h1>Il Tuo Sito Web</h1>
        <nav>
            <!-- Includi il menu principale qui -->
            <ul>
                <li><a href="camere.jsp">Camere</a></li>
                <li><a href="clienti.jsp">Clienti</a></li>
                <li><a href="prenotazioni.jsp">Ordini</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <!-- Il contenuto specifico della pagina verrà incluso qui -->
        <jsp:include page="${pageContent}" />
    </main>

    <footer>
        <!-- Includi il piè di pagina comune qui -->
        <p>&copy; 2023 Il Tuo Sito Web</p>
    </footer>
</body>
</html>

```

Nel codice sopra, pageContent è una variabile che rappresenta il percorso della pagina specifica da includere. Questo valore verrà passato dinamicamente nelle pagine specifiche.

**2. Creazione delle pagine specifiche**

Ora, crea le tue pagine specifiche per Camere, clienti ed ordini. Ad esempio, Camere.jsp, clienti.jsp e ordini.jsp. Includi il contenuto specifico di ciascuna pagina all'interno di queste pagine.

```html

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Camere</title>
    <!-- Aggiungi qui i tuoi collegamenti CSS e script JavaScript specifici per questa pagina -->
</head>
<body>
    <section>
        <h2>Camere</h2>
        <!-- Contenuto specifico della pagina Camere -->
    </section>
</body>
</html>

```


**3. Inclusione del layout comune**

Nelle pagine specifiche, includi il layout comune (layout.jsp) utilizzando <jsp:include>. Passa il percorso della pagina specifica alla variabile pageContent nel layout.

html

```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="layout.jsp">
    <jsp:param name="pageContent" value="Camere.jsp" />
</jsp:include>

```

In questo esempio, value nella riga <jsp:param> indica quale pagina specifica verrà inclusa all'interno del layout.

4. Ripeti il passaggio 3 per le altre pagine (clienti.jsp e ordini.jsp).

In questo modo, avrai un'intestazione e un menu principale condivisi tra tutte le tue pagine e potrai facilmente aggiungere o modificare elementi comuni in un unico posto, ovvero nel file layout.jsp




## MODIFICHE E AGGIORNAMENTI DEL DATABASE
## PRODOTTO, CLIENTE E ACQUISTO
Come prima cosa abbiamo aggiornato le classi Camera, Cliente e Prenotazione con le variabili di istanza che ci sembrava necessario aggiungere o modificare.

- Nel file "Camera.java" abbiamo aggiunto:
```java
    private int id;
    private String tipologia;
    private String descrizione;
    private Double prezzo;
    private String immagine;
    private boolean disponibilita;
```
- Nel file "Cliente.java" abbiamo aggiunto:
```java
    private int id;
    private String nome;
    private String email;
    private String telefono;
    private String carta_id;

```
- Nel file "Prenotazione.java" abbiamo aggiunto:
```java
    private int id;
    private int idCliente;
    private int idProdotto;
    private Date checkIn;
    private Date checkOut;
    private double totale;

```
> Una volta dichiarate le nuove variabili le abbiamo aggiunte all'interno dei costruttori e abbiamo creato anche i loro getter e setter. Ad esempio per "Prenotazione" le modifiche sono state:
```java
    // Costruttore con parametri
     public Prenotazione(int id, int idCliente, int idProdotto, Date checkIn, Date checkOut, Double totale){
        this.id = id;
        this.idCliente = idCliente;
        this.idProdotto = idProdotto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totale = totale;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
```

## DATABASE
> A questo punto ci siamo dedicati alle modifiche vere e proprie sul database:
Abbiamo creato quindi un database con 3 tabelle e abbiamo inserito al suo interno dei dummy datas eseguendo i seguenti comandi con sqlite3:
 ```sql
        sqlite3 database.db
        
        CREATE TABLE IF NOT EXISTS clienti (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        carta_id TEXT NOT NULL,
        email TEXT NOT NULL,
        telefono TEXT NOT NULL,

        );

        CREATE TABLE IF NOT EXISTS camere (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        tipologia TEXT NOT NULL,
        descrizione TEXT NOT NULL,
        prezzo REAL NOT NULL,
        immagine TEXT,
        disponibilita BOOLEAN NOT NULL DEFAULT 0
        );

        CREATE TABLE IF NOT EXISTS prenotazioni (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        id_cliente INTEGER NOT NULL,
        id_camera INTEGER NOT NULL,
        check_in DATE NOT NULL,
        check_out DATE NOT NULL,
        totale REAL NOT NULL,
        FOREIGN KEY (id_camera) REFERENCES camere(id),
        FOREIGN KEY (id_cliente) REFERENCES clienti(id)
        );
    
        INSERT INTO clienti (nome, carta_id, email, telefono)VALUES
        ('Mario Rossi', 'AX9283F', 'mario@email.com', '555-1234'),
        ('Luigi Bianchi', 'GE63820F', 'luigi@email.com', '555-5678'),
        ('Giovanna Verdi', 'UH27846G', 'giovanna@email.com', '555-9876');

        INSERT INTO CAMERE (tipologia, descrizione, prezzo, immagine) VALUES
        ('Doppia', 'Descrizione doppia', 80.99, 'immagine1.jpg'),
        ('Suite', 'Descrizione suite', 190.99, 'immagine2.jpg'),
        ('Singola', 'Descrizione singola', 70.49, 'immagine3.jpg')

        INSERT INTO checkin (id_cliente, id_camera, check_in, check_out, totale)VALUES
        (1, 1, '2023-09-15', '2023-09-18', 300.0),
        (2, 3, '2023-09-20', '2023-09-23', 450.0),
        (3, 3, '2023-10-01', '2023-10-05', 1000.0);
```


## FILE 'DAO'
> Le ultime modifiche rimaste sono quelle apportate ai file 'DAO'. In tutte le classi abbiamo integrato all'interno dei metodi e delle query gi� esistenti le nuove variabili di "Camere", "Cliente" e "Prenotazione".
  ```java
    public void insertCamera(Camera p) { //metodo di inserimento che prende in input un Camera (p)
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO camere (tipologia, descrizione, prezzo, immagine, disponibilita) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, p.getTipologia()); //riporto i valori nella riga della tabella camere
            stmt.setString(2, p.getDescrizione());
            stmt.setDouble(3, p.getPrezzo());
            stmt.setString(4, p.getImmagine());
            stmt.setBoolean(5, p.getDisponibilita());
            stmt.executeUpdate();
        }catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }
  ```

>Inoltre nella classe **CameraDAO** abbiamo aggiunto 3 nuovi metodi:
  ```java
    public void cancellaCamera(int id){ //metodo per cancellare un Camera che prende in input l'id
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM camere WHERE id = ?")){
            stmt.setInt(1, id); // Cancella il Camera in base all'id
            stmt.executeUpdate();
        }catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }
     private static void camereDisponibili(Connection conn) throws SQLException {//metodo per visualizzare le camere disponibili
        String selezionaCamereDisponibili = "SELECT * FROM camere WHERE disponibilita = 0";
        
        try (PreparedStatement stmt = conn.prepareStatement(selezionaCamereDisponibili)) {
            ResultSet rs = stmt.executeQuery();
            while (resultSet.next()) {
                rs.getId();
                rs.getTipologia();
                rs.getDescrizione();
                rs.getPrezzo();
                rs.getImmagine();
                rs.getDisponibilita();
            }
        }
    }

    private static void updateDisponibilita(Connection conn, int idCamera, boolean occupata) throws SQLException {//metodo per modificare lo stato della camera
        String updateDisponibilitaCamera = "UPDATE camere SET disponibilita = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(updateDisponibilitaCamera)) {
            stmt.setBoolean(1, occupata);
            stmt.setInt(2, idCamera);
            stmt.executeUpdate();
        }
    }
  ```
>Nella classe **ClienteDAO** abbiamo aggiunto un nuovo metodo per inserire un cliente nel database:
```java
public void insertCliente(Cliente c) {//metodo per inserire un nuovo cliente nel database
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO clienti (nome, carta_id, email, telefono) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCarta_id());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getTelefono());
            stmt.executeUpdate();

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }
```
>Nella classe **PrenotazioneDAO** abbiamo aggiunto un metodo per calcolare il prezzo totale
```java
private static double calculatePrice(Connection conn, int roomId, int numberOfNights) throws SQLException {
        String selectRoomPriceQuery = "SELECT price FROM Rooms WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(selectRoomPriceQuery)) {
            stmt.setInt(1, roomId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                double roomPrice = resultSet.getDouble("price");
                return roomPrice * numberOfNights;
            }
        }
        return 0.0;
    }
```
