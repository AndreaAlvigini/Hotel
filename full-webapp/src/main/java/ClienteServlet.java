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
    // Ottieni l'ID del cliente dalla richiesta
    String clienteId = request.getParameter("id");

    if (clienteId != null && !clienteId.isEmpty()) {
        // Se è presente un parametro "id", gestisci la richiesta per visualizzare i dati di un cliente specifico

        // Converti l'ID del cliente in un intero
        int id = Integer.parseInt(clienteId);

        // Ottieni il cliente dal database utilizzando l'ID
        Cliente cliente = clienteDAO.getClienteById(id);

        // Verifica se il cliente è stato trovato
        if (cliente != null) {
            // Imposta l'attributo "cliente" nella richiesta, che sarà utilizzato dalla pagina JSP
            request.setAttribute("cliente", cliente);

            // Prepara un dispatcher per inoltrare la richiesta alla pagina JSP "cliente.jsp"
            RequestDispatcher dispatcher = request.getRequestDispatcher("cliente.jsp");

            // Inoltra la richiesta alla pagina JSP per la visualizzazione dei dati del cliente
            dispatcher.forward(request, response);
        } else {
            // Se il cliente non è stato trovato, puoi gestire l'errore in base alle tue esigenze
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente non trovato");
        }
    } else {
        List<Cliente> clienti = clienteDAO.getAllClienti();
        
        // Imposta l'attributo "clienti" nella richiesta, che sarà utilizzato dalla pagina JSP
        request.setAttribute("clienti", clienti);
        
        // Prepara un dispatcher per inoltrare la richiesta alla pagina JSP "clienti.jsp"
        RequestDispatcher dispatcher = request.getRequestDispatcher("clienti.jsp");
        
        // Inoltra la richiesta alla pagina JSP per la visualizzazione dei dati
        dispatcher.forward(request, response);
    }
}

}
