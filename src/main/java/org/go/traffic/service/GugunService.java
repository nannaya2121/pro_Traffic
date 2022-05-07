package org.go.traffic.service;

import java.util.List;

import org.go.traffic.model.GugunDTO;

public interface GugunService {

    void gugunInsert(GugunDTO dto);

	List<GugunDTO> findAllGugun();

	List<GugunDTO> gugunOneList(String city_value);

	GugunDTO gugunFindByValue(String detailGugunValue);

}
