<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" crossorigin="anonymous">
</head>

<body>
    <jsp:include page="header.jsp" />

    <main class="container">
        <form action="${pageContext.request.contextPath}/PrenotazioneNuovaServlet" method="POST">
            <div>
                <label for="camera_id">Scegli la stanza:</label>
                <select name="camera_id" id="camera_id">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>

            <div>
                <label for="checkInDate">Check-in</label>
                <input type="date" id="checkInDate" name="checkInDate" />
            </div>

            <div>
                <label for="checkOutDate">Check-out</label>
                <input type="date" id="checkOutDate" name="checkOutDate" />
            </div>

            <div>
                <label for="cliente_carta_id">Carta identità cliente</label>
                <input type="text" name="cliente_carta_id" id="cliente_carta_id">
            </div>

            <div>
                <label for="cliente_nome">Nome cliente</label>
                <input type="text" name="cliente_nome" id="cliente_nome">
            </div>

            <div>
                <label for="cliente_cognome">Cognome cliente</label>
                <input type="text" name="cliente_cognome" id="cliente_cognome">
            </div>

            <div>
                <label for="cliente_email">Email cliente</label>
                <input type="email" name="cliente_email" id="cliente_email">
            </div>

            <div>
                <label for="cliente_telefono">Telefono cliente</label>
                <input type="text" name="cliente_telefono" id="cliente_telefono">
            </div>

            <input type="submit" value="Invia">
        </form>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>

</html>