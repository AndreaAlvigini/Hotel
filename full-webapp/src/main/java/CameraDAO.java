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
                Camera c = new Camera(); // creazione oggetto Camera
                // utilizzo il metodo di Camera p per assegnare il valore all'id del Camera,
                // leggendolo dal valore della colonna della riga corrente del ResultSet
                c.setId(rs.getInt("id"));
                c.setTipologia(rs.getString("tipologia"));
                c.setDescrizione(rs.getString("descrizione"));
                c.setPrezzo(rs.getDouble("prezzo"));
                c.setImmagine(rs.getString("immagine"));
                c.setDisponibilita(rs.getBoolean("disponibilita"));
                camere.add(c);
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return camere; // riporta la lista di Camere
    }

    public Camera getCameraById(int id) { // metodo di selezione Camera in base all'id (in input) che restituisce un
                                          // oggetto Camera
        Camera c = null; // creazione oggetto Camera vuoto

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM camere WHERE id = ?")) { // selezione dalla
                                                                                                    // tabella prodotti
                                                                                                    // in base all'id

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // assegnazione dei valori all'oggetto Camera in base alla riga dell'id fornito
                    c = new Camera();
                    c.setId(rs.getInt("id"));
                    c.setTipologia(rs.getString("tipologia"));
                    c.setDescrizione(rs.getString("descrizione"));
                    c.setPrezzo(rs.getDouble("prezzo"));
                    c.setImmagine(rs.getString("immagine"));
                    c.setDisponibilita(rs.getBoolean("disponibilita"));
                }
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return c; // riporta il Camera completo
    }

    public void insertCamera(Camera c) { // metodo di inserimento che prende in input un Camera (p)
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO camere (tipologia, descrizione, prezzo, immagine, disponibilita) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, c.getTipologia()); // riporto i valori nella riga della tabella prodotti
            stmt.setString(2, c.getDescrizione());
            stmt.setDouble(3, c.getPrezzo());
            stmt.setString(4, c.getImmagine());
            stmt.setBoolean(5, c.getDisponibilita());
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
                Camera c = new Camera(); // creazione oggetto Camera
                // utilizzo il metodo di Camera p per assegnare il valore all'id del Camera,
                // leggendolo dal valore della colonna della riga corrente del ResultSet
                c.setId(rs.getInt("id"));
                c.setTipologia(rs.getString("tipologia"));
                c.setDescrizione(rs.getString("descrizione"));
                c.setPrezzo(rs.getDouble("prezzo"));
                c.setImmagine(rs.getString("immagine"));
                c.setDisponibilita(rs.getBoolean("disponibilita"));
                camere.add(c);
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return camere; // riporta la lista di Camere
    }
    
}
