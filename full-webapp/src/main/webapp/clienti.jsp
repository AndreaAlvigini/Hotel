<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotel</title>
    <!-- Link al file CSS di Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" crossorigin="anonymous">
    <!-- Link al file CSS personalizzato -->
    <link rel="stylesheet" href="./css/style.css">
    <!-- Link al file CSS di FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
    <main class="container">
        <!-- Inclusione del file "header.jsp" -->
        <jsp:include page="header.jsp" />
        
        <!-- Titolo con icona FontAwesome -->
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-left mb-4">
                        <i class="fas fa-users mr-2"></i>
                        Tutti i clienti
                    </h1>
                </div>
            </div>
        </div>
        
        <!-- Contenuto della pagina -->
        <div class="grid-container">
            <c:forEach var="cliente" items="${clienti}">
                <div class="card grid-item">
                    <div class="card-body">
                        <a href="cliente.jsp?id=${cliente.id}" class="text-decoration-none"> <!-- Rimuove la sottolineatura dai link -->
                            <h4 class="card-title fs-3">${cliente.nome} ${cliente.cognome}</h4>
                        </a>
                        <p class="card-text fs-6">${cliente.email}</p>
                        <p class="card-text fs-6">${cliente.telefono}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        
        <!-- Inclusione del file "footer.jsp" -->
        <jsp:include page="footer.jsp" />
    </main>
    
    <!-- Script di Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
