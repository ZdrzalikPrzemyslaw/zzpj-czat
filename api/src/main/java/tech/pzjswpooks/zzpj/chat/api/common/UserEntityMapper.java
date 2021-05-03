package tech.pzjswpooks.zzpj.chat.api.common;

import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.ChangeUserRequestDto;

public class UserEntityMapper {

    public static UsersEntity mapChangeUserDtoToUser(ChangeUserRequestDto r) {
        return new UsersEntity(r.getEmail(), r.getFirstName(), r.getLanguage(), r.getLastName(), r.getPhoneNumber(), r.getAccountsEntity());
    }

}
