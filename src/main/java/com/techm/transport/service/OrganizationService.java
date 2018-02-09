package com.techm.transport.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.techm.transport.entity.Organization;

@Service
public class OrganizationService{

	private static final AtomicLong counter = new AtomicLong();
	private static List<Organization> list;

	static{
		list= populateDummy();
	}

	public List<Organization> getAllOrganizations(){
		return list;
	}

	public synchronized boolean addOrganization(Organization org) {
		if (list.contains(org)) {
			return false;
		} else {
			list.add(org);
			return true;
		}
	}


	public void updateOrganization(Organization org) {
		if (list.contains(org)) {
			int index = list.indexOf(org);
			list.set(index, org);
		} 
	}

	public void deleteOrganization(Integer id) {
		for (Organization organization : list) {
			if (organization.getId()==id) {
				list.remove(organization);
				break;
			}
		}
	}

	public Organization getOrganizationyId(Integer id) {
		Organization org = null;
		for (Organization organization : list) {
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
	
	private static List<Organization> populateDummy(){
		List<Organization> users = new ArrayList<Organization>();
		users.add(new Organization(counter.incrementAndGet(), "Tech Mahindra", new Date(), "Admin"));
		users.add(new Organization(counter.incrementAndGet(), "Nissan", new Date(), "Admin"));
		users.add(new Organization(counter.incrementAndGet(), "Arthrex", new Date(), "Admin"));
		users.add(new Organization(counter.incrementAndGet(), "Valeo", new Date(), "Admin"));
		return users;	
	}

}
