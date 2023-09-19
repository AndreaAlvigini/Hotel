creazione archetipo webapp

- mvn archetype:generate -D"groupId"=com.example -D"artifactId"=GestioneClienti -D"archetypeArtifactId"=maven-archetype-webapp -D"interactiveMode"=false

compila

- mvn compile

clean

- mvn clean

install

- mvn install

fai partire jetty

- mvn jetty:run

vai alla pagina del sito

- localhost:8080/clienti

```
GestioneClienti/
---src/
-------main/
-----------java/
----------------com/
-------------------example/
-------------------Clienti.java
-------------------ClientiDAO.java
-------------------ClientiServlet.java
---resources/
---crea_database.sql
---webapp/
---------WEB-INF/
---------web.xml
---clienti.jsp
pom.xml

Spiegazione della struttura:

src/main/java/com/example/: Questa è la directory dove risiederanno le tue classi Java. Ho incluso le classi Clienti, ClientiDAO, e ClientiServlet qui.

src/main/resources/: Questa directory contiene risorse come lo script SQL (crea_database.sql) che può essere utilizzato per creare il database.

src/main/webapp/: Questa è la directory principale per i file web dell'applicazione. Ho incluso il file clienti.jsp qui, che è la tua pagina JSP frontend.

src/main/webapp/WEB-INF/: Questa directory contiene il file web.xml, che è utilizzato per configurare le impostazioni del servlet e altre impostazioni web.

pom.xml: Questo è il file di configurazione Maven, che contiene le dipendenze e altre configurazioni per il tuo progetto

```

# Gestione clienti

- Creazione del Modello (Clienti.java): Questa classe rappresenterà un cliente nel tuo sistema.
- Creazione del DAO (ClientiDAO.java): Questa classe sarà responsabile per l'interazione con il database SQLite.
- Creazione del Servlet (ClientiServlet.java): Questo servlet gestirà le richieste HTTP e interagirà con il DAO per recuperare o salvare i dati nel database.
- Creazione della Pagina JSP: Questa pagina servirà come frontend per l'applicazione, permettendo agli utenti di interagire con il sistema

1. Clienti.java
```java

public class Clienti {
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private boolean isAdmin;

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

```

Nel codice sopra:

- Ho aggiunto un campo isAdmin di tipo boolean per tenere traccia se un cliente è un amministratore o meno.
- Ho implementato i metodi getter per ogni campo, che restituiscono il valore del campo corrispondente.
- Ho implementato i metodi setter per ogni campo, che impostano il valore del campo corrispondente.
- Ora puoi utilizzare questi metodi getter e setter per accedere e modificare i campi dell'oggetto Clienti nella tua applicazione

2. ClientiDAO.java
```java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientiDAO {
    private Connection connect() {
        // Connessione al database SQLite
        String url = "jdbc:sqlite:clienti.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<Clienti> getAllClienti() {
        String sql = "SELECT id, nome, cognome, email FROM clienti";
        List<Clienti> clientiList = new ArrayList<>();
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Clienti clienti = new Clienti();
                clienti.setId(rs.getInt("id"));
                clienti.setNome(rs.getString("nome"));
                clienti.setCognome(rs.getString("cognome"));
                clienti.setEmail(rs.getString("email"));
                clientiList.add(clienti);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clientiList;
    }

    // Altri metodi per inserire, aggiornare e cancellare clienti
    // ...
}

```

3. ClientiServlet.java
```java

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/clienti")
public class ClientiServlet extends HttpServlet {
    private ClientiDAO clientiDAO = new ClientiDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Clienti> clientiList = clientiDAO.getAllClienti();
        request.setAttribute("clientiList", clientiList);
        try {
            request.getRequestDispatcher("clienti.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodi doPost, doPut, doDelete per gestire altre operazioni CRUD
    // ...
}

```

4. Pagina JSP (clienti.jsp)
```jsp

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Clienti</title>
</head>
<body>
    <h1>Lista Clienti</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
        </tr>
        <c:forEach var="clienti" items="${clientiList}">
            <tr>
                <td>${clienti.id}</td>
                <td>${clienti.nome}</td>
                <td>${clienti.cognome}</td>
                <td>${clienti.email}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

```

