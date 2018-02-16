package com.techm.transport.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.techm.transport.entity.Location;
import com.techm.transport.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


//@CrossOrigin(origins="/**")
@Controller
@RequestMapping("transport/1.0")
@Api(description="Location operations", tags= {"Locations"})
public class LocationController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LocationService service;


	@ApiOperation(value = "${LocationController.getLocation}", response = Location.class) 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resource of given id"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@GetMapping("locs/{locId}")
	public ResponseEntity<Location> getLocation(@ApiParam(name = "locId", value = "Id of location", required = true) @PathVariable("locId") Integer id){
		LOGGER.info("Getting organization details of id-" + id);
		Location Location = service.getLocationById(id);
		return new ResponseEntity<Location>(Location, HttpStatus.OK);
	}
	
	@ApiOperation(value = "${LocationController.getJourneyTypesOfLocation}", response = Location.class) 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resource of given id"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
	}
			)
	@GetMapping("locs/{locId}/journeyTypes")
	public ResponseEntity<Location> getJourneyTypesOfLocation(@ApiParam(name = "locId", value = "Id of location", required = true) @PathVariable("locId") Integer id){
		LOGGER.info("Getting organization details of id-" + id);
		Location Location = service.getJourneyTypesOfLocation(id);
		return new ResponseEntity<Location>(Location, HttpStatus.OK);
	}
	
	
	
	/*@ApiOperation(value = "${LocationController.getLocationByCityId}", response = Location.class) 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resource of given id"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
	}
			)
	@GetMapping("locs/{cityId}")
	public ResponseEntity<List<Location>> getLocationByCityId(@ApiParam(name = "cityId", value = "id of city", required = true) @PathVariable("cityId") Integer id){
		LOGGER.info("Getting organization details of id-" + id);
		List<Location> locs = service.getLocationsByCityId(id);
		return new ResponseEntity<List<Location>>(locs, HttpStatus.OK);
	}*/
	
	
	@PostMapping("locs/{locName}/city/{cityId}")
	public ResponseEntity<Void> addLocation(@ApiParam(name = "locName", value = "Name of location", required = true) @PathVariable("locName") String locName,
											@ApiParam(name = "cityId", value = "Id of City", required = true) @PathVariable("cityId") Integer cityId, UriComponentsBuilder builder){
//		LOGGER.info("Adding organization. Location id -\"+ id");
		boolean flag = service.addLocationToCity(locName, cityId);
		if (!flag) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(builder.path("/org/{id}").buildAndExpand(Location.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	/*@ApiOperation(value = "${LocationController.getLocation.locations}", response = Location.class) 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@GetMapping("locs/{LocationId}/locs")
	public ResponseEntity<Location> getLocsOfLocation(@ApiParam(name = "id", value = "id of Location", required = true)@PathVariable("LocationId") Integer LocationId){
		Location Location = service.getLocsOfLocation(LocationId);
		return new ResponseEntity<Location>(Location, HttpStatus.OK);
	}
	
	@ApiOperation(value = "${LocationController.getCities}", response = Location.class) 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	//@GetMapping("locs")
	public ResponseEntity<List<Location>> getAllCities(){
		LOGGER.info("Getting details of all locs");
		List<Location> locs = service.getAllCities();
		return new ResponseEntity<List<Location>>(locs, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "${LocationController.addLocation}") 
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Successfully created resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resources"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 409, message = "Resource with given name already exists"),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	

	@PutMapping("Location/{id}")
	public ResponseEntity<Location> updateLocation(@PathVariable("id") Integer id, @RequestBody Location Location){
		LOGGER.info("Updating organization. Organization id -"+ id);
		//logger.info("Updating with id {}", id);
		Location currentLocation = service.getLocationById(id);

		if (currentLocation == null) {
			//logger.error("Unable to update. Org with id {} not found.", id);
			return new ResponseEntity("Unable to update. Org with id "+id+" not found.", HttpStatus.NOT_FOUND);
		}

		currentLocation.setName(Location.getName());

		service.updateLocation(Location);
		return new ResponseEntity<Location>(Location, HttpStatus.OK);
	}

	@DeleteMapping("Location/{id}")
	public ResponseEntity<Void> deleteLocation(@PathVariable("id") Integer id){
		LOGGER.info("Deleting organization. Organization id -"+ id);
		service.deleteLocation(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}*/

}
