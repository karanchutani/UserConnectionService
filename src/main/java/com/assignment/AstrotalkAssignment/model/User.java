package com.assignment.AstrotalkAssignment.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String mobile;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_detail_id")
    private AddresEntity address;

    private boolean isCreated;

    @ManyToMany(targetEntity = User.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> connections;

    public User(){}

    public User(String email, String mobile, boolean isCreated) {
        this.email = email;
        this.mobile = mobile;
        this.isCreated = isCreated;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public AddresEntity getAddress() {
        return address;
    }

    public void setAddress(AddresEntity address) {
        this.address = address;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setIsCreated(boolean isCreated) {
        this.isCreated = isCreated;
    }

    public List<User> getConnections() {
        return connections;
    }

    public void setConnections(List<User> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address=" + address +
                ", isCreated=" + isCreated +
                ", connections=" + connections +
                '}';
    }
}
