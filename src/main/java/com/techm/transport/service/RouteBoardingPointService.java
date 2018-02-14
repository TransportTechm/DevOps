package com.techm.transport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.transport.entity.RouteBoardingPoint;
import com.techm.transport.entity.SampleData;

@Service
public class RouteBoardingPointService{
	
	@Autowired
	BoardingPointService boardingPointService;

	private static List<RouteBoardingPoint> list;

	static{
		list= SampleData.populateRouteBoardingPoints();
	}

	public List<RouteBoardingPoint> getAllRouteBoardingPoints(){
		return list;
	}

	public synchronized boolean addRouteBoardingPoint(RouteBoardingPoint org) {
		if (list.contains(org)) {
			return false;
		} else {
			list.add(org);
			return true;
		}
	}


	public void updateRouteBoardingPoint(RouteBoardingPoint org) {
		if (list.contains(org)) {
			int index = list.indexOf(org);
			list.set(index, org);
		} 
	}

	public void deleteRouteBoardingPoint(Integer id) {
		for (RouteBoardingPoint RouteBoardingPoint : list) {
			if (RouteBoardingPoint.getId()==id) {
				list.remove(RouteBoardingPoint);
				break;
			}
		}
	}

	public RouteBoardingPoint getRouteBoardingPointyId(Integer id) {
		RouteBoardingPoint org = null;
		for (RouteBoardingPoint RouteBoardingPoint : list) {
			if (RouteBoardingPoint.getId()==id) {
				org = RouteBoardingPoint;
				break;
			}
		}
		return org;
	}
	
	public RouteBoardingPoint getRouteBoardingPoints(Integer id) {
		RouteBoardingPoint org = null;
		for (RouteBoardingPoint RouteBoardingPoint : list) {
			if (RouteBoardingPoint.getRouteId().intValue()==id.intValue()) {
				org = RouteBoardingPoint;
				break;
			}
		}
		return org;
	}


}
