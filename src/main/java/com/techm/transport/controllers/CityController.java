package com.techm.transport.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.techm.transport.entity.City;
import com.techm.transport.entity.Organization;
import com.techm.transport.service.CityService;
import com.techm.transport.service.OrganizationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("transport/1.0")
public class CityController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CityService service;


	@GetMapping("cities/{id}")
	@ApiOperation(value = "Get City Details") 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Get all Cities"),
			@ApiResponse(code = 404, message = "Organization with given username does not exist"),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	public ResponseEntity<City> getCity(@ApiParam(name = "id", value = "id of city", required = true) @PathVariable("id") Integer id){
		LOGGER.info("Getting organization details of id-" + id);
		City city = service.getCityById(id);
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}
	
	
	@GetMapping("cities/{cityId}/locs")
	public ResponseEntity<City> getLocsOfcity(@PathVariable("cityId") Integer cityId){
		City city = service.getLocsOfcity(cityId);
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}

	@GetMapping("cities")
	public ResponseEntity<List<City>> getAllCities(){
		LOGGER.info("Getting details of all cities");
		List<City> cities = service.getAllCities();
		return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	}

	/*@PostMapping("cities/{orgName}/city/{cityName}")
	public ResponseEntity<Void> addCity(@PathVariable("cityName") Integer cityName, UriComponentsBuilder builder){
		LOGGER.info("Adding organization. City id -\"+ id");
		boolean flag = service.addCity(cityName);
		if (!flag) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/org/{id}").buildAndExpand(city.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}*/

	/*@PutMapping("city/{id}")
	public ResponseEntity<City> updateCity(@PathVariable("id") Integer id, @RequestBody City city){
		LOGGER.info("Updating organization. Organization id -"+ id);
		//logger.info("Updating with id {}", id);
		City currentCity = service.getCityById(id);

		if (currentCity == null) {
			//logger.error("Unable to update. Org with id {} not found.", id);
			return new ResponseEntity("Unable to update. Org with id "+id+" not found.", HttpStatus.NOT_FOUND);
		}

		currentCity.setName(city.getName());

		service.updateCity(city);
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}

	@DeleteMapping("city/{id}")
	public ResponseEntity<Void> deleteCity(@PathVariable("id") Integer id){
		LOGGER.info("Deleting organization. Organization id -"+ id);
		service.deleteCity(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}*/

}
