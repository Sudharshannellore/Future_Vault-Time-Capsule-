package com.futurevault.dtos;

public class CapsuleResponseDto {

    private String title;
    private String message;
    private String targetemail;
    private Long targetmobile;
    private String openDateTime;

    public CapsuleResponseDto() {
        System.out.println("CapsuleResponseDto Activated");
    }

    public CapsuleResponseDto(String title, String message, String targetemail, Long targetmobile, String openDateTime) {
        this.title = title;
        this.message = message;
        this.targetemail = targetemail;
        this.targetmobile = targetmobile;
        this.openDateTime = openDateTime;
    }

    @Override
    public String toString() {
        return "CapsuleResponseDto{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", targetemail='" + targetemail + '\'' +
                ", targetmobile=" + targetmobile +
                ", openDateTime='" + openDateTime + '\'' +
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

    public String getOpenDateTime() {
        return openDateTime;
    }

    public void setOpenDateTime(String openDateTime) {
        this.openDateTime = openDateTime;
    }
}
