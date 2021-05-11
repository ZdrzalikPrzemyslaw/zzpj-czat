package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.UsersEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.UserData;
import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeUserRequestDto;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.Objects;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UsersManagerImplementation implements UsersManager {

    private UsersEntityFacade usersEntityFacade;

    @Inject
    public UsersManagerImplementation(UsersEntityFacade usersEntityFacade) {
        this.usersEntityFacade = usersEntityFacade;
    }

    public UsersManagerImplementation() {
    }

    @Override
    public void changeUserDetails(UsersEntity usersEntity, ChangeUserRequestDto r) {
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
