/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaretwo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Megatron
 */
public class Customer {

    
    public Customer(int customerId, String customerName, int customerAddressId, int customerIsActive, String customerCreateDate, String customerCreatedBy, String customerLastUpdate, String customerLastUpdatedBy) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddressId = customerAddressId;
        this.customerIsActive = customerIsActive;
        this.customerCreateDate =customerCreateDate;
        this.customerCreatedBy = customerCreatedBy;
        this.customerLastUpdate = customerLastUpdate;
        this.customerLastUpdatedBy = customerLastUpdatedBy;
    }
    
    private Integer customerId;
    private String customerName;
    private Integer customerAddressId;
    private Integer customerIsActive;
    private String customerCreateDate;
    private String customerCreatedBy;
    private String customerLastUpdate;
    private String customerLastUpdatedBy;
    
    private String addressOne;
    private String addressTwo;
    private Integer cityId;

   
    private String city;
    private String postalCode;
    private String phone;
    private String country;

    
    private String addressCreateDate;
    private String addressCreatedBy;
    private String addressLastUpdate;
    private String addressLastUpdatedBy;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(Integer customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public Integer getCustomerIsActive() {
        return customerIsActive;
    }

    public void setCustomerIsActive(Integer customerIsActive) {
        this.customerIsActive = customerIsActive;
    }

    public String getCustomerCreateDate() {
        return customerCreateDate;
    }

    public void setCustomerCreateDate(String customerCreateDate) {
        this.customerCreateDate = customerCreateDate;
    }

    public String getCustomerCreatedBy() {
        return customerCreatedBy;
    }

    public void setCustomerCreatedBy(String customerCreatedBy) {
        this.customerCreatedBy = customerCreatedBy;
    }

    public String getCustomerLastUpdate() {
        return customerLastUpdate;
    }

    public void setCustomerLastUpdate(String customerLastUpdate) {
        this.customerLastUpdate = customerLastUpdate;
    }

    public String getCustomerLastUpdatedBy() {
        return customerLastUpdatedBy;
    }

    public void setCustomerLastUpdatedBy(String customerLastUpdatedBy) {
        this.customerLastUpdatedBy = customerLastUpdatedBy;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressCreateDate() {
        return addressCreateDate;
    }

    public void setAddressCreateDate(String addressCreateDate) {
        this.addressCreateDate = addressCreateDate;
    }

    public String getAddressCreatedBy() {
        return addressCreatedBy;
    }

    public void setAddressCreatedBy(String addressCreatedBy) {
        this.addressCreatedBy = addressCreatedBy;
    }

    public String getAddressLastUpdate() {
        return addressLastUpdate;
    }

    public void setAddressLastUpdate(String addressLastUpdate) {
        this.addressLastUpdate = addressLastUpdate;
    }

    public String getAddressLastUpdatedBy() {
        return addressLastUpdatedBy;
    }

    public void setAddressLastUpdatedBy(String addressLastUpdatedBy) {
        this.addressLastUpdatedBy = addressLastUpdatedBy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    
    
    
    
}
