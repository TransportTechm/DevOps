package com.techm.transport.entity;

public class RouteBusAllocation {
	private Integer id;
	private Integer routeNo;
	private Integer vehicleRegId;
	private Integer locationId;
	
	public RouteBusAllocation(Integer id, Integer routeNo, Integer vehicleRegId, Integer locationId) {
		this.id=id;
		this.routeNo = routeNo;
		this.vehicleRegId = vehicleRegId;
		this.locationId = locationId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRouteNo() {
		return routeNo;
	}
	public void setRouteNo(Integer routeNo) {
		this.routeNo = routeNo;
	}
	public Integer getVehicleRegId() {
		return vehicleRegId;
	}
	public void setVehicleRegId(Integer vehicleRegId) {
		this.vehicleRegId = vehicleRegId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
}
