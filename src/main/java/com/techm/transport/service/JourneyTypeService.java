package com.techm.transport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.transport.entity.JourneyType;
import com.techm.transport.entity.SampleData;

@Service
public class JourneyTypeService{

	private static List<JourneyType> list;

	static{
		list= SampleData.populateJourneyType();
	}

	public List<JourneyType> getAllJourneyTypes(){
		return list;
	}

	public synchronized boolean addJourneyType(JourneyType org) {
		if (list.contains(org)) {
			return false;
		} else {
			list.add(org);
			return true;
		}
	}


	public void updateJourneyType(JourneyType org) {
		if (list.contains(org)) {
			int index = list.indexOf(org);
			list.set(index, org);
		} 
	}

	public void deleteJourneyType(Integer id) {
		for (JourneyType JourneyType : list) {
			if (JourneyType.getId()==id) {
				list.remove(JourneyType);
				break;
			}
		}
	}

	public JourneyType getJourneyTypeyId(Integer id) {
		JourneyType org = null;
		for (JourneyType JourneyType : list) {
			if (JourneyType.getId().intValue()==id.intValue()) {
				org = JourneyType;
				break;
			}
		}
		return org;
	}
	
	public List<JourneyType> getJourneyTypesByLocId(Integer id) {
		List<JourneyType> journeyTypes = new ArrayList<JourneyType>();
		for (JourneyType JourneyType : list) {
			if (JourneyType.getLocId().intValue()==id.intValue()) {
				journeyTypes.add(JourneyType);
			}
		}
		return journeyTypes;
	}

}
