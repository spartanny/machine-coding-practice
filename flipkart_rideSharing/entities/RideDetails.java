package fkRideSharing.entities;

import fkRideSharing.enums.TripStatus;

import java.util.Objects;

public class RideDetails {
	private int numberOfSeats;
  private int availableSeats;
  private String origin; // Will be better to use an enum here to mantain consistency in records
  private String destination;
  private Vehicle vehicle;
  private TripStatus tripStatus;

  public TripStatus getTripStatus() {
    return tripStatus;
  }

  public void setTripStatus(TripStatus tripStatus) {
    this.tripStatus = tripStatus;
  }

  public void setNumberOfSeats(int numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public RideDetails(int numberOfSeats, String origin, String destination) {
    this.numberOfSeats = numberOfSeats;
    this.availableSeats = numberOfSeats;
    this.origin = origin;
    this.destination = destination;
    this.tripStatus = TripStatus.OFFERED;
  }

	public int getNumberOfSeats() {
    return numberOfSeats;
  }
  
  public String getOrigin() {
    return origin;
  }

  public String getDestination() {
    return destination;
  }


  public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
  }

  public int getAvailableSeats() {
    return availableSeats;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RideDetails that = (RideDetails) o;
    return Objects.equals(origin, that.origin) && Objects.equals(destination, that.destination);
  }

  @Override
  public String toString() {
    return "RideDetails{" +
        "availableSeats=" + availableSeats +
        ", origin='" + origin + '\'' +
        ", destination='" + destination + '\'' +
        ", vehicle=" + vehicle +
        ", tripStatus=" + tripStatus +
        '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(origin, destination);
  }
}
