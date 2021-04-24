package tech.pzjswpooks.zzpj.chat.api.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("level.admin")
@NamedQueries({
        @NamedQuery(name = "AdminData.findAll", query = "SELECT a FROM AdminData a")})
public class AdminData extends AccessLevelsEntity implements Serializable {

    // TODO: 24.04.2021 Nwm jak to dziala z dziedziczeniem
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}