package tech.pzjswpooks.zzpj.chat.api.ejb.facades;

import tech.pzjswpooks.zzpj.chat.api.entities.AccessLevelsEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public AccessLevelsEntity findById(String id) {
        TypedQuery<AccessLevelsEntity> tq = em.createNamedQuery("AccessLevelsEntity.findById", AccessLevelsEntity.class);
        tq.setParameter("id", id);
        return tq.getSingleResult();
    }

    public List<AccessLevelsEntity> findAll(String id) {
        TypedQuery<AccessLevelsEntity> tq = em.createNamedQuery("AccessLevelsEntity.findAll", AccessLevelsEntity.class);
        return tq.getResultList();
    }
}
