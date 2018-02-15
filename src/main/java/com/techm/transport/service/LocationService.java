package com.techm.transport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.transport.entity.Location;
import com.techm.transport.entity.SampleData;

@Service
public class LocationService{
	
	@Autowired
	JourneyTypeService journeyTypeService;

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
	
	public synchronized boolean addLocationToCity(String locName, Integer cityId) {
		if (getLocationsByNameAndCityId(locName, cityId)!=null) {
			return false;
		} else {
			list.add(new Location((int)SampleData.locCounter.incrementAndGet(), locName, cityId));
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
		for (Location loc : list) {
			if (loc.getId().intValue()==id.intValue()) {
				list.remove(loc);
				break;
			}
		}
	}

	public Location getLocationById(Integer id) {
		Location currentloc = null;
		for (Location loc : list) {
			if (loc.getId().intValue()==id.intValue()) {
				currentloc = loc;
				break;
			}
		}
		currentloc.setJourneyTypes(null);
		return currentloc;
	}
	public List<Location> getLocationsByCityId(Integer id) {
		List<Location> locs = new ArrayList<Location>();
		for (Location loc : list) {
			if (loc.getCityId().intValue()==id.intValue()) {
				locs.add(loc);
			}
		}
		return locs;
	}
	public List<Location> getLocationsByNameAndCityId(String locName, Integer id) {
		List<Location> locs = new ArrayList<Location>();
		for (Location loc : list) {
			if (loc.getName().equalsIgnoreCase(locName) && loc.getCityId().intValue()==id.intValue()) {
				locs.add(loc);
			}
		}
		return locs;
	}
	
	public Location getJourneyTypesOfLocation(Integer id) {
		Location loc = getLocationById(id);
		loc.setJourneyTypes(journeyTypeService.getJourneyTypesByLocId(id));
		return loc;
	}

	/*public Location getLocationByName(String orgName) {
		Location org = orgRepository.findByOrgName(orgName);
		return org;
	}*/
	
}
