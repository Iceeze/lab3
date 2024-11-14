package ru.ermishin.geometry;

public class Rectangle extends Figure {
    private double width, height; // ширина и высота прямоугольника
    private Point p; // координаты верхнего левого угла

    public Rectangle(Point p, double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Ошибка! Стороны прямоугольника должны быть > 0");
        }
        this.p = p;
        this.width = width;
        this.height = height;
    }

    public String toString() {
        return "Прямоугольник в точке " + p + " со сторонами " + width + " " + height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}