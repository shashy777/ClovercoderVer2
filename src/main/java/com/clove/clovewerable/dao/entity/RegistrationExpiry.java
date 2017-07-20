package com.clove.clovewerable.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author @Unascribed
 */
@Entity
@Table(name = "registration_expiry")
public class RegistrationExpiry implements Serializable {

    private static final long serialVersionUID = 7837581458377462349L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "registration_expiry_sequence_generator")
    @SequenceGenerator(name = "registration_expiry_sequence_generator", sequenceName = "registration_expiry_sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "exp_token")
    private String expToken;

    @Column(name = "created_date_time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdDateTime;

    @Column(name = "login_id")
    private String loginId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpToken() {
        return expToken;
    }

    public void setExpToken(String expToken) {
        this.expToken = expToken;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.id);
        builder.append(this.expToken);
        builder.append(this.createdDateTime);

        return builder.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegistrationExpiry other = (RegistrationExpiry) obj;
        if (this.id != other.id) {
            return false;
        }

        return true;
    }
}
