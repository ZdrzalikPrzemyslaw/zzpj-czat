package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.payloads.request.EditAccountRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.UsersResponseDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersManager {

    void changeUserDetails(String username, EditAccountRequestDTO r);

    List<UsersResponseDTO> searchUserByUsernameRegex(String filter);

    List<UsersResponseDTO> searchUserByEmailRegex(String filter);

    List<UsersResponseDTO> searchUserByFirstOrLastNameRegex(String filter);

}
