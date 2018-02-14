package com.techm.transport.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.techm.transport.entity.BoardingPoint;
import com.techm.transport.entity.City;
import com.techm.transport.entity.SampleData;
import com.techm.transport.service.BoardingPointService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@Controller
@RequestMapping("transport/1.0")
@Api(description="Boarding point operations",tags= {"Boarding Points"})
public class BoardingPointController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardingPointService service;

	@ApiOperation(value = "${BoardingPointController.getBoardingPoint}") 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resource of given id"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@GetMapping("boardpoints/{id}")
	public ResponseEntity<BoardingPoint> getBoardingPoint(@ApiParam(name = "id", value = "id of boarding point", required = true) @PathVariable("id") Integer id){
		LOGGER.info("Getting BoardingPoint details of id-" + id);
		BoardingPoint org = service.getBoardingPointyId(id);
		return new ResponseEntity<BoardingPoint>(org, HttpStatus.OK);
	}
	
	@ApiOperation(value = "${BoardingPointController.getAllBoardingPoint}") 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resources"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@GetMapping("boardpoints")
	public ResponseEntity<List<BoardingPoint>> getAllBoardingPoint(){
		LOGGER.info("Getting details of all BoardingPoints");
		List<BoardingPoint> orgs = service.getAllBoardingPoints();
		return new ResponseEntity<List<BoardingPoint>>(orgs, HttpStatus.OK);
	}
	
	@ApiOperation(value = "${BoardingPointController.addBoardingPoint}") 
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Successfully created resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resources"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 409, message = "Resource with given name already exists"),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@PostMapping("boardpoints/{name}")
	public ResponseEntity<Void> addBoardingPoint(@ApiParam(name = "name", value = "name of boarding point", required = true)@PathVariable("name") String name, UriComponentsBuilder builder){
		LOGGER.info("Adding BoardingPoint. BoardingPoint name -"+ name);
		boolean flag = service.addBoardingPoint(name);
		if (!flag) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(builder.path("/org/{id}").buildAndExpand(bp.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
