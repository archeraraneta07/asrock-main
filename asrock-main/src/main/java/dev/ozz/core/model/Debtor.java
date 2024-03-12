package dev.ozz.core.model;

import dev.ozz.server.DebtorDAO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Debtor {
    private IntegerProperty debtorID;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty middleName;
    private StringProperty extensionName;
    private StringProperty email;
    private StringProperty phoneNumber;
    private StringProperty address;
    private IntegerProperty gender;
    private StringProperty jobName;
    private StringProperty tinid;
    private LongProperty monthly_Income;
    private StringProperty fullname;

    public Debtor(String firstName,
            String lastName,
            String middleName,
            String extensionName,
            String email,
            String phoneNumber,
            String address,
            int gender,
            String jobName,
            String tinid,
            long monthly_Income) {
        this(DebtorDAO.getLastId() + 1,
                firstName,
                lastName,
                middleName,
                extensionName,
                email,
                phoneNumber,
                address,
                gender,
                jobName,
                tinid,
                monthly_Income);
    }
    

    public Debtor(int debtorID,
            String firstName,
            String lastName,
            String middleName,
            String extensionName,
            String email,
            String phoneNumber,
            String address,
            int gender,
            String jobName,
            String tinid,
            long monthly_Income) {
        this.debtorID = new SimpleIntegerProperty(debtorID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.middleName = new SimpleStringProperty(middleName);
        this.extensionName = new SimpleStringProperty(extensionName);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.gender = new SimpleIntegerProperty(gender);
        this.address = new SimpleStringProperty(address);
        this.jobName = new SimpleStringProperty(jobName);
        this.tinid = new SimpleStringProperty(tinid);
        this.monthly_Income = new SimpleLongProperty(monthly_Income);
             
        if (middleName.isBlank()||middleName.isEmpty()) {
            this.fullname=new SimpleStringProperty(firstName+" "+middleName+" "+lastName+" "+extensionName);
        }else{
            this.fullname=new SimpleStringProperty(firstName+" "+middleName.charAt(0)+" "+lastName+" "+extensionName);
        }
        

    }

    // getters
    public int getDebtorID() {
        return debtorID.get();
    }

    public String getFirstname() {
        return firstName.get();
    }

    public String getLastname() {
        return lastName.get();
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public String getExtensionName() {
        return extensionName.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public int getGender() {
        return gender.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getJobName() {
        return jobName.get();
    }

    public String getTinid() {
        return tinid.get();
    }
    public String getfullname(){
        return fullname.get();
    }
    public long getmonthly_income() {
        return monthly_Income.get();
    }

    // setters
    public void setDebtorID(int debtorID) {
        this.debtorID.set(debtorID);
    }

    public void setFirstname(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastname(String lastName) {
        this.lastName.set(lastName);
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public void setExtensionName(String extensionName) {
        this.extensionName.set(extensionName);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setGender(int gender) {
        this.gender.set(gender);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setjobName(String jobName) {
        this.jobName.set(jobName);
    }

    public void setTinid(String tinid) {
        this.tinid.set(tinid);
    }

    public void setmonthly_income(long monthly_Income) {
        this.monthly_Income.set(monthly_Income);
    }

    // properties
    public IntegerProperty debtorIDProperty() {
        return debtorID;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public StringProperty extensionNameProperty() {
        return extensionName;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public IntegerProperty genderProperty() {
        return gender;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty jobNameProperty() {
        return jobName;
    }

    public StringProperty tinidProperty() {
        return tinid;
    }

    public LongProperty monthlyIncomeProperty() {
        return monthly_Income;
    }
    public StringProperty fullnameProperty(){
        return fullname;
    }
}
