package org.scd.service;

import org.scd.config.exception.BusinessException;
import org.scd.model.Location;
import org.scd.model.dto.LocationCreateDTO;
import org.scd.model.dto.LocationFilterDTO;
import org.scd.model.dto.LocationGetDTO;
import org.scd.model.dto.LocationUpdateDTO;

import java.util.Date;
import java.util.List;

public interface LocationService {

    Location getLocation(Long id) throws BusinessException;
    Location create(final LocationCreateDTO locationCreateDTO)throws BusinessException;
    Location update(final Long id,final LocationUpdateDTO locationUpdateDTO)throws BusinessException;
    Location delete(Long id) throws BusinessException;
    List<Location> filter(final Long userid, final String startDate , final String endDate) throws BusinessException;


}
