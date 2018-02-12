package com.techm.transport.entity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonPropertyOrder({"id", "name"})
public class Location {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name;
	
	private Integer cityId;
	
	@JsonIgnore
	private Integer journeyType;
	
	public Location(Integer id, String name, Integer cityId, Integer journeyType) {
		this.id = id;
		this.name = name;
		this.cityId = cityId;
		this.journeyType = journeyType;
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
	
	@JsonIgnore
	public Integer getCityId() {
		return cityId;
	}

	@JsonIgnore
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public Integer getJourneyType() {
		return journeyType;
	}
	public void setJourneyType(Integer journeyType) {
		this.journeyType = journeyType;
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
