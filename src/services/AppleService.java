package services;

import java.awt.*;
import java.util.Random;

public class AppleService {

    private static Random random = new Random();

    public static void drawApple(int coordinateX, int coordinateY, Graphics g, int UNIT_SIZE) {
        g.setColor(Color.RED);
        g.fillOval(coordinateX, coordinateY, UNIT_SIZE, UNIT_SIZE);
    }

    public static int checkCoordinateOverlapWithSnake(int appleCoordinate, int[] axis, int SCREEN_METRIC, int UNIT_SIZE) {
        for (int i = 0; i < axis.length; i++) {
            while (appleCoordinate == axis[i]) {
                appleCoordinate = random.nextInt((SCREEN_METRIC / UNIT_SIZE)) * UNIT_SIZE;
            }
        }

        return appleCoordinate;
    }
}
