-- Używamy postgresql, jesli nie macie - pobrac.
-- To w założeniu wykonacie tylko raz, skrypt budujący bazę danych znajdziecie w pliku createDBStructures.sql
-- To wykonujemy sobie przez inteliJ żeby stworzyć sobie lokalnie baze danych i użytkownika.
DROP DATABASE if exists chatDB;
DROP USER if exists zzpjadmin;
CREATE USER zzpjadmin with password 'password';
CREATE DATABASE chatDB with owner zzpjadmin;