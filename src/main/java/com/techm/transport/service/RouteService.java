package com.techm.transport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.transport.entity.BoardingPoint;
import com.techm.transport.entity.Route;
import com.techm.transport.entity.RouteBoardingPoint;
import com.techm.transport.entity.RouteBusAllocation;
import com.techm.transport.entity.SampleData;

@Service
public class RouteService{
	
	@Autowired
	RouteBoardingPointService routeBoardingPointService;
	
	@Autowired
	BoardingPointService boardingPointService;

	@Autowired
	RouteBusAllocationService routeBusAllocationService;

	private static List<Route> list;

	static{
		list= SampleData.populateRoutes();
	}

	/*public List<Route> getAllRoutes(){
		for (Route route : list) {
			int routeId = route.getRouteNo().intValue();
			List<BoardingPoint> bpoints = new ArrayList<BoardingPoint>();
			for (RouteBoardingPoint rbp : routeBoardingPointService.getAllRouteBoardingPoints()) {
				if (rbp.getRouteId().intValue()==routeId) {
					int bid = rbp.getBoardPointId();
					BoardingPoint bp = boardingPointService.getBoardingPointyId(bid);
					bpoints.add(bp);
				}
			}
			route.setBpoints(bpoints);
		}
		return list;
	}*/

	public synchronized boolean addRoute(Route org) {
		if (list.contains(org)) {
			return false;
		} else {
			list.add(org);
			return true;
		}
	}


	public void updateRoute(Route org) {
		if (list.contains(org)) {
			int index = list.indexOf(org);
			list.set(index, org);
		} 
	}

	public void deleteRoute(Integer id) {
		for (Route Route : list) {
			if (Route.getRouteNo()==id) {
				list.remove(Route);
				break;
			}
		}
	}

	public Route getRouteById(Integer id) {
		Route org = null;
		for (Route Route : list) {
			if (Route.getRouteNo().intValue()==id.intValue()) {
				org = Route;
				break;
			}
		}
		return org;
	}
	
	public Route getRouteBoardingPoints(Integer id) {
		Route route = getRouteById(id);
		List<BoardingPoint> bpoints = new ArrayList<BoardingPoint>();
		for (RouteBoardingPoint rbp : routeBoardingPointService.getAllRouteBoardingPoints()) {
			if (rbp.getRouteId().intValue()==id.intValue()) {
				int bid = rbp.getBoardPointId();
				BoardingPoint bp = boardingPointService.getBoardingPointyId(bid);
				bpoints.add(bp);
			}
		}
		route.setBpoints(bpoints);
		return route;
	}
	
	public List<Route> getRoutesOfLocation(Integer journeyTypeId){
		List<Route> routes = new ArrayList<Route>();
		List<RouteBusAllocation> routeIds = routeBusAllocationService.getRoutesOfjourneyTypeId(journeyTypeId);
		for (RouteBusAllocation routeBusAlloc : routeIds) {
			Route r = getRouteBoardingPoints(routeBusAlloc.getRouteNo());
			r.setVehicleRegNO(routeBusAlloc.getVehicleRegId());
			r.setSeatCapacity(routeBusAlloc.getSeatCapacity());
			routes.add(r);
		}
		return routes;
	}
	public Route getRoute(Integer routId){
		RouteBusAllocation rba = routeBusAllocationService.getRoutesByRouteId(routId);
		Route route = getRouteById(routId);
		route.setSeatCapacity(rba.getSeatCapacity());
		route.setVehicleRegNO(rba.getVehicleRegId());
		route.setBpoints(null);
		
		return route;
	}

	/*public Route getRouteByName(String orgName) {
		Route org = orgRepository.findByOrgName(orgName);
		return org;
	}*/
	
}
