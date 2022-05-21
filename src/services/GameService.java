package services;


import java.awt.*;
import java.awt.event.KeyEvent;

public class GameService {

    public static void drawUserInterface(Graphics g, int applesEaten, int SCREEN_WIDTH) {
        g.setColor(new Color(219, 190, 174));
        g.setFont(new Font("Arial", Font.BOLD, 25));
        FontMetrics metrics = g.getFontMetrics();
        g.drawString(String.format("Score: %d", applesEaten),
                (SCREEN_WIDTH - metrics.stringWidth(String.format("Score: %d", applesEaten))) / 2,
                g.getFont().getSize());
    }

    public static void drawGameOver(Graphics g, int applesEaten, int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        //SCORE STRING
        g.setColor(new Color(219, 190, 174));
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics scoreMetrics = g.getFontMetrics();
        g.drawString(String.format("Score: %d", applesEaten),
                (SCREEN_WIDTH - scoreMetrics.stringWidth(String.format("Score: %d", applesEaten))) / 2,
                g.getFont().getSize());
        //GAMEOVER STRING
        g.setColor(new Color(219, 190, 174));
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics gameOverMetrics = g.getFontMetrics();
        g.drawString("Game Over", (SCREEN_WIDTH - gameOverMetrics.stringWidth("Game Over")) / 2,
                SCREEN_HEIGHT / 2);
    }

    public static char checkKeyInputAndSetDirection(KeyEvent e, char direction) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
        }

        return direction;
    }
}
