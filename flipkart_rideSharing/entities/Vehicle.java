package fkRideSharing.entities;

public class Vehicle {
    private String ownerName;
    private String type;
    private String registrationNumber;

    public Vehicle(String ownerName, String type, String registrationNumber) {
        this.ownerName = ownerName;
        this.type = type;
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
