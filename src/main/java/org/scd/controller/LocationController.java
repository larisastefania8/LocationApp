package org.scd.controller;
import org.scd.config.exception.BusinessException;
import org.scd.model.Location;
import org.scd.model.dto.LocationCreateDTO;
import org.scd.model.dto.LocationFilterDTO;
import org.scd.model.dto.LocationGetDTO;
import org.scd.model.dto.LocationUpdateDTO;
import org.scd.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
@RestController()
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;
    //a method parameter should be bound to a URI template variable

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable("id") final Long id) throws BusinessException {
        System.out.println("Id-ul selectat: " + id);
        if (locationService.getLocation(id) == null)
            throw new BusinessException(400, "Id-ul nu a fost gasit");
        final Location locationCreateDTO = locationService.getLocation(id);
        return ResponseEntity.ok(locationCreateDTO);

    }

    @PostMapping("/create")
    public ResponseEntity<Location> create(@RequestBody final LocationCreateDTO locationCreateDTO) throws BusinessException {

        return ResponseEntity.ok(locationService.create(locationCreateDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Location> update(@PathVariable("id") final Long id,@RequestBody final LocationUpdateDTO locationUpdateDTO) throws BusinessException{

        return ResponseEntity.ok(locationService.update(id,locationUpdateDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Location> delete(@PathVariable("id")final Long id) throws  BusinessException{

        return ResponseEntity.ok(locationService.delete(id));
    }


@GetMapping(path="/filter")
public ResponseEntity<List<Location>> filterLocations(
        @RequestParam(name = "userid", required = false) final Long userid,
        @RequestParam(name = "startDate", required = false) final String startDate,
        @RequestParam(name = "endDate", required = false) final String endDate

        ) throws BusinessException {



    final List<Location> response = locationService.filter(userid,startDate,endDate);
    return ResponseEntity.ok(response);
        }
        }
