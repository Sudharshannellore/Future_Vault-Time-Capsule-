package com.futurevault.services;

import com.futurevault.dtos.CapsuleDto;
import com.futurevault.dtos.CapsuleResponseDto;
import com.futurevault.dtos.CapsuleUserResponseDto;

import java.util.List;

public interface CapsuleService {

    public CapsuleUserResponseDto CREATE(CapsuleDto dto, Long userid);
    public CapsuleResponseDto RESPONSE_DTO(Long capsuleid);
    public void DELETE_CAPSULE(Long capsuleid);


}
