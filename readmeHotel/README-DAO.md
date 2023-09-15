# MODIFICHE E AGGIORNAMENTI DEL DATABASE
## PRODOTTO, CLIENTE E ACQUISTO
> Come prima cosa abbiamo aggiornato le classi Prodotto, Cliente e Acquisto con le variabili di istanza che ci sembrava necessario aggiungere o modificare.
- Nel file "Prodotto.java" abbiamo aggiunto:
```
    private String descrizione;
    private String immagine;
```
- Nel file "Cliente.java" abbiamo aggiunto:
```
    private int punti;
    private String indirizzo;
    private String telefono;
```
- Nel file "Acquisto.java" abbiamo aggiunto:
```
    private Double tot;
```
> Una volta dichiarate le nuove variabili le abbiamo aggiunte all'interno dei costruttori e abbiamo creato anche i loro getter e setter. Ad esempio per "Acquisto" le modifiche sono state: 
```
    // Costruttore con parametri
    public Acquisto(int id, int idProdotto, int idCliente, Date dataAcquisto, Double tot) {
        this.id = id;
        this.idProdotto = idProdotto;
        this.idCliente = idCliente;
        this.dataAcquisto = dataAcquisto;
        // Agiunto costruttore con parametri per totale
        this.tot= tot;
    }

    //aggiunto getter e setter per totale
    public Double getTot() {
        return tot;
    }

    public void setTotale(Double tot) {
        this.tot = tot;
    }
```

## DATABASE
> A questo punto ci siamo dedicati alle modifiche vere e proprie sul database:
- Abbiamo creato quindi un database con 3 tabelle e abbiamo inserito al suo interno dei dummy datas eseguendo i seguenti comandi con sqlite3:
   - ```
        sqlite3 mydatabase.db
        
        CREATE TABLE IF NOT EXISTS clienti (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        indirizzo TEXT NOT NULL,
        email TEXT NOT NULL,
        telefono TEXT NOT NULL,
        punti INTEGER
        );

        CREATE TABLE IF NOT EXISTS prodotti (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        descrizione TEXT NOT NULL,
        prezzo REAL NOT NULL,
        immagine TEXT
        );

        CREATE TABLE IF NOT EXISTS acquisto (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        data DATE NOT NULL,
        id_prodotto INTEGER NOT NULL,
        id_cliente INTEGER NOT NULL,
        totale REAL NOT NULL,
        FOREIGN KEY (id_prodotto) REFERENCES prodotti(id),
        FOREIGN KEY (id_cliente) REFERENCES clienti(id)
        );
        ```
    - ```
        INSERT INTO clienti (nome, indirizzo, email, telefono, punti)VALUES
        ('Mario Rossi', 'Via Roma 123', 'mario@email.com', '555-1234', 100),
        ('Luigi Bianchi', 'Via Milano 456', 'luigi@email.com', '555-5678', 75),
        ('Giovanna Verdi', 'Via Napoli 789', 'giovanna@email.com', '555-9876', 150);

        INSERT INTO prodotti (nome, descrizione, prezzo, immagine) VALUES
        ('Prodotto 1', 'Descrizione prodotto 1', 10.99, 'immagine1.jpg'),
        ('Prodotto 2', 'Descrizione prodotto 2', 19.99, 'immagine2.jpg'),
        ('Prodotto 3', 'Descrizione prodotto 3', 7.49, 'immagine3.jpg')

        INSERT INTO acquisto (data, id_prodotto, id_cliente, totale)VALUES
        ('2023-09-08', 1, 1, 10.99),
        ('2023-09-08', 2, 2, 19.99),
        ('2023-09-07', 3, 3, 7.49);
        ```

## FILE 'DAO'
> Le ultime modifiche rimaste sono quelle apportate ai file 'DAO'. In tutte le classi abbiamo integrato all'interno dei metodi e delle query già esistenti le nuove variabili di "Prodotto", "Cliente" e "Acquisto".
- Esempio:
  ```
    public void insertProdotto(Prodotto p) { //metodo di inserimento che prende in input un Prodotto (p)
        //inserisco all'interno della query anche descrizione e immagine
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO prodotti (nome, descrizione, prezzo, immagine) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, p.getNome()); //riporto i valori nella riga della tabella prodotti
            stmt.setString(2, p.getDescrizione()); //DESCRIZIONE
            stmt.setDouble(3, p.getPrezzo());
            stmt.setString(4, p.getImmagine()); //IMMAGINE
            stmt.executeUpdate();
        }catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }
  ```

> Inoltre nella classe "Prodotto" abbiamo aggiunto un nuovo metodo:
  ```
    public void cancellaProdotto(int id){ //metodo per cancellare un prodotto che prende in input l'id
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM prodotti WHERE id = ?")){
            stmt.setInt(1, id); // Cancella il prodotto in base all'id
            stmt.executeUpdate();
        }catch (SQLException e) {
            // gestisci l'eccezione
            e.printStackTrace();
        }
    }
