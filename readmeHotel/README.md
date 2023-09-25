# GretaHotel
## Introduzione
GretaHotel è un'applicazione di database management pensata per la reception di un Hotel che permette una facile gestione dei dati trattando in particolare camere, clienti e prenotazioni.

Le tecnologie con cui è stata creata sono:

- Java
- SQLite: databse relazionale
- Maven: strumento di gestionedelle dipedenze e di build del progetto
- Java Server Page: framework per lo sviluppo di applicazioni web in java
- Bootstrap: framework css e script per lo styling
- Fontawesome: libreria icone per migliorare un interfaccia utente

Vediamo alcuni dei comandi inziziali per creare l'applicazione!

creazione archetipo webapp tramite maven

- mvn archetype:generate -D"groupId"=com.example -D"artifactId"=full-webapp -D"archetypeArtifactId"=maven-archetype-webapp -D"interactiveMode"=false

- clean

- mvn clean

install

- mvn install

fai partire jetty

- mvn jetty:run

Per andare alla pagina del sito

- localhost:8080/

#### La suddivisione in cartelle comparirà così:

full-webapp/
- src/
- - main/
- - - java/
- - - - Cliente.java
- - - - ClienteDAO.java
- - - - ClienteServlet.java
- - - - AggiungiClienteServlet.java
- - - - DettaglioClienteServlet.java
- - - - Camera.java
- - - - CameraDAO.java
- - - - CameraServlet.java
- - - - Prenotazione.java
- - - - PrenotazioneDAO.java
- - - - PrenotazioneServlet.java
- - - - PrenotazioneSingolaServlet.java
- resources/
- - crea_database.sql
- webapp/
- - clienti.jsp
- - cliente.jsp
- - erroreCliente.jsp
- - aggiungiCliente.jsp
- - camera.jsp
- - camere.jsp
- - prenotazione.jsp
- - prenotazioni.jsp
- - index.jsp
- - - assets
- - - - foto_camere
- - - WEB-INF/
- - - - web.xml
- - - css
- - - - style.css

# Dipendenze Maven
Il file pom.xml è un documento di configurazione in un progetto Maven che serve a definire le dipendenze del progetto e configurare le sue impostazioni fondamentali;, controlla quali librerie il progetto richiede e come deve essere costruito ed eseguito.

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>full-webapp</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>full-webapp Maven Webapp</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>

    <!-- Dipendenza per SQLite -->
    <dependency>
      <groupId>org.xerial</groupId>
      <artifactId>sqlite-jdbc</artifactId>
      <version>3.42.0.0</version>
    </dependency>

    <!-- Dipendenze per Servlet page -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>

    <!-- Dipendenza per JSP -->
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>javax.servlet.jsp-api</artifactId>
      <version>2.3.3</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>
  <build>
    <finalName>full-webapp</finalName>
    <plugins>
      <!-- Altri plugin ... -->

      <plugin>
          <groupId>org.eclipse.jetty</groupId>
          <artifactId>jetty-maven-plugin</artifactId>
          <version>9.4.35.v20201120</version>
      </plugin>

      <!-- Altri plugin ... -->
  </plugins>
  </build>
