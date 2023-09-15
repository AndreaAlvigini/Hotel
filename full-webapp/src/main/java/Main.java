import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {

            // CameraDAO cameraDAO = new CameraDAO(conn);
            // ClienteDAO clienteDAO = new ClienteDAO(conn);
            // PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO(conn);

            // // Stampa tutti i camere
            // List<Camera> camera = cameraDAO.getAllProdotti();
            // System.out.println("Camere:");
            // for (Camera p : camere) {
            //     System.out.println(p.getId() + ": " + p.getNome() + " (" + p.getPrezzo() + ")");
            // }

            // // Stampa tutti i clienti
            // List<Cliente> clienti = clienteDAO.getAllClienti();
            // System.out.println("\nClienti:");
            // for (Cliente c : clienti) {
            //     System.out.println(c.getId() + ": " + c.getNome() + " (" + c.getEmail() + ")");
            // }

            // // Stampa tutti gli prenotazioni
            // List<Prenotazione> prenotazioni = prenotazioneDAO.getAllPrenotazioni();
            // System.out.println("\nPrenotazioni:");
            // for (Prenotazione a : prenotazioni) {
            //     System.out.println(a.getId() + ": Camera " + a.getIdCamera() + ", Cliente " + a.getIdCliente() + ", Data " + a.getDataPrenotazione());
            // }


            // prenotazioneDAO.calcolaPrezzo();
            // CameraDAO cameraDAO = new CameraDAO(conn);
            // List<Camera> camera = cameraDAO.getCamereDisponibili();
            // System.out.println("Camere:");
            // for (Camera p : camera) {
            //     System.out.println(p);
            // }
            // clienteDAO.controllaCliente("AU7654S");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}