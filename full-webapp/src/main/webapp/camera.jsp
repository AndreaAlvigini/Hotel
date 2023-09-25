<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Camera #${camera.id}</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <link rel="stylesheet" href="./css/style.css">
        </head>

<body>
    <jsp:include page="header.jsp" />
    <main class="container">
        <h1 style="text-align: center;">Dettagli della camera #${camera.id}</h1>

        <div class="grid-container">
            <p>ID: ${camera.id}</p>
            <p>Tipologia: ${camera.tipologia}</p>
            <p>Descrizione: ${camera.descrizione}</p>
            <img src="{pageContext.request.contextPath}/assets/foto_camere/${camera.immagine}" alt="foto della camera" class="card-img-top float-start">
            
        </div>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Bagno</th>
                    <th scope="col">Condizionatore</th>
                    <th scope="col">Prezzo</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${camera.bagno}">
                                <span>&#10003;</span> <!-- Segno di spunta -->
                            </c:when>
                            <c:otherwise>
                                <span>&#10007;</span> <!-- Croce -->
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${camera.condizionatore}">
                                <span>&#10003;</span> <!-- Segno di spunta -->
                            </c:when>
                            <c:otherwise>
                                <span>&#10007;</span> <!-- Croce -->
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${camera.prezzo}/notte</td>
                </tr>
            </tbody>
        </table>
    </main>
    <jsp:include page="footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
</body>
</html>