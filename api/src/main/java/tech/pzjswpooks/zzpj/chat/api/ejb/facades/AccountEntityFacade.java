package tech.pzjswpooks.zzpj.chat.api.ejb.facades;

import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * Klasa definiująca główne operacje wykonywane na encjach typu AccountEntity.
 */
@Stateless
public class AccountEntityFacade extends AbstractFacade<AccountsEntity> {

    @PersistenceContext(unitName = "zzpjadmin")
    private EntityManager em;

    /**
     * Tworzy nową instancję klasy AccountFacade.
     */
    public AccountEntityFacade() {
        super(AccountsEntity.class);
    }

    /**
     * Find by username account.
     *
     * @param username username
     * @return account
     */
    public AccountsEntity findByUsername(String username) {
        TypedQuery<AccountsEntity> tq = em.createNamedQuery("AccountsEntity.findByUsername", AccountsEntity.class);
        tq.setParameter("username", username);
        return tq.getSingleResult();
    }

    /**
     * Tworzy nową instancję klasy AccountFacade.
     *
     * @param entityClass entity class
     */
    public AccountEntityFacade(Class<AccountsEntity> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void edit(AccountsEntity entity)  {
        try {
            super.edit(entity);
        } catch (PersistenceException e) {
            throw e;
            // TODO: 20.04.2021 - uzupełnić o wyjątki aplikacyjne
        }
    }


}
