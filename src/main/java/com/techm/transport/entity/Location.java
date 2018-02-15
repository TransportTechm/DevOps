package com.techm.transport.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonPropertyOrder({"id", "name"})
public class Location {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonIgnore
	private Integer cityId;
	
	@JsonInclude(Include.NON_NULL)
	private List<JourneyType> journeyTypes;
	
	public Location(Integer id, String name, Integer cityId) {
		this.id = id;
		this.name = name;
		this.cityId = cityId;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public List<JourneyType> getJourneyTypes() {
		return journeyTypes;
	}

	public void setJourneyTypes(List<JourneyType> journeyTypes) {
		this.journeyTypes = journeyTypes;
	}

	@Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (cityId ^ (cityId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        if (id != other.id && cityId != other.cityId)
            return false;
        return true;
    }

}
