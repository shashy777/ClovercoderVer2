package com.clove.clovewerable.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
@Table(name = "user_details")
public class UserDetails implements Serializable {

    private static final long serialVersionUID = -2889961235927012131L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_details_sequence_generator")
    @SequenceGenerator(name = "user_details_sequence_generator", sequenceName = "user_details_sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "created_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDateTime;

    @Column(name = "created_by")
    private String createdBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.id);
        builder.append(this.loginId);
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
        final UserDetails other = (UserDetails) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.loginId, other.loginId)) {
            return false;
        }

        return true;
    }
}
