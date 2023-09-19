<h2>Inserisci/Modifica Cliente</h2>
    <form action="clienti" method="post">
        <input type="hidden" name="id" value="${cliente.id}">
        Nome: <input type="text" name="nome" value="${cliente.nome}"><br>
        Cognome: <input type="text" name="cognome" value="${cliente.cognome}"><br>
        Email: <input type="text" name="email" value="${cliente.email}"><br>
        <input type="submit" value="Salva">
    </form>