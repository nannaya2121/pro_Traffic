package org.go.traffic.service;

import java.util.List;

import org.go.traffic.model.CityDTO;

public interface CityService {

	List<CityDTO> cityFindAll();
	CityDTO cityFindByValue(String cityValue);

}
