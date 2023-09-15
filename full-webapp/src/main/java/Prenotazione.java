//Questa é una classe che rappresenta il modello di dati per le Prenotazioni del nostro Hotel e possiede solo i campi che corrispondono alle colonne nelle tabelle del database.
import java.util.Date;

public class Prenotazione {
    private int id;
    private int idCliente;
    private int idCamera;
    private Date checkIn;
    private Date checkOut;
    private int notti;
    private double totale;

    public Prenotazione(){
    }

    public Prenotazione(int id, int idCliente, int idCamera, Date checkIn, Date checkOut, int notti, Double totale){
        //id prenotazione
        this.id = id;
        // id del cliente che effettua la prenotazione
        this.idCliente = idCliente;
        // id della camera che 
        this.idCamera = idCamera;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        th
        this.totale = totale;

    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getIdCliente(){
        return idCliente;
    }
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }

    public int getIdCamera(){
        return idCamera;
    }
    public void setIdCamera(int idCamera){
        this.idCamera = idCamera;
    }

    public Date getCheckIn(){
        return checkIn;
    }
    public void setCheckIn(Date checkIn){
        this.checkIn = checkIn;
    }

    public Date getCheckOut(){
        return checkOut;
    }
    public void setCheckOut(Date checkOut){
        this.checkOut = checkOut;
    }

    public int getNotti () {
        return notti;
    }

    public void setNotti (int notti) {
        this.notti = notti;
    }

    public Double getTotale(){
        return totale;
    }

    public void setTotale(Double totale){
        this.totale = totale;
    }

}