</project>
```
# Creazione database SQLite
Abbiamo creato un database relazionale tramite SQLite per la gestione dei dati, presenta tre tabelle, una per ogni tipologia di dato.
il database è stato creato con uno script .sql che presentiamo di seguito:

```sql
        CREATE TABLE IF NOT EXISTS clienti (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        cognome TEXT NOT NULL,
        carta_id TEXT NOT NULL,
        email TEXT NOT NULL,
        telefono TEXT NOT NULL

        );

        CREATE TABLE IF NOT EXISTS camere (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        tipologia TEXT NOT NULL,
        descrizione TEXT NOT NULL,
        prezzo REAL NOT NULL,
        immagine TEXT,
        bagno BOOLEAN DEFAULT TRUE,
        condizionatore BOOLEAN DEFAULT TRUE,
        disponibilita BOOLEAN DEFAULT TRUE
        );

        CREATE TABLE IF NOT EXISTS prenotazioni (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        id_cliente INTEGER NOT NULL,
        id_camera INTEGER NOT NULL,
        check_in DATE NOT NULL,
        check_out DATE NOT NULL,
        notti INTEGER NOT NULL,
        totale REAL NOT NULL,
        FOREIGN KEY (id_camera) REFERENCES camere(id),
        FOREIGN KEY (id_cliente) REFERENCES clienti(id)
        );

        INSERT INTO clienti (nome, cognome, carta_id, email, telefono)VALUES
        ('Mario', 'Rossi', 'AX9283F', 'mario@email.com', '555-1234'),
        ('Luigi', 'Bianchi', 'GE63820F', 'luigi@email.com', '555-5678'),
        ('Giovanna', 'Verdi', 'UH27846G', 'giovanna@email.com', '555-9876');

        INSERT INTO camere (tipologia, descrizione, prezzo, immagine, bagno, condizionatore, disponibilita) VALUES
        ('Doppia', 'Descrizione doppia', 80.99, 'MainArticle.jpg', TRUE, FALSE, TRUE ),
        ('Suite', 'Descrizione suite', 190.99, 'MainArticle.jpg', TRUE, TRUE, FALSE ),
        ('Singola', 'Descrizione singola', 70.49, 'MainArticle.jpg', FALSE, FALSE, FALSE );

        INSERT INTO prenotazioni (id_cliente, id_camera, check_in, check_out, notti, totale)VALUES
        (1, 2, '2023-09-08 10:30:45.123', '2023-09-12 10:30:45.123', 4, 763.96),
        (3, 1, '2023-09-28 10:30:45.123', '2023-09-30 10:30:45.123', 2, 161.98),
        (2, 3, '2023-12-07 10:30:45.123', '2023-12-11 10:30:45.123', 4, 281.96);
