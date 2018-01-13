use master;
DROP DATABASE IF EXISTS NetflixStatistix;
CREATE DATABASE NetflixStatistix;
GO
USE NetflixStatistix;



-- Parent is PROGRAMMA
DROP TABLE IF EXISTS PROGRAMMA;
CREATE TABLE PROGRAMMA (
    ProgrammaId int NOT NULL PRIMARY KEY
);

INSERT INTO PROGRAMMA(ProgrammaId) VALUES
    (1001),(1002),(1003),(1004),(1005),(1006),(1007),(1008),
    (1009),(1010),(2000),(2001),(2002),(2003),(2004),(2005),
    (2006),(2007),(2008),(2009),(2010),(2011),(2012),(2013),
    (2014),(2015),(2016),(2017),(2018),(2019),(3001),(3002),
    (3003),(3004),(3005),(3006),(3007),(3008),(3009),(3010),
    (3101),(3102),(3103),(3105),(3104),(3106),(3107),(3108),
    (3109),(3110),(8001),(8002),(8004),(8008),(8010),(8011),
    (8012),(8014),(8016),(8017);



-- FK leidt af van de parent
DROP TABLE IF EXISTS FILM;
CREATE TABLE FILM (
    Id int NOT NULL PRIMARY KEY,
    Titel varchar(50) NOT NULL,
    Leeftijdsindicatie varchar (50) NOT NULL,
    Taal varchar(50) NOT NULL,
    Tijdsduur varchar(8) NOT NULL,
    Genre varchar(50) NOT NULL,
    ProgrammaId int,

    CONSTRAINT FILM_FK FOREIGN KEY (ProgrammaId) REFERENCES PROGRAMMA (ProgrammaId)
        ON UPDATE NO ACTION 
        ON DELETE CASCADE
);

INSERT INTO FILM (Id, Titel, Leeftijdsindicatie, Taal, Tijdsduur, Genre) VALUES
    (1010, 'The Abominable Bride', '12', 'Engels', '01:29', 'Detective'),
    (8001, 'The Life of Brian', '12', 'Engels', '01:34', 'Humor'),
    (8002, 'Pulp Fiction', '16 jaar en ouder', 'Engels-Amerikaans', '02:34', 'Misdaad' ),
    (8004, 'Pruimebloesem', '18 jaar en ouder', 'Nederlands', '01:20', 'Erotiek' ),
    (8008, 'Reservoir Dogs', '16 jaar en ouder', 'Engels-Amerikaans', '01:39', 'Misdaad'),
    (8010, 'The Good, the Bad and the Ugly', '12 jaar en ouder', 'Engels-Amerikaans', '02:41', 'Western' ),
    (8011, 'Andy Warhol''s Dracula', '16 jaar en ouder', 'Engels-Amerikaans', '01:43', 'Humor'),
    (8012, 'Ober', '6 jaar en ouder', 'Nederlands', '01:37', 'Humor'),
    (8014, 'Der Untergang', '6 jaar en ouder', 'Duits', '02:58', 'Oorlog'),
    (8016, 'De helaasheid der dingen', '12 jaar en ouder', 'Vlaams', '01:48', 'Humor'),
    (8017, 'A Clockwork Orange', '16 jaar en ouder', 'Engels', '02:16', 'SF');



DROP TABLE IF EXISTS SERIE;
CREATE TABLE SERIE (
    Serie varchar(50) NOT NULL PRIMARY KEY,
    Seizoen varchar(50) NOT NULL,
    Leeftijd varchar (50) NOT NULL,
    Taal varchar(50) NOT NULL,
    Genre varchar(25) NOT NULL,
    Lijkteenbeetjeop varchar(50) NOT NULL
);

INSERT INTO SERIE (Serie, Seizoen, Leeftijd, Taal, Genre, Lijkteenbeetjeop) VALUES
    ('Sherlock', 'S01E01', '12 jaar en ouder', 'Engels', 'Detective', 'Fargo'),
    ('Breaking Bad', 'S01E01', '16 jaar en ouder', 'Engels-Amerikaans', 'Spanning', 'Fargo'),
    ('Fargo', 'S01E01', '16 jaar en ouder', 'Engels-Amerikaans', 'Spanning', 'Breaking Bad');



-- FK leidt af van de parent
DROP TABLE IF EXISTS AFLEVERING;
CREATE TABLE AFLEVERING (
    Id int NOT NULL PRIMARY KEY,
    Serie varchar(50) NOT NULL,
    Seizoen varchar (50) NOT NULL,
    Titel varchar(50) NOT NULL,
    Tijdsduur varchar(8) NOT NULL,
    ProgrammaId int,

    CONSTRAINT AFLEVERING_FK FOREIGN KEY (ProgrammaId) REFERENCES PROGRAMMA (ProgrammaId)
        ON UPDATE NO ACTION 
        ON DELETE CASCADE
);

