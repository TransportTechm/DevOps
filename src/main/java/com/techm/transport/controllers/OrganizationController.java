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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.techm.transport.entity.City;
import com.techm.transport.entity.Organization;
import com.techm.transport.service.CityService;
import com.techm.transport.service.OrganizationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


//@CrossOrigin(origins="/**")
@Controller
@RequestMapping("transport/1.0")
@Api(description="Organization operations",tags= {"Organizations"})
public class OrganizationController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrganizationService orgService;
	
	@ApiOperation(value = "${OrganizationController.getOrganization}") 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resource of given id"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@GetMapping("orgs/{id}")
	public ResponseEntity<Organization> getOrganization(@ApiParam(name = "id", value = "id of organization", required = true) @PathVariable("id") Integer id){
		LOGGER.info("Getting organization details of id-" + id);
		Organization org = orgService.getOrganizationById(id);
		return new ResponseEntity<Organization>(org, HttpStatus.OK);
	}
	
	/*@ApiOperation(value = "${OrganizationController.getAllOrganization}") 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get all resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@GetMapping("orgs")
	public ResponseEntity<List<Organization>> getAllOrganization(){
		LOGGER.info("Getting details of all organizations");
		List<Organization> orgs = orgService.getAllOrganizations();
		return new ResponseEntity<List<Organization>>(orgs, HttpStatus.OK);
	}*/
	
	@ApiOperation(value = "${OrganizationController.addOrganization}") 
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Successfully created resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resources"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 409, message = "Resource with given name already exists"),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@PostMapping("orgs/{orgname}")
	public ResponseEntity<Void> addOrganization(@ApiParam(name = "orgname", value = "Name of organization", required = true) @PathVariable("orgname") String name, UriComponentsBuilder builder){
		LOGGER.info("Adding organization. Organization name -"+ name);
		boolean flag = orgService.addOrganization(name);
		if (!flag) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(builder.path("/org/{"+org.getId()+"}").buildAndExpand(org.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	/*@ApiOperation(value = "${OrganizationController.addCityInOrg}") 
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Successfully created resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resources"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 409, message = "Resource with given name already exists"),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@PostMapping("orgs/{orgId}/cities/{cityname}")
	public ResponseEntity<Void> addCityInOrg(@ApiParam(name = "orgId", value = "id of organization", required = true) @PathVariable("orgId") Integer orgId,  
											@ApiParam(name = "cityname", value = "name of city", required = true) @PathVariable("cityname") String cityName, UriComponentsBuilder builder){
		LOGGER.info("Adding organization. City id -\"+ id");
		boolean flag = cityService.addCity(orgId, cityName);
		if (!flag) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(builder.path("/org/{id}").buildAndExpand(city.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}*/
		
	@ApiOperation(value = "${OrganizationController.getCitiesOfOrg}") 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get all resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@GetMapping("orgs/{orgId}/cities")
	public ResponseEntity<Organization> getCitiesOfOrg(@ApiParam(name = "orgId", value = "Id of organization", required = true) @PathVariable("orgId") Integer orgId){
		Organization org = orgService.getCitiesOfOrg(orgId);
		return new ResponseEntity<Organization>(org, HttpStatus.OK);
	}
	/*@PutMapping("org/{id}")
	public ResponseEntity<Organization> updateOrganization(@PathVariable("id") Integer id, @RequestBody Organization org){
		LOGGER.info("Updating organization. Organization id -"+ id);
		//logger.info("Updating with id {}", id);
		Organization currentOrg = service.getOrganizationyId(id);

		if (currentOrg == null) {
			//logger.error("Unable to update. Org with id {} not found.", id);
			return new ResponseEntity("Unable to update. Org with id "+id+" not found.", HttpStatus.NOT_FOUND);
		}

		currentOrg.setOrgName(org.getOrgName());
//		currentOrg.setUpdatedAt(new Date());

		service.updateOrganization(org);
		return new ResponseEntity<Organization>(org, HttpStatus.OK);
	}

	@DeleteMapping("org/{id}")
	public ResponseEntity<Void> deleteOrganization(@PathVariable("id") Integer id){
		LOGGER.info("Deleting organization. Organization id -"+ id);
		service.deleteOrganization(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
*/
}
