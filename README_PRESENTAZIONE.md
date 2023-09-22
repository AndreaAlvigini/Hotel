# FARE TESTO PER BLOG
# GretaHotel
## Introduzione
GretaHotel è un'applicazione di database management pensata per la reception di un hotel che permette una facile gestione dei dati relativi a camere, clienti e prenotazioni.

## Struttura
- farlo sul main
Di seguito mostrimao la build di maven nella sua divisione in folders

full-webapp/
---src/
-------main/
-----------java/
----------------Cliente.java
----------------ClienteDAO.java
----------------ClienteServlet.java
----------------AggiungiClienteServlet.java
----------------DettaglioClienteServlet.java
----------------Camera.java
----------------CameraDAO.java
----------------CameraServlet.java
----------------Prenotazione.java
----------------PrenotazioneDAO.java
----------------PrenotazioneServlet.java
----------------PrenotazioneSingolaServlet.java
---resources/
---crea_database.sql
---webapp/
---------WEB-INF/
---------web.xml
---clienti.jsp
---cliente.jsp
---erroreCliente.jsp
---aggiungiCliente.jsp
---camera.jsp
---camere.jsp
---prenotazione.jsp
---prenotazioni.jsp

- Modello
Le classi modello ecc rappresentano l'entità del Cliente, Camera, Prenotazioni
- DAO
Le classi dao ecc CameraDAO, ClientiDAO, PrenotazioneDAO
- Servlet
La servlet clienti contiene la lista dei clienti non filtrata.
la servlet cliente contiene i detttagli del cliente selezionato
Le servlet relative a camere e prenotazioni seguono la stessa logica
- jsp
Jsp
- Funzionamento
SQLite un databse relazionale utilizzato per memorizzare i dati dell'applicazione, nel dettaglio i modelli modelli hanno il compito di mettere a disposizione i campi del database, le classi di tipo DAO contengono le istruzioni per manipolare i dati, le servlet gestiscono le connessioni e le interazioni nelle corrispettive pagine. i jsp riguardano la presentazione dei dati.

## Funzionalità
Cosa può fare una o un receptionist?
Elencare le azioni!

Quest'applicazione è stata progettata per essere utilizzata da una reception perciò contiene tutti gli strumenti per gestire camere, clienti e prenotazioni come specificato in funzionamento, con il minimo sforzo da parte dll'operatore.

Dotata di un'interfaccia semplice e pulita, l'applicazione è divisa in tre sezioni distinte, ciascuna dedicata alle operazioni da eseguire su una delle tre tipologie di dato di cui si occupa.

Questa suddivisione permette all'utente di navigare e accedere ai dati nella maniera più rapida e intuitiva possibile.

## Tecnologie utilizzate
Le tecnologie con cui è stata creata sono:
- Java: linguaggio di programmazione orientato agli oggetti
- SQLite: database relazionale
- Maven: strumento di gestione delle dipedenze e di build del progetto
- Java Server Page: framework frontend per lo sviluppo di applicazioni web in java
- Bootstrap: libreria css e script per lo styling delle pagine
- Fontawesome: libreria icone per migliorare interfaccia utente

## conclusioni
IL progetto offre solo la visualizzazione dei dati, ma in futuro può essere completato dall'inserimento e dalla rimozione direttamente dall'interfaccia