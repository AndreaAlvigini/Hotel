<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prenotazioni</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        crossorigin="anonymous">
</head>

<body>
    <jsp:include page="header.jsp" />

    <main class="container">
        <div>
            <h3 class="mb-4">Tutte le prenotazioni</h3>
        </div>

        <div class="mb-3">
            <form action="/prenotazioni" method="GET">
                <div class="d-flex">
                    <div class="me-2">
                        <!-- <label for="tipologia-camera">Tipologia camera</label> -->
                        <select name="tipologia-camera" id="tipologia-camera" class="form-select">
                            <option selected disabled>Scegli il tipo di camera</option>
                            <option value="Suite">Suite</option>
                            <option value="Doppia">Doppia</option>
                        </select>
                    </div>
                    <div>
                        <select name="ordina-data-check-in" id="ordina-data-check-in" class="form-select">
                            <option selected disabled>Ordina per check-in</option>
                            <option value="check-in-recente">Più recente</option>
                            <option value="check-in-vecchio">Meno recente</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-dark ms-2">Filtra</button>
                </div>
            </form>
        </div>

        <div>
            <c:forEach var="prenotazione" items="${prenotazioni}">
                <div class="border border-light-subtle rounded p-3 mb-2">
                    <div class="d-md-flex justify-content-between align-items-center">
                        <div>
                            <h6>N. prenotazione</h6>
                            <p class="mb-md-0">#${prenotazione.id}</p>
                        </div>
                        <div>
                            <h6>Date</h6>
                            <p class="mb-md-0">${prenotazione.checkIn}/${prenotazione.checkOut}</p>
                        </div>
                        <div>
                            <h6>Camera</h6>
                            <p class="mb-md-0">${prenotazione.cameraNumero} - ${prenotazione.cameraTipologia}
                            </p>
                        </div>
                        <div>
                            <h6>Totale</h6>
                            <p class="mb-md-0">€ ${prenotazione.totale}</p>
                        </div>
                        <div>
                            <a href="/prenotazioni/${prenotazione.id}">Dettagli</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
</body>

</html>