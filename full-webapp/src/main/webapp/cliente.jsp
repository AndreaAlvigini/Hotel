<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Hotel</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <link rel="stylesheet" href="./css/style.css">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        </head>

        <body>
            <main class="container">
                <jsp:include page="header.jsp" />
        
                <div class="cliente-dettaglio">
                    <h1>Id Cliente: ${cliente.id}</h1>
                    <h1>Telefono: ${cliente.telefono}</h1>
                    <h1>Email: ${cliente.email}</h1>
                    <h1>ID carta: ${cliente.carta_id}</h1>
                    <h1>Nome: ${cliente.nome}</h1>
                    <h1>Cognome: ${cliente.cognome}</h1>

                </div>
                
                <jsp:include page="footer.jsp" />
            </main>
            
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>

        </html>