5. File pom.xml
Il file pom.xml è utilizzato in progetti Maven per definire le dipendenze e altre configurazioni. Ecco un esempio di un pom.xml che include le dipendenze per SQLite e la Servlet API:

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>GestioneClienti</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <dependencies>
        <!-- SQLite JDBC Driver -->
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.43.0</version>
        </dependency>
        
        <!-- Servlet API -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>
        
        <!-- JSTL -->
        <dependency>
            <groupId>javax.servlet.jsp.jstl</groupId>
            <artifactId>javax.servlet.jsp.jstl-api</artifactId>
            <version>1.2.1</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.3.1</version>
            </plugin>
        </plugins>
    </build>
</project>

```

6. Script SQLite per Creare il Database
Per creare il database e una tabella clienti con SQLite, puoi creare uno script SQL come il seguente:

```sql

CREATE TABLE IF NOT EXISTS clienti (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cognome TEXT NOT NULL,
    email TEXT
);

-- Inserisci alcuni dati di esempio
INSERT INTO clienti (nome, cognome, email)
VALUES 
('Mario', 'Rossi', 'mario.rossi@example.com'),
('Luca', 'Bianchi', 'luca.bianchi@example.com');

```

Puoi salvare questo script in un file, ad esempio crea_database.sql, e poi eseguirlo con il comando sqlite3 da terminale dalla cartella resources:

```sh
cd resources
sqlite3 clienti.db < crea_database.sql

```

Questo comando creerà un nuovo database chiamato clienti.db e eseguirà lo script SQL per creare la tabella e inserire alcuni dati di esempio

7. Esempio di web.xml
Il file web.xml potrebbe avere una configurazione come la seguente per mappare il tuo servlet:

```xml

<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <servlet>
        <servlet-name>ClientiServlet</servlet-name>
        <servlet-class>com.example.ClientiServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>ClientiServlet</servlet-name>
        <url-pattern>/clienti</url-pattern>
    </servlet-mapping>

</web-app>

```

8. inserimento, la modifica e la rimozione dei clienti

clienti.jsp
```jsp

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Clienti</title>
</head>
<body>
    <h1>Gestione Clienti</h1>
    
    <h2>Inserisci/Modifica Cliente</h2>
    <form action="clienti" method="post">
        <input type="hidden" name="id" value="${cliente.id}">
        Nome: <input type="text" name="nome" value="${cliente.nome}"><br>
        Cognome: <input type="text" name="cognome" value="${cliente.cognome}"><br>
        Email: <input type="text" name="email" value="${cliente.email}"><br>
        <input type="submit" value="Salva">
    </form>
    
    <h2>Lista Clienti</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Azioni</th>
        </tr>
        <c:forEach var="cliente" items="${clientiList}">
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.cognome}</td>
                <td>${cliente.email}</td>
                <td>
                    <a href="clienti?azione=modifica&id=${cliente.id}">Modifica</a> |
                    <a href="clienti?azione=elimina&id=${cliente.id}">Elimina</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

```


Nel tuo servlet (ClientiServlet.java), dovrai gestire le richieste POST per l'inserimento e la modifica dei clienti, e le richieste GET per la modifica e l'eliminazione dei clienti. Ecco un esempio di come potresti fare questo:

ClientiServlet.java (estratto)
```java

@WebServlet("/clienti")
public class ClientiServlet extends HttpServlet {
    private ClientiDAO clientiDAO = new ClientiDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String azione = request.getParameter("azione");
        String id = request.getParameter("id");
        
        if ("modifica".equals(azione) && id != null) {
            Clienti cliente = clientiDAO.getClienteById(Integer.parseInt(id));
            request.setAttribute("cliente", cliente);
        } else if ("elimina".equals(azione) && id != null) {
            clientiDAO.eliminaCliente(Integer.parseInt(id));
        }
        
        List<Clienti> clientiList = clientiDAO.getAllClienti();
        request.setAttribute("clientiList", clientiList);
        request.getRequestDispatcher("clienti.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        
        Clienti cliente = new Clienti();
        cliente.setNome(nome);
        cliente.setCognome(cognome);
        cliente.setEmail(email);
        
        if (id == null || id.isEmpty()) {
            clientiDAO.inserisciCliente(cliente);
        } else {
            cliente.setId(Integer.parseInt(id));
            clientiDAO.modificaCliente(cliente);
        }
        
        response.sendRedirect("clienti");
    }
}

