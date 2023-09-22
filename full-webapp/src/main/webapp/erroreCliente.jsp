<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!DOCTYPE html>
        <html lang="it">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Aggiungi Cliente</title>
            <!-- Link to Bootstrap CSS -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <!-- Link to custom CSS -->
            <link rel="stylesheet" href="./css/style.css">
            <!-- Link to FontAwesome -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
                integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
                crossorigin="anonymous" referrerpolicy="no-referrer" />
        </head>

        <body>
            <!-- visualizzazione dei dati del cliente -->
            <jsp:include page="header.jsp" />
            <main class="container">
                <div class="card mt-4">
                    <div class="card-header">
                        <h1 class="text-left mb-4 fw-bold">
                            <i class="fa-solid fa-id-card"></i> Il cliente esiste già!
                        </h1>
                    </div>
                    <div class="card-body">
                        <h5>La carta d'identità che hai provato ad inserire è già associata ad un altro cliente,
                            controlla meglio</h5>
                        <a href="/clienti" class="btn btn-outline-primary mt-2">
                            Torna alla lista clienti <i class="fas fa-chevron-circle-right"></i>
                        </a>
                    </div>
                </div>
            </main>
            <!-- Inclusione il footer -->
            <jsp:include page="footer.jsp" />
            <!-- Script for Bootstrap -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>
        </html>