package ru.ermishin.geometry;

import ru.ermishin.main.Measurable;
import ru.ermishin.main.HasPolyLine;

import java.util.Arrays;
import java.util.List;

public class PolyLine implements Measurable {
    protected Point[] points;

    public PolyLine(Point[] points) {
        this.points = points;
    }

    public PolyLine() {
        this(null);
    }

    public String toString() {
        StringBuilder outputBuild = new StringBuilder();
        outputBuild.append("Линия [");
        for (Point p : points) {
            outputBuild.append(p);
            outputBuild.append(", ");
        }
        if (outputBuild.length() == 7) outputBuild.append("]");
        else outputBuild.replace(outputBuild.length()-2, outputBuild.length(), "]"); // Убираем последнюю запятую и закрываем скобки []
        String output = outputBuild.toString();
        return output;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return this.points;
    }

    public void addPoints(Point[] newPoints) {
        if (this.points == null) this.setPoints(newPoints);
        else {
            Point[] resultPoints = new Point[points.length + newPoints.length];
            for (int i = 0; i < points.length; i++) resultPoints[i] = points[i];
            for (int i = 0; i < newPoints.length; i++) resultPoints[i+points.length] = newPoints[i];
            this.points = resultPoints;
        }
    }

    public void movePoint(int index, int newX, int newY) {
        points[index-1].move(newX, newY);
    }

    public double getLength() {
        double totalLength = 0;
        for (int i = 0; i < points.length - 1; i++) {
            totalLength += points[i].distanceToPoint(points[i + 1]);
        }
        return totalLength;
    }

    public static PolyLine mergePolyLine(List<HasPolyLine> objects) {
        PolyLine mergedPolyLine = new PolyLine();

        for (HasPolyLine obj : objects) {
            mergedPolyLine.addPoints(obj.getPolyLine().getPoints());
        }
        return mergedPolyLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolyLine polyLine = (PolyLine) o;
        if (points == null && polyLine.getPoints() == null) return true;
        if (points == null || polyLine.getPoints() == null) return false;
        if (points.length != polyLine.getPoints().length) return false;
        for (int i = 0; i < points.length; i++) {
            if (!points[i].equals(polyLine.getPoints()[i])) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }
}
