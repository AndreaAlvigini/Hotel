# GretaHotel
## Introduzione
GretaHotel è un'applicazione di database management pensata per la reception di un Hotel che permette una facile gestione dei dati trattando in particolare camere, clienti e prenotazioni.

Le tecnologie con cui è stata creata sono:

- Java
- SQLite: databse relazionale
- Maven: strumento di gestionedelle dipedenze e di build del progetto
- Java Server Page: framework per los viluppo di applicazioni web in java
- Bootstrap: framework css e script per lo styling
- Fontawesome: libreria icone per migliorare un interfaccia utente

Vediamo alcuni dei comandi inziziali per creare l'applicazione!

creazione archetipo webapp tramite maven

- mvn archetype:generate -D"groupId"=com.example -D"artifactId"=GestioneClienti -D"archetypeArtifactId"=maven-archetype-webapp -D"interactiveMode"=false
  
- clean

- mvn clean

install

- mvn install

fai partire jetty

- mvn jetty:run

Per andare alla pagina del sito

- localhost:8080/clienti


# Gestione clienti
- Creazione del Modello (Clienti.java): Questa classe rappresenterà un cliente nel tuo sistema.
- Creazione del DAO (ClientiDAO.java): Questa classe sarà responsabile per l'interazione con il database SQLite.
- Creazione del Servlet (ClientiServlet.java): Questo servlet gestirà le richieste HTTP e interagirà con il DAO per recuperare o salvare i dati nel database.
- Creazione della Pagina JSP: Questa pagina servirà come frontend per l'applicazione, permettendo agli utenti di interagire con il sistema

## Modello Cliente.java

```java
//Questa é una classe che rappresenta il modello di dati per Cliente e possiede solo i campi che corrispondono alle colonne nelle tabelle del database.
public class Cliente {
    // Specifico i campi che appartengono alla classe Cliente
    private int id;
    private String nome;
    // aggiunto cognome
    private String cognome;

    private String email;

    // aggiungo telefono
    private String telefono;
    // aggiunto carta_id
    private String carta_id;

    // Costruttore vuoto
    public Cliente() {
    }

    // Costruttore con parametri
    public Cliente(int id, String nome, String cognome, String email, String telefono, String carta_id) {
        this.id = id;
        this.nome = nome;
        //aggiunto cognome
        this.cognome = cognome;
        this.email = email;
        // aggiunto carta_id
        this.carta_id = carta_id;
        // aggiunto telefono
        this.telefono = telefono;

    }

    // Getter e Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    //aggiunto getter setter cognome
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // aggiunto telefono GETTER e SETTER
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // GETTER e SETTER carta_id
    public String getCarta_id() {
        return carta_id;
    }

    public void setCarta_id(String carta_id) {
        this.carta_id = carta_id;
    }

}
```

- In queto codice ho aggiunto i campi di tipo String:
    - cognome
    - carta_id
    - telefono
  
- Ho implementato i metodi **getter** per ogni campo, che restituiscono il valore del campo corrispondente.
- Ho implementato i metodi **setter** per ogni campo, che impostano il valore del campo corrispondente.
> Ora si possono utilizzare questi metodi getter e setter per accedere e modificare i campi dell'oggetto Clienti nell'applicazione


