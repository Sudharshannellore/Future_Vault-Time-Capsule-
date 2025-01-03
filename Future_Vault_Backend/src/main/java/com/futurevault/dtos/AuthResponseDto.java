package com.futurevault.dtos;

import java.util.List;

public class AuthResponseDto {

    private Long userid;
    private String username;
    private String useremail;
    private List<CapsuleUserResponseDto> capsuleUserResponseDto;

    public AuthResponseDto() {
        System.out.println("AuthResponseDto Activated");
    }

    public AuthResponseDto(Long userid, String username, String useremail) {
        this.userid = userid;
        this.username = username;
        this.useremail = useremail;
    }

    public AuthResponseDto(Long userid, String username, String useremail, List<CapsuleUserResponseDto> capsuleUserResponseDto) {
        this.userid = userid;
        this.username = username;
        this.useremail = useremail;
        this.capsuleUserResponseDto = capsuleUserResponseDto;
    }

    @Override
    public String toString() {
        return "AuthResponseDto{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", useremail='" + useremail + '\'' +
                ", capsuleUserResponseDto=" + capsuleUserResponseDto +
                '}';
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public List<CapsuleUserResponseDto> getCapsuleUserResponseDto() {
        return capsuleUserResponseDto;
    }

    public void setCapsuleUserResponseDto(List<CapsuleUserResponseDto> capsuleUserResponseDto) {
        this.capsuleUserResponseDto = capsuleUserResponseDto;
    }
}
