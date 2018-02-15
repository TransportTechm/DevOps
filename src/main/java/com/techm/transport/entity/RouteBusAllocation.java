package com.techm.transport.entity;

public class RouteBusAllocation {
	private Integer id;
	private Integer routeNo;
	private String vehicleRegId;
	private Integer seatCapacity;
	private Integer journeyTypeId;
	
	public RouteBusAllocation(Integer id, Integer routeNo, String vehicleRegId, Integer seatCapacity, Integer journeyTypeId) {
		this.id=id;
		this.routeNo = routeNo;
		this.vehicleRegId = vehicleRegId;
		this.seatCapacity = seatCapacity;
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
	public String getVehicleRegId() {
		return vehicleRegId;
	}
	public void setVehicleRegId(String vehicleRegId) {
		this.vehicleRegId = vehicleRegId;
	}
	public Integer getJourneyTypeId() {
		return journeyTypeId;
	}
	public void setJourneyTypeId(Integer journeyTypeId) {
		this.journeyTypeId = journeyTypeId;
	}

	public Integer getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	
}
