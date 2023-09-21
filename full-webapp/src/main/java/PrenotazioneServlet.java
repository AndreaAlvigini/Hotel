
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager; // Importa DriverManager da java.sql
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrenotazioneServlet extends HttpServlet {
    private CameraDAO CameraDAO;
    private PrenotazioneDAO PrenotazioneDAO;

    public void init() {
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database.", e);
        }

        CameraDAO = new CameraDAO(conn);
        PrenotazioneDAO = new PrenotazioneDAO(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Camera> camere = CameraDAO.getAllCamere();
        request.setAttribute("camere", camere);

        List<Prenotazione> prenotazioni;

        // Controlla se i filtri sono applicati o meno
        String tipologiaCamera = request.getParameter("tipologia-camera");
        String checkInDateString = request.getParameter("ordina-data-check-in");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date checkInDate = dateFormat.parse(checkInDateString);

        if (tipologiaCamera != null && !tipologiaCamera.isEmpty()) {
            prenotazioni = PrenotazioneDAO.getPrenotazioniByFilter(PrenotazioneDAO.sql, tipologiaCamera, checkInDate);
        } else {
            prenotazioni = PrenotazioneDAO.getAllPrenotazioni(PrenotazioneDAO.sql);
        }

        request.setAttribute("prenotazioni", prenotazioni);

        // Invio delle richieste e redirect
        RequestDispatcher dispatcher = request.getRequestDispatcher("prenotazioni.jsp");
        dispatcher.forward(request, response);
    }
}