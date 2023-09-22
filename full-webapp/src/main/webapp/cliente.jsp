<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Dettagli Cliente</title>
            <!-- link al foglio di stile di Bootstrap -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <!-- foglio di stile personalizzato -->
            <link rel="stylesheet" href="./css/style.css">
            <!-- foglio di stile per Font Awesome -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
                integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
                crossorigin="anonymous" referrerpolicy="no-referrer" />
        </head>

        <body>
            <!-- header -->
            <jsp:include page="header.jsp" />
            <main class="container">



                <!-- visualizzazione dei dati del cliente -->
                <div class="card mt-4">
                    <div class="card-header">
                        <h1 class="text-left mb-4">
                            <i class="fa-solid fa-user"></i>
                            Dettagli Cliente
                        </h1>
                    </div>
                    <div class="card-body">
                        <p class="card-title">Nome: <span class="fw-bolder">${cliente.nome}</span></p>
                        <p class="card-title">Cognome: <span class="fw-bolder">${cliente.cognome}</span></p>
                        <p class="card-text">Telefono: <span class="fw-bolder">${cliente.telefono}</span></p>
                        <p class="card-text">Email: <span class="fw-bolder">${cliente.email}</span></p>
                        <p class="card-text">ID Carta: <span class="fw-bolder">${cliente.carta_id}</span></p>
                        <p class="card-text">ID Cliente: <span class="fw-bolder">${cliente.id}</span></p>
                    </div>
                </div>


            </main>
            <!-- Inclusione il footer -->
            <jsp:include page="footer.jsp" />
            <!-- Aggiungi il file JavaScript di Bootstrap -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>

        </html>