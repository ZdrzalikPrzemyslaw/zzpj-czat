package tech.pzjswpooks.zzpj.chat.api.ejb.facades;

import tech.pzjswpooks.zzpj.chat.api.entities.ChatUsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ChatUsersFacade extends AbstractFacade<ChatUsersEntity> {

    @PersistenceContext(unitName = "zzpjadmin")
    private EntityManager em;

    /**
     * Tworzy nową instancję klasy ChatUsersFacade.
     */
    public ChatUsersFacade() {
        super(ChatUsersEntity.class);
    }

    /**
     *  Tworzy nową instancję klasy AccountFacade.
     * @param entityClass entity class.
     */
    public ChatUsersFacade(Class<ChatUsersEntity> entityClass) {
        super(entityClass);
    }

    /**
     * Find users in chat by id.
     *
     * @param id id
     * @return ChatUsersEntity
     */
    public ChatUsersEntity findById(String id) {
        TypedQuery<ChatUsersEntity> tq = em.createNamedQuery("ChatUsersEntity.findById", ChatUsersEntity.class);
        tq.setParameter("id", id);
        return tq.getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
