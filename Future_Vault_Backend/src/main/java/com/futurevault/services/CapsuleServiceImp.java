package com.futurevault.services;

import com.futurevault.dtos.CapsuleDto;
import com.futurevault.dtos.CapsuleResponseDto;
import com.futurevault.dtos.CapsuleUserResponseDto;
import com.futurevault.entites.Capsules;
import com.futurevault.entites.Users;
import com.futurevault.repositories.CapsulesRepository;
import com.futurevault.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class CapsuleServiceImp implements CapsuleService  {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CapsulesRepository capsulesRepository;


    @Override
    public CapsuleUserResponseDto CREATE(CapsuleDto dto, Long userid) {

      if (usersRepository.existsById(userid)){

          Users users = usersRepository.findById(userid).get();
          Capsules capsules = new Capsules();

          capsules.setTitle(dto.getTitle());
          capsules.setMessage(dto.getMessage());
          capsules.setTargetemail(dto.getTargetemail());
          capsules.setTargetmobile(dto.getTargetmobile());

          String datetime = dto.getUnloackdatetime();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          LocalDateTime localDateTime = null;
          try {
              localDateTime = LocalDateTime.parse(datetime, formatter);
          } catch (Exception e) {
              e.printStackTrace();
           }

          capsules.setUnlockdatetime(localDateTime);
          capsules.setUser(users);

          users.getCapsules().add(capsules);


          capsulesRepository.save(capsules);
          usersRepository.save(users);

          CapsuleUserResponseDto capsuleUserResponseDto = new CapsuleUserResponseDto();
          capsuleUserResponseDto.setCapsuleid(capsules.getCapsuleid());
          capsuleUserResponseDto.setCapsuletitle(capsules.getTitle());
          capsuleUserResponseDto.setUnlockdateTime(capsules.getUnlockdatetime().toString());

           return capsuleUserResponseDto;
      }
      else {
          return null;
      }
    }


    @Override
    public CapsuleResponseDto RESPONSE_DTO(Long capsuleid) {
        Capsules capsules = capsulesRepository.findById(capsuleid).get();

        if (capsules.getUnlockdatetime().equals(LocalDateTime.now())||capsules.getUnlockdatetime().isBefore(LocalDateTime.now())){
            CapsuleResponseDto capsuleResponseDto = new CapsuleResponseDto(capsules.getTitle(),
                    capsules.getMessage(), capsules.getTargetemail(), capsules.getTargetmobile(), capsules.getUnlockdatetime().toString());
            return capsuleResponseDto;
        }
        else{
            return null;
        }
    }

    @Override
    public void DELETE_CAPSULE(Long capsuleid) {
        capsulesRepository.deleteById(capsuleid);
    }

}
