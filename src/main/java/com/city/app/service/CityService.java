package com.city.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.city.app.model.City;

public interface CityService {
	
	City addCity(City city);
	
	City updateCity(String cityId,City city);
	
	City deleteCity(String cityId);
	
	List<City> getAllCity();

	City getCity(String cityId);
	
    void load() throws FileNotFoundException, IOException;
	
}
