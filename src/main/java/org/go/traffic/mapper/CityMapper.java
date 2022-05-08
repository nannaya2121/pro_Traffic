package org.go.traffic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.go.traffic.model.CityDTO;

@Mapper
public interface CityMapper {

    List<CityDTO> cityFindAll();

	CityDTO cityFindByValue(String cityValue);

}
