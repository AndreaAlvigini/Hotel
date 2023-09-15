# FULL WEB APP 

- Impostazione dell'ambiente di sviluppo:
Prima di tutto, assicurati di avere installato Java, Maven, SQLite e Visual Studio Code. Installa anche l'extension pack Java per Visual Studio Code.

**Creazione del progetto Maven:**
- Crea un nuovo progetto Maven in Visual Studio Code. Puoi fare questo aprendo la linea di comando (o il terminale integrato in VS Code) e digitando mvn archetype:generate. Segui le istruzioni per creare il tuo progetto.

**Aggiunta delle dipendenze:**
- Aggiungi le dipendenze necessarie nel tuo pom.xml. Per un'applicazione web, avrai bisogno di dipendenze come javax.servlet-api, jsp-api, sqlite-jdbc, etc.

**Creazione del database SQLite:**
- Crea il tuo database SQLite e le tabelle necessarie usando il comando sqlite3 nella linea di comando. Puoi interagire con il database SQLite in Java usando il driver JDBC.

**Creazione del modello di dati:**
- Crea le classi Java che rappresentano il tuo modello di dati. Queste classi avranno campi che corrispondono alle colonne nelle tabelle del tuo database.

**Implementazione del DAO:**
- Crea un Data Access Object (DAO) per interagire con il database. Il DAO incapsuler� la logica SQL necessaria per creare, leggere, aggiornare e cancellare record nel database.

**Creazione dei Servlet:**
- Crea i Servlet che gestiranno le richieste HTTP per la tua applicazione. Ad esempio, potresti avere un Servlet per gestire la registrazione degli utenti, un altro per gestire l'accesso, etc.

**Creazione delle pagine JSP:**
- Crea le pagine JSP che saranno visualizzate agli utenti. Le pagine JSP possono usare il modello di dati per visualizzare i dati agli utenti.

**Configurazione del file web.xml:**
- Configura il tuo web.xml per mappare le URL alle classi Servlet.

**Implementazione dell'autenticazione:**
- Per creare pagine riservate agli utenti, dovrai implementare un qualche tipo di autenticazione. Ci� potrebbe includere il controllo delle credenziali di accesso durante il login e il salvataggio dell'ID dell'utente nella sessione.

**Test dell'applicazione:**
- Infine, testa la tua applicazione web. Puoi farlo usando il plugin jetty-maven-plugin o tomcat-maven-plugin, che ti permetteranno di eseguire la tua applicazione web direttamente da Maven.

Ricorda che questo � solo un esempio di alto livello di come potresti strutturare il tuo progetto. Potresti dover adattare questi passaggi in base alle tue esigenze specifiche.

## AGGIUNTA DELLE DIPENDENZE

L'aggiunta delle dipendenze nel tuo progetto Maven avviene tramite il file pom.xml. Ecco un esempio di come potresti aggiungere le dipendenze per SQLite, Servlet e JSP:

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- ...altri elementi del pom... -->

    <dependencies>

        <!-- Dipendenza per SQLite -->
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.36.0.3</version>
        </dependency>

        <!-- Dipendenza per Servlet -->
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

    <!-- ...altri elementi del pom... -->

</project>

```

Ricorda di sostituire le versioni delle dipendenze con le versioni attuali al momento dello sviluppo. Puoi trovare queste informazioni nel Maven Repository.

Nell'esempio sopra, la scope delle dipendenze Servlet e JSP � impostata a provided. Ci� significa che queste librerie sono previste per essere fornite dal server di servlet (ad esempio, Tomcat o Jetty) al momento dell'esecuzione, quindi non devono essere incluse nel pacchetto di guerra dell'applicazione

## CREA DATABASE DA SCRIPT

Puoi creare un **database SQLite** e le relative tabelle direttamente dalla linea di comando.

Per utilizzare sqlite3 da riga di comando, devi avere SQLite installato sul tuo sistema. Ecco come puoi installarlo su vari sistemi operativi:

1. Windows:

Vai alla pagina di download di SQLite.

https://www.sqlite.org/download.html

Scarica il file sqlite-tools-win32-x86-*.zip dal blocco "Precompiled Binaries for Windows".

Estrai il file zip in una cartella sul tuo computer.

Aggiungi la cartella alla variabile di ambiente PATH del tuo sistema.

2. macOS:

SQLite viene fornito preinstallato su macOS. Tuttavia, se desideri utilizzare una versione pi� recente, puoi installarla utilizzando Homebrew:


brew install sqlite

3. Linux:

Su molte distribuzioni Linux, SQLite � gi� preinstallato. Se non lo �, puoi installarlo utilizzando il gestore di pacchetti del tuo sistema. Ad esempio, su Ubuntu, puoi fare:

>sudo apt-get update
sudo apt-get install sqlite3

Dopo aver installato SQLite, dovresti essere in grado di avviare il comando sqlite3 dalla riga di comando.

Puoi creare un nuovo database SQLite digitando sqlite3 mydatabase.db. Questo comando creer� un nuovo file mydatabase.db (o aprir� un file esistente con quel nome) e ti porter� in una shell

Ecco un esempio su come creare un database chiamato mydatabase.db con tre tabelle: prodotti, clienti e acquisti.

Apri la linea di comando e digita:

```bash