```
durante questo processo, oltre a creare il database, abbiamo inserito i dati all'interno delle tabelle, quindi abbiamo usato i comandi *CREATE TABLE IF NOT EXISTS* e *INSERT INTO* <i>"nome della tabella"<i>.
# Gestione clienti
- Creazione del Modello (Clienti.java): Questa classe rappresenterà un cliente nel tuo sistema.
- Creazione del DAO (ClientiDAO.java): Questa classe sarà responsabile per l'interazione con il database SQLite.
- Creazione del Servlet (ClientiServlet.java): Questo servlet gestirà le richieste HTTP e interagirà con il DAO per recuperare o salvare i dati nel database.
- Creazione della Pagina JSP: Questa pagina servirà come frontend per l'applicazione, permettendo agli utenti di interagire con il sistema

## Modello Cliente.java
Questa é una classe che rappresenta il modello di dati per Cliente e possiede solo i campi che corrispondono alle colonne nelle tabelle del database.
```java
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

  <servlet>
    <servlet-name>DettaglioClienteServlet</servlet-name>
    <servlet-class>DettaglioClienteServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>DettaglioClienteServlet</servlet-name>
    <url-pattern>/clienti/*</url-pattern>
    
  </servlet-mapping>
    
</web-app>
```

## JavaServer Pages: clienti.jsp
Una JavaServer Page (JSP) è una pagina web dinamica che permette l'integrazione di codice Java all'interno di markup HTML.

Clienti.jsp corrisponde alla pagina dove verranno visualizzati tutti i clienti nel database.

Tramite dei tag JSLT possiamo semplicemente accedere ai dati dei nostri oggetti java ed utilizzare dei controlli di flusso o operazioni di iterazione. In questo caso iteriamo i dati di ogni cliente con la taglib:

```jsp
<c:forEach var="cliente" items="${clienti}">
```
di seguito  **clienti.jsp**
```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Clienti</title>
            <!-- Link al file CSS di Bootstrap -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <!-- Link al file CSS personalizzato -->
            <link rel="stylesheet" href="./css/style.css">
            <!-- Link al file CSS di FontAwesome -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        </head>

        <body>
            <!-- Intestazione -->
            <jsp:include page="header.jsp" />
            <main class="container">
                <!-- Contenuto principale -->
                <div class="container mt-4">
                    <div class="d-flex justify-content-between align-items-center">
                        <h1 class="mb-0">
                            Lista Clienti
                        </h1>
                        <!-- <i class="fas fa-users fa-2x" ></i> -->
                        <a href="aggiungiCliente.jsp" class="btn btn-primary">
                            Aggiungi Cliente <i class="fas fa-user-plus ml-2"></i>
                        </a>
                    </div>
                    <hr>
                    <div class="row">
                        <c:forEach var="cliente" items="${clienti}">
                            <div class="col-md-4">
                                <div class="card mb-4">
                                    <div class="card-body">
                                        <h5 class="card-title">${cliente.nome}</h5>
                                        <h5 class="card-subtitle mb-2">${cliente.cognome}</h5>
                                        <a href="/clienti/${cliente.id}" class="btn btn-sm btn-outline-primary mt-2">
                                            Dettagli <i class="fas fa-chevron-circle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                
            </main>
            <!-- Footer -->
            <jsp:include page="footer.jsp" />
            <!-- Script di Bootstrap -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>

        </html>
```
### cliente.jsp
Pagina per la visualizzazione  della lista dei clienti.
```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Clienti</title>
            <!-- Link al file CSS di Bootstrap -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <!-- Link al file CSS personalizzato -->
            <link rel="stylesheet" href="./css/style.css">
            <!-- Link al file CSS di FontAwesome -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        </head>

        <body>
            <!-- Intestazione -->
            <jsp:include page="header.jsp" />
            <main class="container">
                <!-- Contenuto principale -->
                <div class="container mt-4">
                    <div class="d-flex justify-content-between align-items-center">
                        <h1 class="mb-0">
                            Lista Clienti
                        </h1>
                        <!-- <i class="fas fa-users fa-2x" ></i> -->
                        <a href="aggiungiCliente.jsp" class="btn btn-primary">
                            Aggiungi Cliente <i class="fas fa-user-plus ml-2"></i>
                        </a>
                    </div>
                    <hr>
                    <div class="row">
                        <c:forEach var="cliente" items="${clienti}">
                            <div class="col-md-4">
                                <div class="card mb-4">
                                    <div class="card-body">
                                        <h5 class="card-title">${cliente.nome}</h5>
                                        <h5 class="card-subtitle mb-2">${cliente.cognome}</h5>
                                        <a href="/clienti/${cliente.id}" class="btn btn-sm btn-outline-primary mt-2">
                                            Dettagli <i class="fas fa-chevron-circle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                
            </main>
            <!-- Footer -->
            <jsp:include page="footer.jsp" />
            <!-- Script di Bootstrap -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>

        </html>
```
### aggiungiCliente.jsp
Pagina per l'inserzione di un cliente, si tratta semplicemente di un form con i campi dei dati cliente e si viene reindirizzati alla lista clienti dopo l'inserzione
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Aggiungi Cliente</title>
            <!-- Link al file CSS di Bootstrap -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <!-- Link al file CSS personalizzato -->
            <link rel="stylesheet" href="./css/style.css">
            <!-- Link a fontawesome  -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
                integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
                crossorigin="anonymous" referrerpolicy="no-referrer" />
        </head>

        <body>
            <jsp:include page="header.jsp" />
            <main class="container">
                <!-- Includi l'header se necessario -->

                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <h1 class="text-left mb-4">Aggiungi Cliente</h1>
                        </div>
                    </div>
                </div>

                <!-- Form per l'aggiunta del cliente -->
                <div class="container">
                    <div class="row">
                        <div class="col-6">
                            <form action="aggiungiCliente" method="post">
                                <div class="mb-3">
                                    <label for="nome" class="form-label">Nome:</label>
                                    <input type="text" class="form-control" id="nome" name="nome" required>
                                </div>
                                <div class="mb-3">
                                    <label for="cognome" class="form-label">Cognome:</label>
                                    <input type="text" class="form-control" id="cognome" name="cognome" required>
                                </div>
                                <div class="mb-3">
                                    <label for="carta_id" class="form-label">Numero
                                        Carta d'Identità:</label>
                                    <input type="text" class="form-control" id="carta_id" name="carta_id" required>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email:</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
                                <div class="mb-3">
                                    <label for="telefono" class="form-label">Telefono:</label>
                                    <input type="tel" class="form-control" id="telefono" name="telefono">
                                </div>
                                <div class="mb-3">
                                    <button type="submit" class="btn btn-primary">
                                        Aggiungi Cliente <i class="fas fa-user-plus ml-2"></i>
                                    </button>
                            </form>
                        </div>


                    </div>


            </main>

            <!-- Footer -->
            <jsp:include page="footer.jsp" />
            <!-- Script di Bootstrap -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>

        </html>
```
### erroreCliente.jsp
Pagina jsp per segnalare errore di inserzione cliente, quando una carta d'identità corrisponde già a un cliente si viene reindirizzati proprio a questa pagina, si ha poi la possibilità di ritornare alla lista dei clienti.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!DOCTYPE html>
        <html lang="it">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Aggiungi Cliente</title>
            <!-- Link to Bootstrap CSS -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <!-- Link to custom CSS -->
            <link rel="stylesheet" href="./css/style.css">
            <!-- Link to FontAwesome -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
                integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
                crossorigin="anonymous" referrerpolicy="no-referrer" />
        </head>

        <body>
            <!-- visualizzazione dei dati del cliente -->
            <jsp:include page="header.jsp" />
            <main class="container">
                <div class="card mt-4">
                    <div class="card-header">
                        <h1 class="text-left mb-4 fw-bold">
                            <i class="fa-solid fa-id-card"></i> Il cliente esiste già!
                        </h1>
                    </div>
                    <div class="card-body">
                        <h5>La carta d'identità che hai provato ad inserire è già associata ad un altro cliente,
                            controlla meglio</h5>
                        <a href="/clienti" class="btn btn-outline-primary mt-2">
                            Torna alla lista clienti <i class="fas fa-chevron-circle-right"></i>
                        </a>
                    </div>
                </div>
            </main>
            <!-- Inclusione il footer -->
            <jsp:include page="footer.jsp" />
            <!-- Script for Bootstrap -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>
        </html>
```
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

```java
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

```java
String sql = "SELECT prenotazioni.*, " +
    "clienti.nome AS clienteNome, clienti.cognome AS clienteCognome, clienti.carta_id AS clienteDocumento, clienti.email AS clienteEmail, clienti.telefono AS clienteTelefono, " +
    "camere.id AS cameraNumero, camere.tipologia AS cameraTipologia " +
    "FROM prenotazioni " +
    "JOIN clienti ON prenotazioni.id_cliente = clienti.id " +
    "JOIN camere ON prenotazioni.id_camera = camere.id";
```
Questa è la query di richiesta al database dei dati presenti nella tabella *prenotazioni* comune ai tre metodi principali.

```java
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
```java
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
```java
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
```java
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
```java
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
```java
sql += " WHERE prenotazioni.id = " + id + ";";
```

### Servlet
#### PrenotazioneServlet
Come impostato nel file **web.xml** questa servlet entra in gioco quando un utente fa una richiesta di **GET** alla pagina, ovvero la carica e visualizza.
```xml
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
```java
public class PrenotazioneServlet extends HttpServlet {
    
// codice

}
```

Successivamente gestisce le operazioni da fare quando viene fatta la richiesta di **GET** alla pagina */prenotazioni*.
```java
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
```java
if (tipologiaCamera != null && !tipologiaCamera.isEmpty() || checkInDateOrder != null && !checkInDateOrder.isEmpty()) {

    // codice

}
```

I possibili parametri per il filtraggio arrivano dalla pagina stessa all'interno della richiesta di **GET** e vengono filtrati e salvati qui:
```java
String tipologiaCamera = request.getParameter("tipologia-camera");
String checkInDateOrder = request.getParameter("ordina-data-check-in");
```

Dopo aver creato la lista delle prenotazioni la servlet si occupa di inviare questo parametro alla pagina tramite:
```java
request.setAttribute("prenotazioni", prenotazioni);
```
ed infine invio al broswer dell'utente la pagina:
```java
RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni.jsp");
dispatcher.forward(request, response);
```

#### PrenotazioneSingolaServlet
Sfruttando le stesse premesse relative all'utilizzo della libreria **javax.servlet** per gestire le richieste HTTP, si occupa di quelle inviate all'indirizzo */prenotazioni/id*, dove **id** viene sostituito dall'id della prenotazione salvata nel database.
```java
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

