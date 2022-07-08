package com.city.app.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.city.app.model.City;
import com.city.app.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	private final static String COMMA_DELIMITER = ",";

	@Autowired
	private ResourceLoader resourceLoader;

	private List<City> cityList = new ArrayList<City>();

	@Override
	public City addCity(City city) {
		cityList.add(city);
		return city;
	}

	@Override
	public City updateCity(String cityId, City city) {
		City updateCity = null;
		for (City c : cityList) {
			if (c.getCityId().equals(cityId)) {
				c.setName(city.getName());
				c.setPhoto(city.getPhoto());
				updateCity = c;
				break;
			}
		}
		return updateCity;
	}

	@Override
	public City deleteCity(String cityId) {
		City deletedCity = null;
		for (City city : cityList) {
			if (city.getCityId().equals(cityId)) {
				cityList.remove(city);
				deletedCity = city;
				break;
			}
		}
		return deletedCity;
	}

	@Override
	public List<City> getAllCity() {
		return this.cityList;
	}

	@Override
	public City getCity(String cityId) {
		City city = null;
		for (City c : cityList) {
			if (c.getCityId().equals(cityId)) {
				city = c;
				break;
			}
		}
		return city;

	}

	@PostConstruct
	public void load() throws FileNotFoundException, IOException {

		Resource resource = resourceLoader.getResource("classpath:assests/csv/cities.csv");

		try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"))) {
			String line;
			int skipHeader = 0;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_DELIMITER);
				if (skipHeader >= 1) {
					City city = new City();
					city.setCityId(values[0]);
					city.setName(values[1]);
					city.setPhoto(values[2]);
					cityList.add(city);
				}

				skipHeader++;
			}
		}
	}

}
