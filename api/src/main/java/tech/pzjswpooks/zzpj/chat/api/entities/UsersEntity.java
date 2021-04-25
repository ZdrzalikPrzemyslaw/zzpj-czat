package tech.pzjswpooks.zzpj.chat.api.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.security.SecureRandom;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"account_id"})
})
@NamedQueries({
        @NamedQuery(name = "UsersEntity.findAll", query = "SELECT a FROM ChatUsersEntity a"),
        @NamedQuery(name = "UsersEntity.findById", query = "SELECT a FROM ChatUsersEntity a WHERE a.id = :id")
})
public class UsersEntity {

    @Id
    @SequenceGenerator(name = "users_generator", sequenceName = "users_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Basic(optional = false)
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;
    @Basic
    @Column(name = "phone_number", nullable = true, length = 15)
    private String phoneNumber;
    @Basic
    @Column(name = "language", nullable = true, length = 35)
    private String language;
    @JoinColumn(name = "account_id", unique = true, referencedColumnName = "id", nullable = false, updatable = false)
    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private AccountsEntity accountId;

    public UsersEntity() {

    }

    UsersEntity(String email, String firstName, String language, String lastName, String phoneNumber, AccountsEntity accountsEntity) {
        this.accountId = accountsEntity;
        this.email = email;
        this.firstName = firstName;
        this.language = language;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsersEntity that = (UsersEntity) o;

        return new EqualsBuilder().append(id, that.id).append(email, that.email).append(firstName, that.firstName)
                .append(lastName, that.lastName).append(phoneNumber, that.phoneNumber).append(language, that.language).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(email).append(firstName).append(lastName).append(phoneNumber).append(language).toHashCode();
    }
}
