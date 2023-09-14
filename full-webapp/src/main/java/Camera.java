public class Camera{
    private int id;
    private String tipologia;
    private String descrizione;
    private Double prezzo;
    private String immagine;
    private boolean disponibilita;

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