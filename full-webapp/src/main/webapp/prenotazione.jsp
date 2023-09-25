<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prenotazione #${prenotazione.id}</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        crossorigin="anonymous">
</head>

<body>
    <jsp:include page="header.jsp" />
    
    <main class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2 border border-light-subtle rounded p-4">
                <div>
                    <div>
                        <h3>Prenotazione #${prenotazione.id}</h3>
                    </div>
                    <div
                        class="d-md-flex justify-content-between align-items-center border-bottom border-light-subtle pb-3">
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
                            <p class="mb-md-0">${prenotazione.cameraNumero} - ${prenotazione.cameraTipologia}</p>
                        </div>
                        <div>
                            <h6>Totale</h6>
                            <p class="mb-md-0">€ ${prenotazione.totale}</p>
                        </div>
                    </div>
                </div>

                <div>
                    <div class="mt-3">
                        <h6>Cliente</h6>
                        <p>${prenotazione.clienteNome} ${prenotazione.clienteCognome}</p>
                    </div>
                    <div class="mt-3">
                        <h6>Documento</h6>
                        <p>${prenotazione.clienteDocumento}</p>
                    </div>
                    <div class="mt-3">
                        <h6>Contatti</h6>
                        <p class="mb-0"><a
                                href="tel:${prenotazione.clienteTelefono}">${prenotazione.clienteTelefono}</a></p>
                        <p class="mb-sm-0"><a
                                href="mailto:${prenotazione.clienteEmail}">${prenotazione.clienteEmail}</a></p>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
</body>

</html>