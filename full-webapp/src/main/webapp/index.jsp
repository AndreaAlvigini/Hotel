<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greta Hotel - Homepage</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body style="background-image: url('${pageContext.request.contextPath}/assets/homepage.jpg'); background-size: cover; background-position: center center; background-repeat: no-repeat;" class="vh-100 overlay-container">
    <div class="overlay"></div>
    <main class="content">
        <header>
            <nav class="navbar navbar-expand-lg bg-body-tertiary pt-2 pb-2 mb-4">
                <div class="container">
                    <a href="/"><img src="${pageContext.request.contextPath}/assets/logo.svg" alt="Logo" class="me-5" style="width: 48px; height: auto;"></a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="/camere">Camere</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/clienti">Clienti</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/prenotazioni">Prenotazioni</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <div class="container text-center" style="margin-top: 160px;">
            <div class="row">
                <div class="col-8 offset-2">
                    <img src="${pageContext.request.contextPath}/assets/vertical_logo.svg" alt="Logo" style="width: 300px;" class="mb-5">
                    <div>
                        <a href="/clienti" class="btn btn-light">Entra</a>
                    </div>
                </div>
            </div>
            
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>

</html>