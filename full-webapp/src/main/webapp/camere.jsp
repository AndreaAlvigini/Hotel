<!-- dichiarazione indispensabile per poter utilizzare i tag JSTL JavaServer Pages Standard Tag Library all'interno del file JSP -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <link rel="stylesheet" href="./css/style.css">
        </head>

        <body>
            <jsp:include page="header.jsp" />
            <main class="container">
                <div class="container mt-4">
                    <div class="d-flex justify-content-between align-items-center">
                        <h1 class="mb-0">
                            Tutte le camere
                        </h1>
                    </div>
                    <hr>
                <div class="grid-container">
                    <c:forEach var="camera" items="${camere}">
                        
                            <div class="card grid-item mb-4">
                                <img src="./assets/foto_camere/${camera.immagine}"
                                    alt="foto della camera numero ${camera.id}" class="card-img-top">
                                <div class="card-body">
                                    <h4 class="card-title">${camera.id} - ${camera.tipologia}</h4>
                                    <p class="card-text">${camera.prezzo}/notte</p>
                                    <a href="/camere/${camera.id}" class="btn btn-primary">Vai alla scheda</a>
                                </div>
                            </div>
                        
                    </c:forEach>
                </div>
            </main>
            <jsp:include page="footer.jsp" />
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>

        </html>