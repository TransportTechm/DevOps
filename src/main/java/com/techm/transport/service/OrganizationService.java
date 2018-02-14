package com.techm.transport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.transport.entity.City;
import com.techm.transport.entity.Organization;
import com.techm.transport.entity.SampleData;

@Service
public class OrganizationService{

	@Autowired
	CityService cityService;
	
	private static List<Organization> orgList;

	static{
		orgList= SampleData.populateOrgs();
	}
	
	

	public List<Organization> getAllOrganizations(){
		return orgList;
	}

	public synchronized boolean addOrganization(String orgName) {
		if (getOrganizationByName(orgName)!=null) {
			return false;
		} else {
			orgList.add(new Organization((int)SampleData.orgCounter.incrementAndGet(), orgName));
			return true;
		}
	}


	public void updateOrganization(Organization org) {
		if (orgList.contains(org)) {
			int index = orgList.indexOf(org);
			orgList.set(index, org);
		} 
	}

	public void deleteOrganization(Integer id) {
		for (Organization organization : orgList) {
			if (organization.getId()==id) {
				orgList.remove(organization);
				break;
			}
		}
	}

	public Organization getOrganizationById(Integer id) {
		Organization org = null;
		for (Organization organization : orgList) {
			if (organization.getId()==id) {
				org = organization;
				break;
			}
		}
		org.setCities(null);
		return org;
	}
	
	public Organization getOrganizationByName(String orgName) {
		Organization org = null;
		for (Organization organization : orgList) {
			if (organization.getOrgName().equalsIgnoreCase(orgName)) {
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
	
	public Organization getCitiesOfOrg(Integer orgId) {
		Organization org = getOrganizationById(orgId);
		List<City> cities = new ArrayList<City>();
		for (City c : cityService.getAllCities()) {
			if (c.getOrgId()==orgId) {
				cities.add(c);
			}
		}
		org.setCities(cities);
		return org;
	}

}
