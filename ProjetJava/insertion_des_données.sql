USE bibliotheque;

-- Pour la table Bibliothecaire
INSERT INTO Bibliothecaire (Username, Pwd, Email, Nom, Prenom)
VALUES ('biblio', '123', 'biblio@example.com', 'NomBibliothecaire', 'PrenomBibliothecaire');

-- Pour la table Assistant
INSERT INTO Assistant (Username, Pwd, Email, Nom, Prenom)
VALUES ('assistant', '123', 'assistant@example.com', 'NomAssistant', 'PrenomAssistant');

-- Insertion des données pour les deux tables d'etudiant et enseignant
INSERT INTO Usager (CIN, Email, nom, Prenom, Telephone) VALUES 
('AA111', 'user1@library.com', 'Leroy', 'Guy', '0345678901'),
('BB222', 'user2@library.com', 'Roux', 'Helen', '0345678902'),
('CC333', 'user3@library.com', 'Lefevre', 'Ian', '0345678903'),
('DD444', 'etu1@uni.com', 'Doe', 'John', '0101010101'),
('EE555', 'etu2@uni.com', 'Doe', 'Jane', '0202020202'),
('FF666', 'etu3@uni.com', 'Smith', 'Alex', '0303030303');

INSERT INTO Enseignant (CIN, grade) VALUES 
('AA111', 'Professeur'),
('BB222', 'Maître de conférences'),
('CC333', 'Chargé de recherche');

INSERT INTO Etudiant (CIN, Address, CNE) VALUES 
('DD444', '123 Rue de Medv', 'E001'),
('EE555', '456 Avenue', 'E002'),
('FF666', '789 Boulevard des Étudiants', 'E003');

-- Insertion des données pour la table de livre
INSERT INTO Livre (ISBN, Titre, Resume, DateEdition) VALUES 
('978-0451524935', '1984', '1984 est un roman dystopique écrit par George Orwell.', '1949-06-08'),
('978-2070413119', 'Le Petit Prince', 'Le Petit Prince est un roman de l’écrivain et aviateur français Antoine de Saint-Exupéry.', '1943-04-06'),
  ("978-2207112345", "Algorithmes pour débutants", 
   "Ce livre est une introduction aux concepts fondamentaux de l'algorithmique...", "2023-01-10"),
  ("978-2123456789", "Le guide complet du HTML et CSS", 
   "Apprenez à créer des sites web modernes et responsives...", "2022-09-21"),
  ("978-2757812340", "L'économie pour tous", 
   "Comprendre les mécanismes économiques de façon simple et accessible...", "2021-03-15"),
  ("978-2894795678", "Les secrets du marathonien", 
   "Améliorez vos performances en course à pied grâce à ce guide complet...", "2020-05-12");

-- Insertion des données pour la table des auteurs
INSERT INTO Auteur (nomAuteur) VALUES 
('George Orwell'),
('Antoine de Saint-Exupéry'),
("Jean Dupont"),
("Marie Dubois"),
("Pierre Durand"),
("Anna Garcia");

-- Insertion des données pour la table keyLivre
INSERT INTO keyLivre (ISBN, idKeyword) VALUES 
('978-0451524935', 6),
('978-2070413119', 5);

INSERT INTO keyLivre (ISBN, idKeyword) VALUES
  ("978-2207112345", 1), 
  ("978-2207112345", 2);

INSERT INTO keyLivre (ISBN, idKeyword) VALUES
  ("978-2123456789", 3); 

INSERT INTO keyLivre (ISBN, idKeyword) VALUES
  ("978-2757812340", 6); 

INSERT INTO keyLivre (ISBN, idKeyword) VALUES
  ("978-2894795678", 4);

-- Insertion des données pour la table AuteurLivre
INSERT INTO AuteurLivre (ISBN, idAuteur) VALUES 
('978-0451524935', 1),
('978-2070413119', 2); 

INSERT INTO AuteurLivre (ISBN, idAuteur) VALUES
  ("978-2207112345", 1);

INSERT INTO AuteurLivre (ISBN, idAuteur) VALUES
  ("978-2123456789", 2);

INSERT INTO AuteurLivre (ISBN, idAuteur) VALUES
  ("978-2757812340", 3),
  ("978-2894795678", 4);

-- Insertion des données pour la table Exemplaire
INSERT INTO Exemplaire (NumeroInventaire, idEtat, ISBN, disponibility) VALUES 
('INV001', 1, '978-0451524935', true), 
('INV002', 2, '978-2070413119', true); 

INSERT INTO Exemplaire (NumeroInventaire, idEtat, ISBN, disponibility) VALUES
  ("EX1234", 1, "978-2207112345", true), 
  ("EX2345", 2, "978-2207112345", true); 

INSERT INTO Exemplaire (NumeroInventaire, idEtat, ISBN, disponibility) VALUES
  ("EX5678", 3, "978-2123456789", true);
  
INSERT INTO Exemplaire (NumeroInventaire, idEtat, ISBN, disponibility) VALUES
  ("EX7890", 1, "978-2757812340", true),
  ("EX9012", 2, "978-2894795678", true);

-- Exemples d'insertion pour des emprunts d'étudiants
INSERT INTO emprunte (id_emprunteur, ISBN, NumeroInventaire, date_emprunt, date_prevue_retour, dateRelance, dateRetoure)
VALUES ('DD444', '978-0451524935', 'INV001', '2024-02-31', '2024-03-07', '2024-03-09', NULL),
       ('EE555', '978-2070413119', 'INV002', '2024-03-31', '2024-04-07', NULL, NULL),
       ('FF666', '978-2207112345', 'EX1234', '2024-03-31', '2024-04-07', NULL, NULL);

-- Exemples d'insertion pour des emprunts d'enseignants
INSERT  INTO emprunte (id_emprunteur, ISBN, NumeroInventaire, date_emprunt, date_prevue_retour, dateRelance, dateRetoure)
VALUES ('AA111', '978-0451524935', 'INV001', '2024-01-31', '2024-02-14', NULL, NULL),
       ('BB222', '978-2070413119', 'INV002', '2024-01-31', '2024-02-14', '2024-02-16', NULL),
       ('CC333', '978-2207112345', 'EX1234', '2024-03-31', '2024-04-14', NULL, NULL);