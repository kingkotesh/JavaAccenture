package com.demo.Movies_Database_Managemenet_System;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoginId")
    private int loginId;

    // Remove the @Column annotation for userName
    @Column(name = "Password", length = 10, nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "UserName", referencedColumnName = "UserName", foreignKey = @ForeignKey(name = "FK_Login_User"))
    private User user;

    // Constructors, Getters, and Setters
    
    public Login(int loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Login [loginId=" + loginId + ", password=" + password + "]";
    }
}
