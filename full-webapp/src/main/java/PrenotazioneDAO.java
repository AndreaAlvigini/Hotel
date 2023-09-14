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
    

    public void inserisciPrenotazione(Prenotazione p, Cliente c, Date checkInDate, Date checkOutDate, double totale) { //metodo per inserire una nuova prenotazione nel database
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO prenotazioni (id_cliente, id_camera, check_in, check_out, totale) VALUES (?, ?, ?, ?, ?)")) {
            
            Camera r = new Camera();
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

    public double calcolaPrezzo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        double prezzo = 200.00;
        LocalDate a = LocalDate.parse("2023-09-05", formatter);
        LocalDate b = LocalDate.parse("2023-09-07", formatter);
        long giorniDifferenza = ChronoUnit.DAYS.between(a, b);
        double prezzoTot = prezzo * giorniDifferenza;
        System.out.println(prezzoTot);
        return prezzoTot;

    }

    // private static double calculatePrice(Connection conn, int roomId, int
    // numberOfNights) throws SQLException {
    // String selectRoomPriceQuery = "SELECT price FROM Rooms WHERE id = ?";

    // try (PreparedStatement stmt = conn.prepareStatement(selectRoomPriceQuery)) {
    // stmt.setInt(1, roomId);
    // ResultSet resultSet = stmt.executeQuery();
    // if (resultSet.next()) {
    // double roomPrice = resultSet.getDouble("price");
    // return roomPrice * numberOfNights;
    // }
    // }
    // return 0.0;
    // }
}
