/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.authorization.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shava.calendar.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author raul
 */
@Entity
@Table(name = "user_calendar")
    @NamedQuery(name = "UserCalendar.findAll", query = "SELECT u FROM UserCalendar u")
    @NamedQuery(name = "UserCalendar.findByUserAndPassword", query = "SELECT u FROM UserCalendar u WHERE u.email = :email and u.userPassword = :password")
    @NamedQuery(name = "UserCalendar.findByFirstName", query = "SELECT u FROM UserCalendar u WHERE u.firstName = :firstName")
    @NamedQuery(name = "UserCalendar.findByLastName", query = "SELECT u FROM UserCalendar u WHERE u.lastName = :lastName")
    @NamedQuery(name = "UserCalendar.findByEmail", query = "SELECT u FROM UserCalendar u WHERE u.email = :email")
    @NamedQuery(name = "UserCalendar.findByMobileNumber", query = "SELECT u FROM UserCalendar u WHERE u.mobileNumber = :mobileNumber")
    @NamedQuery(name = "UserCalendar.findByToken", query ="select a from UserCalendar a inner join a.tokens t where t.tokenHash = :tokenHash and t.tokenType = :tokenType and t.expiration > CURRENT_TIMESTAMP")
    
public class UserCalendar extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String FIND_BY_EMAIL = "UserCalendar.findByEmail";
    public static final String FIND_BY_TOKEN = "UserCalendar.findByToken";
    public static final String FIND_BY_USER_PASSWORD = "UserCalendar.findByUserAndPassword";
    
    @Id
    @SequenceGenerator(name="user_calendar_seq",sequenceName="user_calendar_user_calendar_id_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="user_calendar_seq")
    @Column(name = "user_calendar_id")
    private Integer userCalendarId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "last_name")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    
    @NotNull
    @Column(name = "user_password")
    private String userPassword;
    
    @Size(max = 20)
    @Column(name = "mobile_number")
    private String mobileNumber;
    
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Token> tokens = new ArrayList<>();

    public UserCalendar() {
    }

    public UserCalendar(Integer userCalendarId) {
        this.userCalendarId = userCalendarId;
    }
    
    public UserCalendar(String email, String password) {
        this.userPassword = password;
        this.email = email;
    }

    public UserCalendar(Integer userCalendarId, String firstName, String lastName, String email) {
        this.userCalendarId = userCalendarId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getUserCalendarId() {
        return userCalendarId;
    }

    public void setUserCalendarId(Integer userCalendarId) {
        this.userCalendarId = userCalendarId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userCalendarId != null ? userCalendarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCalendar)) {
            return false;
        }
        UserCalendar other = (UserCalendar) object;
        if ((this.userCalendarId == null && other.userCalendarId != null) || (this.userCalendarId != null && !this.userCalendarId.equals(other.userCalendarId))) {
            return false;
        }
        return true;
    }
    
     public void addToken(Token token) {
        this.tokens.add(token);
        token.setAccount(this);
    }

    public void removeToken(Token token) {
        this.tokens.remove(token);
        token.setAccount(this);
    }

    @Override
    public String toString() {
        return "com.shava.calendar.authorization.entity.UserCalendar[ userCalendarId=" + userCalendarId + " ]";
    }

    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }
    
    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    /**
     * @return the tokens
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * @param tokens the tokens to set
     */
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
    
}
