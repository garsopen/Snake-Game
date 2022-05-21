package services;

import java.awt.*;

public class SnakeService {

    public static void drawSnake(int bodyParts, Graphics g, int UNIT_SIZE, int[] x, int[] y) {
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(new Color(255, 255, 255));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                g.setColor(new Color(248, 240, 227));
                g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE, 8, 8);
            }
        }
    }

    public static boolean checkSnakeHeadBorderCollision(int[] x, int[] y, int SCREEN_WIDTH, int SCREEN_HEIGHT, boolean running) {
        if (x[0] < 0) {
            running = false;
        } else if (x[0] > SCREEN_WIDTH) {
            running = false;
        } else if (y[0] < 0) {
            running = false;
        } else if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        return running;
    }

    public static boolean checkSnakeBodyCollision(int[] x, int[] y, int bodyParts, boolean running) {

        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        return running;
    }
}
