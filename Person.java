//Emma_Griffin_OOP2_Project
package OOP2_Project_MyShop;

import java.io.Serializable;

public class Person implements Serializable {

    //attributes
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNum;

    //no args constructor
    public Person() {
        this("unknown", "unknown", "unknown", "unknown");
    }

    //contructor with args
    public Person(String firstName, String lastName, String address, String phoneNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    //mutators -- they set
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    //accessors -- they get
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    @Override
    public String toString() {
        return
                "First Name: " + firstName + "\n" +
                        "Last Name: " + lastName + "\n" +
                        "Address: " + address + "\n" +
                        "Phone Number: " + phoneNum + "\n";
    }

}