# Projet Banque Java

## Description
Ce projet est une application Java qui simule la gestion de comptes bancaires et permet de gérer à la fois des comptes classiques et des comptes épargne.  
Il inclut également une connexion à une base de données PostgreSQL pour récupérer les comptes et les banques et affiche leurs informations dans la console.

Le projet couvre :
- Gestion des comptes bancaires (dépôt, retrait, calcul d’intérêts pour les comptes épargne)  
- Gestion des exceptions liées aux montants invalides  
- Connexion à une base PostgreSQL avec JDBC  

---

## Fonctionnalités

1. **Gestion des comptes**
   - Création de comptes avec identifiant, propriétaire et solde.
   - Dépôt et retrait d’argent avec validation des montants.
   - Affichage des informations du compte avec la méthode `toString()`.

2. **Comptes épargne**
   - Héritage de la classe `Compte`.
   - Gestion du taux d’intérêt.
   - Calcul automatique des intérêts avec la méthode `calculerInterets()`.

3. **Gestion des exceptions**
   - Classe `MontantNonValideException` pour gérer les cas de dépôt ou retrait invalide.

4. **Connexion à PostgreSQL**
   - Récupération des comptes et des banques depuis une base PostgreSQL via JDBC.
   - Affichage des listes de comptes et banques dans la console.

--

