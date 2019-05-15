package util;

import javafx.geometry.Point2D;

public class Position extends Point2D {
    double angle;

    public Position(double x, double y, double angle) {
        super(x, y);
        this.angle = angle;
    }

    public Position(Point2D point, double angle) {
        super(point.getX(), point.getY());
        this.angle = angle;
    }

    // copy constructor
    public Position(Position position) {
        super(position.getX(), position.getY());
        this.angle = position.angle;
    }

    public double getAngle() {
        return angle;
    }

    public Position add(double x, double y, double angle) {
        return new Position(super.add(x, y), getAngle() + angle); //todo: implement 360° overflow
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
