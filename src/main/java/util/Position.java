package util;

import javafx.geometry.Point2D;

public class Position extends Point2D {
    double angle;

    public Position(double x, double y, double angle) {
        super(x, y);
        this.angle = handleAngleOverflow(angle);
    }

    public Position(Point2D point, double angle) {
        this(point.getX(), point.getY(),angle);
    }

    // copy constructor
    public Position(Position position) {
        this(position.getX(), position.getY(),position.getAngle());
    }

    public double getAngle() {
        return angle;
    }

    public Position add(double x, double y, double angle) {
        double newAngle = getAngle()+angle;

        return new Position(super.add(x, y), newAngle);
    }

    private double handleAngleOverflow(double angle) {
        while(angle > 360) {
            angle -= 360;
        }
        while(angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
