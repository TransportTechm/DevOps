package com.techm.transport.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Route {
	
	private Integer routeNo;
	private String name;
	private String origin;
	private String destination;
	private String departureTime;
	
	private List<BoardingPoint> bpoints;
	
	public Route(Integer routeNo, String name, String origin, String destination, String departureTime) {
		this.routeNo=routeNo;
		this.name=name;
		this.origin=origin;
		this.destination=destination;
		this.departureTime=departureTime;
	}
	
	public Integer getRouteNo() {
		return routeNo;
	}
	public void setRouteNo(Integer routeNo) {
		this.routeNo = routeNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	public List<BoardingPoint> getBpoints() {
		return bpoints;
	}
	
	public void setBpoints(List<BoardingPoint> bpoints) {
		this.bpoints = bpoints;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (routeNo ^ (routeNo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (routeNo != other.routeNo)
			return false;
		return true;
	}
	
}
