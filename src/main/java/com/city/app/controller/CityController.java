package com.city.app.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.city.app.model.City;
import com.city.app.service.CityService;

@CrossOrigin()
@RestController
@RequestMapping({ "/cities" })
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping
	public List<City> getAllCity() {
		return cityService.getAllCity();
	}

	@GetMapping(path = { "/{id}" })
	public City getCity(@PathVariable("id") String cityId) {
		return cityService.getCity(cityId);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public City deleteCity(@PathVariable("id") String cityId) {
		return cityService.deleteCity(cityId);
	}

	@PostMapping
	public City createCity(@RequestBody City city) {
		return cityService.addCity(city);
	}
	
	@PutMapping(path = { "/{id}" })
	//@RolesAllowed("ROLE_ADMIN")
	@RolesAllowed("ROLE_ADMIN")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public City updateCity(@PathVariable("id") String cityId,@RequestBody City city) {
		return cityService.updateCity(cityId,city);
	}

}