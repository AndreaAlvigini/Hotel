<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotel</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>

<body>
    <main class="container">
        <!-- Inclusione del file "header.jsp" -->
        <jsp:include page="header.jsp" />
        
        <!-- Visualizza i dati del cliente -->
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-left mb-4">
                        Dati del cliente
                    </h1>
                </div>
            </div>
        </div>
        
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h4>${cliente.nome} ${cliente.cognome}</h4>
                    <p>Email: ${cliente.email}</p>
                    <p>Telefono: ${cliente.telefono}</p>
                    <p>ID Carta: ${cliente.carta_id}</p>
                    
                </div>
            </div>
        </div>
        
        <!-- Inclusione del file "footer.jsp" -->
        <jsp:include page="footer.jsp" />
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
</body>

</html>
