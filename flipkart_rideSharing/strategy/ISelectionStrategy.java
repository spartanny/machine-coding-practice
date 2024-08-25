package fkRideSharing.strategy;

import fkRideSharing.entities.RideDetails;

import java.util.List;

public abstract class ISelectionStrategy {

    public List<RideDetails> applyBaseFilters(List<RideDetails> rideDetails, RideDetails requestRideDetails) {
        return rideDetails.stream().filter(i -> i.equals(requestRideDetails) && i.getNumberOfSeats() >= requestRideDetails.getNumberOfSeats()).toList();
    }

    abstract public RideDetails getRide(List<RideDetails> rideDetails, RideDetails requestRideDetails);
}
