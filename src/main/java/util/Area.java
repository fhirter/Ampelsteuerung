package util;

import javafx.geometry.Point2D;

public class Area {
    private int xWidth;
    private int yWidth;
    private Point2D center;

    public Area(int xWidth, int length, Point2D center) {
        this.xWidth = xWidth;
        this.yWidth = length;
        this.center = center;
    }


    public Area(int size, Point2D center) {
        this(size, size, center);
    }

    public boolean isInside(Position position) {
        final double x = position.getX();
        final double y = position.getY();

        final double centerX = center.getX();
        final double centerY = center.getY();

        if (x > (centerX - xWidth / 2) && x < (centerX + xWidth / 2)) {
            if (y > (centerY - yWidth / 2) && y < (centerY + yWidth / 2)) {
                return true;
            }
        }
        return false;
    }
}
