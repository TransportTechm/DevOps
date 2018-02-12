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

@Controller
@RequestMapping("transport/1.0")
public class RouteController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RouteService routeService;
	
	@Autowired
	private RouteBusAllocationService routeBusAllocationService;


	@GetMapping("routes/{id}")
	public ResponseEntity<Route> getRouteBoardingPoints(@PathVariable("id") Integer id){
		LOGGER.info("Getting Route details of id-" + id);
		Route org = routeService.getRouteBoardingPoints(id);
		return new ResponseEntity<Route>(org, HttpStatus.OK);
	}

	@GetMapping("routes")
	public ResponseEntity<List<Route>> getAllRoute(){
		LOGGER.info("Getting details of all Routes");
		List<Route> orgs = routeService.getAllRoutes();
		return new ResponseEntity<List<Route>>(orgs, HttpStatus.OK);
	}

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
	}
	
	@GetMapping("routes/{locId}/{journeytypeId}")
	public ResponseEntity<List<Route>> getRouteBoardingPoint(@PathVariable("locId") Integer locId, @PathVariable("journeytypeId") Integer journeyTypeId){
//		LOGGER.info("Getting RouteBoardingPoint details of id-" + id);
		List<Route> routes = new ArrayList<Route>();
		List<Integer> routeBusAllocs = routeBusAllocationService.getRouteBusAllocation(locId, journeyTypeId);
		for (Integer routeNo : routeBusAllocs) {
			Route r = routeService.getRouteBoardingPoints(routeNo);
			routes.add(r);
		}
		return new ResponseEntity<List<Route>>(routes, HttpStatus.OK);
	}
}
