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

    
    public Customer(String customerName, int customerAddressId, int customerIsActive, String customerCreateDate, String customerCreatedBy, String customerLastUpdate, String customerLastUpdatedBy) {
        this.customerName = new SimpleStringProperty(customerName);
        this.customerAddressId = new SimpleIntegerProperty(customerAddressId);
        this.customerIsActive = new SimpleIntegerProperty(customerIsActive);
        this.customerCreateDate = new SimpleStringProperty(customerCreateDate);
        this.customerCreatedBy = new SimpleStringProperty(customerCreatedBy);
        this.customerLastUpdate = new SimpleStringProperty(customerLastUpdate);
        this.customerLastUpdatedBy = new SimpleStringProperty(customerLastUpdatedBy);
    }
    

    private SimpleStringProperty customerName;
    private SimpleIntegerProperty customerAddressId;
    private SimpleIntegerProperty customerIsActive;
    private SimpleStringProperty customerCreateDate;
    private SimpleStringProperty customerCreatedBy;
    private SimpleStringProperty customerLastUpdate;
    private SimpleStringProperty customerLastUpdatedBy;
    
    private SimpleStringProperty addressOne;
    private SimpleStringProperty addressTwo;
    private SimpleIntegerProperty cityId;
    private SimpleStringProperty postalCode;
    private SimpleStringProperty phone;
    private SimpleStringProperty addressCreateDate;
    private SimpleStringProperty addressCreatedBy;
    private SimpleStringProperty addressLastUpdate;
    private SimpleStringProperty addressLastUpdatedBy;

    public SimpleStringProperty getCustomerName() {
        return customerName;
    }

    public void setCustomerName(SimpleStringProperty customerName) {
        this.customerName = customerName;
    }

    public SimpleIntegerProperty getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(SimpleIntegerProperty customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public SimpleIntegerProperty getCustomerIsActive() {
        return customerIsActive;
    }

    public void setCustomerIsActive(SimpleIntegerProperty customerIsActive) {
        this.customerIsActive = customerIsActive;
    }

    public SimpleStringProperty getCustomerCreateDate() {
        return customerCreateDate;
    }

    public void setCustomerCreateDate(SimpleStringProperty customerCreateDate) {
        this.customerCreateDate = customerCreateDate;
    }

    public SimpleStringProperty getCustomerCreatedBy() {
        return customerCreatedBy;
    }

    public void setCustomerCreatedBy(SimpleStringProperty customerCreatedBy) {
        this.customerCreatedBy = customerCreatedBy;
    }

    public SimpleStringProperty getCustomerLastUpdate() {
        return customerLastUpdate;
    }

    public void setCustomerLastUpdate(SimpleStringProperty customerLastUpdate) {
        this.customerLastUpdate = customerLastUpdate;
    }

    public SimpleStringProperty getCustomerLastUpdatedBy() {
        return customerLastUpdatedBy;
    }

    public void setCustomerLastUpdatedBy(SimpleStringProperty customerLastUpdatedBy) {
        this.customerLastUpdatedBy = customerLastUpdatedBy;
    }

    public SimpleStringProperty getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(SimpleStringProperty addressOne) {
        this.addressOne = addressOne;
    }

    public SimpleStringProperty getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(SimpleStringProperty addressTwo) {
        this.addressTwo = addressTwo;
    }

    public SimpleIntegerProperty getCityId() {
        return cityId;
    }

    public void setCityId(SimpleIntegerProperty cityId) {
        this.cityId = cityId;
    }

    public SimpleStringProperty getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(SimpleStringProperty postalCode) {
        this.postalCode = postalCode;
    }

    public SimpleStringProperty getPhone() {
        return phone;
    }

    public void setPhone(SimpleStringProperty phone) {
        this.phone = phone;
    }

    public SimpleStringProperty getAddressCreateDate() {
        return addressCreateDate;
    }

    public void setAddressCreateDate(SimpleStringProperty addressCreateDate) {
        this.addressCreateDate = addressCreateDate;
    }

    public SimpleStringProperty getAddressCreatedBy() {
        return addressCreatedBy;
    }

    public void setAddressCreatedBy(SimpleStringProperty addressCreatedBy) {
        this.addressCreatedBy = addressCreatedBy;
    }

    public SimpleStringProperty getAddressLastUpdate() {
        return addressLastUpdate;
    }

    public void setAddressLastUpdate(SimpleStringProperty addressLastUpdate) {
        this.addressLastUpdate = addressLastUpdate;
    }

    public SimpleStringProperty getAddressLastUpdatedBy() {
        return addressLastUpdatedBy;
    }

    public void setAddressLastUpdatedBy(SimpleStringProperty addressLastUpdatedBy) {
        this.addressLastUpdatedBy = addressLastUpdatedBy;
    }

    
    
    
    
}
