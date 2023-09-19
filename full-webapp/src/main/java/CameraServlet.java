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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cameraId = request.getParameter("id");

        if (cameraId != null) {
            try {
                int id = Integer.parseInt(cameraId);
                Camera camera = CameraDAO.getCameraById(id);

                if (camera != null) {
                    request.setAttribute("camera", camera);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("camera.jsp");
                    dispatcher.forward(request, response);
                    return; // Termina la richiesta dopo il forward
                }
            } catch (NumberFormatException e) {
                // Gestisci il caso in cui l'ID della camera non sia un numero valido
                // Ad esempio, reindirizza a una pagina di errore o gestisci in modo appropriato.
                e.printStackTrace();
            }
        }

        // Se l'ID della camera non è valido o non è stato specificato, mostra tutte le camere
        List<Camera> camere = CameraDAO.getAllCamere();
        request.setAttribute("camere", camere);
        RequestDispatcher dispatcher = request.getRequestDispatcher("camere.jsp");
        dispatcher.forward(request, response);
    }
}