```java
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

# Gestione camere
Per una migliore gestione e modularità del codice si è divisa la logica relativa al modello, alla gestione ed alla visualizzazione dei dati relativi alle camere in file distinti.

- Creazione del **Modello** (Camera.java): Questa classe rappresenterà un camera nel sistema.
- Creazione del **DAO** (CameraDAO.java): Questa classe sarà responsabile per l'interazione con il database SQLite.
- Creazione del **Servlet** (CameraServlet.java): Questo servlet gestirà le richieste HTTP e interagirà con il DAO per recuperare o salvare i dati nel database.
- Creazione della Pagina **JSP**: Questa pagina servirà come frontend per l'applicazione, permettendo agli utenti di interagire con il sistema

## Modello Carmera.java

Questa é una classe che rappresenta il modello di dati per le Camere del nostro Hotel e possiede solo i campi che corrispondono alle colonne nelle tabelle del database.

```java

public class Camera{
    //Specifico i campi che appartengono alla classe Prodotto
    private int id;
    //aggiunto tipologia
    private String tipologia;
    private String descrizione;
    private boolean bagno;
    private boolean condizionatore;
    private Double prezzo;
    //aggiunta immagine
    private String immagine;
    

    // Costruttore vuoto
    public Camera(){
    }
    // Costruttore vuoto
    public Camera(int id, String tipologia, String descrizione, boolean bagno, boolean condizionatore, Double prezzo, String immagine){
        this.id = id;
        //aggiungiamo tipologia
        this.tipologia = tipologia;
        this.descrizione = descrizione;
        this.bagno = bagno;
        this.condizionatore = condizionatore;
        this.prezzo = prezzo;
        this.immagine = immagine;
    }
    // Getter e Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //aggiunto tipologia
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
    
