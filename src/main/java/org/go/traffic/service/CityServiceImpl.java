package org.go.traffic.service;

import java.util.List;

import org.go.traffic.mapper.CityMapper;
import org.go.traffic.model.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired(required = true)
    private CityMapper mapper;

    @Override
    public List<CityDTO> cityFindAll() {
        return mapper.cityFindAll();
    }

	@Override
	public CityDTO cityFindByValue(String cityValue) {
		return mapper.cityFindByValue(cityValue);
	}

}
