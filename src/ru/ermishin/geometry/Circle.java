package ru.ermishin.geometry;


public class Circle extends Figure {
    private Point p; // координаты центра
    private int r; // радиус

    public Circle(Point p, int r) {
        if (r <= 0) {
            throw new IllegalArgumentException("Ошибка! Радиус должен быть > 0");
        }
        this.p = p;
        this.r = r;
    }

    public String toString() {
        return "Круг с центром " + p + ", радиус = " + r;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(r, 2);
    }
}