INSERT INTO AFLEVERING (Id, Serie, Seizoen, Titel, Tijdsduur) VALUES
    (1001, 'Sherlock', 'S01E01', 'A Study in Pink', '01:28'), 
    (1002, 'Sherlock', 'S01E02', 'The Blind Banker', '01:28'), 
    (1003, 'Sherlock', 'S01E03', 'The Great Game', '01:28'), 
    (1004, 'Sherlock', 'S02E01', 'A Scandal in Belgravia', '01:28'), 
    (1005, 'Sherlock', 'S02E02', 'The Hounds of Baskerville', '01:28'), 
    (1006, 'Sherlock', 'S02E03', 'The Reichenbach Fall', '01:28'), 
    (1007, 'Sherlock', 'S03E01', 'The Empty Hearse', '01:28'), 
    (1008, 'Sherlock', 'S03E02', 'The Sign of Three', '01:28'), 
    (1009, 'Sherlock', 'S03E03', 'His Last Vow', '01:28'), 
    (2000, 'Breaking Bad', 'S01E01', 'Pilot', '00:58'), 
    (2001, 'Breaking Bad', 'S01E02', 'Cat''s in the Bag…', '00:48'), 
    (2002, 'Breaking Bad', 'S01E03', '…And the Bag’s in the River', '00:48'), 
    (2003, 'Breaking Bad', 'S01E04', 'Cancer Man', '00:48'), 
    (2004, 'Breaking Bad', 'S01E05', 'Gray Matter', '00:48'), 
    (2005, 'Breaking Bad', 'S01E06', 'Crazy Handful of Nothin''', '00:48'), 
    (2006, 'Breaking Bad', 'S01E07', 'A No-Rough-Stuff-Type Deal', '00:48'), 
    (2007, 'Breaking Bad', 'S02E01', 'Seven Thirty-Seven', '00:48'), 
    (2008, 'Breaking Bad', 'S02E02', 'Grilled', '00:48'), 
    (2009, 'Breaking Bad', 'S02E03', 'Bit by a Dead Bee', '00:48'), 
    (2010, 'Breaking Bad', 'S02E04', 'Down', '00:48'), 
    (2011, 'Breaking Bad', 'S02E05', 'Breakage', '00:48'), 
    (2012, 'Breaking Bad', 'S02E06', 'Peekaboo', '00:48'), 
    (2013, 'Breaking Bad', 'S02E07', 'Negro Y Azul', '00:48'), 
    (2014, 'Breaking Bad', 'S02E08', 'Better Call Saul', '00:48'), 
    (2015, 'Breaking Bad', 'S02E09', '4 Days Out', '00:48'), 
    (2016, 'Breaking Bad', 'S02E10', 'Over', '00:48'), 
    (2017, 'Breaking Bad', 'S02E11', 'Mandala', '00:48'), 
    (2018, 'Breaking Bad', 'S02E12', 'Phoenix', '00:48'), 
    (2019, 'Breaking Bad', 'S02E13', 'ABQ', '00:48'), 
    (3001, 'Fargo', 'S01E01', 'The Crocodile''s Dilemma', '01:08'), 
    (3002, 'Fargo', 'S01E02', 'The Rooster Prince', '01:08'), 
    (3003, 'Fargo', 'S01E03', 'A Muddy Road', '01:08'), 
    (3004, 'Fargo', 'S01E04', 'Eating the Blame', '01:08'), 
    (3005, 'Fargo', 'S01E05', 'The Six Ungraspables', '01:08'), 
    (3006, 'Fargo', 'S01E06', 'Buridan''s Ass', '01:08'), 
    (3007, 'Fargo', 'S01E07', 'Who Shaves the Barber?', '01:08'), 
    (3008, 'Fargo', 'S01E08', 'The Heap', '01:08'), 
    (3009, 'Fargo', 'S01E09', 'A Fox, a Rabbit, and a Cabbage', '01:08'), 
    (3010, 'Fargo', 'S01E10', 'Morton''s Fork', '01:08)'), 
    (3101, 'Fargo', 'S02E01', 'Waiting for Dutch', '01:08'), 
    (3102, 'Fargo', 'S02E02', 'Before the Law', '01:08'), 
    (3103, 'Fargo', 'S02E03', 'The Myth of Sisyphus', '01:08'), 
    (3105, 'Fargo', 'S02E04', 'The Gift of the Magi', '01:08'), 
    (3104, 'Fargo', 'S02E05', 'Fear and Trembling', '01:08'), 
    (3106, 'Fargo', 'S02E06', 'Rhinoceros', '01:08'), 
    (3107, 'Fargo', 'S02E07', 'Did you do this? No, you did it!', '01:08'), 
    (3108, 'Fargo', 'S02E08', 'Loplop', '01:08'), 
    (3109, 'Fargo', 'S02E09', 'The Castle', '01:08'), 
    (3110, 'Fargo', 'S02E10', 'Palindrome', '01:08');



