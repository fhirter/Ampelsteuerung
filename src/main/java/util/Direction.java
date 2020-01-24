package util;

import java.util.Random;

public enum Direction {
    EAST, SOUTH, WEST, NORTH;
    private Direction opposite;
    private int angle;

    static {
        WEST.opposite = EAST;
        EAST.opposite = WEST;
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
    }

    static {
        EAST.angle = 0;
        NORTH.angle = 90;
        WEST.angle = 180;
        SOUTH.angle = 270;

    }

    public Direction getOpposite() {
        return opposite;
    }

    public double getAngle() {
        return angle;
    }

    public static Direction getRandomDirection() {
        return values()[(new Random()).nextInt(values().length)];
    }
    public static Direction getRandomDirection(Direction not) {
        Direction direction;
        do {
            direction = Direction.getRandomDirection();
        } while (not == direction);

        return direction;
    }
}
