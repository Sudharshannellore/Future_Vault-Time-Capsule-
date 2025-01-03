package com.futurevault.dtos;

public class CapsuleUserResponseDto {

    private Long capsuleid;
    private String capsuletitle;
    private String unlockdateTime;

    public CapsuleUserResponseDto() {
        System.out.println("CapsuleUserResponseDto Activated");
    }

    public CapsuleUserResponseDto(Long capsuleid, String capsuletitle, String unlockdateTime) {
        this.capsuleid = capsuleid;
        this.capsuletitle = capsuletitle;
        this.unlockdateTime = unlockdateTime;
    }

    @Override
    public String toString() {
        return "CapsuleUserResponseDto{" +
                "capsuleid=" + capsuleid +
                ", capsuletitle='" + capsuletitle + '\'' +
                ", unlockdateTime='" + unlockdateTime + '\'' +
                '}';
    }

    public Long getCapsuleid() {
        return capsuleid;
    }

    public void setCapsuleid(Long capsuleid) {
        this.capsuleid = capsuleid;
    }

    public String getCapsuletitle() {
        return capsuletitle;
    }

    public void setCapsuletitle(String capsuletitle) {
        this.capsuletitle = capsuletitle;
    }

    public String getUnlockdateTime() {
        return unlockdateTime;
    }

    public void setUnlockdateTime(String unlockdateTime) {
        this.unlockdateTime = unlockdateTime;
    }
}
