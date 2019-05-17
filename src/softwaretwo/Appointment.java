/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaretwo;

/**
 *
 * @author Dan Burke
 */
public class Appointment {
    
    private Integer appId;
    private Integer custId;

    private String customerName;
    private String title;
    private String descrip;
    private String location;
    private String contact;
    private String url;
    private String start;
    private String end;
    private String createdBy;
    private String createDate;
    private String lastUpdate;
    private String lastUpdateBy;
    
    public Appointment(Integer appId, Integer custId, String title, String descrip, String location, String contact, String url, String start, String end, String createdBy, String createDate, String lastUpdate, String lastUpdateBy) {
        this.appId = appId;
        this.custId = custId;
        this.title = title;
        this.descrip = descrip;
        this.location = location;
        this.contact = contact;
        this.url = url;
        this.start = start;
        this.end = end;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }
    
    public Appointment(Integer appId, String custName, String title, String descrip, String location, String contact, String url, String start, String end, String createdBy, String createDate, String lastUpdate, String lastUpdateBy) {
        this.appId = appId;
        this.customerName = custName;
        this.title = title;
        this.descrip = descrip;
        this.location = location;
        this.contact = contact;
        this.url = url;
        this.start = start;
        this.end = end;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    
    
      public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
    
        
    
}
