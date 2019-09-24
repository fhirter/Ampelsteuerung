package util;

import javafx.geometry.Point2D;

public class Area {
    private int width;
    private int height;
    private Point2D center;

    public Area(int width, int length, Point2D center) {
        this.width = width;
        this.height = length;
        this.center = center;
    }

    public Area(int size, Point2D center) {
        this(size, size, center);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point2D getCenter() {
        return center;
    }

    public boolean isInside(Position position) {
        final double x = position.getX();
        final double y = position.getY();

        final double centerX = center.getX();
        final double centerY = center.getY();

        if (x > (centerX - width / 2) && x < (centerX + width / 2)) {
            if (y > (centerY - height / 2) && y < (centerY + height / 2)) {
                return true;
            }
        }
        return false;
    }
}
