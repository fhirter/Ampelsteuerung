package util;

import javafx.geometry.Point2D;

public class Position extends Point2D {
    private double angle;

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

    public Position add(Point2D point, double angle) {
        return add(point.getX(), point.getY(), angle);
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


    public Position rotate(double dphi, Point2D pivot) {
        dphi = Math.toRadians(dphi);
        Point2D shift = this.subtract(pivot);       // translate for pivot center to be on 0,0

        double x = shift.getX();
        double y = shift.getY();

        double x1 = x*Math.cos(dphi) - y*Math.sin(dphi);        // then rotate
        double y1 = x*Math.sin(dphi) + y*Math.cos(dphi);

        Point2D newpoint = (new Point2D(x1,y1)).add(pivot);     // and shift back
        return new Position(newpoint.getX(), newpoint.getY(), this.getAngle() + Math.toDegrees(dphi));
    }
}
