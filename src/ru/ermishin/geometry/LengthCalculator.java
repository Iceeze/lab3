package ru.ermishin.geometry;

import ru.ermishin.main.Measurable;

public class LengthCalculator {
    public static double calculateTotalLength(Measurable[] objects) {
        double totalLength = 0;
        for (Measurable obj : objects) {
            totalLength += obj.getLength();
        }
        return totalLength;
    }
}