package org.go.traffic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.go.traffic.model.GugunDTO;

@Mapper
public interface GugunMapper {

    void gugunInsert(GugunDTO dto);

}
