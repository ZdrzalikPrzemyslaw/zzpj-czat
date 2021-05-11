package tech.pzjswpooks.zzpj.chat.api.entities;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name = "access_levels", uniqueConstraints = {
        @UniqueConstraint(name = "acc_lvl_level_account_pair_unique", columnNames = {"level", "account_id"})
})
@NamedQueries({
        @NamedQuery(name = "AccessLevelsEntity.findAll", query = "SELECT a FROM AccessLevelsEntity a"),
        @NamedQuery(name = "AccessLevelsEntity.findById", query = "SELECT a FROM AccessLevelsEntity a WHERE a.id = :id"),
        @NamedQuery(name = "AccessLevelsEntity.findByUsername", query = "SELECT a FROM AccessLevelsEntity a, AccountsEntity ae WHERE a.accountId = ae and ae.username = :username")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "level", discriminatorType = DiscriminatorType.STRING)
public class AccessLevelsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "access_levels_generator")
    @SequenceGenerator(name = "access_levels_generator", sequenceName = "access_levels_seq", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "level", nullable = false, length = 32, updatable = false, insertable  = false)
    private String level;
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AccountsEntity accountId;
    @Basic(optional = false)
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;
    @Basic
    @Version
    @Column(name = "version", nullable = true)
    private Long version = 0L;

    public AccessLevelsEntity() {

    }

    public void setAccountId(AccountsEntity accountId) {
        this.accountId = accountId;
    }

    // Nie jestem pewien tego konstruktora
    public AccessLevelsEntity(Long id, String level, AccountsEntity accountId) {
        this.id = id;
        this.level = level;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public AccountsEntity getAccountId() {
        return accountId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccessLevelsEntity that = (AccessLevelsEntity) o;

        return new EqualsBuilder().append(id, that.id).append(level, that.level).append(accountId, that.accountId).append(enabled, that.enabled).append(version, that.version).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("level", level)
                .append("accountId", accountId)
                .append("enabled", enabled)
                .append("version", version)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(level).append(accountId).append(enabled).append(version).toHashCode();
    }


}