INSERT INTO prodotti (nome, prezzo) VALUES ('Prodotto1', 10.99);
INSERT INTO prodotti (nome, prezzo) VALUES ('Prodotto2', 15.99);
INSERT INTO prodotti (nome, prezzo) VALUES ('Prodotto3', 20.99);

```

Questo creer� un nuovo database SQLite chiamato **mydatabase.db** e ti aprir� una shell interattiva SQLite per quel database.

Ora, puoi creare le tabelle. Ecco un esempio di come potresti strutturare le tue tabelle:

```sql

CREATE TABLE prodotti (
    id INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    prezzo REAL NOT NULL
);

CREATE TABLE clienti (
    id INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE
);

CREATE TABLE acquisti (
    id INTEGER PRIMARY KEY,
    id_prodotto INTEGER NOT NULL,
    id_cliente INTEGER NOT NULL,
    data_acquisto TEXT NOT NULL,
    FOREIGN KEY(id_prodotto) REFERENCES prodotti(id),
    FOREIGN KEY(id_cliente) REFERENCES clienti(id)
);

```
In questo esempio, prodotti ha tre colonne: id, nome e prezzo. clienti ha tre colonne: id, nome e email. acquisti ha quattro colonne: id, id_prodotto, id_cliente e data_acquisto.

Le colonne id_prodotto e id_cliente in acquisti sono chiavi esterne che fanno riferimento alle rispettive tabelle prodotti e clienti.

Ricorda che questa � solo una struttura di tabelle di esempio, potrebbe essere necessario adattarla in base alle esigenze specifiche della tua applicazione.

## INSERISCI I DATI

```sql
INSERT INTO prodotti (nome, prezzo) VALUES ('Prodotto1', 10.99);
INSERT INTO prodotti (nome, prezzo) VALUES ('Prodotto2', 15.99);
INSERT INTO prodotti (nome, prezzo) VALUES ('Prodotto3', 20.99);

INSERT INTO clienti (nome, email) VALUES ('Cliente1', 'cliente1@example.com');
INSERT INTO clienti (nome, email) VALUES ('Cliente2', 'cliente2@example.com');
INSERT INTO clienti (nome, email) VALUES ('Cliente3', 'cliente3@example.com');

INSERT INTO acquisti (id_prodotto, id_cliente, data_acquisto) VALUES (1, 1, '2023-07-25');
INSERT INTO acquisti (id_prodotto, id_cliente, data_acquisto) VALUES (2, 2, '2023-07-25');
INSERT INTO acquisti (id_prodotto, id_cliente, data_acquisto) VALUES (3, 3, '2023-07-25');
```

# modello di Dati

Per rappresentare le tabelle prodotti, clienti e acquisti in Java, avrai bisogno di creare classi Java corrispondenti. Ecco un esempio di come potrebbero apparire queste classi:

```java

public class Prodotto {
    private int id;
    private String nome;
    private double prezzo;

    // Costruttori, getter e setter qui...
}

public class Cliente {
    private int id;
    private String nome;
    private String email;

    // Costruttori, getter e setter qui...
}

public class Acquisto {
    private int id;
    private int idProdotto;
    private int idCliente;
    private Date dataAcquisto;

    // Costruttori, getter e setter qui...
}
```

Ogni classe ha campi che corrispondono alle colonne nella tabella corrispondente del database. Ad esempio, la classe Prodotto ha un campo id, un campo nome e un campo prezzo che corrispondono alle colonne id, nome e prezzo nella tabella prodotti.

Le classi dovrebbero avere costruttori per inizializzare i campi, cos� come getter e setter per accedere e modificare i valori dei campi.

Nota che la classe Acquisto ha i campi idProdotto e idCliente per memorizzare le chiavi esterne che fanno riferimento alle tabelle prodotti e clienti. In un'applicazione del mondo reale, potrebbe essere pi� utile avere campi Prodotto e Cliente invece di idProdotto e idCliente, in modo da poter accedere direttamente agli oggetti Prodotto e Cliente correlati. Tuttavia, ci� renderebbe il modello di dati pi� complesso, poich� dovresti gestire le relazioni tra gli oggetti

Ecco un esempio di come potrebbero essere implementati i costruttori, i getter e i setter per la classe Prodotto. Le stesse tecniche possono essere applicate per le classi Cliente e Acquisto

```java

