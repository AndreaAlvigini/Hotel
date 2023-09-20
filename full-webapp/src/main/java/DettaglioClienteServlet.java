import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/clienti/*")
public class DettaglioClienteServlet extends HttpServlet {
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

        // Ottieni l'URI completo dalla richiesta HTTP,  L'URI rappresenta il percorso completo dell'URL richiesto dal client, compreso il contesto dell'applicazione (cioè il contesto Web dell'applicazione)
        String requestURI = request.getRequestURI();
        //divido in parti l'URI usando come punto di interruzione tra una parte l'altra "/"
        String[] parts = requestURI.split("/");

        if (parts.length > 0) {
            //A noi interessa estrapolare l'i del cliente dall'ultima parte dell'URI
            String parametro = parts[parts.length - 1];

            try {
                //assegno alla variabile idCliente e faccio un parse del parametro che contenente l'id
                int idCliente = Integer.parseInt(parametro);
                //Creo un oggetto cliente che restituisce i dati del cliente selezionato tramite l'id che abbiamo estrapolato
                Cliente cliente = clienteDAO.getClienteById(idCliente);
                // Imposta l'attributo "cliente" nella richiesta, che sarà utilizzato dalla pagina JSP
                request.setAttribute("cliente", cliente);

            } catch (NumberFormatException e) {
                response.sendRedirect("/");
            }

            // Prepara un dispatcher per inoltrare la richiesta alla pagina JSP
            // "cliente.jsp"
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente.jsp");
            // Inoltra la richiesta alla pagina JSP per la visualizzazione dei dati
            dispatcher.forward(request, response);
        }
    }
}
