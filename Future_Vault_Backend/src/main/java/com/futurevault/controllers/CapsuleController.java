package com.futurevault.controllers;

import com.futurevault.dtos.CapsuleDto;
import com.futurevault.dtos.CapsuleResponseDto;
import com.futurevault.dtos.CapsuleUserResponseDto;
import com.futurevault.services.CapsuleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/capsules")
public class CapsuleController {

    @Autowired
    private CapsuleServiceImp capsuleServiceImp;

    @PostMapping("/create/{userid}")
    public ResponseEntity<?> CREATE_CAPSULE(@RequestBody CapsuleDto dto, @PathVariable Long userid){

        CapsuleUserResponseDto isCapsule = capsuleServiceImp.CREATE(dto, userid);

        if (isCapsule !=null){
            return ResponseEntity.ok(isCapsule);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userid+" : User Not Exist..!");
        }

    }

    @GetMapping("/get/{capsuleid}")
    public ResponseEntity<?> GET_CAPSULE_RESPONSE(@PathVariable Long capsuleid){
        CapsuleResponseDto capsuleResponseDto = capsuleServiceImp.RESPONSE_DTO(capsuleid);
        if (capsuleResponseDto!=null){
            return ResponseEntity.ok(capsuleResponseDto);
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(capsuleid+" : Capsule Not Opened..!");
        }
    }

    @DeleteMapping("/delete/{capsuleid}")
    public ResponseEntity<?> DELETE_CAPSULE(@PathVariable Long capsuleid){

        capsuleServiceImp.DELETE_CAPSULE(capsuleid);

        return ResponseEntity.ok("CapsuleDeleted..!");
    }

}
