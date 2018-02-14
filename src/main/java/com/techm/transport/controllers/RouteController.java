package com.techm.transport.controllers;

import java.util.ArrayList;
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

import com.techm.transport.entity.Route;
import com.techm.transport.service.RouteBusAllocationService;
import com.techm.transport.service.RouteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("transport/1.0")
@Api(description="Route operations",tags= {"Routes"})
public class RouteController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RouteService routeService;
	
	@ApiOperation(value = "${RouteController.getRouteBoardingPoints}") 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resource of given id"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	//@GetMapping("routes/{id}")
	public ResponseEntity<Route> getRouteBoardingPoints(@PathVariable("id") Integer id){
		LOGGER.info("Getting Route details of id-" + id);
		Route org = routeService.getRouteBoardingPoints(id);
		return new ResponseEntity<Route>(org, HttpStatus.OK);
	}
	
	/*@ApiOperation(value = "${RouteController.getAllRoute}") 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@GetMapping("routes")
	public ResponseEntity<List<Route>> getAllRoute(){
		LOGGER.info("Getting details of all Routes");
		List<Route> orgs = routeService.getAllRoutes();
		return new ResponseEntity<List<Route>>(orgs, HttpStatus.OK);
	}
	
	@ApiOperation(value = "${RouteController.addRoute}") 
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Successfully created resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resources"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 409, message = "Resource with given name already exists"),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@PostMapping("routes")
	public ResponseEntity<Void> addRoute(@RequestBody Route route, UriComponentsBuilder builder){
		LOGGER.info("Adding Route. Route id -\"+ id");
		boolean flag = routeService.addRoute(route);
		if (!flag) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/org/{id}").buildAndExpand(route.getRouteNo()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}*/
	
	@ApiOperation(value = "${RouteController.getRouteForLocationAndJourneyType}") 
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully get resources"),
			@ApiResponse(code = 401, message = "Not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Not found resource you were trying to get."),
			@ApiResponse(code = 500, message = "Internal server error")
							}
					)
	@GetMapping("routes/{journeytypeId}")
	public ResponseEntity<List<Route>> getRoutesOfLocation(@ApiParam(name = "journeytypeId", value = "Id of journey type", required = true)@PathVariable("journeytypeId") Integer journeyTypeId){
//		LOGGER.info("Getting RouteBoardingPoint details of id-" + id);
		List<Route> routes = routeService.getRoutesOfLocation(journeyTypeId);
		
		/*List<Integer> routeBusAllocs = routeBusAllocationService.getRouteBusAllocation(locId, journeyTypeId);
		for (Integer routeNo : routeBusAllocs) {
			Route r = routeService.getRouteBoardingPoints(routeNo);
			routes.add(r);
		}*/
		return new ResponseEntity<List<Route>>(routes, HttpStatus.OK);
	}
}
