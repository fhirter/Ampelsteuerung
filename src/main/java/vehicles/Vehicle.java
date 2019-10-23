package vehicles;

import crossroad.Context;
import util.*;

/**
 *
 *
 *  @autor  Hirter Fabian
 *
 */
public interface Vehicle extends Observable {
    void setSpeed(int speed);

    Position getPosition();

    void setStart(Direction start);
    void setDestination(Direction destination);

    int getLength();
    int getWidth();

    void drive(Double secondsElapsedCapped);
    void driveStraight();

    void setContext(Context context);

}
