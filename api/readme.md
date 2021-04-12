#Co ważne:

### Pobrać sobie:

- https://www.postgresql.org/
  
- https://s3-eu-west-1.amazonaws.com/payara.fish/Payara+Downloads/5.2021.1/payara-5.2021.1.zip?utm_referrer=https%3A%2F%2Fwww.payara.fish%2Fdownloads%2Fpayara-platform-community-edition%2F

- https://plugins.jetbrains.com/plugin/15114-payara-platform-tools


Dodać sobie folder bin w folderze postgres do patha

### Zainstalować

Połączyć się do zainstalowanej bazy danych przez inteliJ podając:
- hosta localhost port 5432
  
- Jak nie wiemy jaki podać login i hasło https://www.postgresqltutorial.com/postgresql-reset-password/
- Baza danych postgres

Wykonać skrypt w pliku src/main/resources/META-INF/sql/createDB.sql poprzez utworzone połączenie

Tworzymy nowe połączenie z bazą danych chatdb

Teraz powinno dać się wdrożyć aplikację poprzez wtyczkę Payara - wybieramy preset Local.

Jesli plugin nie działa na naszej wersji - robimy downgrade.

W tym celu tworzymy nową konfigurację poprzez Payara Server.

Podajemy ścieżkę do folderu z payara.

W domyślnym url zmieniamy http na https.

Pozostałe Domyślne ustawienia powinny być ok, jeśli coś trzeba fix to klikamy fix.

Uruchamiamy install z lifecycle mavena w celu zbudowania aplikacji.

Odpalamy.

Jak wejdzmey na stronę powinno być error 404.