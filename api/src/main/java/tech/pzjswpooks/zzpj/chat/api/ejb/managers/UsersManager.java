package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.EditAccountRequestDTO;

import javax.ejb.Local;

@Local
public interface UsersManager {

    void changeUserDetails(String username, EditAccountRequestDTO r);

}
