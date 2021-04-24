package tech.pzjswpooks.zzpj.chat.api.config;

import java.sql.Connection;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


// TODO: 24.04.2021 To powinno być wg wielu znanych doktorów wczytywane z pliku (dokladnie login hasło itp) który hest git ignored. NIE IWME JAK TO ZROBIC : ( 
@DataSourceDefinition(
        name = "java:app/jdbc/zzpjadmin",
        className = "org.postgresql.ds.PGSimpleDataSource",
        // TODO: User i passwrd
        user = "zzpjadmin",
        password = "password",
        // TODO: narazie loclahost
        serverName = "localhost",
        portNumber = 5432,
        databaseName = "chatdb",
        transactional = true,
        isolationLevel = Connection.TRANSACTION_READ_COMMITTED)

// todo Nie pewny jestem tego stateless
@Stateless
public class JDBCConfig {

    //    Uczynienie z tej klasy komponentu Stateless
    //    i wstrzykniecie zarzadcy encji korzystajacego z zzpjadmin
    //    powoduje aktywowanie tej jednostki skladowania,
    //    a w konsekwencji utworzenie (z ew. usunieciem!) struktur w bazie danych
    //    @see persistence.xml
    @PersistenceContext(unitName = "zzpjadmin")
    private EntityManager em;

}
