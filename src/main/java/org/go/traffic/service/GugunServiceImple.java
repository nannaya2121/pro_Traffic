package org.go.traffic.service;

import java.util.List;

import org.go.traffic.mapper.GugunMapper;
import org.go.traffic.model.GugunDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GugunServiceImple implements GugunService {

    @Autowired
    private GugunMapper mapper;

    @Override
    public void gugunInsert(GugunDTO dto) {
        mapper.gugunInsert(dto);
    }

	@Override
	public List<GugunDTO> findAllGugun() {
		return mapper.findAllGugun();
	}

	@Override
	public List<GugunDTO> gugunOneList(String city_value) {
		return mapper.gugunOneList(city_value);
	}

	@Override
	public GugunDTO gugunFindByValue(String detailGugunValue) {
		return mapper.gugunFindByValue(detailGugunValue);
	}

}
