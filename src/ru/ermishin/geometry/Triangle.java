package ru.ermishin.geometry;

import ru.ermishin.main.HasPolyLine;

public class Triangle extends Figure implements HasPolyLine {
    private Point p1, p2, p3;

    public Triangle(Point p1, Point p2, Point p3) {
        if (p1.equals(p2) || p2.equals(p3) || p3.equals(p1)) {
            throw new IllegalArgumentException("Ошибка! Все три вершины должны находиться на разных координатах.");
        }
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public String toString() {
        return "Треугольник с вершинами " + p1 + ' ' + p2 + ' ' + p3;
    }

    @Override
    public double calculateArea() {
        return 0.5 * Math.abs(p1.getX() * (p2.getY() - p3.getY()) +
                p2.getX() * (p3.getY() - p1.getY()) + p3.getX() * (p1.getY() - p2.getY()));
    }

    public PolyLine getPolyLine() {
        Point[] points = new Point[4];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p1; // замыкаем контур
        return new PolyLine(points);
    }
}