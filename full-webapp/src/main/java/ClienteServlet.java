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
            String url = "jdbc:sqlite:database.db";
            Connection conn = DriverManager.getConnection(url);
            clienteDAO = new ClienteDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace(); // Puoi gestire l'eccezione in modo appropriato qui
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clienti = clienteDAO.getAllClienti();
        request.setAttribute("clienti", clienti);
        //AGGIUNGERE "AGGIUNGI CLIENTE"?
        RequestDispatcher dispatcher = request.getRequestDispatcher("clienti.jsp");
        dispatcher.forward(request, response);
    }
}
