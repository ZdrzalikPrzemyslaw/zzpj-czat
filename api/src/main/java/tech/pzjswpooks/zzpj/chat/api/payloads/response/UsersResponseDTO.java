package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;

public class UsersResponseDTO {

    private String firstName;
    private String lastName;
    private String email;

    public UsersResponseDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UsersResponseDTO(UsersEntity usersEntity) {
        this.firstName = usersEntity.getFirstName();
        this.lastName = usersEntity.getLastName();
        this.email = usersEntity.getEmail();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
