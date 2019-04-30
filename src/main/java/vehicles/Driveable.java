package vehicles;

import util.Position;

public interface Driveable {
    Position getPosition();

    int getLength();

    int getWidth();

    default void drive() {
        if (currentDirection == destination) {
            driveStraight();
        } else if (crossroad.canITurn(position) == true) {
            turn();
        } else {
            driveStraight();
        }
    }

    default void driveStraight() {
        forward = step;
        lateral = 0.0;
        mapDirection();
    }

    void turn();
}
