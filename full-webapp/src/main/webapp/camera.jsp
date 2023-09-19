<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
        <!DOCTYPE html>
        <html lang="it">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
                crossorigin="anonymous">
            <link rel="stylesheet" href="./css/style.css">
        </head>

<body>

    <main class="container">
                
        <jsp:include page="header.jsp" />
        <h1 style="text-align: center;">Dettagli della camera</h1>

        <div class="grid-container">
                <p>ID: ${camera.id}</p>
                <p>Tipologia: ${camera.tipologia}</p>
                <p>Descrizione: ${camera.descrizione}</p>
                <p>Bagno: ${camera.bagno}</p>
                <p>Condizionatore: ${camera.condizionatore}</p>
                <p>Prezzo: ${camera.prezzo} € per notte</p>
                <img src="./assets/foto_camere/${camera.immagine}" alt="foto della camera" class="card-img-top">
            
        </div>
    </main>
</body>
</html>