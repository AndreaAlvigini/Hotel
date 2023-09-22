<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Clienti</title>
            <!-- Link al file CSS di Bootstrap -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <!-- Link al file CSS personalizzato -->
            <link rel="stylesheet" href="./css/style.css">
            <!-- Link al file CSS di FontAwesome -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        </head>

        <body>
            <!-- Intestazione -->
            <main class="container">
                <header>
                    <jsp:include page="header.jsp" />
                </header>
                <!-- Contenuto principale -->
                <div class="container mt-4">
                    <div class="d-flex justify-content-between align-items-center">
                        <h1 class="mb-0">
                            Lista Clienti
                        </h1>
                        <!-- <i class="fas fa-users fa-2x" ></i> -->
                        <a href="aggiungiCliente.jsp" class="btn btn-primary">
                            Aggiungi Cliente <i class="fas fa-user-plus ml-2"></i>
                        </a>
                    </div>
                    <hr>
                    <div class="row">
                        <c:forEach var="cliente" items="${clienti}">
                            <div class="col-md-4">
                                <div class="card mb-4">
                                    <div class="card-body">
                                        <h5 class="card-title">${cliente.nome}</h5>
                                        <h5 class="card-subtitle mb-2">${cliente.cognome}</h5>
                                        <a href="/clienti/${cliente.id}" class="btn btn-sm btn-outline-primary mt-2">
                                            Dettagli <i class="fas fa-chevron-circle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <!-- Footer -->
                <footer>
                    <jsp:include page="footer.jsp" />
                </footer>
            </main>
            <!-- Script di Bootstrap -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
        </body>

        </html>