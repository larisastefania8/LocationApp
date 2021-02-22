package org.scd.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.GeneratedValue;

@AllArgsConstructor
@NoArgsConstructor
public class LocationGetDTO {
    @GeneratedValue
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
