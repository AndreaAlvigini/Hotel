sqlite3 mydatabase.db
        
        CREATE TABLE IF NOT EXISTS clienti (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        carta_id TEXT NOT NULL,
        email TEXT NOT NULL,
        telefono TEXT NOT NULL,

        );

        CREATE TABLE IF NOT EXISTS camere (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        tipologia TEXT NOT NULL,
        descrizione TEXT NOT NULL,
        prezzo REAL NOT NULL,
        immagine TEXT,
        disponibilita INTEGER NOT NULL
        );

        CREATE TABLE IF NOT EXISTS prenotazioni (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        id_cliente INTEGER NOT NULL,
        id_camera INTEGER NOT NULL,
        notti INTEGER NOT NULL,
        check_in DATE NOT NULL,
        check_out DATE NOT NULL,
        totale REAL NOT NULL,
        FOREIGN KEY (id_camera) REFERENCES camere(id),
        FOREIGN KEY (id_cliente) REFERENCES clienti(id)
        );
        ```
    - ```
        INSERT INTO clienti (nome, carta_id, email, telefono)VALUES
        ('Mario Rossi', 'AX9283F', 'mario@email.com', '555-1234'),
        ('Luigi Bianchi', 'GE63820F', 'luigi@email.com', '555-5678'),
        ('Giovanna Verdi', 'UH27846G', 'giovanna@email.com', '555-9876');

        INSERT INTO CAMERE (tipologia, descrizione, prezzo, immagine, disponibilita) VALUES
        ('Doppia', 'Descrizione doppia', 80.99, 'immagine1.jpg', 1),
        ('Suite', 'Descrizione suite', 190.99, 'immagine2.jpg', 0),
        ('Singola', 'Descrizione singola', 70.49, 'immagine3.jpg', 1)

        INSERT INTO checkin (data, id_cliente, id_camera, notti, totale)VALUES
        ('2023-09-08', 1, 1, 4, 10.99),
        ('2023-09-28', 2, 1, 2, 19.99),
        ('2023-12-07', 3, 3, 3, );
        ```