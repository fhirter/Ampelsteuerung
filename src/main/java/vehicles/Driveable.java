package vehicles;

import util.Position;

public interface Driveable {
    Position getPosition();

    int getLength();
    int getWidth();

    void drive();
    void setNewPosition(Double secondsElapsedCapped);

    void driveStraight();

}
