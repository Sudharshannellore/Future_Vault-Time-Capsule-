package com.futurevault.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "capsules")
public class Capsules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long capsuleid;

    private String title;
    private String message;
    private String targetemail;
    private Long targetmobile;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime unlockdatetime;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnore
    private Users user;

    private Boolean isMessageSent = false;

    public Capsules() {
        System.out.println("Capsule Entity Activated");
    }

    public Capsules(Long capsuleid, String title, String message, String targetemail, Long targetmobile, LocalDateTime unlockdatetime, Users user, Boolean isMessageSent) {
        this.capsuleid = capsuleid;
        this.title = title;
        this.message = message;
        this.targetemail = targetemail;
        this.targetmobile = targetmobile;
        this.unlockdatetime = unlockdatetime;
        this.user = user;
        this.isMessageSent = isMessageSent;
    }

    @Override
    public String toString() {
        return "Capsules{" +
                "capsuleid=" + capsuleid +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", targetemail='" + targetemail + '\'' +
                ", targetmobile=" + targetmobile +
                ", unlockdatetime=" + unlockdatetime +
                ", user=" + user +
                ", isMessageSent=" + isMessageSent +
                '}';
    }

    public Long getCapsuleid() {
        return capsuleid;
    }

    public void setCapsuleid(Long capsuleid) {
        this.capsuleid = capsuleid;
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

    public LocalDateTime getUnlockdatetime() {
        return unlockdatetime;
    }

    public void setUnlockdatetime(LocalDateTime unlockdatetime) {
        this.unlockdatetime = unlockdatetime;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Boolean getMessageSent() {
        return isMessageSent;
    }

    public void setMessageSent(Boolean messageSent) {
        isMessageSent = messageSent;
    }
}
