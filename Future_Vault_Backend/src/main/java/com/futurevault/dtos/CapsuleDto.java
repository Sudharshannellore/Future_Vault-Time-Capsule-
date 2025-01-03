package com.futurevault.dtos;

public class CapsuleDto {

    private String title;
    private String message;
    private String targetemail;
    private Long targetmobile;
    private String unloackdatetime;

    public CapsuleDto() {
        System.out.println("Capsule Dto Activated");
    }

    public CapsuleDto(String title, String message, String targetemail, Long targetmobile, String unloackdatetime) {
        this.title = title;
        this.message = message;
        this.targetemail = targetemail;
        this.targetmobile = targetmobile;
        this.unloackdatetime = unloackdatetime;
    }

    @Override
    public String toString() {
        return "CapsuleDto{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", targetemail='" + targetemail + '\'' +
                ", targetmobile=" + targetmobile +
                ", unloackdatetime='" + unloackdatetime + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTargetemail() {
        return targetemail;
    }

    public void setTargetemail(String targetemail) {
        this.targetemail = targetemail;
    }

    public Long getTargetmobile() {
        return targetmobile;
    }

    public void setTargetmobile(Long targetmobile) {
        this.targetmobile = targetmobile;
    }

    public String getUnloackdatetime() {
        return unloackdatetime;
    }

    public void setUnloackdatetime(String unloackdatetime) {
        this.unloackdatetime = unloackdatetime;
    }
}
