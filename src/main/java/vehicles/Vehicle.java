package vehicles;

import util.Direction;
import util.Position;

public interface Vehicle {
    Position getPosition();

    void setStart(Direction start);
    void setDestination(Direction destination);

    int getLength();
    int getWidth();

    void drive(Double secondsElapsedCapped);
    void driveStraight();

    Position getStartPosition();
}
