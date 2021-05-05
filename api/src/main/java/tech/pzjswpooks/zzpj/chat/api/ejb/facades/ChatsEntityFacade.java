package tech.pzjswpooks.zzpj.chat.api.ejb.facades;

import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Klasa definiująca główne operacje wykonywane na encjach typu ChatsEntity.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ChatsEntityFacade extends AbstractFacade<ChatsEntity> {

    @PersistenceContext(unitName = "zzpjadmin")
    private EntityManager em;

    /**
     * Tworzy nową instancję klasy ChatsEntity.
     */
    public ChatsEntityFacade() {
        super(ChatsEntity.class);
    }


    /**
     * Tworzy nową instancję klasy ChatsFacade.
     *
     * @param entityClass entity class
     */
    public ChatsEntityFacade(Class<ChatsEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }




}
