import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAO {
    private Connection conn;// connessione

    public PrenotazioneDAO(Connection conn) {
        this.conn = conn;
    }

    public double calcolaPrezzo(double prezzo, int notti) {

        double prezzoTot = prezzo * notti;

        System.out.println(prezzoTot);
        return prezzoTot;
    }

    public void inserisciPrenotazione(Cliente c, Camera r, int notti, Date checkInDate, Date checkOutDate, double totale) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO prenotazioni (id_cliente, id_camera, notti, check_in, check_out, totale) VALUES (?, ?, ?, ?, ?, ?)")) {
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

    public void creaPrenotazione(int camera_id, int notti, Date checkInDate, Date checkOutDate,
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
        double prezzo = p.calcolaPrezzo(camera.getPrezzo(), notti);
        p.inserisciPrenotazione(cliente, camera, notti, checkInDate, checkOutDate, prezzo);
    }

    public List<Prenotazione> getAllPrenotazioni() {
        List<Prenotazione> prenotazioni = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM prenotazioni")) {

            while (rs.next()) {
                Prenotazione p = new Prenotazione();
                p.setId(rs.getInt("id"));
                p.setIdCliente(rs.getInt("id_cliente"));
                p.setIdCamera(rs.getInt("id_camera"));
                p.setNotti(rs.getInt("notti"));
                p.setCheckIn(rs.getString("check_in"));
                p.setCheckOut(rs.getString("check_out"));
                p.setTotale(rs.getDouble("totale"));
                prenotazioni.add(p);
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return prenotazioni; // riporta la lista di Camere
    }

    public List<Camera> getCamereDisponibili(Date checkIn, Date checkOut) {
        List<Integer> idCamere = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT id_camera FROM prenotazioni WHERE check_in <= ? AND check_out >= ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, checkOut);
            stmt.setDate(2, checkIn);

            rs = stmt.executeQuery();

            while (rs.next()) {
                idCamere.add(rs.getInt("id_camera"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Camera> camereDisponibili = new ArrayList<>();
        CameraDAO c = new CameraDAO(conn);
        Camera camera;
        for (int idCamera: idCamere){
            camera = c.getCameraById(idCamera);
            camereDisponibili.add(camera);

        }
        return camereDisponibili;
    }
}
