import services.AppleService;
import services.GameService;
import services.SnakeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 1200;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 30;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;

    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(28, 28, 28));
        this.setFocusable(true);
        this.addKeyListener((new MyKeyAdapter()));
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            AppleService.drawApple(appleX, appleY, g, UNIT_SIZE);
            SnakeService.drawSnake(bodyParts, g, UNIT_SIZE, x, y);
            GameService.drawUserInterface(g, applesEaten, SCREEN_WIDTH);
        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        appleX = AppleService.
                checkCoordinateOverlapWithSnake(appleX, x, SCREEN_WIDTH, UNIT_SIZE);
        appleY = AppleService.
                checkCoordinateOverlapWithSnake(appleY, y, SCREEN_HEIGHT, UNIT_SIZE);
    }


    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        running = SnakeService.
                checkSnakeBodyCollision(x, y, bodyParts, running);
        running = SnakeService.
                checkSnakeHeadBorderCollision(x, y, SCREEN_WIDTH, SCREEN_HEIGHT, running);
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        GameService.drawGameOver(g, applesEaten, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            direction = GameService.checkKeyInputAndSetDirection(e, direction);
        }
    }
}
