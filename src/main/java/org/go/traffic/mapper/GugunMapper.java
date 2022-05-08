package org.go.traffic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.go.traffic.model.GugunDTO;

@Mapper
public interface GugunMapper {

    void gugunInsert(GugunDTO dto);
    List<GugunDTO> findAllGugun();
	List<GugunDTO> gugunOneList(String city_value);
	GugunDTO gugunFindByValue(String detailGugunValue);

}