public class Prodotto {
    private int id;
    private String nome;
    private double prezzo;

    // Costruttore vuoto
    public Prodotto() {}

    // Costruttore con parametri
    public Prodotto(int id, String nome, double prezzo) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
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

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
```
```java

public class Cliente {
    private int id;
    private String nome;
    private String email;

    // Costruttore vuoto
    public Cliente() {}

    // Costruttore con parametri
    public Cliente(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

```

```java

import java.util.Date;

public class Acquisto {
    private int id;
    private int idProdotto;
    private int idCliente;
    private Date dataAcquisto;

    // Costruttore vuoto
    public Acquisto() {}

    // Costruttore con parametri
    public Acquisto(int id, int idProdotto, int idCliente, Date dataAcquisto) {
        this.id = id;
        this.idProdotto = idProdotto;
        this.idCliente = idCliente;
        this.dataAcquisto = dataAcquisto;
    }

    // Getter e Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }
}

```

I costruttori, i getter e i setter sono una parte fondamentale delle classi del modello dei dati. Il costruttore � un metodo speciale utilizzato per inizializzare l'oggetto quando viene creato. Puoi avere costruttori con o senza parametri.

I getter e i setter sono metodi che ti permettono di accedere e modificare i valori dei campi privati di un'istanza di classe. I getter sono metodi senza parametri che restituiscono il valore del campo, mentre i setter sono metodi con un parametro corrispondente al tipo di campo, che impostano il valore del campo a quello del parametro.

Questi metodi sono un esempio di incapsulamento, uno dei pilastri fondamentali dell'Object Oriented Programming (OOP). Grazie all'incapsulamento, i campi di una classe possono essere resi privati e accessibili solo tramite i metodi getter e setter, il che mantiene l'integrit� dei dati

Nel caso della classe Acquisto, si noti l'uso della classe Date per rappresentare il campo dataAcquisto. Questa classe � parte del pacchetto java.util e rappresenta un istante specifico nel tempo

**Implementazione del DAO: Crea un Data Access Object (DAO) per interagire con la sorgente dei dati per ottenere e memorizzare i dati**

```java

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

    private Connection conn; //creiamo un istanza della connessione per poi poterla riutilizzare in diverse parti dell app

    //Passando un oggetto Connection tramite il costruttore, la classe ProdottoDAO riceve la dipendenza (cioè la connessione al database) da una fonte esterna.
    public ProdottoDAO(Connection conn) {
        this.conn = conn;
    }

    //metodo che esegue una query SQL per selezionare tutti i record dalla tabella "prodotti". 
    public List<Prodotto> getAllProdotti() throws SQLException {
        List<Prodotto> prodotti = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM prodotti");

        while(rs.next()) {
            Prodotto p = new Prodotto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPrezzo(rs.getDouble("prezzo"));
            prodotti.add(p);
        }

        return prodotti;
    }

    public Prodotto getProdottoById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM prodotti WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()) {
            Prodotto p = new Prodotto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPrezzo(rs.getDouble("prezzo"));
            return p;
        }

        return null;
    }

    public void insertProdotto(Prodotto p) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO prodotti (nome, prezzo) VALUES (?, ?)");
        stmt.setString(1, p.getNome());
        stmt.setDouble(2, p.getPrezzo());
        stmt.executeUpdate();
    }

    // Metodi simili possono essere creati per l'aggiornamento e l'eliminazione dei prodotti
}

```

Nell'esempio sopra, il ProdottoDAO ha un metodo getAllProdotti per ottenere tutti i prodotti dal database, un metodo getProdottoById per ottenere un singolo prodotto per id, e un metodo insertProdotto per inserire un nuovo prodotto nel database.

Per eseguire le query SQL, il DAO utilizza gli oggetti Statement e PreparedStatement. Questi oggetti consentono di eseguire comandi SQL statici e parametrizzati, rispettivamente. Il risultato di una query viene restituito in un oggetto ResultSet, che pu� essere iterato per ottenere i dati.

Ricorda che questo � solo un esempio di base. In un'applicazione del mondo reale, dovresti implementare il controllo degli errori e la gestione delle eccezioni, e potresti voler utilizzare un connection pool per gestire le connessioni al database. Inoltre, dovresti assicurarti di chiudere sempre le tue risorse JDBC (Connection, Statement e ResultSet) in un blocco finally per prevenire perdite di risorse.

# la gestione corretta delle risorse in JDBC

**la gestione corretta delle risorse in JDBC � fondamentale per prevenire problemi come le perdite di memoria****. Una pratica comune � chiudere le risorse JDBC in un blocco finally, che viene eseguito indipendentemente dal fatto che si verifichi un'eccezione o meno.

Ecco un esempio di come potresti farlo:

```java

