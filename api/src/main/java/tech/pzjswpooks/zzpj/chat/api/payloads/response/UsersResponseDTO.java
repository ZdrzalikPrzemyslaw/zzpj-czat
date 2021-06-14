package tech.pzjswpooks.zzpj.chat.api.payloads.response;

import tech.pzjswpooks.zzpj.chat.api.entities.UsersEntity;

public class UsersResponseDTO {

    private final String firstName;
    private String phoneNumber;
    private String language;
    private final String lastName;
    private final String email;

    public UsersResponseDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UsersResponseDTO(UsersEntity usersEntity) {
        this.firstName = usersEntity.getFirstName();
        this.lastName = usersEntity.getLastName();
        this.email = usersEntity.getEmail();
        this.phoneNumber = usersEntity.getPhoneNumber();
        this.language = usersEntity.getLanguage();
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
