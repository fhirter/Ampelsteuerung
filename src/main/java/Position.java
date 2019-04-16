import java.awt.geom.Point2D;

public class Position extends Point2D
{
    double x, y, angle;

    public Position(int x, int y, int angle) {
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

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getAngle() {
        return angle;
    }
}