public List<Prodotto> getAllProdotti() {
    List<Prodotto> prodotti = new ArrayList<>();
    Statement stmt = null;
    ResultSet rs = null;

    try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM prodotti");

        while(rs.next()) {
            Prodotto p = new Prodotto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPrezzo(rs.getDouble("prezzo"));
            prodotti.add(p);
        }
    } catch (SQLException e) {
        // gestisci l'eccezione
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }

    return prodotti;
}

```

In questo esempio, il Statement e il ResultSet sono inizializzati a null all'inizio del metodo. Poi, nel blocco try, vengono effettivamente creati e utilizzati per eseguire la query e processare i risultati. Se tutto va bene, le risorse vengono chiuse nel blocco finally.

Tuttavia, a partire da Java 7, � possibile utilizzare il try-with-resources statement per una gestione pi� elegante e sicura delle risorse. Questo nuovo tipo di dichiarazione try assicura che ogni risorsa sar� chiusa alla fine del blocco. Ecco come potrebbe apparire con un try-with-resources:

```java

public List<Prodotto> getAllProdotti() {
    List<Prodotto> prodotti = new ArrayList<>();

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM prodotti")) {

        while(rs.next()) {
            Prodotto p = new Prodotto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPrezzo(rs.getDouble("prezzo"));
            prodotti.add(p);
        }
    } catch (SQLException e) {
        // gestisci l'eccezione
        e.printStackTrace();
    }

    return prodotti;
}

```

In questo esempio, Statement e ResultSet sono dichiarati nel try-with-resources statement. Alla fine del blocco try, verranno chiusi automaticamente, anche se si verifica un'eccezione. Questo rende il codice pi� breve e facile da mantenere.

# try-with-resources ProdottoDAO

Puoi seguire lo stesso modello per creare i DAO per le classi Cliente e Acquisto

Ecco un esempio di come potrebbe essere implementato il DAO per la classe Prodotto utilizzando il try-with-resources. Ricorda che in questo esempio sto presupponendo che tu abbia una connessione a un database SQLite disponibile.

// ProdottoDAO.java

```java

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

    private Connection conn;

    public ProdottoDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Prodotto> getAllProdotti() {
        List<Prodotto> prodotti = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM prodotti")) {

            while(rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPrezzo(rs.getDouble("prezzo"));
                prodotti.add(p);
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return prodotti;
    }

    public Prodotto getProdottoById(int id) {
        Prodotto p = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM prodotti WHERE id = ?")) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if(rs.next()) {
                    p = new Prodotto();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setPrezzo(rs.getDouble("prezzo"));
                }

            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return p;
    }

    public void insertProdotto(Prodotto p) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO prodotti (nome, prezzo) VALUES (?, ?)")) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPrezzo());
            stmt.executeUpdate();

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }
}

```

Questi DAO seguono lo stesso modello del ProdottoDAO, con metodi per ottenere tutte le entit�, ottenere un'entit� per id e inserire una nuova entit� nel database. Ricorda, devi gestire le eccezioni in modo appropriato per la tua applicazione

// AcquistoDAO.java

```java


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcquistoDAO {

    private Connection conn;

    public AcquistoDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Acquisto> getAllAcquisti() {
        List<Acquisto> acquisti = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM acquisti")) {

            while(rs.next()) {
                Acquisto a = new Acquisto();
                a.setId(rs.getInt("id"));
                a.setIdProdotto(rs.getInt("id_Prodotto"));
                a.setIdCliente(rs.getInt("id_Cliente"));
                a.setDataAcquisto(rs.getDate("data_Acquisto"));
                acquisti.add(a);
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return acquisti;
    }

    public Acquisto getAcquistoById(int id) {
        Acquisto a = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM acquisti WHERE id = ?")) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if(rs.next()) {
                    a = new Acquisto();
                    a.setId(rs.getInt("id"));
                    a.setIdProdotto(rs.getInt("id_Prodotto"));
                    a.setIdCliente(rs.getInt("id_Cliente"));
                    a.setDataAcquisto(rs.getDate("data_Acquisto"));
                }

            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return a;
    }

    public void insertAcquisto(Acquisto a) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO acquisti (id_Prodotto, id_Cliente, data_Acquisto) VALUES (?, ?, ?)")) {

            stmt.setInt(1, a.getIdProdotto());
            stmt.setInt(2, a.getIdCliente());
            stmt.setDate(3, new java.sql.Date(a.getDataAcquisto().getTime()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }
}

```

// ClienteDAO.java

```java


// ClienteDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Cliente> getAllClienti() {
        List<Cliente> clienti = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM clienti")) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                clienti.add(c);
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return clienti;
    }

    public Cliente getClienteById(int id) {
        Cliente c = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clienti WHERE id = ?")) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setEmail(rs.getString("email"));
                }

            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return c;
    }

    public void insertCliente(Cliente c) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO clienti (nome, email) VALUES (?, ?)")) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }
}

