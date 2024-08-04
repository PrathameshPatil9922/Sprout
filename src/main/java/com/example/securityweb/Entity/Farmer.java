package com.example.securityweb.Entity;

import com.example.securityweb.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Farmer {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private double fieldOfCane = 0.0;
	    public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Double getFieldOfCane() {
			return fieldOfCane;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		
		
		private String location;
		@Column(name = "phone")
		private String Phone;
		
		 @OneToOne
		    @JoinColumn(name = "user_id")
		    private User user;
		 
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public void setFieldOfCane(double fieldOfCane) {
			this.fieldOfCane = fieldOfCane;
		}
		public String getPhone() {
			return Phone;
		}
		public void setPhone(String Phone) {
			this.Phone = Phone;
		}

}
