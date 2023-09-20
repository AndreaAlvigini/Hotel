import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conn;// connessione

    public ClienteDAO(Connection conn) {// costruttore di classe
        this.conn = conn;
    }
    //metodo che restituisce una lisa di tutti i clienti presenti nel database
    public List<Cliente> getAllClienti() { // metodo che resituisce una lista di oggetti Cliente
        List<Cliente> clienti = new ArrayList<>(); // creazione della lista

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM clienti")) {

            while (rs.next()) {
                Cliente c = new Cliente(); // creazione oggetto Cliente
                c.setId(rs.getInt("id")); // assegno i valori a CLiente in base alla riga del ResultSet
                c.setNome(rs.getString("nome"));
                c.setCognome(rs.getString("cognome"));
                c.setCarta_id(rs.getString("carta_id"));
                c.setEmail(rs.getString("email"));
                c.setTelefono(rs.getString("telefono"));
                clienti.add(c);// aggiungo alla lista Cliente valori completi
            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return clienti;
    }
    //Metodo per controllare se all'interno del database esiste un cliente inserendo come dato di controllo l'id della carta
    public boolean controllaSePresente(String carta_id) {
        boolean doesExists = false;

        try {
            String sql = "SELECT EXISTS (SELECT * FROM clienti WHERE carta_id = ?) AS doesExists";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, carta_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                doesExists = rs.getBoolean("doesExists");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doesExists;
    }

    //Metodo per selezionare un cliente da un determito id
    public Cliente getClienteById(int id) {
        Cliente c = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clienti WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCognome(rs.getString("cognome"));
                    c.setCarta_id(rs.getString("carta_id"));
                    c.setEmail(rs.getString("email"));
                    c.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
        return c;// riporto la lista con i dati CLiente
    }

    // Metodo di selezione di un Acquisto che prende in input l'id e restituisce
    // l'Acquisto corrispondente
    public void insertCliente(Cliente c) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO clienti (nome, cognome, carta_id, email, telefono) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCognome());
            stmt.setString(3, c.getCarta_id());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getTelefono());
            stmt.executeUpdate();

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }

    // Metodo per selezionare un cliente da carta id
    public Cliente getClienteByCartaId(String carta_id) {
        Cliente c = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clienti WHERE id = ?")) {

            stmt.setString(1, carta_id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCarta_id(rs.getString("carta_id"));
                    c.setEmail(rs.getString("email"));
                    c.setTelefono(rs.getString("telefono"));
                }

            }

        } catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }

        return c;
    }

}