```

// Main.java

```java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {

            ProdottoDAO prodottoDAO = new ProdottoDAO(conn);
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            AcquistoDAO acquistoDAO = new AcquistoDAO(conn);

            // Stampa tutti i prodotti
            List<Prodotto> prodotti = prodottoDAO.getAllProdotti();
            System.out.println("Prodotti:");
            for (Prodotto p : prodotti) {
                System.out.println(p.getId() + ": " + p.getNome() + " (" + p.getPrezzo() + ")");
            }

            // Stampa tutti i clienti
            List<Cliente> clienti = clienteDAO.getAllClienti();
            System.out.println("\nClienti:");
            for (Cliente c : clienti) {
                System.out.println(c.getId() + ": " + c.getNome() + " (" + c.getEmail() + ")");
            }

            // Stampa tutti gli acquisti
            List<Acquisto> acquisti = acquistoDAO.getAllAcquisti();
            System.out.println("\nAcquisti:");
            for (Acquisto a : acquisti) {
                System.out.println(a.getId() + ": Prodotto " + a.getIdProdotto() + ", Cliente " + a.getIdCliente() + ", Data " + a.getDataAcquisto());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

```

# crea db

Creazione del database SQLite tabelle prodotti clienti acquisti 

Apri la linea di comando e digita:

```bash

sqlite3 database.db

```

Questo creer� un nuovo database SQLite chiamato mydatabase.db e ti aprir� una shell interattiva SQLite per quel database.

Ora, puoi creare le tabelle. Ecco un esempio di come potresti strutturare le tue tabelle:

```sql

CREATE TABLE prodotti (
    id INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    prezzo REAL NOT NULL
);

CREATE TABLE clienti (
    id INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE
);

CREATE TABLE acquisti (
    id INTEGER PRIMARY KEY,
    id_prodotto INTEGER NOT NULL,
    id_cliente INTEGER NOT NULL,
    data_acquisto DATE NOT NULL,
    FOREIGN KEY (id_prodotto) REFERENCES prodotti (id),
    FOREIGN KEY (id_cliente) REFERENCES clienti (id)
);

INSERT INTO prodotti (nome, prezzo) VALUES ('Prodotto1', 10.99);
INSERT INTO prodotti (nome, prezzo) VALUES ('Prodotto2', 15.99);
INSERT INTO prodotti (nome, prezzo) VALUES ('Prodotto3', 20.99);

INSERT INTO clienti (nome, email) VALUES ('Cliente1', 'cliente1@example.com');
INSERT INTO clienti (nome, email) VALUES ('Cliente2', 'cliente2@example.com');
INSERT INTO clienti (nome, email) VALUES ('Cliente3', 'cliente3@example.com');

INSERT INTO acquisti (id_prodotto, id_cliente, data_acquisto) VALUES (1, 1, '2023-07-23 10:30:45.123');
INSERT INTO acquisti (id_prodotto, id_cliente, data_acquisto) VALUES (2, 2, '2023-07-25 11:30:45.123');
INSERT INTO acquisti (id_prodotto, id_cliente, data_acquisto) VALUES (3, 3, '2023-07-25 10:31:45.123');

```

In questo esempio, prodotti ha tre colonne: id, nome e prezzo. clienti ha tre colonne: id, nome e email. acquisti ha quattro colonne: id, id_prodotto, id_cliente e data_acquisto. Le colonne id_prodotto e id_cliente in acquisti sono chiavi esterne che fanno riferimento alle rispettive tabelle prodotti e clienti

**oppure script a�ternativ**

Apri la linea di comando e digita:

```bash

sqlite3 database.db

```


```sql

-- script.sql

-- Crea la tabella prodotti
CREATE TABLE prodotti (
  id INTEGER PRIMARY KEY,
  nome TEXT NOT NULL,
  prezzo REAL NOT NULL
);

-- Crea la tabella clienti
CREATE TABLE clienti (
  id INTEGER PRIMARY KEY,
  nome TEXT NOT NULL,
  email TEXT NOT NULL
);

-- Crea la tabella acquisti
CREATE TABLE acquisti (
  id INTEGER PRIMARY KEY,
  idProdotto INTEGER NOT NULL,
  idCliente INTEGER NOT NULL,
  dataAcquisto DATE NOT NULL,
  FOREIGN KEY (idProdotto) REFERENCES prodotti (id),
  FOREIGN KEY (idCliente) REFERENCES clienti (id)
);

-- Inserisci alcuni dati di esempio
INSERT INTO prodotti (nome, prezzo) VALUES ('Prodotto1', 10.99), ('Prodotto2', 15.99), ('Prodotto3', 12.99);
INSERT INTO clienti (nome, email) VALUES ('Cliente1', 'cliente1@example.com'), ('Cliente2', 'cliente2@example.com'), ('Cliente3', 'cliente3@example.com');
INSERT INTO acquisti (idProdotto, idCliente, dataAcquisto) VALUES (1, 1, date('now')), (2, 2, date('now')), (3, 3, date('now'));

```

# Creazione dei Servlet: Crea i Servlet che gestiranno le richieste HTTP

Nell'applicazione Java Servlet, la logica del controllo viene gestita dai servlet. Creiamo i servlet per gestire le richieste HTTP relative ai prodotti, ai clienti e agli acquisti.

Assicurati che la dipendenza per le Servlet API sia presente nel tuo pom.xml. Dovresti avere una dipendenza simile a questa:

```xml

<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>

```

Qui di seguito un esempio base di come potrebbero essere i Servlet:

ProdottoServlet.java

```java

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager; // Importa DriverManager da java.sql

public class ProdottoServlet extends HttpServlet {
    private ProdottoDAO prodottoDAO;

    public void init() {
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database.", e);
        }
        prodottoDAO = new ProdottoDAO(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prodotto> prodotti = prodottoDAO.getAllProdotti();
        request.setAttribute("prodotti", prodotti);
        RequestDispatcher dispatcher = request.getRequestDispatcher("prodotti.jsp");
        dispatcher.forward(request, response);
    }
}

```

ClienteServlet.java

```java

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class ClienteServlet extends HttpServlet {
    private ClienteDAO clienteDAO;

    public void init() {
        try {
            String url = "jdbc:sqlite:database.db";
            Connection conn = DriverManager.getConnection(url);
            clienteDAO = new ClienteDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace(); // Puoi gestire l'eccezione in modo appropriato qui
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clienti = clienteDAO.getAllClienti();
        request.setAttribute("clienti", clienti);
        RequestDispatcher dispatcher = request.getRequestDispatcher("clienti.jsp");
        dispatcher.forward(request, response);
    }
}

```
AcquistoServlet.java

```java

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager; // Importa DriverManager da java.sql

public class AcquistoServlet extends HttpServlet {
    private AcquistoDAO acquistoDAO;

    public void init() {
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database.", e);
        }
        acquistoDAO = new AcquistoDAO(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Acquisto> acquisti = acquistoDAO.getAllAcquisti();
        request.setAttribute("acquisti", acquisti);
        RequestDispatcher dispatcher = request.getRequestDispatcher("acquisti.jsp");
        dispatcher.forward(request, response);
    }
}

```

Nota che in ogni servlet, utilizziamo il metodo doGet() per gestire le richieste HTTP GET. Queste richieste ritornano una lista di entit� (prodotti, clienti o acquisti) dal database e le inoltrano a una pagina JSP per la visualizzazione. Devi sostituire "prodotti.jsp", "clienti.jsp" e "acquisti.jsp" con le tue vere pagine JSP.

Le pagine JSP sono utilizzate per creare l'interfaccia utente dell'applicazione. Creeremo pagine JSP per visualizzare i prodotti, i clienti e gli acquisti.

Le espressioni come <c:forEach>, <c:out> e altri simili sono parte del Tag Library Standard (JSTL), che � una libreria utilizzata nelle pagine JSP per semplificare la gestione dei dati e la logica nelle pagine JSP. Di seguito, ti dar� una breve spiegazione di alcuni dei tag JSTL pi� comuni:

<c:forEach>: Questo tag viene utilizzato per iterare su una collezione di oggetti e ripetere il contenuto del suo corpo per ogni elemento nella collezione. � molto utile per la creazione di tabelle e liste dinamiche.

Esempio:

```jsp

<c:forEach var="item" items="${collezione}">
    <c:out value="${item}" />
</c:forEach>

```

<c:out>: Questo tag viene utilizzato per visualizzare il valore di una variabile o di un'espressione in una pagina JSP. Aiuta a prevenire l'iniezione di script indesiderati e garantisce che i dati vengano correttamente formattati.

Esempio:

```jsp

<c:out value="${variabile}" />

```

<c:if> e <c:choose>: Questi tag vengono utilizzati per condizionare l'output in base a una condizione. <c:if> viene utilizzato per eseguire un blocco di codice se una condizione � vera, mentre <c:choose> consente di selezionare uno dei blocchi di codice in base a una serie di condizioni.

Esempio:

```jsp

<c:if test="${condizione}">
    <!-- Codice da eseguire se la condizione � vera -->
</c:if>

```

<c:set>: Questo tag viene utilizzato per assegnare un valore a una variabile, che pu� essere utilizzata successivamente nella pagina JSP. � utile per evitare di eseguire la stessa espressione pi� volte.

Esempio:

```jsp

<c:set var="nome" value="${utente.nome}" />

```

<c:url>: Questo tag viene utilizzato per generare URL dinamicamente. Puoi specificare i parametri dell'URL e il tag costruir� l'URL completo per te.

Esempio:

```jsp

<c:url var="url" value="/pagina.jsp">
    <c:param name="id" value="${id}" />
</c:url>

```

Questi sono solo alcuni dei tag JSTL pi� comuni. La JSTL offre molti altri tag per gestire le operazioni comuni nelle pagine JSP, come il formattamento delle date, la manipolazione delle stringhe e la gestione delle collezioni. � una libreria molto utile per semplificare lo sviluppo di pagine JSP.

# visualizzare i valori delle variabili

inserisci <c:out value="${prodotti}" /> nella pagina per visualizzare il valore della variabile.

prodotti.jsp

```html

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prodotti</title>
</head>
<body>
    <h1>Lista dei prodotti</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Prezzo</th>
        </tr>
        <c:forEach var="prodotto" items="${prodotti}">
            <tr>
                <td>${prodotto.id}</td>
                <td>${prodotto.nome}</td>
                <td>${prodotto.prezzo}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

```

clienti.jsp

```html

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Clienti</title>
</head>
<body>
    <h1>Lista dei clienti</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
        </tr>
        <c:forEach var="cliente" items="${clienti}">
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.email}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

```

acquisti.jsp

```html

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Acquisti</title>
</head>
<body>
    <h1>Lista degli acquisti</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>ID Prodotto</th>
            <th>ID Cliente</th>
            <th>Data Acquisto</th>
        </tr>
        <c:forEach var="acquisto" items="${acquisti}">
            <tr>
                <td>${acquisto.id}</td>
                <td>${acquisto.idProdotto}</td>
                <td>${acquisto.idCliente}</td>
                <td>${acquisto.dataAcquisto}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

```

Queste pagine JSP utilizzano l'attributo items della taglib JSTL <c:forEach> per iterare attraverso le liste di prodotti, clienti e acquisti passate come attributi di richiesta dai rispettivi servlet. Per ogni entit�, stampa i dettagli in una nuova riga della tabella HTML.

Ricorda che dovrai includere la libreria JSTL nel tuo progetto per utilizzare <c:forEach>. Se stai utilizzando Maven, puoi aggiungere questa dipendenza al tuo pom.xml:

```xml

<dependency>
    <groupId>javax.servlet.jsp.jstl</groupId>
    <artifactId>javax.servlet.jsp.jstl-api</artifactId>
    <version>1.2.1</version>
</dependency>
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>jstl-impl</artifactId>
    <version>1.2</version>
</dependency>

```

# Configurazione del file web.xml 

Il file web.xml � il file di configurazione per l'applicazione web e si trova solitamente nella directory WEB-INF. Questo file � utilizzato per definire i servlet, i filtri, i listener e altre componenti, insieme alle rispettive propriet� di configurazione e mappatura URL.

Qui di seguito un esempio di configurazione per il file web.xml:

```xml

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
                             http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <servlet>
        <servlet-name>ProdottoServlet</servlet-name>
        <servlet-class>full-webapp.ProdottoServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>ProdottoServlet</servlet-name>
        <url-pattern>/prodotti</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>ClienteServlet</servlet-name>
        <servlet-class>it.mio.package.ClienteServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>ClienteServlet</servlet-name>
        <url-pattern>/clienti</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>AcquistoServlet</servlet-name>
        <servlet-class>it.mio.package.AcquistoServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>AcquistoServlet</servlet-name>
        <url-pattern>/acquisti</url-pattern>
    </servlet-mapping>

</web-app>

```

In questo file, abbiamo definito tre servlet (ProdottoServlet, ClienteServlet, AcquistoServlet) e abbiamo mappato ciascuno di essi a un URL diverso (/prodotti, /clienti, /acquisti).

Ricorda di sostituire "it.mio.package" con il nome del tuo package.

# eseguire il tutto

Assicurati che il plugin Jetty sia configurato nel file pom.xml del tuo progetto. Deve essere elencato tra i plugin all'interno della sezione <build> <plugins> come indicato nell'esempio sopra. Verifica che il tuo pom.xml contenga una sezione come questa:

```xml

<build>
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

```

Per accedere alle pagine dell'applicazione Java Maven che hai configurato nel file web.xml, devi utilizzare il tuo browser web e l'URL appropriato per ciascuna servlet. Nell'esempio che hai fornito nel tuo file web.xml, hai mappato tre servlet (ProdottoServlet, ClienteServlet, AcquistoServlet) a tre diversi URL (/prodotti, /clienti, /acquisti).

Supponendo che tu stia eseguendo l'applicazione localmente sul tuo computer, puoi accedere alle pagine nel seguente modo:

Pagina dei Prodotti: Vai al tuo browser web e inserisci l'URL http://localhost:8080/prodotti, dove yourAppName dovrebbe essere sostituito con il nome dell'applicazione web che hai configurato. Questo URL ti porter� alla pagina gestita dalla servlet ProdottoServlet.

Pagina dei Clienti: Per accedere alla pagina dei clienti, inserisci l'URL http://localhost:8080/clienti nel tuo browser. Questo URL ti porter� alla pagina gestita dalla servlet ClienteServlet.

Pagina degli Acquisti: Infine, per accedere alla pagina degli acquisti, inserisci l'URL http://localhost:8080/acquisti nel tuo browser. Questo URL ti porter� alla pagina gestita dalla servlet AcquistoServlet.

Ricorda che localhost:8080 � l'indirizzo predefinito per il server locale quando esegui l'applicazione in modalit� di sviluppo. Assicurati di avere l'applicazione in esecuzione e che il nome dell'applicazione nell'URL corrisponda a quanto configurato nel tuo ambiente.

Per assicurarti che l'applicazione Java Maven sia in esecuzione, segui questi passaggi:

Build del Progetto: Prima di tutto, assicurati di avere il tuo progetto Java Maven correttamente costruito. Puoi farlo eseguendo il comando seguente nella directory del tuo progetto (dove si trova il file pom.xml):

```

mvn clean install

```

Questo comando compila il tuo progetto, gestisce le dipendenze e costruisce l'artefatto del tuo progetto (solitamente un file WAR o JAR) nella directory target del tuo progetto.

Avvio del Server Web: Successivamente, dovrai assicurarti di avere un server web o servlet container in esecuzione. Puoi utilizzare Jetty, Tomcat, Wildfly o qualsiasi altro container servlet che preferisci. Se stai usando Jetty, puoi avviarlo con il comando:

```

mvn jetty:run

```
Per altri container, dovresti avviarli secondo le istruzioni specifiche del container.

Verifica l'URL: Una volta che il server web � in esecuzione, puoi utilizzare il tuo browser web per accedere alle pagine dell'applicazione tramite gli URL configurati nel tuo file web.xml. Assicurati di avere l'URL corretto e che corrisponda alla configurazione delle servlet nel tuo web.xml, come descritto nelle risposte precedenti.

Controlla i Log del Server: Durante l'avvio del server web, dovresti vedere i log che indicano che l'applicazione � stata distribuita con successo. Questi log ti diranno anche su quale porta � in ascolto il server (solitamente la porta 8080 per Jetty o Tomcat). Puoi controllare i log per eventuali errori o eccezioni che potrebbero impedire l'avvio dell'applicazione.

Risolvi gli Errori: Se incontri errori durante l'avvio, dovrai risolverli. Gli errori comuni includono problemi con le dipendenze del progetto, errori di configurazione del server o errori nel tuo codice Java. Analizza attentamente i messaggi di errore nei log e cerca una soluzione specifica per il problema che stai riscontrando.

Una volta che l'applicazione � in esecuzione con successo e il server � in ascolto sulla porta specificata, dovresti essere in grado di accedere alle pagine dell'applicazione tramite il browser utilizzando gli URL configurati. Assicurati di avere anche il tuo server web in esecuzione mentre stai cercando di accedere alle pagine dell'applicazione.

Pulisci la cache di Maven: A volte, i problemi di risoluzione delle dipendenze possono essere risolti eliminando la cache di Maven. Puoi farlo eliminando la directory .m2/repository nella tua directory utente o eseguendo il comando:

```

mvn dependency:purge-local-repository

```



