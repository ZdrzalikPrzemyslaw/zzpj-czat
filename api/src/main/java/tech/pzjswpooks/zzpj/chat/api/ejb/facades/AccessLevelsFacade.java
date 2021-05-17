package tech.pzjswpooks.zzpj.chat.api.ejb.facades;

import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Interceptors(LogInterceptor.class)
public class AccessLevelsFacade extends AbstractFacade<AccessLevelsEntity> {
    @PersistenceContext(unitName = "zzpjadmin")
    private EntityManager em;

    /**
     * Tworzy nową instancję klasy AccessLevelsFacade.
     */
    public AccessLevelsFacade() {
        super(AccessLevelsEntity.class);
    }

    /**
     * Tworzy nową instancję klasy AccessLevelsFacade.
     *
     * @param entityClass typ obiektowy encji.
     */
    public AccessLevelsFacade(Class<AccessLevelsEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<AccessLevelsEntity> findByUsername(String username) {
        TypedQuery<AccessLevelsEntity> tq = em.createNamedQuery("AccessLevelsEntity.findByUsername", AccessLevelsEntity.class);
        tq.setParameter("username", username);
        return tq.getResultList();
    }
}
