import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CameraDAO {
    private Connection conn; // connessione

    public CameraDAO(Connection conn) { // costruttore di classe
        this.conn = conn;
    }

    public List<Camera> getAllCamere() { // metodo che restituisce una lista di oggetti Camera
        List<Camera> camere = new ArrayList<>(); // creazione della lista

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM camere")) {

            while (rs.next()) {
                Camera p = new Camera(); // creazione oggetto Camera
                // utilizzo il metodo di Camera p per assegnare il valore all'id del Camera,
                // leggendolo dal valore della colonna della riga corrente del ResultSet
                p.setId(rs.getInt("id"));
                p.setTipologia(rs.getString("tipologia"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setImmagine(rs.getString("immagine"));
                p.setDisponibilita(rs.getBoolean("disponibilita"));
                camere.add(p);
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return camere; // riporta la lista di Camere
    }

    public Camera getCameraById(int id) { // metodo di selezione Camera in base all'id (in input) che restituisce un
                                          // oggetto Camera
        Camera p = null; // creazione oggetto Camera vuoto

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM camere WHERE id = ?")) { // selezione dalla
                                                                                                    // tabella prodotti
                                                                                                    // in base all'id

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // assegnazione dei valori all'oggetto Camera in base alla riga dell'id fornito
                    p = new Camera();
                    p.setId(rs.getInt("id"));
                    p.setTipologia(rs.getString("tipologia"));
                    p.setDescrizione(rs.getString("descrizione"));
                    p.setPrezzo(rs.getDouble("prezzo"));
                    p.setImmagine(rs.getString("immagine"));
                    p.setDisponibilita(rs.getBoolean("disponibilita"));
                }
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return p; // riporta il Camera completo
    }

    public void insertCamera(Camera p) { // metodo di inserimento che prende in input un Camera (p)
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO camere (tipologia, descrizione, prezzo, immagine, disponibilita) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, p.getTipologia()); // riporto i valori nella riga della tabella prodotti
            stmt.setString(2, p.getDescrizione());
            stmt.setDouble(3, p.getPrezzo());
            stmt.setString(4, p.getImmagine());
            stmt.setBoolean(5, p.getDisponibilita());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

    }

    public void cancellaCamera(int id) { // metodo per cancellare un Camera che prende in input l'id
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM camere WHERE id = ?")) {
            stmt.setInt(1, id); // Cancella il Camera in base all'id
            stmt.executeUpdate();
        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }

    public List<Camera> getCamereDisponibili() { // metodo che restituisce una lista di oggetti Camera
        List<Camera> camere = new ArrayList<>(); // creazione della lista

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM camere WHERE disponibilita = true")) {

            while (rs.next()) {
                Camera p = new Camera(); // creazione oggetto Camera
                // utilizzo il metodo di Camera p per assegnare il valore all'id del Camera,
                // leggendolo dal valore della colonna della riga corrente del ResultSet
                p.setId(rs.getInt("id"));
                p.setTipologia(rs.getString("tipologia"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setImmagine(rs.getString("immagine"));
                p.setDisponibilita(rs.getBoolean("disponibilita"));
                camere.add(p);
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return camere; // riporta la lista di Camere
    }
    
}
