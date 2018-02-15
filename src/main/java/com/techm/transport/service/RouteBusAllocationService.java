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

	public List<RouteBusAllocation> getRoutesOfjourneyTypeId(Integer journeyTypeId) {
		List<RouteBusAllocation> routeBusAllocs = new ArrayList<RouteBusAllocation>();
		for (RouteBusAllocation rbusAllo : list) {
			Integer jtId = rbusAllo.getJourneyTypeId();
			if (jtId.intValue()==journeyTypeId.intValue()) {
				routeBusAllocs.add(rbusAllo);
			}
		}
		return routeBusAllocs;
	}
	public RouteBusAllocation getRoutesByRouteId(Integer routeId) {
		RouteBusAllocation rba=null;
		for (RouteBusAllocation rbusAllo : list) {
			if (rbusAllo.getRouteNo().intValue()==routeId.intValue()) {
				rba=rbusAllo;
				break;
			}
		}
		return rba;
	}

}
