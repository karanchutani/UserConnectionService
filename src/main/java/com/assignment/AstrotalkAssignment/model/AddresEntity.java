package com.assignment.AstrotalkAssignment.model;

import javax.persistence.*;

@Entity
@Table
public class AddresEntity extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String location;

    private String pinCode;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "AddresEntity{" +
                "addressId=" + addressId +
                ", location='" + location + '\'' +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }
}
