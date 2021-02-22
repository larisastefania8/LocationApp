package org.scd.model;

import org.scd.repository.UserRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="LOCATIONS")
public class Location implements  Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
    @Column(name = "LONGITUDE",nullable = false,length = 100)
    private String longitude;
    @Column(name = "LATITUDE",nullable = false,length = 100)
    private String latitude;

    @Column(name = "DATE",nullable = false,length = 256)
    private Date date;


    @ManyToOne // one row in this table is mapped to multiple rows in another table.
    @JoinColumn(name="user",referencedColumnName = "email") //specify the column we'll use for joining an entity association or element collection
    private User user;

    private Long user_id;



    public Location(){}
    public Location(String longitude,String latitude,Date date,User user,Long user_id)

    {

        this.longitude=longitude;
        this.latitude=latitude;
        this.date=date;
        this.user=user;
        this.user_id=user_id;
    }

    public Date getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getEmail()
    {
        return user.getEmail();
    }

    public void setUser(User user) {
        this.user=user;
        this.user.setEmail(user.getEmail());
    }
    public String getUser()
    {
        return user.toString();

    }
    public  Long getUser_id()
    {
        return user_id;
    }
    public void  setUser_id(Long user_id)
    {
        this.user_id=user_id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
