import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/camere/*")
public class DettaglioCameraServlet extends HttpServlet {

    private CameraDAO cameraDAO;

    public void init() {
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database.", e);
        }

        cameraDAO = new CameraDAO(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    String[] parts = requestURI.split("/");

    if (parts.length > 0) {
        String parametro = parts[parts.length - 1];

        try {
            int idCamera = Integer.parseInt(parametro);
            Camera camera = cameraDAO.getCameraById(idCamera);

            if (camera == null) {
                response.sendRedirect("/");
                return;
            }

            request.setAttribute("camera", camera);

        } catch (NumberFormatException e) {
            response.sendRedirect("/");
            return;
        }
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("/camera.jsp");
    dispatcher.forward(request, response);
    }
}
