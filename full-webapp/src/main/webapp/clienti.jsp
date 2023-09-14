<!-- dichiarazione indispensabile per poter utilizzare i tag JSTL JavaServer Pages Standard Tag Library all'interno del file JSP -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
</head>
<body>
    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Id Carta</th>
            <th>Email</th>
            <th>Telefono</th>
        </tr>
        <c:forEach var="cliente" items="${clienti}">
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.cognome}</td>
                <td>${cliente.carta_id}</td>
                <td>${cliente.email}</td>
                <td>${cliente.telefono}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
