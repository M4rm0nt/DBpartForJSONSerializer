```sql
-- Erstellen der Tabellen
CREATE TABLE lebewesen (
    id SERIAL PRIMARY KEY,
    art VARCHAR(255)
);

CREATE TABLE mensch (
    lebewesen_id INT PRIMARY KEY REFERENCES lebewesen(id),
    alter INT,
    name VARCHAR(255),
    geburtstag TIMESTAMP,
    heute_tag TIMESTAMP,
    geschlecht VARCHAR(255)
) INHERITS (lebewesen);

CREATE TABLE hobbies (
    id SERIAL PRIMARY KEY,
    mensch_id INT REFERENCES mensch(lebewesen_id),
    hobby VARCHAR(255)
);

CREATE TABLE haustier_kategorien (
    id SERIAL PRIMARY KEY,
    mensch_id INT REFERENCES mensch(lebewesen_id),
    kategorie VARCHAR(255)
);

CREATE TABLE haustiere (
    kategorie_id INT REFERENCES haustier_kategorien(id),
    name VARCHAR(255)
);

-- Löschen der Tabellen, falls sie bereits existieren
DROP TABLE IF EXISTS haustiere;
DROP TABLE IF EXISTS haustier_kategorien;
DROP TABLE IF EXISTS hobbies;
DROP TABLE IF EXISTS mensch;
DROP TABLE IF EXISTS lebewesen;

-- Transaktion für person1 (Alice)

BEGIN;

-- Insert für Lebewesen und Mensch für Alice
INSERT INTO lebewesen (id, art) VALUES (1, 'Homo sapiens');
INSERT INTO mensch (lebewesen_id, alter, name, geburtstag, heute_tag, geschlecht) VALUES (1, 43, 'Alice', '1980-05-15 00:00:00', CURRENT_TIMESTAMP, 'weiblich');

-- Hobbies für Alice
INSERT INTO hobbies (mensch_id, hobby) VALUES (1, 'Schreiben'), (1, 'Singen');

-- Haustiere für Alice
INSERT INTO haustier_kategorien (id, mensch_id, kategorie) VALUES (1, 1, 'Katzen'), (2, 1, 'Hunde');
INSERT INTO haustiere (kategorie_id, name) VALUES (1, 'Marla'), (1, 'Anton'), (2, 'Melli'), (2, 'Sven');

COMMIT;

-- Transaktion für person2 (Bob)

BEGIN;

-- Insert für Lebewesen und Mensch für Bob
INSERT INTO lebewesen (id, art) VALUES (2, 'Homo sapiens');
INSERT INTO mensch (lebewesen_id, alter, name, geburtstag, heute_tag, geschlecht) VALUES (2, 23, 'Bob', '2000-05-15 00:00:00', CURRENT_TIMESTAMP, 'weiblich');

-- Hobbies für Bob
INSERT INTO hobbies (mensch_id, hobby) VALUES (2, 'Karate'), (2, 'Tanzen');

-- Haustiere für Bob
INSERT INTO haustier_kategorien (id, mensch_id, kategorie) VALUES (3, 2, 'Pferde'), (4, 2, 'Hunde');
INSERT INTO haustiere (kategorie_id, name) VALUES (3, 'Kahn'), (3, 'Lilly'), (4, 'Timo'), (4, 'Lullu');

COMMIT;

-- Transaktion für person3 (Cedrik)

BEGIN;

-- Insert für Lebewesen und Mensch für Cedrik
INSERT INTO lebewesen (id, art) VALUES (3, 'Homo sapiens');
INSERT INTO mensch (lebewesen_id, alter, name, geburtstag, heute_tag, geschlecht) VALUES (3, 33, 'Cedrik', '1990-05-15 00:00:00', CURRENT_TIMESTAMP, 'weiblich');

-- Hobbies für Cedrik
INSERT INTO hobbies (mensch_id, hobby) VALUES (3, 'Lesen'), (3, 'Schwimmen');

-- Haustiere für Cedrik
INSERT INTO haustier_kategorien (id, mensch_id, kategorie) VALUES (5, 3, 'Katzen'), (6, 3, 'Hunde');
INSERT INTO haustiere (kategorie_id, name) VALUES (5, 'Mila'), (5, 'Chris'), (6, 'Max'), (6, 'Moritz');

COMMIT;
