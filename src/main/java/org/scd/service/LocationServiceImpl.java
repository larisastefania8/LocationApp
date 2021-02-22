package org.scd.service;

import org.scd.config.exception.BusinessException;
import org.scd.model.Location;
import org.scd.model.User;
import org.scd.model.dto.LocationCreateDTO;
import org.scd.model.dto.LocationFilterDTO;
import org.scd.model.dto.LocationUpdateDTO;
import org.scd.model.security.CustomUserDetails;
import org.scd.repository.LocationRepository;
import org.scd.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;


    public LocationServiceImpl(LocationRepository locationRepository, UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;

    }

    @Override
    public Location getLocation(Long id) throws  BusinessException {

        final Optional<Location> optionalLocation=locationRepository.findById(id);

        if(!optionalLocation.isPresent())
        { throw new BusinessException(404,"Location not found");
        }

        final Location existingLocation=optionalLocation.get();
        final Optional<User> user1=userRepository.findById(existingLocation.getUser_id());
        final User user2= user1.get();
        final Location locationCreateDTO = new Location();
        locationCreateDTO.setId(existingLocation.getId());
        locationCreateDTO.setDate(existingLocation.getDate());
        locationCreateDTO.setLatitude(existingLocation.getLatitude());
        locationCreateDTO.setLongitude(existingLocation.getLongitude());
        locationCreateDTO.setUser(user2);
        locationCreateDTO.setUser_id(user2.getId());


        return locationCreateDTO;
    }

    @Override
    public Location create(LocationCreateDTO locationCreateDTO) throws BusinessException {
        if(locationCreateDTO.getLatitude()==null)
        {
            throw new BusinessException(404,"Don't forget latitude!!");
        }
        if(locationCreateDTO.getLongitude()==null)
        {
            throw new BusinessException(404,"Don't forget longitude!!");
        }
        final User currentUser = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Location location =new Location();
        location.setDate(new Date());
        location.setLatitude(locationCreateDTO.getLatitude());
        location.setLongitude(locationCreateDTO.getLongitude());
        location.setUser(currentUser);
        location.setUser_id(currentUser.getId());
        final Location locationCreated=locationRepository.save(location);

        return locationCreated;
    }
    @Override
    public Location update(Long id,LocationUpdateDTO locationUpdateDTO)throws BusinessException{
        if(locationUpdateDTO.getLatitude()==null)
        {
            throw new BusinessException(404,"Don't forget latitude!!!");
        }
        if(locationUpdateDTO.getLongitude()==null)
        {
            throw new BusinessException(404,"Don't forget longitude!!");
        }
        Location location=getLocation(id);
        location.setLatitude(locationUpdateDTO.getLatitude());
        location.setLongitude(locationUpdateDTO.getLongitude());
        location.setDate(new Date());
        locationRepository.save(location);
        return location;


    }
    @Override
    public Location delete(Long id)throws BusinessException{
        Location location=getLocation(id);
        locationRepository.delete(location);
        return location;
    }

    public List<Location> filter(final Long userid, final String startDate , final String endDate) throws  BusinessException{
       if(locationRepository.findAllByUserId(userid)== null)
            throw new BusinessException(402,"Id-ul was not found");
        List<Location> location=locationRepository.findAllByUserId(userid, startDate, endDate);
        if(location.isEmpty())
            throw  new BusinessException(402,"There is no locations between this dates");
        return locationRepository.findAllByUserId(userid, startDate, endDate);

    }
}
