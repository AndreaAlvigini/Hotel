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
            <main class="container">
                <div class="grid-container">
                    <c:forEach var="camera" items="${camere}">
                        
                            <div class="card grid-item">
                                <img src="./assets/foto_camere/${camera.immagine}"
                                    alt="foto della camera numero ${camera.id}" class="card-img-top">
                                <div class="card-body">
                                    <h4 class="card-title">${camera.id} - ${camera.tipologia}</h4>
                                    <p class="card-text">${camera.prezzo} € per notte</p>
                                </div>
                            </div>
                        
                    </c:forEach>
                </div>
                <div class="grid text-center">
                    <div class="g-col-4">.g-col-4</div>
                    <div class="g-col-4">.g-col-4</div>
                    <div class="g-col-4">.g-col-4</div>
                </div>
            </main>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>

        </html>