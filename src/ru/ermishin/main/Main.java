package ru.ermishin.main;

import ru.ermishin.geometry.*;
import ru.ermishin.tree.*;
import ru.ermishin.math.*;

import java.util.Scanner;
import java.util.Arrays;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        out.println("Вариант 10\n");
        out.println("Список задач:");
        out.println("1. Квадрат и Сторона Квадрата (1.3)");
        out.println("2. Бинарное дерево (2.4)");
        out.println("3. Замкнутая ломаная (3.2)");
        out.println("4. Фигуры (4.4)");
        out.println("5. Измерение длины (5.5)");
        out.println("6. Объединение в ломаную (5.7)");
        out.println("7. Сравнение ломаных линий (6.4)");
        out.println("8. Возведение в степень (7.3)");
        out.println("9. Клонирование точки (8.4)");
        out.print("Введите номер задания: ");
        String choice = scanner.next();

        switch (choice) {
            case "1": {
                // Задание 1.3
                Square s = new Square(5, 3, 23);
                PolyLine pl = s.getPolyLine();
                out.println("Длина ломаной: " + pl.getLength());
                pl.movePoint(4, 15, 25);
                out.println("Длина ломаной после изменения точки: " + pl.getLength());
                out.println();
                try {
                    Square s1 = createSquare(scanner);
                    out.println(s1);
                } catch (IllegalArgumentException e) {
                    out.println(e.getMessage());
                }
            } break;

            case "2": {
                // Задание 2.4
                Node binTree = new Node();
                binTree.setValue(3);
                binTree.setValue(5);
                binTree.setValue(4);
                binTree.setValue(7);
                binTree.setValue(1);
                binTree.setValue(2);
                out.println(binTree);
            } break;

            case "3": {
                // Задание 3.2
                try {
                    int pointsCount = getValidInt("Введите количество точек для ломанной: ", scanner);
                    while (pointsCount < 0) {
                        out.println("Количество точек должно быть > 0.");
                        pointsCount = getValidInt("Введите количество точек для ломанной: ", scanner);
                    }

                    Point[] points = new Point[pointsCount];
                    for (int i = 0; i < pointsCount; i++) {
                        int x = getValidInt("Введите координату x точки " + (i+1) + ": ", scanner);
                        int y = getValidInt("Введите координату y точки " + (i+1) + ": ", scanner);
                        Point p = new Point(x, y);
                        points[i] = p;
                    }
                    ClosedPolyLine cl = new ClosedPolyLine(points);
                    out.println(cl);
                }
                catch (NegativeArraySizeException | IllegalArgumentException e) {
                    out.println(e.getMessage());
                }
            } break;

            case "4": {
                // Задание 4.4
                out.println("Введите фигуру (круг, квадрат, прямоугольник, треугольник): ");
                String whichFigure = scanner.next();
                try {
                    switch (whichFigure) {
                        case "круг": {
                            Circle circle = createCircle(scanner);
                            out.println(circle);
                            out.println("Площадь круга: " + circle.calculateArea());
                        } break;

                        case "квадрат": {
                            Square square = createSquare(scanner);
                            out.println(square);
                            out.println("Площадь квадрата: " + square.calculateArea());
                        } break;

                        case "прямоугольник": {
                            Rectangle r = createRectangle(scanner);
                            out.println(r);
                            out.println("Площадь прямоугольника: " + r.calculateArea());
                        } break;

                        case "треугольник": {
                            Triangle tr = createTriangle(scanner);
                            out.println(tr);
                            out.println("Площадь треугольника: " + tr.calculateArea());
                        } break;

                        default: out.println("Данной фигуры нет в списке.");
                    }
                } catch (IllegalArgumentException e) {
                    out.println(e.getMessage());
                }
            } break;

            case "5": {
                // Задание 5.5
                out.println("Создание ломаной");
                PolyLine polyLine = createPolyLine(scanner);

                out.println("\nСоздание линии");
                Line line = createLine(scanner);

                Measurable[] objects = { line, polyLine };
                double totalLength = LengthCalculator.calculateTotalLength(objects);
                out.println("\nДлина линии: " + line.getLength());
                out.println("Длина ломаной: " + polyLine.getLength());
                out.println("Общая длина: " + totalLength);
            } break;

            case "6": {
                // Задание 5.7
                Square s = createSquare(scanner);
                Triangle t = createTriangle(scanner);
                Line l = createLine(scanner);

                PolyLine resP = PolyLine.mergePolyLine(Arrays.asList(s, t, l));
                out.println(resP);
            } break;

            case "7": {
                // Задание 6.4
                PolyLine[] pls = new PolyLine[2];
                for (int i = 0; i < 2; i++) {
                    out.println("Создание " + (i + 1) + "-ой ломаной");
                    PolyLine pl = createPolyLine(scanner);
                    pls[i] = pl;
                }
                PolyLine pl3 = new PolyLine(pls[1].getPoints()); // 3-я ломаная будет равна второй
                out.println("Третья ломаная создана с такими же точками, что и вторая.");

                out.println("\nПервая ломаная: " + pls[0]);
                out.println("Вторая ломаная: " + pls[1]);
                out.println("Третья ломаная: " + pl3);

                out.println("\nРавны ли первая и вторая ломаная: " + pls[0].equals(pls[1]));
                out.println("Равны ли вторая и третья ломаная: " + pls[1].equals(pl3));
            } break;

            case "8": {
                // Задание 7.3
                out.print("Введите основание (x): ");
                String xStr = scanner.next();
                out.print("Введите показатель степени (y): ");
                String yStr = scanner.next();
                try {
                    double result = PowerCalculator.power(xStr, yStr);
                    System.out.println("Результат: " + result);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Введите корректные числовые значения.");
                } catch (ArithmeticException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            } break;

            case "9": {
                // Задание 8.4
                // Создаем исходную точку
                int x = getValidInt("Введите координату x: ", scanner);
                int y = getValidInt("Введите координату y: ", scanner);
                Point originalPoint = new Point(x, y);
                Point clonedPoint;
                try {
                    clonedPoint = originalPoint.clone(); // клонируем исходную точку
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Оригинальная точка: " + originalPoint);
                System.out.println("Клонированная точка: " + clonedPoint);

                // Проверяем, что это два разных объекта, но с одинаковыми значениями полей
                System.out.println("Один и тот же объект? " + (originalPoint == clonedPoint));
                System.out.println("Равны по значению? " + originalPoint.equals(clonedPoint));
            } break;

            default: out.println("Такой задачи нет в списке.");
        }
    }

    // Метод для проверки корректного ввода целого числа
    public static int getValidInt(String message, Scanner scanner) {
        while (true) {
            out.print(message);
            if (scanner.hasNextInt()) return scanner.nextInt();
            else {
                out.println("Ошибка: это не целое число. Попробуйте снова.");
                scanner.next();
            }
        }
    }

    // Метод для создания квадрата
    public static Square createSquare(Scanner scanner) {
        int x = getValidInt("Введите координату x верхнего левого угла квадрата: ", scanner);
        int y = getValidInt("Введите координату y верхнего левого угла квадрата: ", scanner);
        int lenSide = getValidInt("Введите значение стороны квадрата: ", scanner);
        return new Square(x, y, lenSide);
    }

    // Метод для создания квадрата
    public static Rectangle createRectangle(Scanner scanner) {
        int x = getValidInt("Введите координату x верхнего левого угла прямоугольника: ", scanner);
        int y = getValidInt("Введите координату y верхнего левого угла прямоугольника: ", scanner);
        int w = getValidInt("Введите значение ширины прямоугольника: ", scanner);
        int h = getValidInt("Введите значение высоты прямоугольника: ", scanner);
        return new Rectangle(new Point(x, y), w, h);
    }

    // Метод для создания треугольника
    public static Triangle createTriangle(Scanner scanner) {
        Point[] points = new Point[3];
        for (int i = 0; i < 3; i++) {
            int x = getValidInt("Введите координату x для вершины " + (i+1) + " треугольника: ", scanner);
            int y = getValidInt("Введите координату y для вершины " + (i+1) + " треугольника: ", scanner);
            Point p = new Point(x, y);
            points[i] = p;
        }
        return new Triangle(points[0], points[1], points[2]);
    }

    // Метод для создания круга
    public static Circle createCircle(Scanner scanner) {
        int x = getValidInt("Введите координату x центра: ", scanner);
        int y = getValidInt("Введите координату y центра: ", scanner);
        Point p = new Point(x, y);
        int r = getValidInt("Введите радиус: ", scanner);
        return new Circle(p, r);
    }

    // Метод для создания ломаной
    public static PolyLine createPolyLine(Scanner scanner) {
        int lenP = getValidInt("Введите количество точек ломаной: ", scanner);
        Point[] points = new Point[lenP];
        for (int i = 0; i < lenP; i++) {
            int x = getValidInt("Введите координату x точки " + (i+1) + ": ", scanner);
            int y = getValidInt("Введите координату y точки " + (i+1) + ": ", scanner);
            Point p = new Point(x, y);
            points[i] = p;
        }
        return new PolyLine(points);
    }

    // Метод для создания линии
    public static Line createLine(Scanner scanner) {
        Point[] points = new Point[2];
        for (int i = 0; i < 2; i++) {
            int x = getValidInt("Введите координату x для " + (i+1) + " точки линии: ", scanner);
            int y = getValidInt("Введите координату y для " + (i+1) + " точки линии: ", scanner);
            Point p = new Point(x, y);
            points[i] = p;
        }
        return new Line(points[0], points[1]);
    }
}