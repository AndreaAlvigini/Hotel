//Questa é una classe che rappresenta il modello di dati per le Prenotazioni del nostro Hotel e possiede solo i campi che corrispondono alle colonne nelle tabelle del database.
import java.sql.Date;

public class Prenotazione {
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

    public Prenotazione(){
    }

    public Prenotazione(int id, int idCliente, int idCamera, int notti, Date checkIn, Date checkOut, Double totale){
        this.id = id;
        this.idCliente = idCliente;
        this.idCamera = idCamera;
        this.notti = notti;
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

    // Getter e Setter per recuperare i dati del clienti collegati alla tabella prenotazione tramite foreign key
    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteCognome() {
        return clienteCognome;
    }

    public void setClienteCognome(String clienteCognome) {
        this.clienteCognome = clienteCognome;
    }

    public String getClienteDocumento() {
        return clienteDocumento;
    }

    public void setClienteDocumento(String clienteDocumento) {
        this.clienteDocumento = clienteDocumento;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteTelefono() {
        return clienteTelefono;
    }

    public void setClienteTelefono(String clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }

    // Getter e Setter per recuperare i dati delle camere collegati alla tabella prenotazione tramite foreign key
    public int getCameraNumero() {
        return cameraNumero;
    }

    public void setCameraNumero(int cameraNumero) {
        this.cameraNumero = cameraNumero;
    }

    public String getCameraTipologia() {
        return cameraTipologia;
    }

    public void setCameraTipologia(String cameraTipologia) {
        this.cameraTipologia = cameraTipologia;
    }

}
