import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PrenotazioneNuovaServlet")
public class PrenotazioneNuovaServlet extends HttpServlet {
    private PrenotazioneDAO prenotazioneDAO;

    public void init() {
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database.", e);
        }

        prenotazioneDAO = new PrenotazioneDAO(conn);
    }

    // GET request (carica la pagina)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/prenotazioni-nuova.jsp");
        dispatcher.forward(request, response);
    }

    // POST request (invia il form)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
        String camera_id = request.getParameter("camera_id");          
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        String cliente_carta_id = request.getParameter("cliente_carta_id");
        String cliente_nome = request.getParameter("cliente_nome");
        String cliente_cognome = request.getParameter("cliente_cognome");
        String cliente_email = request.getParameter("cliente_email");
        String cliente_telefono = request.getParameter("cliente_telefono");
        System.out.println(camera_id);        
        System.out.println(checkInDate);
        System.out.println(checkOutDate);
        System.out.println(cliente_carta_id);
        System.out.println(cliente_nome);
        System.out.println(cliente_cognome);
        System.out.println(cliente_email);
        System.out.println(cliente_telefono);

        response.sendRedirect("prenotazioni");
    }    
}