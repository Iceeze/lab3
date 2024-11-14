package ru.ermishin.geometry;

import ru.ermishin.main.HasPolyLine;

public class Square extends Figure implements HasPolyLine {
    private int lenSide; // длина стороны квадрата
    private Point p;

    public Square(Point p, int lenSide) {
        if (lenSide <= 0) {
            throw new IllegalArgumentException("Ошибка! Cторона должна быть > 0");
        }
        this.p = p;
        this.lenSide = lenSide;
    }

    public Square(int x, int y, int lenSide) {
        this(new Point(x, y), lenSide);
    }

    public String toString() {
        return "Квадрат в точке " + p  + " со стороной " + lenSide;
    }

    public PolyLine getPolyLine() {
        Point p2 = new Point(p.getX() + lenSide, p.getY());
        Point p3 = new Point(p.getX(), p.getY() - lenSide);
        Point p4 = new Point(p.getX() + lenSide, p.getY() - lenSide);
        Point[] points = new Point[] {p, p2, p3, p4, p};
        return new PolyLine(points);
    }

    public int getLenSide() {
        return lenSide;
    }

    public void setLenSide(int lenSide) {
        this.lenSide = lenSide;
    }

    @Override
    public double calculateArea() {
        return Math.pow(lenSide, 2);
    }

}
