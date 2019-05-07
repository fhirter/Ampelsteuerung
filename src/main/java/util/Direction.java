package util;

public enum Direction {
    WEST, EAST, NORTH, SOUTH;
    private Direction opposite;
    private int angle;

    static {
        WEST.opposite = EAST;
        EAST.opposite = WEST;
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
    }

    static {
        WEST.angle = 180;
        EAST.angle = 0;
        NORTH.angle = 270;
        SOUTH.angle = 90;
    }

    public Direction getOpposite() {
        return opposite;
    }

    public double getAngle() {
        return angle;
    }


};
