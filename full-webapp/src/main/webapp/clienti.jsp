<!-- dichiarazione indispensabile per poter utilizzare i tag JSTL JavaServer Pages Standard Tag Library all'interno del file JSP -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
        <!DOCTYPE html>
        <html lang="it">

        <head>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Hotel</title>
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                    crossorigin="anonymous">
                <link rel="stylesheet" href="./css/style.css">
            </head>
        </head>
        <body>
            <main class="container">
                <jsp:include page="header.jsp" />
                <div class="container">
                    <div class="row">
                        <div class="col-12 text-center">
                            <h1>Tutti i clienti</h1>
                        </div>
                    </div>
                </div>
                <div class="grid-container">
                    <c:forEach var="cliente" items="${clienti}">
                        <div class="card grid-item">
                            <div class="card-body">
                                <h4 class="card-title">${cliente.nome} ${cliente.cognome}</h4>
                                <p class="card-text fs-5">Id carta: ${cliente.carta_id}</p>
                                <p class="card-text fs-4">${cliente.email}</p>
                                <p class="card-text fs-4">${cliente.telefono}</p>
                                <a href="#" class="btn btn-primary">Modifica</a>
                                <a href="#" class="btn btn-primary">Elimina</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <jsp:include page="footer.jsp" />
            </main>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        </body>
        </body>
        </html>