package com.techm.transport.entity;

public class RouteBusAllocation {
	private Integer id;
	private Integer routeNo;
	private Integer vehicleRegId;
	private Integer journeyTypeId;
	
	public RouteBusAllocation(Integer id, Integer routeNo, Integer vehicleRegId, Integer journeyTypeId) {
		this.id=id;
		this.routeNo = routeNo;
		this.vehicleRegId = vehicleRegId;
		this.journeyTypeId = journeyTypeId;
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
	public Integer getJourneyTypeId() {
		return journeyTypeId;
	}
	public void setJourneyTypeId(Integer journeyTypeId) {
		this.journeyTypeId = journeyTypeId;
	}
	
}
