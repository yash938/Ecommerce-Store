package com.Ecommerce.web.application.Model;

import com.Ecommerce.web.application.Domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String mobile;
    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @OneToMany
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    private Set<Coupon> usedCoupon = new HashSet<>();

    // Default Constructor
    public User() {
    }

    // All-Args Constructor
    public User(Long id, String email, String password, String fullName, String mobile, USER_ROLE role, Set<Address> addresses, Set<Coupon> usedCoupon) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.mobile = mobile;
        this.role = role;
        this.addresses = addresses;
        this.usedCoupon = usedCoupon;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Coupon> getUsedCoupon() {
        return usedCoupon;
    }

    public void setUsedCoupon(Set<Coupon> usedCoupon) {
        this.usedCoupon = usedCoupon;
    }

    // Equals and HashCode (Optional if needed for testing or business logic)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", role=" + role +
                '}';
    }
}
