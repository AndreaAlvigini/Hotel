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

        String sql = "SELECT prenotazioni.*, " +
        "clienti.nome AS clienteNome, clienti.cognome AS clienteCognome, clienti.carta_id AS clienteDocumento, clienti.email AS clienteEmail, clienti.telefono AS clienteTelefono, " +
        "camere.id AS cameraNumero, camere.tipologia AS cameraTipologia " +
        "FROM prenotazioni " +
        "JOIN clienti ON prenotazioni.id_cliente = clienti.id " +
        "JOIN camere ON prenotazioni.id_camera = camere.id";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Prenotazione p = new Prenotazione();
                p.setId(rs.getInt("id"));
                p.setIdCliente(rs.getInt("id_cliente"));
                p.setIdCamera(rs.getInt("id_camera"));
                p.setNotti(rs.getInt("notti"));
                p.setCheckIn(rs.getString("check_in"));
                p.setCheckOut(rs.getString("check_out"));
                p.setTotale(rs.getDouble("totale"));

                p.setClienteNome(rs.getString("clienteNome"));
                p.setClienteCognome(rs.getString("clienteCognome"));
                p.setClienteDocumento(rs.getString("clienteDocumento"));
                p.setClienteEmail(rs.getString("clienteEmail"));
                p.setClienteTelefono(rs.getString("clienteTelefono"));

                p.setCameraNumero(rs.getInt("cameraNumero"));
                p.setCameraTipologia(rs.getString("cameraTipologia"));

                prenotazioni.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prenotazioni;
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
