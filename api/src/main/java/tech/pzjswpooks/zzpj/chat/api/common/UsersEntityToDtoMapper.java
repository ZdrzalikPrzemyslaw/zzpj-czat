package tech.pzjswpooks.zzpj.chat.api.common;

import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;
import tech.pzjswpooks.zzpj.chat.api.payloads.response.UsersResponseDTO;

public class UsersEntityToDtoMapper {

    public static UsersResponseDTO mapUsersEntityToDto(UsersEntity usersEntity) {
        return new UsersResponseDTO(usersEntity.getFirstName(),usersEntity.getLastName(), usersEntity.getEmail());
    }
}
