package com.futurevault.entites;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Capsules> capsules;

    public Users() {
        System.out.println("user entity Activated");
    }

    public Users(Long userid, String username, String password, String email, List<Capsules> capsules) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.capsules = capsules;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", capsules=" + capsules +
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Capsules> getCapsules() {
        return capsules;
    }

    public void setCapsules(List<Capsules> capsules) {
        this.capsules = capsules;
    }
}
