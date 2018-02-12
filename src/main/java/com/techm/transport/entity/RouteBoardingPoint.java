package com.techm.transport.entity;

import java.util.List;

public class RouteBoardingPoint {
	private Integer id;
	private Integer boardPointId;
	private Integer routeId;
	
	public RouteBoardingPoint(Integer id, Integer boardPointId, Integer routeId) {
		this.id = id;
		this.boardPointId = boardPointId;
		this.routeId = routeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBoardPointId() {
		return boardPointId;
	}
	public void setBoardPointId(Integer boardPointId) {
		this.boardPointId = boardPointId;
	}
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		RouteBoardingPoint other = (RouteBoardingPoint) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
