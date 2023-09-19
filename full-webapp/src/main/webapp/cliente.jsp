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
        <jsp:include page="header.jsp" />
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-left mb-4">
                        Dettaglio Cliente
                    </h1>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center align-items-center">
            <div class="card">
                <h4 class="card-item><i class="fa-solid fa-user mr-2"></i></h4>
                <h4 class="card-text fs-4">Nome ${cliente.nome}</h4>
                <h4 class="card-text fs-4">Cognome ${cliente.cognome}</h4>
                <p class="card-text fs-4">Id carta: ${cliente.carta_id}</p>
                <p class="card-text fs-4">Email: ${cliente.email}</p>
                <p class="card-text fs-4">Telefono: ${cliente.telefono}</p>
                <a href="" class="btn btn-success">Modifica</a>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
</body>

</html>
