package vehicles;

import util.Position;

public interface Driveable {
    Position getPosition();

    int getLength();
    int getWidth();

    void drive();
    void driveStraight();
    void turn();
}