```


Nel tuo ClientiDAO.java, dovrai aggiungere i metodi getClienteById, inserisciCliente, modificaCliente, e eliminaCliente per gestire le operazioni di inserimento, modifica e eliminazione dei clienti nel database

```java

public class ClientiDAO {
    // ... (altro codice)

    public Clienti getClienteById(int id) {
        String sql = "SELECT id, nome, cognome, email FROM clienti WHERE id = ?";
        Clienti cliente = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = new Clienti();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCognome(rs.getString("cognome"));
                cliente.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }

    public void inserisciCliente(Clienti cliente) {
        String sql = "INSERT INTO clienti (nome, cognome, email) VALUES (?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCognome());
            pstmt.setString(3, cliente.getEmail());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificaCliente(Clienti cliente) {
        String sql = "UPDATE clienti SET nome = ?, cognome = ?, email = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCognome());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setInt(4, cliente.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminaCliente(int id) {
        String sql = "DELETE FROM clienti WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

```

- Spiegazione dei metodi:

- getClienteById: Questo metodo prende un ID come parametro e restituisce un oggetto Clienti che rappresenta il cliente con quell'ID nel database, o null se nessun cliente con quell'ID esiste.

- inserisciCliente: Questo metodo prende un oggetto Clienti come parametro e inserisce un nuovo record nel database con le informazioni da quell'oggetto.

- modificaCliente: Questo metodo prende un oggetto Clienti come parametro e aggiorna il record nel database che ha lo stesso ID dell'oggetto Clienti.

- eliminaCliente: Questo metodo prende un ID come parametro e elimina il record nel database con quell'ID

9. Aggiungi un Pulsante o un Link nella Pagina JSP
In clienti.jsp, aggiungi un pulsante o un link che invia una richiesta al servlet per esportare i dati in un file CSV:

```html

<a href="clienti?azione=esporta_csv">Salva i Clienti in File CSV</a>

```

2. Aggiungi un Metodo nel Servlet
Nel ClientiServlet.java, aggiungi un metodo per gestire l'azione esporta_csv:

```java

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String azione = request.getParameter("azione");

    if ("esporta_csv".equals(azione)) {
        esportaClientiInCSV(response);
        return;
    }

    // ... (il resto del tuo codice doGet)
}

private void esportaClientiInCSV(HttpServletResponse response) {
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment;filename=clienti.csv");

    try (PrintWriter writer = response.getWriter()) {
        writer.println("ID,Nome,Cognome,Email");

        List<Clienti> clientiList = clientiDAO.getAllClienti();
        for (Clienti cliente : clientiList) {
            writer.println(cliente.getId() + "," + cliente.getNome() + "," + cliente.getCognome() + "," + cliente.getEmail());
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

```


- Spiegazione
- Nella pagina JSP, abbiamo aggiunto un link che, quando cliccato, invia una richiesta GET al servlet con l'azione esporta_csv.
- Nel servlet, abbiamo aggiunto un controllo per vedere se l'azione è esporta_csv, e in tal caso, chiamiamo un metodo esportaClientiInCSV per esportare i dati in un file CSV.
- Il metodo esportaClientiInCSV imposta l'intestazione della risposta per indicare che si tratta di un file CSV e quindi scrive i dati dei clienti nel corpo della risposta come un file CSV.
- I dati dei clienti vengono ottenuti chiamando il metodo getAllClienti sul DAO, che restituisce una lista di tutti i clienti nel database.
- Ora, quando clicchi sul link nella tua pagina JSP, dovrebbe scaricare un file CSV con i dati dei clienti


10. implementare il frontend con Bootstrap

1. Aggiungi Bootstrap alla tua Pagina JSP
Innanzitutto, aggiungi i link ai file CSS e JS di Bootstrap nel tuo file clienti.jsp:

```html

<head>
    <title>Gestione Clienti</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

```


2. Modifica la Struttura della Pagina per Utilizzare le Card
Modifica la struttura della pagina per utilizzare le card di Bootstrap invece delle tabelle. Ecco un esempio di come potrebbe apparire:

```html

<body>
    <div class="container">
        <h1>Gestione Clienti</h1>

        <h2>Inserisci/Modifica Cliente</h2>
        <form action="clienti" method="post" class="mb-4">
            <input type="hidden" name="id" value="${cliente.id}">
            <div class="form-group">
                <label>Nome</label>
                <input type="text" name="nome" value="${cliente.nome}" class="form-control">
            </div>
            <div class="form-group">
                <label>Cognome</label>
                <input type="text" name="cognome" value="${cliente.cognome}" class="form-control">
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" name="email" value="${cliente.email}" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Salva</button>
        </form>

        <h2>Lista Clienti</h2>
        <div class="row">
            <c:forEach var="cliente" items="${clientiList}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${cliente.nome} ${cliente.cognome}</h5>
                            <p class="card-text">Email: ${cliente.email}</p>
                            <a href="clienti?azione=modifica&id=${cliente.id}" class="btn btn-warning">Modifica</a>
                            <a href="clienti?azione=elimina&id=${cliente.id}" class="btn btn-danger">Elimina</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="mt-4">
            <a href="clienti?azione=esporta_csv" class="btn btn-success">Salva i Clienti in File CSV</a>
        </div>
    </div>
</body>

```

- Spiegazione
- Abbiamo incluso i file CSS e JS di Bootstrap nel <head> della pagina.
- Abbiamo modificato il form di inserimento/modifica per utilizzare le classi di Bootstrap per i controlli del form e i pulsanti.
- Abbiamo sostituito la tabella con una griglia di card di Bootstrap. Ogni card rappresenta un cliente e mostra il nome, il cognome e l'email del cliente, con pulsanti per modificare o eliminare il cliente.
- Abbiamo anche aggiunto un pulsante per esportare i dati in un file CSV, come prima.
- Ora, la tua pagina dovrebbe avere un aspetto più moderno e responsive grazie a Bootstrap, e i dati dei clienti saranno visualizzati in card invece che in una tabella.

11. includi il layout di bootstrap in modo da averlo condiviso con altre pagine

Per includere un layout di Bootstrap condiviso, puoi creare un file JSP separato (ad esempio, layout.jsp) che contiene le parti comuni del layout, come l'intestazione e il piè di pagina. Quindi, puoi includere questo layout in altre pagine JSP utilizzando la direttiva jsp:include. Ecco come potresti farlo:

1. Creare un File layout.jsp
Crea un file layout.jsp con il seguente contenuto:

```jsp

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Clienti</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Gestione Clienti</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="clienti">Home</a>
                    </li>
                    <!-- Aggiungi altri link di navigazione qui se necessario -->
                </ul>
            </div>
        </nav>

        <div class="container mt-4">
            <jsp:include page="${pageContent}" />
        </div>

        <footer class="footer mt-auto py-3 bg-light">
            <div class="container">
                <span class="text-muted">© 2023 Gestione Clienti</span>
            </div>
        </footer>
    </div>
</body>
</html>

```

2. Creare un File JSP Separato per il Contenuto
Crea un file JSP separato (ad esempio, content.jsp) che contiene solo il contenuto specifico della pagina:

```jsp

<h2>Inserisci/Modifica Cliente</h2>
<!-- (il resto del tuo codice per il form di inserimento e la lista di clienti con le card di Bootstrap) -->

```

3. Modifica il Servlet per Includere il Contenuto Corretto
Nel tuo servlet, imposta un attributo di richiesta per indicare quale pagina di contenuto includere, e quindi inoltra la richiesta al layout:

```java

request.setAttribute("pageContent", "content.jsp");
request.getRequestDispatcher("layout.jsp").forward(request, response);

```


- Spiegazione
- Nel file layout.jsp, abbiamo incluso l'intestazione e il piè di pagina comuni, così come i link ai file CSS e JS di Bootstrap. Abbiamo anche aggiunto una barra di navigazione con un link alla home page.
- Abbiamo utilizzato la direttiva jsp:include per includere il contenuto specifico della pagina. Il percorso della pagina di contenuto è specificato da un attributo di richiesta chiamato pageContent.
- Nel servlet, abbiamo impostato l'attributo pageContent per indicare quale pagina di contenuto includere, e quindi abbiamo inoltrato la richiesta al layout.
- Ora, il layout sarà condiviso tra tutte le pagine che utilizzano questo schema, rendendo più facile mantenere un aspetto coerente attraverso il sito




