package tech.pzjswpooks.zzpj.chat.api.common;

import tech.pzjswpooks.zzpj.chat.api.entities.AccountsEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.request.RegistrationRequestDto;
import tech.pzjswpooks.zzpj.chat.api.utils.HashGenerator;

public class AccountEntityMapper {

    public static AccountsEntity mapRegistrationDtoToAccount(RegistrationRequestDto r, HashGenerator hashGenerator) {
        return new AccountsEntity(r.getUsername(), hashGenerator.generateHash(r.getPassword()), r.getEmail(), r.getFirstName(), r.getLanguage(), r.getLastName(), r.getPhoneNumber());
    }

}
