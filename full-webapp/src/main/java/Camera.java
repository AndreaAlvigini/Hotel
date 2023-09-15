//Questa é una classe che rappresenta il modello di dati per le Camere del nostro Hotelo e possiede solo i campi che corrispondono alle colonne nelle tabelle del database.

public class Camera{
    //Specifico i campi che appartengono alla classe Prodotto
    private int id;
    //aggiunto tipologia
    private String tipologia;
    private String descrizione;
    private Double prezzo;
    //aggiunta immagine
    private String immagine;
    //aggiunto dipsonibilità
    private boolean disponibilita;

    //AGGIUNGERE CAMPI BOOLEANI PER VALORI AGGIUNTIVI + GET SET
    private boolean bagno;

    private boolean condizionatore;

    // Costruttore vuoto
    public Camera(){
    }
    // Costruttore vuoto
    public Camera(int id, String tipologia, String descrizione, Double prezzo, String immagine, boolean disponibilita){
        this.id = id;
        //aggiungiamo tipologia
        this.tipologia = tipologia;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.immagine = immagine;
        //aggiungiamo disponibilità
        this.disponibilita = disponibilita;
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
    //aggiunto disponibilità
    public boolean getDisponibilita (){
        return disponibilita;
    }
    public void setDisponibilita(boolean disponibilita){
        this.disponibilita = disponibilita;
    }
}