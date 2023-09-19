
        CREATE TABLE IF NOT EXISTS clienti (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        cognome TEXT NOT NULL,
        carta_id TEXT NOT NULL,
        email TEXT NOT NULL,
        telefono TEXT NOT NULL

        );

        CREATE TABLE IF NOT EXISTS camere (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        tipologia TEXT NOT NULL,
        descrizione TEXT NOT NULL,
        prezzo REAL NOT NULL,
        immagine TEXT,
        bagno BOOLEAN DEFAULT TRUE,
        condizionatore BOOLEAN DEFAULT TRUE,
        disponibilita BOOLEAN DEFAULT TRUE
        );

        CREATE TABLE IF NOT EXISTS prenotazioni (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        id_cliente INTEGER NOT NULL,
        id_camera INTEGER NOT NULL,
        check_in DATE NOT NULL,
        check_out DATE NOT NULL,
        notti INTEGER NOT NULL,
        totale REAL NOT NULL,
        FOREIGN KEY (id_camera) REFERENCES camere(id),
        FOREIGN KEY (id_cliente) REFERENCES clienti(id)
        );

        INSERT INTO clienti (nome, cognome, carta_id, email, telefono)VALUES
        ('Mario', 'Rossi', 'AX9283F', 'mario@email.com', '555-1234'),
        ('Luigi', 'Bianchi', 'GE63820F', 'luigi@email.com', '555-5678'),
        ('Giovanna', 'Verdi', 'UH27846G', 'giovanna@email.com', '555-9876');

        INSERT INTO camere (tipologia, descrizione, prezzo, immagine, bagno, condizionatore, disponibilita) VALUES
        ('Doppia', 'Descrizione doppia', 80.99, 'MainArticle.jpg', TRUE, FALSE, TRUE ),
        ('Suite', 'Descrizione suite', 190.99, 'MainArticle.jpg', TRUE, TRUE, FALSE ),
        ('Singola', 'Descrizione singola', 70.49, 'MainArticle.jpg', FALSE, FALSE, FALSE );

        INSERT INTO prenotazioni (id_cliente, id_camera, check_in, check_out, notti, totale)VALUES
        (1, 2, '2023-09-08 10:30:45.123', '2023-09-12 10:30:45.123', 4, 763.96),
        (3, 1, '2023-09-28 10:30:45.123', '2023-09-30 10:30:45.123', 2, 161.98),
        (2, 3, '2023-12-07 10:30:45.123', '2023-12-11 10:30:45.123', 4, 281.96);