package com.fms.model.common;

public class Address {

		private String addressId;
		private String street;
		private String unit;
		private String city;
		private String state;
		private String zip;
		
		public String getAddressId() {
			return addressId;
		}
		
		public void setAddressId(String addressId) {
			this.addressId = addressId;
		}
		
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
}
