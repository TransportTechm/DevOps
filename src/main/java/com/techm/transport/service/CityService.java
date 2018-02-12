package com.techm.transport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.transport.entity.City;
import com.techm.transport.entity.Location;
import com.techm.transport.entity.Organization;
import com.techm.transport.entity.SampleData;

@Service
public class CityService{
	
	@Autowired
	LocationService locService;
	@Autowired
	OrganizationService orgService;

	private static List<City> list;

	static{
		list= SampleData.populateCities();
	}

	public List<City> getAllCities(){
		return list;
	}

	public synchronized boolean addCity(City city) {
		if (list.contains(city)) {
			return false;
		} else {
			list.add(city);
			return true;
		}
	}
	
	public synchronized boolean addCity(Integer orgId, String cityName) {
		if (getCityByNameNOrgId(orgId, cityName)!=null) {
			return false;
		} else {
			list.add(new City((int)SampleData.cityCounter.incrementAndGet(), cityName, orgId));
			return true;
		}
	}


	public void updateCity(City city) {
		if (list.contains(city)) {
			int index = list.indexOf(city);
			list.set(index, city);
		} 
	}

	public void deleteCity(Integer id) {
		for (City organization : list) {
			if (organization.getId()==id.intValue()) {
				list.remove(organization);
				break;
			}
		}
	}

	public City getCityById(Integer id) {
		City city = null;
		for (City cities : list) {
			if (cities.getId().intValue()==id.intValue()) {
				city = cities;
				break;
			}
		}
		return city;
	}
	
	public City getCityByName(String name) {
		City city = null;
		for (City c : list) {
			if (c.getName().equalsIgnoreCase(name)) {
				city = c;
				break;
			}
		}
		return city;
	}
	
	public City getCityByNameNOrgId(Integer id, String name) {
		City city = null;
		for (City c : list) {
			if (c.getName().equalsIgnoreCase(name) && c.getOrgId().intValue()==id.intValue()) {
				city = c;
				break;
			}
		}
		return city;
	}
	
	
	public City getLocsOfcity(Integer cityId) {
		City city = getCityById(cityId);
		List<Location> locs = new ArrayList<Location>();
		for (Location l : locService.getAllLocations()) {
			if (l.getCityId().intValue()==cityId.intValue()) {
				locs.add(l);
			}
		}
		city.setLocs(locs);
		return city;
	}

	/*public Organization getOrganizationByName(String orgName) {
		Organization org = orgRepository.findByOrgName(orgName);
		return org;
	}*/
	
}
