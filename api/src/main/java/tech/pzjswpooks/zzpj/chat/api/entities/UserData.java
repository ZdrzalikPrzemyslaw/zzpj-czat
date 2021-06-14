package tech.pzjswpooks.zzpj.chat.api.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("level.user")
@NamedQueries({
        @NamedQuery(name = "UserData.findAll", query = "SELECT a FROM UserData a")})
public class UserData extends AccessLevelsEntity implements Serializable {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}