-- Parent is ACCOUNT
DROP TABLE IF EXISTS ACCOUNT;
CREATE TABLE ACCOUNT (
    Abonneenummer int NOT NULL PRIMARY KEY,
    Naam nvarchar (50) NOT NULL,
    Straat nvarchar (50) NOT NULL,
    Postcode nchar (7) NOT NULL,
    Huisnummer nvarchar (6) NOT NULL,
    Plaats nvarchar (50) NOT NULL
);

INSERT INTO ACCOUNT(Abonneenummer, Naam, Straat, Postcode, Huisnummer, Plaats) VALUES
    (1215426, 'Fam. van Raalte', 'Schopenhauerdijkje', '3991ML', '5', 'Houten'),
    (5602533, 'J. van Betlehem', 'Nietzschestraat', '8542BE', '99', 'Breda'),
    (5285824, 'F. de Kat', 'Kantlaan', '8542CD', '11', 'Breda');



-- FK leidt af van de parent
DROP TABLE IF EXISTS PROFIEL;
CREATE TABLE PROFIEL (
    Abonneenummer int NOT NULL,
    Profielnaam nvarchar (50) NOT NULL PRIMARY KEY,
    Geboortedatum date NOT NULL,

    CONSTRAINT PROFIEL_FK FOREIGN KEY (Abonneenummer) REFERENCES ACCOUNT(Abonneenummer)
        ON UPDATE NO ACTION 
        ON DELETE CASCADE
);

INSERT INTO PROFIEL(Abonneenummer, Profielnaam, Geboortedatum) VALUES
    (1215426, 'Frank', '1968-01-25'),
    (1215426, 'Madelief', '2001-08-19'),
    (5602533, 'Petrus', '1999-06-26'),
    (5602533, 'Paulus', '1999-06-26'),
    (5285824, 'Fritz', '1968-08-19'),
    (5285824, 'Diana', '1988-12-25');



-- FK leidt af van de parent
DROP TABLE IF EXISTS BEKEKEN;
CREATE TABLE BEKEKEN (
    Abonneenummer int NOT NULL,
    Profielnaam varchar (50) NOT NULL,
    Gezien int NOT NULL,
    Percentage int NOT NULL CHECK (Percentage <= 100),

    CONSTRAINT PK_BEKEKEN PRIMARY KEY (Profielnaam, Gezien),
    CONSTRAINT BEKEKEN_FK FOREIGN KEY (Abonneenummer) REFERENCES ACCOUNT (Abonneenummer)
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

INSERT INTO BEKEKEN (Abonneenummer, Profielnaam, Gezien, Percentage) VALUES
    (1215426, 'Frank', '1001', 100),
    (1215426, 'Frank', '1002', 100),
    (1215426, 'Frank', '1003', 78),
    (1215426, 'Madelief', '1001', 100),
    (1215426, 'Madelief', '1021', 60),
    (1215426, 'Madelief', '3001', 91),
    (1215426, 'Madelief', '2001', 100),
    (1215426, 'Madelief', '2002', 100),
    (1215426, 'Madelief', '2003', 100),
    (1215426, 'Madelief', '2004', 100),
    (5602533, 'Petrus', '3001', 100),
    (5602533, 'Petrus', '3002', 100),
    (5602533, 'Petrus', '3010', 60),
    (5602533, 'Petrus', '8001', 100),
    (5602533, 'Petrus', '8002', 99),
    (5602533, 'Paulus', '3001', 100),
    (5602533, 'Paulus', '3002', 74),
    (5602533, 'Paulus', '3010', 60),
    (5602533, 'Paulus', '8001', 100),
    (5602533, 'Paulus', '2019', 10),
    (5285824, 'Fritz', '1001', 100),
    (5285824, 'Fritz', '1002', 100),
    (5285824, 'Fritz', '1010', 5),
    (5285824, 'Diana', '8002', 100),
    (5285824, 'Diana', '1001', 45);