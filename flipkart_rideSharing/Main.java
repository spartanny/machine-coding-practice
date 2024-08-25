package fkRideSharing;

import fkRideSharing.entities.*;
import fkRideSharing.enums.Gender;
import fkRideSharing.strategy.ISelectionStrategy;
import fkRideSharing.strategy.MostVacantSelectionStrategy;
import fkRideSharing.strategy.PreferredVehicleSelectionStrategy;

import java.util.*;

public class Main {

  private static Map<String,User> platformUsers = new HashMap<>();
  private static Map<String, List<Vehicle>> userVehicles = new HashMap<>();
  private static Map<Vehicle, RideDetails> offeredRide = new HashMap<>();

  public static void main(String[] args) {
    System.out.println("******  Beginning the machine coding problem execution *******");

    //onboard users
    add_user("Rohan", Gender.M, 36);
    add_vehicle("Rohan","Swift","KA-01-12345");
    add_user("Shashank", Gender.M, 29);
    add_vehicle("Shashank", "Baleno", "TS-05-62395");
    add_user("Nandini", Gender.F, 29);
    add_user("Shipra", Gender.F, 27);
    add_vehicle("Shipra", "Polo", "KA-05-41491");
    add_vehicle("Shipra", "Activa", "KA-12-12332");
    add_user("Gaurav", Gender.M, 29);
    add_user("Rahul", Gender.M, 35);
    add_vehicle("Rahul", "XUV", "KA-05-1234");

    System.out.println("******  Offer rides  *******");
    offer_ride("Rohan", "Hyderabad","Bangalore",1 ,"Swift", "KA-01-12345");
    offer_ride("Shipra", "Bangalore","Mysore",1 ,"Activa", "KA-12-12332");
    offer_ride("Shipra", "Bangalore","Mysore",2 ,"Polo", "KA-05-41491");
    offer_ride("Shashank", "Hyderabad","Bangalore",2 ,"Baleno", "TS-05-62395");
    offer_ride("Rahul", "Hyderabad","Bangalore",5 ,"XUV",  "KA-05-1234");
    offer_ride("Rohan", "Bangalore","Pune",1 ,"Swift", "KA-01-12345");

    select_ride("Nandini","Bangalore", "Mysore", 1, new MostVacantSelectionStrategy());
    select_ride("Gaurav","Bangalore", "Mysore", 1, new PreferredVehicleSelectionStrategy("Activa"));
    select_ride("Shashank","Mumbai", "Bangalore", 1, new MostVacantSelectionStrategy());
    select_ride("Rohan","Hyderabad", "Bangalore", 1, new PreferredVehicleSelectionStrategy("Baleno"));
    select_ride("Shashank","Hyderabad", "Bangalore", 1, new PreferredVehicleSelectionStrategy("Polo"));

    print_ride_stats();
  }

  // endpoints

  private static void add_user(String name, Gender gender, int age) {
    User user = new User(name, gender, age);
    platformUsers.put(name, user);
  }

  private static void add_vehicle(String ownerName, String vehicleType, String registrationNumber) {
    var user = platformUsers.get(ownerName);
    if(user == null) {
      System.out.println("User is not onboarded yet !");
      return;
    }
    user.getVehicleList().add(new Vehicle(ownerName, vehicleType, registrationNumber));
    userVehicles.put(ownerName, user.getVehicleList());
  }

  private static boolean offer_ride(String userName, String origin, String destination, int seats, String vehicle, String regNum) {
    var vehicleOptional = userVehicles.get(userName).stream().filter(i -> i.getRegistrationNumber().equalsIgnoreCase(regNum)).findFirst();

    if(vehicleOptional.isEmpty() || offeredRide.get(vehicleOptional.get()) != null) {
      System.out.println("~~~~~~ Unable to offer ride ; already one active ride on this vehicle ~~~~~~");
      return false;
    }

    RideDetails rideDetails = new RideDetails(seats, origin, destination);
    rideDetails.setVehicle(vehicleOptional.get());

    offeredRide.put(vehicleOptional.get(), rideDetails);
    platformUsers.get(userName).incrementRidesPublished();
    System.out.println("~~~~~~ Ride created successfully! " + rideDetails + " ~~~~~~");
    return true;
  }

  private static RideDetails select_ride(String userName, String origin, String dest, int numSeats, ISelectionStrategy strategy) {
    if(origin == null || dest == null || numSeats <= 0 || numSeats > 2)
      return null;

    var requestingUser = platformUsers.get(userName);
    if(requestingUser == null) {
      throw new RuntimeException("Not a valid user in the system");
    }

    var currentRideRequest = new RideDetails(numSeats, origin, dest);
    // available rides between routes
    var resultRide = strategy.getRide(offeredRide.values().stream().toList(), currentRideRequest);
    if(resultRide != null) {
      int availableSeats = resultRide.getAvailableSeats() - numSeats;
      resultRide.setAvailableSeats(availableSeats);
      requestingUser.incrementRidesTaken();
    }
    return resultRide;
  }

  private static void print_ride_stats() {
    for(var user : platformUsers.values()) {
      System.out.println(user.toString());
    }
  }

}


