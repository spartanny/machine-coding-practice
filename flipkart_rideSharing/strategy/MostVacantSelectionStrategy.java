package fkRideSharing.strategy;

import fkRideSharing.entities.RideDetails;

import java.util.Comparator;
import java.util.List;

public class MostVacantSelectionStrategy extends ISelectionStrategy {

    @Override
    public RideDetails getRide(List<RideDetails> rideDetails, RideDetails requestRideDetails) {
        rideDetails = applyBaseFilters(rideDetails, requestRideDetails);
        return rideDetails.stream().sorted(Comparator.comparingInt(RideDetails::getAvailableSeats)).findFirst().orElse(null);
    }
}
