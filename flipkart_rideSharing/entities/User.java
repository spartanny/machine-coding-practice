package fkRideSharing.entities;

import fkRideSharing.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class User {
  private String name;
  private Gender gender;
  private int age;
  private List<Vehicle> vehicleList = new ArrayList<>();
  private int ridesTaken;
  private int ridesPublished;

  public User(String name, Gender gender, int age) {
    this.name = name;
    this.gender = gender;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public List<Vehicle> getVehicleList() {
    return vehicleList;
  }

  public void setVehicleList(List<Vehicle> vehicleList) {
    this.vehicleList = vehicleList;
  }

  public void incrementRidesTaken() {
    ++ridesTaken;
  }

  public void incrementRidesPublished() {
    ++ridesPublished;
  }

  @Override
  public String toString() {
    return name + " : " + ridesTaken + " Taken, " + ridesPublished + " Offered";
  }


}
