package com.techm.transport.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.techm.transport.entity.City;
import com.techm.transport.entity.Organization;

@Service
public class CityService{

	private static final AtomicLong counter = new AtomicLong();
	private static List<City> list;

	static{
		list= populateDummy();
	}

	public List<City> getAllOrganizations(){
		return list;
	}

	public synchronized boolean addOrganization(City org) {
		if (list.contains(org)) {
			return false;
		} else {
			list.add(org);
			return true;
		}
	}


	public void updateOrganization(City org) {
		if (list.contains(org)) {
			int index = list.indexOf(org);
			list.set(index, org);
		} 
	}

	public void deleteOrganization(Integer id) {
		for (City organization : list) {
			if (organization.getId()==id) {
				list.remove(organization);
				break;
			}
		}
	}

	public City getOrganizationyId(Integer id) {
		City org = null;
		for (City organization : list) {
			if (organization.getId()==id) {
				org = organization;
				break;
			}
		}
		return org;
	}

	/*public Organization getOrganizationByName(String orgName) {
		Organization org = orgRepository.findByOrgName(orgName);
		return org;
	}*/
	
	private static List<City> populateDummy(){
		List<City> cities = new ArrayList<City>();
		cities.add(new City(counter.incrementAndGet(), "Bangalore", 1 ,new Date(), "Admin"));
		cities.add(new City(counter.incrementAndGet(), "Pune", 1 ,new Date(), "Admin"));
		cities.add(new City(counter.incrementAndGet(), "Mumbai", 1 ,new Date(), "Admin"));
		cities.add(new City(counter.incrementAndGet(), "Bangalore", 2 ,new Date(), "Admin"));
		cities.add(new City(counter.incrementAndGet(), "Bangalore", 3 ,new Date(), "Admin"));
		cities.add(new City(counter.incrementAndGet(), "Bangalore", 4 ,new Date(), "Admin"));
		return cities;	
	}

}
