public class Position
{
    int x, y, angle;

    public Position(int x, int y, int angle)
    {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    // copy constructor
    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
        this.angle = position.angle;
    }
}
