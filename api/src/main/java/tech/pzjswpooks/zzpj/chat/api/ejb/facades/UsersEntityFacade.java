package tech.pzjswpooks.zzpj.chat.api.ejb.facades;

import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 * Klasa definiująca główne operacje wykonywane na encjach typu ChatMessagesEntity.
 */
@Stateless
// TODO: 24.04.2021 Nie jestem pewien czy tutaj ta tranzakcja jest konieczna
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LogInterceptor.class)
public class UsersEntityFacade extends AbstractFacade<UsersEntity> {

    @PersistenceContext(unitName = "zzpjadmin")
    private EntityManager em;

    /**
     * Tworzy nową instancję klasy UsersEntityFacade.
     */
    public UsersEntityFacade() {
        super(UsersEntity.class);
    }

    /**
     * Tworzy nową instancję klasy UsersEntityFacade.
     *
     * @param entityClass entity class
     */
    public UsersEntityFacade(Class<UsersEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void edit(UsersEntity entity)  {
        try {
            super.edit(entity);
        } catch (PersistenceException e) {
            throw e;
            // TODO: 20.04.2021 - uzupełnić o wyjątki aplikacyjne
        }
    }


}
