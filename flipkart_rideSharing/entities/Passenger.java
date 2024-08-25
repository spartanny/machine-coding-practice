package fkRideSharing.entities;


public class Passenger {
  private int numberOfSeats;
  private RideDetails rideDetails; // One Passenger/ account can be a part of one active ride at a time

  public Passenger(int numberOfSeats, RideDetails rideDetails, String userName) {
    this.numberOfSeats = numberOfSeats;
    this.rideDetails = rideDetails;
  }

  public int getNumberOfSeats() {
    return numberOfSeats;
  }

  public RideDetails getRideDetails() {
    return rideDetails;
  }

}
