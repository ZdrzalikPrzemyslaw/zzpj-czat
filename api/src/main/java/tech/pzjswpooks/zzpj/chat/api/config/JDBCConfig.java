package tech.pzjswpooks.zzpj.chat.api.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;


@DataSourceDefinition(
        name = "java:app/jdbc/zzpjadmin",
        className = "org.postgresql.ds.PGSimpleDataSource",
        user = "zzpjadmin",
        password = "pa$$word1",
        //        serverName = "zzpjdb.postgres.database.azure.com",
        serverName = "localhost",
        portNumber = 5432,
        databaseName = "chatdb",
        transactional = true,
        isolationLevel = Connection.TRANSACTION_READ_COMMITTED)

@Stateless
public class JDBCConfig {
    @PersistenceContext(unitName = "zzpjadmin")
    private EntityManager em;
}
