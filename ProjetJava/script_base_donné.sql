CREATE DATABASE IF NOT EXISTS bibliotheque;
USE bibliotheque;

CREATE TABLE IF NOT EXISTS Livre (
    ISBN VARCHAR(20) PRIMARY KEY,
    Titre VARCHAR(255),
    Resume TEXT,
    DateEdition DATE
);

CREATE TABLE IF NOT EXISTS Auteur (
    idAuteur INT AUTO_INCREMENT PRIMARY KEY,
    nomAuteur VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Keyword (
    idKeyword INT AUTO_INCREMENT PRIMARY KEY,
    motCle VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS keyLivre (
    idkeyLivre INT AUTO_INCREMENT PRIMARY KEY,
    ISBN VARCHAR(20),
    idKeyword INT,
    FOREIGN KEY (ISBN) REFERENCES Livre(ISBN),
    FOREIGN KEY (idKeyword) REFERENCES Keyword(idKeyword)
);

CREATE TABLE IF NOT EXISTS AuteurLivre(
    idAuteurLivre INT AUTO_INCREMENT PRIMARY KEY,
    ISBN VARCHAR(20),
    idAuteur INT,
    FOREIGN KEY (ISBN) REFERENCES Livre(ISBN),
    FOREIGN KEY (idAuteur) REFERENCES Auteur(idAuteur)
);

INSERT INTO Keyword(motCle) VALUES ("Informatique");
INSERT INTO Keyword(motCle) VALUES ("Programmation");
INSERT INTO Keyword(motCle) VALUES ("Conception");
INSERT INTO Keyword(motCle) VALUES ("Sport");
INSERT INTO Keyword(motCle) VALUES ("Science");
INSERT INTO Keyword(motCle) VALUES ("Economie");
INSERT INTO Keyword(motCle) VALUES ("Politique");

CREATE TABLE EtatExemplaire (
    idEtat INT AUTO_INCREMENT PRIMARY KEY,
    Etat VARCHAR(100) NOT NULL
);

CREATE TABLE Exemplaire (
    NumeroInventaire VARCHAR(20) PRIMARY KEY,
    idEtat INT,
    ISBN VARCHAR(255),
    disponibility boolean default true,
    FOREIGN KEY (ISBN) REFERENCES Livre(ISBN),
    FOREIGN KEY (idEtat) REFERENCES EtatExemplaire(idEtat)
);

INSERT INTO EtatExemplaire (Etat) VALUES 
('Neuf'),
('Bon état'),
('Partiellement abimé'),
('Très abimé'),
('En réparation');

CREATE TABLE Usager (
    CIN VARCHAR(255) PRIMARY KEY,
    Email VARCHAR(255),
    nom VARCHAR(255),
    Prenom VARCHAR(255),
    Telephone VARCHAR(255)
);

CREATE TABLE Enseignant (
    CIN VARCHAR(255) PRIMARY KEY,
    grade VARCHAR(255),
    FOREIGN KEY (CIN) REFERENCES Usager(CIN)
);

CREATE TABLE Etudiant (
    CNE VARCHAR(255) PRIMARY KEY,
    Address VARCHAR(255),
    CIN VARCHAR(255),
    FOREIGN KEY (CIN) REFERENCES Usager(CIN)
);

CREATE TABLE emprunte (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_emprunteur varchar(255),
    ISBN VARCHAR(20),
    NumeroInventaire VARCHAR(20),
    date_emprunt DATE,
    date_prevue_retour DATE,
	dateRelance DATE,
    dateRetoure DATE,
    FOREIGN KEY (ISBN) REFERENCES livre(ISBN) ON DELETE CASCADE,
    FOREIGN KEY (NumeroInventaire) REFERENCES exemplaire(NumeroInventaire) ON DELETE CASCADE
);

CREATE TABLE Bibliothecaire (
    BibliothecaireID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) UNIQUE NOT NULL,
    Pwd VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Nom VARCHAR(255) NOT NULL,
    Prenom VARCHAR(255) NOT NULL,
    Telephone VARCHAR(20)
);

CREATE TABLE Assistant (
    AssistantID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) UNIQUE NOT NULL,
	Pwd VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Nom VARCHAR(255) NOT NULL,
    Prenom VARCHAR(255) NOT NULL,
    Telephone VARCHAR(20)
);

CREATE VIEW VueEtudiants AS
SELECT 
    U.CIN, 
    U.Email, 
    U.nom, 
    U.Prenom, 
    U.Telephone, 
    Et.Address AS Adresse, 
    Et.CNE
FROM 
    Usager U
JOIN 
    Etudiant Et ON U.CIN = Et.CIN;
    
    
CREATE VIEW VueEnseignants AS
SELECT 
    U.CIN, 
    U.Email, 
    U.nom, 
    U.Prenom, 
    U.Telephone, 
    E.grade
FROM 
    Usager U
JOIN 
    Enseignant E ON U.CIN = E.CIN;