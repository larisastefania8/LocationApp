package org.scd.model.dto;

import org.scd.model.User;

import javax.persistence.GeneratedValue;
import java.util.Date;

public class LocationCreateDTO {
    private String longitude;
    private String latitude;
    private Date date;
    private User user;
    @GeneratedValue
    private Long id;

    public  LocationCreateDTO(){}

    public  LocationCreateDTO(Long id,String longitude,String latitude,User user)
    {
        this.id=id;
        this.longitude=longitude;
        this.latitude=latitude;
        this.user=user;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }


    public String getUser() {
        return user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