    //aggiunto bagno
    public boolean getBagno (){
        return bagno;
    }
    public void setBagno(boolean bagno){
        this.bagno = bagno;
    }

    //aggiunto condizionatore
    public boolean getCondizionatore (){
        return condizionatore;
    }
    public void setCondizionatore(boolean condizionatore){
        this.condizionatore = condizionatore;
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
}
```

- In queto codice ho aggiunto i campi di tipo String:
    - Tipologia
    - Descrizione
    - Immagine

- In queto codice ho aggiunto i campi di tipo Int:
    - Id


- In queto codice ho aggiunto i campi di tipo Boolean:
    - Bagno
    - Condizionatore

- In queto codice ho aggiunto i campi di tipo Double:
    - Prezzo

- Ho implementato i metodi **getter** per ogni campo, che restituiscono il valore del campo corrispondente:
- Ho implementato i metodi **setter** per ogni campo, che impostano il valore del campo corrispondente.
> Ora si possono utilizzare questi metodi per accedere e modificare i campi dell'oggetto Camera nell'applicazione

## Interazione con il database: CameraDAO.java

```java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CameraDAO {
    private Connection conn; // connessione

    public CameraDAO(Connection conn) { // costruttore di classe
        this.conn = conn;
    }

    public List<Camera> getAllCamere() { // metodo che restituisce una lista di oggetti Camera
        List<Camera> camere = new ArrayList<>(); // creazione della lista

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM camere")) {

            while (rs.next()) {
                Camera c = new Camera(); // creazione oggetto Camera
                // utilizzo il metodo di Camera p per assegnare il valore all'id del Camera,
                // leggendolo dal valore della colonna della riga corrente del ResultSet
                c.setId(rs.getInt("id"));
                c.setTipologia(rs.getString("tipologia"));
                c.setDescrizione(rs.getString("descrizione"));
                c.setBagno(rs.getBoolean("bagno"));
                c.setCondizionatore(rs.getBoolean("condizionatore"));
                c.setPrezzo(rs.getDouble("prezzo"));
                c.setImmagine(rs.getString("immagine"));
                camere.add(c);
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return camere; // riporta la lista di Camere
    }

    public Camera getCameraById(int id) { // metodo di selezione Camera in base all'id (in input) che restituisce un
                                          // oggetto Camera
        Camera c = null; // creazione oggetto Camera vuoto

        try (PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM camere WHERE id = ?")) { // selezione dalla
                                                                                                    // tabella prodotti
                                                                                                    // in base all'id

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // assegnazione dei valori all'oggetto Camera in base alla riga dell'id fornito
                    c = new Camera();
                    c.setId(rs.getInt("id"));
                    c.setTipologia(rs.getString("tipologia"));
                    c.setDescrizione(rs.getString("descrizione"));
                    c.setBagno(rs.getBoolean("bagno"));
                    c.setCondizionatore(rs.getBoolean("condizionatore"));
                    c.setPrezzo(rs.getDouble("prezzo"));
                    c.setImmagine(rs.getString("immagine"));
                }
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return c; // riporta il Camera completo
    }
}

```

Per interagire con il database abbiamo inserito alcuni metodi utili alle varie funzionalità:
- **getCameraById** che permette la selezione di una camera per id e viene utile per creare una scheda camera singola
- **getAllCamere**  che prende tutte le camere e restituisce una lista

## Servlet CameraServlet.java
Una servlet è una componente fondamentale nella creazione di applicazioni web in Java. Offre un modo per gestire le richieste e le risposte HTTP in modo dinamico, consentendo lo sviluppo di applicazioni web interattive e personalizzate. riportiamo l'esempio della nostra servlet per quanto riguarda Camera

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cameraId = request.getParameter("id");

        if (cameraId != null) {
            try {
                int id = Integer.parseInt(cameraId);
                Camera camera = CameraDAO.getCameraById(id);

                if (camera != null) {
                    request.setAttribute("camera", camera);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("camera.jsp");
                    dispatcher.forward(request, response);
                    return; // Termina la richiesta dopo il forward
                }
            } catch (NumberFormatException e) {
                // Gestisci il caso in cui l'ID della camera non sia un numero valido
                // Ad esempio, reindirizza a una pagina di errore o gestisci in modo appropriato.
                e.printStackTrace();
            }
        }

        // Se l'ID della camera non è valido o non è stato specificato, mostra tutte le camere
        List<Camera> camere = CameraDAO.getAllCamere();
        request.setAttribute("camere", camere);
        RequestDispatcher dispatcher = request.getRequestDispatcher("camere.jsp");
        dispatcher.forward(request, response);
    }
}
```

Questa servlet stabilsce una connessione con il database SQLite denominato database.db. Inizializza l'istanza di CameraDAO con la connessione al database. Ottiene una lista di oggetti Camera dal CameraDAO. Imposta l'attributo "camera" nella richiesta, che sarà utilizzato dalla pagina JSP. Prepara un dispatcher per inoltrare la richiesta alla pagina JSP "camere.jsp". Inoltra la richiesta alla pagina JSP per la visualizzazione dei dati.
## Servlet DettaglioCameraServlet.java
```java
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/camere/*")
public class DettaglioCameraServlet extends HttpServlet {

    private CameraDAO cameraDAO;

    public void init() {
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database.", e);
        }

        cameraDAO = new CameraDAO(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    String[] parts = requestURI.split("/");

    if (parts.length > 0) {
        String parametro = parts[parts.length - 1];

        try {
            int idCamera = Integer.parseInt(parametro);
            Camera camera = cameraDAO.getCameraById(idCamera);

            if (camera == null) {
                response.sendRedirect("/");
                return;
            }

            // Passa i valori dei checkbox come attributi alla JSP
            request.setAttribute("camera", camera);

        } catch (NumberFormatException e) {
            response.sendRedirect("/");
            return;
        }
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("/camera.jsp");
    dispatcher.forward(request, response);
    }
}
```
## Routing: web.xml
Questa servlet è stata mappata assieme alle altre nel file web.xml e riporta l'annotazione @WebServlet("/camere/*"):

```xml
  <!-- Pagina camere -->
  <servlet>
    <servlet-name>CameraServlet</servlet-name>
    <servlet-class>CameraServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>CameraServlet</servlet-name>
    <url-pattern>/camere</url-pattern>
  </servlet-mapping>

  <!-- Pagina dettaglio-->
  <servlet>
    <servlet-name>DettaglioCameraServlet</servlet-name>
    <servlet-class>DettaglioCameraServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>DettaglioCameraServlet</servlet-name>
    <url-pattern>/camere/*</url-pattern>
  </servlet-mapping>


```

## JavaServer Page: Camere.jsp
Camere.jsp permette la visualizzazione di tutte le camere presenti nel database.

```jsp
<!-- dichiarazione indispensabile per poter utilizzare i tag JSTL JavaServer Pages Standard Tag Library all'interno del file JSP -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <link rel="stylesheet" href="./css/style.css">
        </head>

        <body>
            <main class="container">
                
                <jsp:include page="header.jsp" />
                <h1 style="text-align: center;">Tutte le camere</h1>

                <div class="grid-container">
                    <c:forEach var="camera" items="${camere}">
                        
                            <div class="card grid-item">
                                <img src="./assets/foto_camere/${camera.immagine}"
                                    alt="foto della camera numero ${camera.id}" class="card-img-top">
                                <div class="card-body">
                                    <h4 class="card-title">${camera.id} - ${camera.tipologia}</h4>
                                    <p class="card-text">${camera.prezzo} € per notte</p>
                                    <a href="/camere/${camera.id}" class="btn btn-primary">Vai alla scheda</a>
                                </div>
                            </div>
                        
                    </c:forEach>
                </div>

                <jsp:include page="footer.jsp" />
            </main>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>

        </html>
```


## Camera.jsp
Camera.jps restituisce i dati della camera selezionata.

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Camera #${camera.id}</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <link rel="stylesheet" href="./css/style.css">
        </head>

<body>

    <main class="container">
                
        <jsp:include page="header.jsp" />
        <h1 style="text-align: center;">Dettagli della camera #${camera.id}</h1>

        <div class="grid-container">
            <p>ID: ${camera.id}</p>
            <p>Tipologia: ${camera.tipologia}</p>
            <p>Descrizione: ${camera.descrizione}</p>
            <!-- <p>Bagno:
                <input type="checkbox" name="bagno" value="true" ${bagnoSelected ? 'checked' : ''} disabled>
            </p>
            <p>Condizionatore:
                <input type="checkbox" name="condizionatore" value="true" ${condizionatoreSelected ? 'checked' : ''} disabled>
            </p> -->
            <!-- <p>Prezzo: ${camera.prezzo} euro per notte</p> -->
            <img src="{pageContext.request.contextPath}/assets/foto_camere/${camera.immagine}" alt="foto della camera" class="card-img-top float-start">
            
        </div>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Bagno</th>
                    <th scope="col">Condizionatore</th>
                    <th scope="col">Prezzo</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${camera.bagno}">
                                <span>&#10003;</span> <!-- Segno di spunta -->
                            </c:when>
                            <c:otherwise>
                                <span>&#10007;</span> <!-- Croce -->
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${camera.condizionatore}">
                                <span>&#10003;</span> <!-- Segno di spunta -->
                            </c:when>
                            <c:otherwise>
                                <span>&#10007;</span> <!-- Croce -->
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${camera.prezzo}/notte</td>
                </tr>
            </tbody>
        </table>
    </main>
</body>
</html>
```
