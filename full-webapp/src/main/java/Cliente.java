//Questa é una classe che rappresenta il modello di dati per Cliente e possiede solo i campi che corrispondono alle colonne nelle tabelle del database.
public class Cliente {
    //Specifico i campi che appartengono alla classe Cliente
    private int id;
    private String nome;
    private String email;

    //aggiungo telefono
    private String telefono;
    //aggiunto carta_id
    private String carta_id;


    // Costruttore vuoto
    public Cliente() {
    }

    // Costruttore con parametri
    public Cliente(int id, String nome, String email, String telefono, String carta_id) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        // aggiunto carta_id costruttore
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // aggiunto telefono get set
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //GETTER e SETTER carta_id
    public String getCarta_id() {
        return carta_id;
    }

    public void setCarta_id(String carta_id) {
        this.carta_id = carta_id;
    }


}