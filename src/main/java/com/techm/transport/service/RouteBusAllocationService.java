package com.techm.transport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.transport.entity.Location;
import com.techm.transport.entity.RouteBusAllocation;
import com.techm.transport.entity.SampleData;

@Service
public class RouteBusAllocationService {

	private static List<RouteBusAllocation> list;
	
	@Autowired
	LocationService locationService;

	static{
		list= SampleData.populateRouteBusAllocation();
	}

	public List<RouteBusAllocation> getAllRouteBusAllocations(){
		return list;
	}

	public synchronized boolean addRouteBusAllocation(RouteBusAllocation org) {
		if (list.contains(org)) {
			return false;
		} else {
			list.add(org);
			return true;
		}
	}


	public void updateRouteBusAllocation(RouteBusAllocation org) {
		if (list.contains(org)) {
			int index = list.indexOf(org);
			list.set(index, org);
		} 
	}

	public void deleteRouteBusAllocation(Integer id) {
		for (RouteBusAllocation RouteBusAllocation : list) {
			if (RouteBusAllocation.getId()==id) {
				list.remove(RouteBusAllocation);
				break;
			}
		}
	}

	public List<Integer> getRouteBusAllocation(Integer locId, Integer journeyTypeId) {
		List<Integer> routeBusAllocs = new ArrayList<Integer>();
		for (RouteBusAllocation rbusAllo : list) {
			Integer lid = rbusAllo.getLocationId();
			if (lid.intValue()==locId.intValue()) {
				Location loc = locationService.getLocationById(lid);
				if (loc.getJourneyType().intValue()==journeyTypeId.intValue()) {
					routeBusAllocs.add(rbusAllo.getRouteNo());
				}
			}
		}
		return routeBusAllocs;
	}

}
