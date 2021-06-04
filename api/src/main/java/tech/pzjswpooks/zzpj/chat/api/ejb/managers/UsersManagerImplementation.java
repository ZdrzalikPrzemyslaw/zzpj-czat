package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.ejb.facades.AccountEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.ejb.facades.UsersEntityFacade;
import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.UserData;
import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeUserRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.SearchUserRequestDto;
import tech.pzjswpooks.zzpj.chat.api.utils.LogInterceptor;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LogInterceptor.class)
public class UsersManagerImplementation extends AbstractManager implements UsersManager {

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

    @Override
    public List<UsersEntity> searchUserByUsernameRegex(SearchUserRequestDto dto) {
        List<UsersEntity> allUsers = usersEntityFacade.findAll();
        AccountEntityFacade accountEntityFacade = new AccountEntityFacade();
        String regex = ".*" + dto.getFilter() + ".*";

        /*
        List<UsersEntity> filteredUsers = new ArrayList<>();
        AccountsEntity accountsEntity = new AccountsEntity();
        for ( UsersEntity u : allUsers ) {
            AccountsEntity accountsEntity = accountEntityFacade.find(u.getId());
            if ( Pattern.matches(regex, accountsEntity.getUsername()) ) {
                filteredUsers.add(u);
            }
        }
         */

        // programowanie funkcyjne...
        // ...ale nie jestem pewien czy ok
        return allUsers.stream()
                .filter(u -> Pattern.matches(regex,accountEntityFacade.find(u.getId()).getUsername()))
                .collect(Collectors.toList());
    }
}
