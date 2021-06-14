package tech.pzjswpooks.zzpj.chat.api.ejb.facades;

import tech.pzjswpooks.zzpj.chat.api.entities.ChatUsersEntity;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LogInterceptor.class)
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
     * Tworzy nową instancję klasy AccountFacade.
     *
     * @param entityClass entity class.
     */
    public ChatUsersFacade(Class<ChatUsersEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    public ChatUsersEntity getChatUserByIdAndUsername(String username, Long id) {
        TypedQuery<ChatUsersEntity> tq = em.createNamedQuery("ChatUsersEntity.findUserInChat", ChatUsersEntity.class);
        tq.setParameter("id", id);
        tq.setParameter("username", username);

        return tq.getSingleResult();
    }

}
