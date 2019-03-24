import java.util.Random;

public enum Direction {
    WEST, NORTH, EAST, SOUTH;

    public static Direction getRandomDirection()
    {
        Random random = new Random();
        int rndNumber = random.nextInt(Direction.values().length);
        return Direction.values()[rndNumber];
    }
}
