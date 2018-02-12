package com.techm.transport.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.transport.entity.Location;
import com.techm.transport.entity.SampleData;

@Service
public class LocationService{

	private static List<Location> list;

	static{
		list= SampleData.populateLocations();
	}

	public List<Location> getAllLocations(){
		return list;
	}

	public synchronized boolean addLocation(Location loc) {
		if (list.contains(loc)) {
			return false;
		} else {
			list.add(loc);
			return true;
		}
	}


	public void updateLocation(Location loc) {
		if (list.contains(loc)) {
			int index = list.indexOf(loc);
			list.set(index, loc);
		} 
	}

	public void deleteLocation(Integer id) {
		for (Location Location : list) {
			if (Location.getId()==id) {
				list.remove(Location);
				break;
			}
		}
	}

	public Location getLocationById(Integer id) {
		Location loc = null;
		for (Location Location : list) {
			if (Location.getId().intValue()==id.intValue()) {
				loc = Location;
				break;
			}
		}
		return loc;
	}

	/*public Location getLocationByName(String orgName) {
		Location org = orgRepository.findByOrgName(orgName);
		return org;
	}*/
	
}
