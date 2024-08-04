package com.example.securityweb.DTO;

import com.example.securityweb.User;
import com.example.securityweb.Entity.Farmer;


public class FarmerRegistrationDTO {

    private User user;
    private Farmer farmer;

    public FarmerRegistrationDTO() {
        this.user = new User();
        this.farmer = new Farmer();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}

