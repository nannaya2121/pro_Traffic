package org.go.traffic.service;

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

}
