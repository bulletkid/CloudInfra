package com.manuanand.cloudinfra;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Node {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String partNumber;

	private NodeVendor vendor;

	private DataCenterLocation location;

	private int totalCores;
	
	private int usedCores;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public NodeVendor getVendor() {
		return vendor;
	}

	public void setVendor(NodeVendor vendor) {
		this.vendor = vendor;
	}

	public DataCenterLocation getLocation() {
		return location;
	}

	public void setLocation(DataCenterLocation location) {
		this.location = location;
	}

	public int getTotalCores() {
		return totalCores;
	}

	public void setTotalCores(int totalCores) {
		this.totalCores = totalCores;
	}

	public int getUsedCores() {
		return usedCores;
	}

	public void setUsedCores(int usedCores) {
		this.usedCores = usedCores;
	}
}
