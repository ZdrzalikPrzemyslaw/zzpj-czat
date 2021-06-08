package tech.pzjswpooks.zzpj.chat.api.ejb.facades;

import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.ChatsEntity;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * Klasa definiująca główne operacje wykonywane na encjach typu ChatsEntity.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LogInterceptor.class)
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

    public Collection<ChatsEntity> getChatsByUsernameBelongsTo(String username) {
        TypedQuery<ChatsEntity> tq = em.createNamedQuery("ChatsEntity.findByUsername", ChatsEntity.class);
        tq.setParameter("username", username);
        return tq.getResultList();
    }

    public ChatsEntity getChatByOwnerAndId(String username, Long id) {
        TypedQuery<ChatsEntity> tq = em.createNamedQuery("ChatsEntity.findByOwnerAndId", ChatsEntity.class);
        tq.setParameter("id", id);
        tq.setParameter("username", username);

        return tq.getSingleResult();
    }




}
