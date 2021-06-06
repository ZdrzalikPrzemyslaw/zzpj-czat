package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.UserData;
import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeUserRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.SearchUserRequestDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersManager {

    void changeUserDetails(UsersEntity usersEntity, ChangeUserRequestDto r);

    List<UsersEntity> searchUserByUsernameRegex(SearchUserRequestDto dto);

    List<UsersEntity> searchUserByEmailRegex(SearchUserRequestDto dto);

    List<UsersEntity> searchUserByFirstOrLastNameRegex(SearchUserRequestDto dto);

}
