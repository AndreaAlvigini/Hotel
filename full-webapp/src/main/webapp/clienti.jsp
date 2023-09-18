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
    <!-- <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Id Carta</th>
            <th>Email</th>
            <th>Telefono</th>
        </tr>
        <c:forEach var="cliente" items="${clienti}">
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.cognome}</td>
                <td>${cliente.carta_id}</td>
                <td>${cliente.email}</td>
                <td>${cliente.telefono}</td>
            </tr>
        </c:forEach>
    </table> -->
    <main class="container">
        <jsp:include page="header.jsp" />

        <h1>Tutti i clienti</h1>
        <div class="grid-container">
            <c:forEach var="cliente" items="${clienti}">
                
                    <div class="card grid-item">
                        <div class="card-body">
                            <h4 class="card-title">${cliente.nome} ${cliente.cognome}</h4>
                            <p class="card-text fs-4">ID ${cliente.carta_id}</p>
                            <p class="card-text fs-4">${cliente.email}</p>
                            <p class="card-text fs-4">${cliente.telefono}</p>
                            <a href="#" class="btn btn-primary">Modifica</a>
                            <a href="#" class="btn btn-primary">Vai alla scheda</a>

                        </div>

                    </div>
            </c:forEach>
        </div>
        <jsp:include page="footer.jsp" />
    </main>
    <body>
        
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
    </body>
</body>
</html>
