package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.AccountEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.ejb.facades.UsersEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.EditAccountRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.Objects;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LogInterceptor.class)
public class UsersManagerImplementation extends AbstractManager implements UsersManager {

    private UsersEntityFacade usersEntityFacade;
    private AccountEntityFacade accountEntityFacade;

    @Inject
    public UsersManagerImplementation(UsersEntityFacade usersEntityFacade, AccountEntityFacade accountEntityFacade) {
        this.usersEntityFacade = usersEntityFacade;
        this.accountEntityFacade = accountEntityFacade;
    }

    public UsersManagerImplementation() {
    }

    @Override
    public void changeUserDetails(String username, EditAccountRequestDTO r) {
        var byUsername = accountEntityFacade.findByUsername(username);
        var usersEntity = byUsername.getUserId();
        if (!Objects.isNull(r.getEmail())) {
            usersEntity.setEmail(r.getEmail());
        }
        if (!Objects.isNull(r.getFirstName())) {
            usersEntity.setFirstName(r.getFirstName());
        }
        if (!Objects.isNull(r.getLastName())) {
            usersEntity.setLastName(r.getLastName());
        }
        if (!Objects.isNull(r.getLanguage())) {
            usersEntity.setLanguage(r.getLanguage());
        }
        if (!Objects.isNull(r.getPhoneNumber())) {
            usersEntity.setPhoneNumber(r.getPhoneNumber());
        }
        usersEntityFacade.edit(usersEntity);
    }

}
