package tech.pzjswpooks.zzpj.chat.api.common;

import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.RegistrationRequestDto;

public class AccountEntityMapper {

    public static AccountsEntity mapRegistrationDtoToAccount(RegistrationRequestDto r) {
        return new AccountsEntity(r.getUsername(), r.getPassword(), r.getEmail(), r.getFirstName(), r.getLanguage(), r.getLastName(), r.getPhoneNumber());
    }

}
