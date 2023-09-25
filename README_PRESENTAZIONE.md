# GretaHotel
## Introduzione
GretaHotel è un'applicazione di database management pensata per la reception di un hotel che permette una facile gestione dei dati relativi a camere, clienti e prenotazioni.

## Struttura
Di seguito mostrimao delle cartelle del progetto:

full-webapp/
- src/
- - main/
- - - java/
- - - - Cliente.java
- - - - ClienteDAO.java
- - - - ClienteServlet.java
- - - - AggiungiClienteServlet.java
- - - - DettaglioClienteServlet.java
- - - - Camera.java
- - - - CameraDAO.java
- - - - CameraServlet.java
- - - - Prenotazione.java
- - - - PrenotazioneDAO.java
- - - - PrenotazioneServlet.java
- - - - PrenotazioneSingolaServlet.java
- resources/
- - crea_database.sql
- webapp/
- - clienti.jsp
- - cliente.jsp
- - erroreCliente.jsp
- - aggiungiCliente.jsp
- - camera.jsp
- - camere.jsp
- - prenotazione.jsp
- - prenotazioni.jsp
- - index.jsp
- - - assets
- - - - foto_camere
- - - WEB-INF/
- - - - web.xml
- - - css
- - - - style.css

L'architettura del progetto è suddivisa in tre parti:

### 1 Modello
I modelli hanno il compito di mettere a disposizione i campi del database
### 2 DAO
Le classi di tipo DAO contengono le istruzioni per manipolare i dati
### 3 Servlet
Le servlet gestiscono le connessioni e le interazioni nelle corrispettive pagine

Inoltre abbiamo utilizzato le seguenti tecnologie:
### JSP
I jsp riguardano la presentazione dei dati
### SQLite
SQLite è un database relazionale utilizzato per memorizzare i dati dell'applicazione

## Funzionalità
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

## Conclusioni
Il progetto offre solo una solida visualizzazione dei dati, ma in futuro può essere completato con la possibilità di una totale manipolazione dei dati direttamente dall'interfaccia.