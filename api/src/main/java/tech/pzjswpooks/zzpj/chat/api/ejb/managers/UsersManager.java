package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.entities.UserData;
import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeUserRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.SearchUserRequestDto;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.UsersResponseDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersManager {

    void changeUserDetails(UsersEntity usersEntity, ChangeUserRequestDto r);

    List<UsersResponseDTO> searchUserByUsernameRegex(SearchUserRequestDto dto);

    List<UsersResponseDTO> searchUserByEmailRegex(SearchUserRequestDto dto);

    List<UsersResponseDTO> searchUserByFirstOrLastNameRegex(SearchUserRequestDto dto);

}
