# GretaHotel

GretaHotel è un'applicazione di database management pensata per la reception di un Hotel che permette una facile gestione dei dati trattando in particolare camere, clienti e prenotazioni.

Le tecnologie con cui è stata creata sono:

- Java
- SQLite
- Maven
- Java Server Page
- Bootstrap

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