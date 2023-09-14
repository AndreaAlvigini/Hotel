import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrenotazioneDAO {

    
    private static double calculatePrice(Connection conn, int roomId, int numberOfNights) throws SQLException {
        String selectRoomPriceQuery = "SELECT price FROM Rooms WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(selectRoomPriceQuery)) {
            stmt.setInt(1, roomId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                double roomPrice = resultSet.getDouble("price");
                return roomPrice * numberOfNights;
            }
        }
        return 0.0;
    }
}
