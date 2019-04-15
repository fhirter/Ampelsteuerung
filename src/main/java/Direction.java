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
        WEST.angle = 270;
        EAST.angle = 90;
        NORTH.angle = 0;
        SOUTH.angle = 180;
    }

    public Direction getOpposite() {
        return opposite;
    }

    public int getAngle() {
        return angle;
    }
};
