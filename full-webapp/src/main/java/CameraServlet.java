import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager; // Importa DriverManager da java.sql
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CameraServlet extends HttpServlet {
    private CameraDAO CameraDAO;

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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Camera> camere = CameraDAO.getAllCamere();
        request.setAttribute("camere", camere);
        //AGGIUNGI ALTRI METODI?
        RequestDispatcher dispatcher = request.getRequestDispatcher("camere.jsp");
        dispatcher.forward(request, response);
    }
}