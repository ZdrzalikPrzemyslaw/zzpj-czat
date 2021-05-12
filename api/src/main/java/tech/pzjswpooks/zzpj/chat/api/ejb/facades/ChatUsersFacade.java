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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
