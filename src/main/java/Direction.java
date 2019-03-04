import java.util.Random;

public enum Direction {
    WEST, EAST, NORTH, SOUTH;

    private Direction opposite;

    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
    }

    public Direction getOppositeDirection() {
        return opposite;
    }

    public static Direction getRandomDirection()
    {
        Random random = new Random();
        int rndNumber = random.nextInt(Direction.values().length);
        return Direction.values()[rndNumber];
    }
}
