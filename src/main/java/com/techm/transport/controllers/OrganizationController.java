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

import com.techm.transport.entity.Organization;
import com.techm.transport.service.OrganizationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("transport/1.0")
public class OrganizationController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrganizationService service;


	@GetMapping("org/{id}")
	@ApiOperation(value = "Get all organizations", 
			      notes="",
			      response=Organization.class,
			      responseContainer="")
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Organization with given username does not exist"),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	public ResponseEntity<Organization> getOrganization(@ApiParam(name = "id", value = "id of organization", required = true) @PathVariable("id") Integer id){
		LOGGER.info("Getting organization details of id-" + id);
		Organization org = service.getOrganizationyId(id);
		return new ResponseEntity<Organization>(org, HttpStatus.OK);
	}

	@GetMapping("orgs")
	public ResponseEntity<List<Organization>> getAllOrganization(){
		LOGGER.info("Getting details of all organizations");
		List<Organization> orgs = service.getAllOrganizations();
		return new ResponseEntity<List<Organization>>(orgs, HttpStatus.OK);
	}

	@PostMapping("orgs")
	public ResponseEntity<Void> addOrganization(@RequestBody Organization org, UriComponentsBuilder builder){
		LOGGER.info("Adding organization. Organization id -\"+ id");
		boolean flag = service.addOrganization(org);
		if (!flag) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/org/{id}").buildAndExpand(org.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("org/{id}")
	public ResponseEntity<Organization> updateOrganization(@PathVariable("id") Integer id, @RequestBody Organization org){
		LOGGER.info("Updating organization. Organization id -"+ id);
		//logger.info("Updating with id {}", id);
		Organization currentOrg = service.getOrganizationyId(id);

		if (currentOrg == null) {
			//logger.error("Unable to update. Org with id {} not found.", id);
			return new ResponseEntity("Unable to update. Org with id "+id+" not found.", HttpStatus.NOT_FOUND);
		}

		currentOrg.setOrgName(org.getOrgName());
		currentOrg.setUpdatedAt(new Date());

		service.updateOrganization(org);
		return new ResponseEntity<Organization>(org, HttpStatus.OK);
	}

	@DeleteMapping("org/{id}")
	public ResponseEntity<Void> deleteOrganization(@PathVariable("id") Integer id){
		LOGGER.info("Deleting organization. Organization id -"+ id);
		service.deleteOrganization(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
