import java.util.Date;

public class Prenotazione {
    private int id;
    private int idCliente;
    private int idProdotto;
    private Date checkIn;
    private Date checkOut;
    private double totale;

    public Prenotazione(){
    }

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

    public int getIdCliente(){
        return idCliente;
    }
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }

    public int getIdProdotto(){
        return idProdotto;
    }
    public void setIdProdotto(int idProdotto){
        this.idProdotto = idProdotto;
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

    public Double getTotale(){
        return totale;
    }

    public void setTotale(Double totale){
        this.totale = totale;
    }
}