## Interazione con il database: ClientiDAO.java
Le classi `DAO` (Data Access Object) sono dedicate alle interazioni con il database SQL, conterranno dei metodi per la manipolazione dei dati specifici per ogni parte.
In questo caso vediamo le calsse DAO per la sezione clienti

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conn;// connessione

    public ClienteDAO(Connection conn) {// costruttore di classe
        this.conn = conn;
    }

    public List<Cliente> getAllClienti() { // metodo che resituisce una lista di oggetti Cliente
        List<Cliente> clienti = new ArrayList<>(); // creazione della lista

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM clienti")) {

            while (rs.next()) {
                Cliente c = new Cliente(); // creazione oggetto Cliente
                c.setId(rs.getInt("id")); // assegno i valori a CLiente in base alla riga del ResultSet
                c.setNome(rs.getString("nome"));
                c.setCognome(rs.getString("cognome"));
                c.setCarta_id(rs.getString("carta_id"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                clienti.add(c);// aggiungo alla lista Cliente valori completi
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return clienti;
    }
    //Metodo che controlla se il cliente esiste già nel database
    public boolean controllaSePresente(String carta_id) {
        boolean doesExists = false;

        try {
            String sql = "SELECT EXISTS (SELECT * FROM clienti WHERE carta_id = ?) AS doesExists";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, carta_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                doesExists = rs.getBoolean("doesExists");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doesExists;
    }

    //Metodo che permette la selezione di un cliente per id
    public Cliente getClienteById(int id) {
        Cliente c = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clienti WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCognome(rs.getString("cognome"));
                    c.setCarta_id(rs.getString("carta_id"));
                    c.setEmail(rs.getString("email"));
                    c.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
        return c;// riporto la lista con i dati CLiente
    }

    
    //Metodo per l'inserzione di un cliente nel database
    public void insertCliente(Cliente c) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO clienti (nome, cognome, carta_id, email, telefono) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCognome());
            stmt.setString(3, c.getCarta_id());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getTelefono());
            stmt.executeUpdate();

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }

    // Metodo per selezionare un cliente dall'id della carta
    public Cliente getClienteByCartaId(String carta_id) {
        Cliente c = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clienti WHERE id = ?")) {

            stmt.setString(1, carta_id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCarta_id(rs.getString("carta_id"));
                    c.setEmail(rs.getString("email"));
                    c.setTelefono(rs.getString("telefono"));
                }

            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return c;
    }

}
```
Per interagire con il database abbiamo inserito alcuni metodi utili alle varie funzionalità del database:

- **getClienteById** che permette la selezione di un cliente per id e viene utile per creare una scheda cliente singolo
- **insertCliente** l'inserzione di un cliente nel database
- **getClienteByCartaId** per selezionare un cliente dall'id della carta
- **controllaSePresente** metodo per verificare l'esistenza di un cliente nel database, restituisce un booleano "doesExists"

## Servlet ClienteServlet.java
Una servlet è una componente fondamentale nella creazione di applicazioni web in Java. Offre un modo per gestire le richieste e le risposte HTTP in modo dinamico, consentendo lo sviluppo di applicazioni web interattive e personalizzate. riportiamo l'esempio della nostra servlet per quanto riguarda Cliente
```java
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClienteServlet extends HttpServlet {
    private ClienteDAO clienteDAO;

    public void init() {
        try {
            // Stabilisce una connessione al database SQLite denominato "database.db"
            String url = "jdbc:sqlite:database.db";
            Connection conn = DriverManager.getConnection(url);
            
            // Inizializza l'istanza di ClienteDAO con la connessione al database
            clienteDAO = new ClienteDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisce eventuali eccezioni SQL stampando una traccia nello standard output
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Ottiene una lista di oggetti Cliente dal ClienteDAO
        List<Cliente> clienti = clienteDAO.getAllClienti();
        
        // Imposta l'attributo "clienti" nella richiesta, che sarà utilizzato dalla pagina JSP
        request.setAttribute("clienti", clienti);
        
        // Prepara un dispatcher per inoltrare la richiesta alla pagina JSP "clienti.jsp"
        RequestDispatcher dispatcher = request.getRequestDispatcher("clienti.jsp");
        
        // Inoltra la richiesta alla pagina JSP per la visualizzazione dei dati
        dispatcher.forward(request, response);
    }
}

```
Tramite questa servlet possiamo gestire le richieste e le risposte http mettendo in relazione la nostra pagina clienti.jsp con le funzionalità che abbiamo creato con i database.
In particolare qui utilizziamo il metoso *getAllClienti*
## Servlet DettaglioClientiServlet.java

Questa servlet invece serve per estrapolare i dati a frontend del singolo cliente per crearne una scheda in dettaglio, viene utilizzato infatti il metodo getClientebyId
```java
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/clienti/*")
public class DettaglioClienteServlet extends HttpServlet {
    private ClienteDAO clienteDAO;

    public void init() {
        try {
            // Stabilisce una connessione al database SQLite denominato "database.db"
            String url = "jdbc:sqlite:database.db";
            Connection conn = DriverManager.getConnection(url);

            // Inizializza l'istanza di ClienteDAO con la connessione al database
            clienteDAO = new ClienteDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisce eventuali eccezioni SQL stampando una traccia nello standard output
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Ottieni l'URI completo dalla richiesta HTTP,  L'URI rappresenta il percorso completo dell'URL richiesto dal client, compreso il contesto dell'applicazione (cioè il contesto Web dell'applicazione)
        String requestURI = request.getRequestURI();
        //divido in parti l'URI
        String[] parts = requestURI.split("/");

        if (parts.length > 0) {
            //A noi interessa estrapolare l'i del cliente dall'ultima parte dell'URI
            String parametro = parts[parts.length - 1];

            try {
                //assegno alla variabile idCliente e faccio un parse del parametro che contenente l'id
                int idCliente = Integer.parseInt(parametro);
                //Creo un oggetto cliente che restituisce i dati del cliente selezionato tramite l'id che abbiamo estrapolato
                Cliente cliente = clienteDAO.getClienteById(idCliente);
                // Imposta l'attributo "cliente" nella richiesta, che sarà utilizzato dalla pagina JSP
                request.setAttribute("cliente", cliente);

            } catch (NumberFormatException e) {
                response.sendRedirect("/");
            }

            // Prepara un dispatcher per inoltrare la richiesta alla pagina JSP
            // "cliente.jsp"
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente.jsp");
            // Inoltra la richiesta alla pagina JSP per la visualizzazione dei dati
            dispatcher.forward(request, response);
        }
    }
}
```
## Servlet AggiungiClienteServlet.java
Questa servlet esegue le operazioni in risposta a una richiesta *POST* inviata dall'utente, utilizziamo quindi un metodo doPost
```java
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/clienti/*")
public class AggiungiClienteServlet extends HttpServlet {
    private ClienteDAO clienteDAO;

    public void init() {
        try {
            // Stabilisce una connessione al database SQLite denominato "database.db"
            String url = "jdbc:sqlite:database.db";
            Connection conn = DriverManager.getConnection(url);

            // Inizializza l'istanza di ClienteDAO con la connessione al database
            clienteDAO = new ClienteDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisce eventuali eccezioni SQL stampando una traccia nello standard output
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Ottieni i parametri dal modulo JSP
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String numeroCartaIdentita = request.getParameter("carta_id");

        System.out.println(nome);
        System.out.println(cognome);
        System.out.println(numeroCartaIdentita);

        // Esegui la logica per verificare se il cliente esiste già
        boolean clienteEsiste = clienteDAO.controllaSePresente(numeroCartaIdentita);
        System.out.println(clienteEsiste);

        if (clienteEsiste) {
            // Se il cliente esiste già, indirizza l'utente a una pagina di errore
            
            response.sendRedirect("/erroreCliente.jsp");
            
            

        } else {
            // Se il cliente non esiste ancora, aggiungilo al sistema
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setCognome(cognome);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setCarta_id(numeroCartaIdentita);

            clienteDAO.insertCliente(cliente);

            // Dopo l'aggiunta del cliente, reindirizza l'utente alla pagina principale dei clienti
            response.sendRedirect("/clienti");
            
        }
    }
}
```
Questa servlet gestisce l'aggiunta di nuovi clienti al sistema e verifica duplicati basati sul numero di carta d'identità. Se un cliente con lo stesso numero esiste già, reindirizza l'utente a una pagina di errore. Altrimenti, crea un nuovo cliente e lo inserisce nel database, quindi reindirizza l'utente alla pagina principale dei clienti.

## Routing: web.xml
Le servlet sono mappate nel file `web.xml` e riportano le annotazioni *@WebServlet* per il giusto indirizzamento:

```xml
<!-- Il file web.xml serve per il routing, ovvero per lo smistamento delle richieste alle varie pagine, xml riguarda l'informazione e non la formattazione-->

<web-app>

  <display-name>Archetype Created Web Application</display-name>

  <!-- Pagina clienti -->
  <servlet>
    <servlet-name>ClienteServlet</servlet-name>
    <servlet-class>ClienteServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>ClienteServlet</servlet-name>
    <url-pattern>/clienti</url-pattern>

  </servlet-mapping>
    <!--aggiunto DettaglioClienteServlet-->
  <servlet>
    <servlet-name>DettaglioClienteServlet</servlet-name>
    <servlet-class>DettaglioClienteServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>DettaglioClienteServlet</servlet-name>
    <url-pattern>/clienti/*</url-pattern>
    
</web-app>
```
