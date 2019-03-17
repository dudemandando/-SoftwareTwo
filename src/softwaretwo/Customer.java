/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaretwo;

/**
 *
 * @author Megatron
 */
public class Customer {

    public Customer(String customerName, int customerAddressId, int customerIsActive, String customerCreatedBy, String customerLastUpdate, String customerLastUpdatedBy, String addressOne, String addressTwo, int cityId, String postalCode, String phone, String addressCreateDate, String addressCreatedBy, String addressLastUpdate, String addressLastUpdatedBy) {
        this.customerName = customerName;
        this.customerAddressId = customerAddressId;
        this.customerIsActive = customerIsActive;
        this.customerCreatedBy = customerCreatedBy;
        this.customerLastUpdate = customerLastUpdate;
        this.customerLastUpdatedBy = customerLastUpdatedBy;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;
        this.addressCreateDate = addressCreateDate;
        this.addressCreatedBy = addressCreatedBy;
        this.addressLastUpdate = addressLastUpdate;
        this.addressLastUpdatedBy = addressLastUpdatedBy;
    }

    public Customer(String customerName, int customerAddressId, int customerIsActive, String customerCreateDate, String customerCreatedBy, String customerLastUpdate, String customerLastUpdatedBy) {
        this.customerName = customerName;
        this.customerAddressId = customerAddressId;
        this.customerIsActive = customerIsActive;
        this.customerCreateDate = customerCreateDate;
        this.customerCreatedBy = customerCreatedBy;
        this.customerLastUpdate = customerLastUpdate;
        this.customerLastUpdatedBy = customerLastUpdatedBy;
    }
    
    
    
    
    private String customerName;
    private int customerAddressId;
    private int customerIsActive;
    private String customerCreateDate;
    private String customerCreatedBy;
    private String customerLastUpdate;
    private String customerLastUpdatedBy;
    
    private String addressOne;
    private String addressTwo;
    private int cityId;
    private String postalCode;
    private String phone;
    private String addressCreateDate;
    private String addressCreatedBy;
    private String addressLastUpdate;
    private String addressLastUpdatedBy;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(int customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public int getCustomerIsActive() {
        return customerIsActive;
    }

    public void setCustomerIsActive(int customerIsActive) {
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
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
    
    
    
}
