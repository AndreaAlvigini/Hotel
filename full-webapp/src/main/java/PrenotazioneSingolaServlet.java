import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/prenotazioni/*")
public class PrenotazioneSingolaServlet extends HttpServlet {
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

        PrenotazioneDAO = new PrenotazioneDAO(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String[] parts = requestURI.split("/");
        
        if (parts.length > 0) {
            String parametro = parts[parts.length - 1];

            try {
                int idPrenotazione = Integer.parseInt(parametro);
                Prenotazione prenotazione = PrenotazioneDAO.getPrenotazioneById(idPrenotazione);

                if (prenotazione == null) {
                    response.sendRedirect("/prenotazioni");
                }
                
                request.setAttribute("prenotazione", prenotazione);

            } catch (NumberFormatException e) {
                response.sendRedirect("/prenotazioni");
                return;
            }            
        }     

        RequestDispatcher dispatcher = request.getRequestDispatcher("/prenotazione.jsp");
        dispatcher.forward(request, response);
    }
}