package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationRequestDto {
    @NotEmpty
    @Size(min = 6, max = 32, message = "Invalid size of username")
    private String username;
    @NotNull
    @Size(min = 8, message = "Invalid size of password")
    private String password;
    @NotNull
    @Email(message = "Not an email")
    @Size(max = 100, message = "Too long email")
    private String email;
    @NotEmpty
    @Size(max = 50, message = "Too long first name")
    private String firstName;
    @NotEmpty
    @Size(max = 80, message = "Too long last name")
    private String lastName;
    private String language;
    private String phoneNumber;

    public RegistrationRequestDto(@NotEmpty @Size(min = 6, max = 32, message = "Invalid size of username") String username,
                                  @NotNull @Size(min = 8, message = "Invalid size of password") String password,
                                  @NotNull @Email(message = "Not an email") @Size(max = 100, message = "Too long email") String email,
                                  @NotEmpty @Size(max = 50, message = "Too long first name") String firstName,
                                  @NotEmpty @Size(max = 80, message = "Too long last name") String lastName,
                                  String language, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
        this.phoneNumber = phoneNumber;
    }

    public RegistrationRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLanguage() {
        return language;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("password", password)
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("language", language)
                .append("phoneNumber", phoneNumber)
                .toString();
    }
}
