import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/clienti/*")
public class AggiungiClienteServlet extends HttpServlet {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Ottieni i parametri dal modulo JSP
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String numeroCartaIdentita = request.getParameter("carta_id");

        System.out.println(nome);
        System.out.println(cognome);
        System.out.println(numeroCartaIdentita);

        // Esegui la logica per verificare se il cliente esiste già
        boolean clienteEsiste = clienteDAO.controllaSePresente("carta_id");

        if (clienteEsiste) {
            // Se il cliente esiste già, gestisci l'errore o reindirizza con un messaggio di
            // errore
            request.setAttribute("errore", "Il cliente esiste già.");
        } else {
            // Se il cliente non esiste ancora, aggiungilo al sistema
            Cliente cliente = new Cliente();
            
            cliente.setNome(nome);
            cliente.setCognome(cognome);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setCarta_id(numeroCartaIdentita);

            clienteDAO.insertCliente(cliente);

            // Dopo l'aggiunta del cliente, reindirizza l'utente alla pagina principale dei
            // clienti
            response.sendRedirect("/clienti");
        }
    }
}