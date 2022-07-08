package com.city.app.model;

import java.util.Objects;

public class City {
	private String cityId;
	private String name;
	private String photo;
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cityId, name, photo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(cityId, other.cityId) && Objects.equals(name, other.name)
				&& Objects.equals(photo, other.photo);
	}
	
	

}