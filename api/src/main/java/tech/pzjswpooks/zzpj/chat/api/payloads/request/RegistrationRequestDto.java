package tech.pzjswpooks.zzpj.chat.api.payloads.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import tech.pzjswpooks.zzpj.chat.api.common.I18n;
import tech.pzjswpooks.zzpj.chat.api.security.Login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationRequestDto {
    @NotNull(message = I18n.LOGIN_NULL)
    @Login
    private String username;
    @Size(min = 8, message = I18n.PASSWORD_INVALID_SIZE)
    @NotNull(message = I18n.PASSWORD_NULL)
    private String password;
    @NotNull(message = I18n.EMAIL_NULL)
    @Email(message = I18n.NOT_AN_EMAIL)
    @Size(min = 5, max = 100, message = I18n.EMAIL_INVALID_SIZE)
    private String email;
    @NotNull(message = I18n.FIRST_NAME_NULL)
    @Size(min = 1, max = 50, message = I18n.FIRST_NAME_INVALID_SIZE)
    private String firstName;
    @NotNull(message = I18n.LAST_NAME_NULL)
    @Size(min = 1, max = 80, message = I18n.LAST_NAME_INVALID_SIZE)
    private String lastName;
    private String language;
    @Size(min = 9, max = 15, message = I18n.PHONE_NUMBER_INVALID_SIZE)
    private String phoneNumber;

    public RegistrationRequestDto(String username, String password, String email, String firstName, String lastName, String language, String phoneNumber) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
