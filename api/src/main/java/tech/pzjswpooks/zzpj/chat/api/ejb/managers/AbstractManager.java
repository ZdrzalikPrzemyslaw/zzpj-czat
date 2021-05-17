package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import javax.ejb.AfterBegin;
import javax.ejb.AfterCompletion;
import javax.ejb.BeforeCompletion;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstrakcyjna klasa po której powinne dziedziczyć inne managery, umożliwia logowanie.
 */
public abstract class AbstractManager {

    protected static final Logger LOGGER = Logger.getGlobal();

    private String transactionID;
    boolean lastTransactionRollback;

    protected AbstractManager() {
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean isLastTransactionRollback() {
        return lastTransactionRollback;
    }

    /**
     * Metoda do logowania początku transakcji.
     * @throws IOException Wyjątek
     */
    @AfterBegin
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void afterBegin() throws IOException {
        prepareLogger();
        transactionID = Long.toString(System.currentTimeMillis()) + ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        LOGGER.log(Level.INFO, "Transakcja TXid = {0} rozpoczęta w {1}.", new Object[]{transactionID, this.getClass().getName()});
    }

    /**
     * Metoda do logowania oczekiwania transakcji na zatwierdzenie.
     * @throws IOException Wyjątek
     */
    @BeforeCompletion
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void beforeCompletion() throws IOException {
        prepareLogger();
        LOGGER.log(Level.INFO, "Transakcja TXid={0} przed zatwierdzeniem w {1}.", new Object[]{transactionID, this.getClass().getName()});
    }

    /**
     * Metoda do logowania końca transakcji.
     *
     * @param commmitted czy transakcja została zatwierdzona.
     * @throws IOException Wyjątek
     */
    @AfterCompletion
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void afterCompletion(boolean commmitted) throws IOException {
        prepareLogger();
        lastTransactionRollback = !commmitted;
        LOGGER.log(Level.INFO, "Transakcja TXid={0} zatwierdzona w {1} poprzez {2}.", new Object[]{transactionID, this.getClass().getName(), commmitted ? "ZATWIERDZENIE" : "ODWOłANIE"});
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    private void prepareLogger() throws IOException {
        String logFile = new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "-log.txt";
        FileHandler fileHandler = new FileHandler(logFile);
        LOGGER.addHandler(fileHandler);
    }
}
