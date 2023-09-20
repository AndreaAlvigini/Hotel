import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClienteServlet extends HttpServlet {
    private ClienteDAO clienteDAO;

    public void init() {
        try {
            // Stabilisce una connessione al database SQLite denominato "database.db"
            String url = "jdbc:sqlite:database.db";
            Connection conn = DriverManager.getConnection(url);
            
            // Inizializza l'istanza di ClienteDAO con la connessione al database
            clienteDAO = new ClienteDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace(); // Gestisce eventuali eccezioni SQL stampando una traccia nello standard output
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Ottiene una lista di oggetti Cliente dal ClienteDAO
        List<Cliente> clienti = clienteDAO.getAllClienti();
        
        // Imposta l'attributo "clienti" nella richiesta, che sarà utilizzato dalla pagina JSP
        request.setAttribute("clienti", clienti);
        
        // Prepara un dispatcher per inoltrare la richiesta alla pagina JSP "clienti.jsp"
        RequestDispatcher dispatcher = request.getRequestDispatcher("clienti.jsp");
        
        // Inoltra la richiesta alla pagina JSP per la visualizzazione dei dati
        dispatcher.forward(request, response);
    }
}