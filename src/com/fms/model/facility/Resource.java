package com.fms.model.facility;

public interface Resource {
	
	public String getResourceId();
	public String getResourceStatus();
	
	public void acceptVisitor(ResourceVisitor visitor);
	
	public void attachObserver(ResourceObserver observer);
	public void dettachObserver(ResourceObserver observer);
}
