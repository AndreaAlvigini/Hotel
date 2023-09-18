<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prenotazioni</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" crossorigin="anonymous">
</head>

<body>
    <jsp:include page="header.jsp" />

    <main class="container">
        <div>
            <h3 class="mb-4">Tutte le prenotazioni</h3>
        </div>
        <div>
            <c:forEach var="prenotazione" items="${prenotazioni}">
                <div class="border border-light-subtle rounded p-3 mb-2">
                    <div class="d-sm-flex justify-content-between">
                        <div class="mb-3">
                            <h6>N. prenotazione</h6>
                            <p class="mb-sm-0">#${prenotazione.id}</p>
                        </div>
                        <div>
                            <h6>Check in</h6>
                            <p class="mb-sm-0">${prenotazione.checkIn}</p>
                        </div>
                        <div>
                            <h6>Check out</h6>
                            <p class="mb-sm-0">${prenotazione.checkOut}</p>
                        </div>
                        <div class="text-sm-end">
                            <h6>Totale</h6>
                            <p class="mb-sm-0">€ ${prenotazione.totale}</p>
                        </div>
                    </div>

                    <div class="border-top border-light-subtle">
                        <div class="mt-3">
                            <h6>Cliente</h6>
                            <p>${prenotazione.idCliente}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>

    <jsp:include page="footer.jsp" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>

</html>