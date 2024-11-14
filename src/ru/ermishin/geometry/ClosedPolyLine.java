package ru.ermishin.geometry;

public class ClosedPolyLine extends PolyLine {

    public ClosedPolyLine(Point[] points) {
        super(points);
        if (points[0].getX() != points[points.length - 1].getX() ||
                points[0].getY() != points[points.length - 1].getY() || points.length < 3) {
            throw new IllegalArgumentException("Ошибка! Данная ломаная - не замкнутая.");
        }
    }

    @Override
    public void movePoint(int index, int newX, int newY) {
        if (index == points.length || index == 1) {
            points[0].move(newX, newY);
            points[points.length-1].move(newX, newY);
        }
        points[index-1].move(newX, newY);
    }
}
