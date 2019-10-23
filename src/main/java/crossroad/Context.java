package crossroad;

import util.Area;
import util.Direction;
import util.Observable;
import util.Position;
import vehicles.Vehicle;

import java.util.List;

/**
 * Context a Vehicle can drive on, i.E. a Crossroad
 *
 *  @autor  Hirter Fabian
 *  @since  22.10.2019
 */
public interface Context extends Observable {

    void addVehicle(Vehicle vehicle);
    List<Vehicle> getVehicles();

    boolean canITurn(Position position);
    boolean canIDrive(Position position, Road currentRoad);

    Area getTurningArea();

    void calculatePositions(Double secondsElapsedCapped);

    Road getRoad(Direction destination);
}
