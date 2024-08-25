package fkRideSharing.strategy;

import fkRideSharing.entities.RideDetails;

import java.util.List;

public class PreferredVehicleSelectionStrategy extends ISelectionStrategy {
    private String preferredVehicle;

    public PreferredVehicleSelectionStrategy(String preferredVehicle) {
        this.preferredVehicle = preferredVehicle;
    }

    @Override
    public RideDetails getRide(List<RideDetails> rideDetails, RideDetails requestRideDetails) {
        rideDetails = applyBaseFilters(rideDetails, requestRideDetails);
        return rideDetails.stream().filter(i -> i.getVehicle().getType().contains(preferredVehicle)).findAny().orElse(null);
    }
}
