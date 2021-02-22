package org.scd.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class LocationFilterDTO {


    private Long userid;
    private Date startDate;
    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Long getUserid() {
        return userid;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
