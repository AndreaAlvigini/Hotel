import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PrenotazioneDAO {
    private Connection conn;// connessione

    public PrenotazioneDAO(Connection conn) {
        this.conn = conn;
    }

    public double calcolaPrezzo(double prezzo, String checkIn, String checkOut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkInDate = LocalDate.parse(checkIn, formatter);
        LocalDate checkOutDate = LocalDate.parse(checkOut, formatter);

        long giorniDifferenza = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        double prezzoTot = prezzo * giorniDifferenza;

        System.out.println(prezzoTot);
        return prezzoTot;
    }

    public void inserisciPrenotazione(Cliente c, Camera r, Date checkInDate, Date checkOutDate, double totale) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO prenotazioni (id_cliente, id_camera, check_in, check_out, totale) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, c.getId());
            stmt.setInt(2, r.getId());
            stmt.setDate(3, checkInDate);
            stmt.setDate(4, checkOutDate);
            stmt.setDouble(5, 3);

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }

    public void creaPrenotazione(int camera_id, Date checkInDate, Date checkOutDate,
    int cliente_id, String cliente_carta_id, String cliente_nome, String cliente_cognome, String cliente_email, String cliente_telefono) {

        ClienteDAO c = new ClienteDAO(conn);
        PrenotazioneDAO p = new PrenotazioneDAO(conn);
        CameraDAO r = new CameraDAO(conn);

        boolean doesExists = c.controllaSePresente(cliente_carta_id);
        Cliente cliente;

        if (doesExists) {
            cliente = c.getClienteByCartaId(cliente_carta_id);

        } else {
            cliente = new Cliente(cliente_id, cliente_carta_id, cliente_nome, cliente_cognome, cliente_email, cliente_telefono);
            c.insertCliente(cliente);
        }

        Camera camera = r.getCameraById(camera_id);
        double prezzo = /* p.calcolaPrezzo(camera.getPrezzo(), checkInDate, checkOutDate) */ 200.00;
        p.inserisciPrenotazione(cliente, camera, checkInDate, checkOutDate, prezzo);
    }
}
