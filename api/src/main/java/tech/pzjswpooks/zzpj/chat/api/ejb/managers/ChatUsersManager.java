package tech.pzjswpooks.zzpj.chat.api.ejb.managers;

import tech.pzjswpooks.zzpj.chat.api.exceptions.AppBaseException;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.AddUserToChatRequestDTO;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeChatOwnerRequestDTO;

import javax.ejb.Local;

@Local
public interface ChatUsersManager {

    void addUser(AddUserToChatRequestDTO addUserToChatRequestDTO, Long id) throws AppBaseException;

}
