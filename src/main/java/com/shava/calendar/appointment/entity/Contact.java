/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.appointment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shava.calendar.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
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
@Table(name = "contact")
@NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
@NamedQuery(name ="Contact.findByName", query = "select c from Contact c where c.fullName like :name and c.userCalendarId= :user ")
public class Contact extends  BaseEntity implements Serializable {
    
    public static final String FIND_BY_NAME = "Contact.findByName";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "contact_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "contact_id_seq", sequenceName = "contact_contact_id_seq", allocationSize = 1)
    @Column(name = "contact_id")
    private Integer contactId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "full_name")
    private String fullName;
    @Size(max = 20)
    @Column(name = "mobile_number")
    private String mobileNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_calendar_id")
    private int userCalendarId;
    
    @JsonIgnore
    @OneToMany(mappedBy = "contactId")
    private List<Schedule> scheduleList;

    public Contact() {
    }

    public Contact(Integer contactId) {
        this.contactId = contactId;
    }

    public Contact(Integer contactId, String fullName, int userCalendarId) {
        this.contactId = contactId;
        this.fullName = fullName;
        this.userCalendarId = userCalendarId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserCalendarId() {
        return userCalendarId;
    }

    public void setUserCalendarId(int userCalendarId) {
        this.userCalendarId = userCalendarId;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactId != null ? contactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.contactId == null && other.contactId != null) || (this.contactId != null && !this.contactId.equals(other.contactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shava.calendar.appointment.entity.Contact[ contactId=" + contactId + " ]";
    }
    
}
