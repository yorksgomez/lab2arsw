package snakepackage;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import enums.GridSize;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author jd-
 *
 */
public class SnakeApp {

    private static SnakeApp app;
    public static final int MAX_THREADS = 8;
    Snake[] snakes = new Snake[MAX_THREADS];
    Snake bestSnake, worstSnake;
    private static final Cell[] spawn = {
        new Cell(1, (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(GridSize.GRID_WIDTH - 2,
        3 * (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(3 * (GridSize.GRID_WIDTH / 2) / 2, 1),
        new Cell((GridSize.GRID_WIDTH / 2) / 2, GridSize.GRID_HEIGHT - 2),
        new Cell(1, 3 * (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(GridSize.GRID_WIDTH - 2, (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell((GridSize.GRID_WIDTH / 2) / 2, 1),
        new Cell(3 * (GridSize.GRID_WIDTH / 2) / 2,
        GridSize.GRID_HEIGHT - 2)};
    private JFrame frame;
    private static Board board;
    int nr_selected = 0;
    Thread[] thread = new Thread[MAX_THREADS];
    Thread control;

    public SnakeApp() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("The Snake Race");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(618, 640);
        frame.setSize(GridSize.GRID_WIDTH * GridSize.WIDTH_BOX + 17,
                GridSize.GRID_HEIGHT * GridSize.HEIGH_BOX + 40);
        frame.setLocation(dimension.width / 2 - frame.getWidth() / 2,
                dimension.height / 2 - frame.getHeight() / 2);
        board = new Board();
        
        
        frame.add(board,BorderLayout.CENTER);
        
        JPanel actionsBPabel=new JPanel();
        actionsBPabel.setLayout(new FlowLayout());

        JButton btnStart = new JButton("Iniciar");
        JButton btnResume = new JButton("Resume");
        JButton btnPause = new JButton("Pause");

        btnStart.addActionListener((e) -> control.start());

        btnPause.addActionListener((e) -> {
            actionsBPabel.remove(btnPause);
            actionsBPabel.add(btnResume);

            bestSnake = searchBestSnake();

            if(bestSnake != null)
                bestSnake.setBestColor();

            if(worstSnake != null)
                worstSnake.setWorstColor();

            board.repaint();
            actionsBPabel.repaint();
            actionsBPabel.revalidate();
            stop();
        });

        btnResume.addActionListener((e) -> {
            actionsBPabel.remove(btnResume);
            actionsBPabel.add(btnPause);

            if(bestSnake != null)
                bestSnake.setDefaultColor();

            if(worstSnake != null)
                worstSnake.setDefaultColor();

            board.repaint();
            actionsBPabel.repaint();
            actionsBPabel.revalidate();
            resume();

        });

        actionsBPabel.add(btnStart);

        frame.add(actionsBPabel,BorderLayout.SOUTH);

        control = new Thread() {
            @Override
            public void run() {
                actionsBPabel.remove(btnStart);
                actionsBPabel.add(btnPause);
                actionsBPabel.repaint();
                actionsBPabel.revalidate();
                app.start();
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            app = new SnakeApp();
            app.init();
        });
    }

    private void init() {
        
        for (int i = 0; i != MAX_THREADS; i++) {    
            snakes[i] = new Snake(i + 1, spawn[i], i + 1);
            snakes[i].addObserver(board);
            thread[i] = new Thread(snakes[i]);
        }

        frame.setVisible(true);
    }
    
    private void start() {

        for (int i = 0; i != MAX_THREADS; i++) {    
            thread[i].start();
        }

        while (true) {
            int x = 0;
            for (int i = 0; i != MAX_THREADS; i++) {
                if (snakes[i].isSnakeEnd() == true) {
                    x++;
                }
            }
            if (x == MAX_THREADS) {
                break;
            }
        }

        System.out.println("Thread (snake) status:");
        for (int i = 0; i != MAX_THREADS; i++) {
            System.out.println("["+i+"] :"+thread[i].getState());
        }
        
    }

    private void stop() {
        Snake.doStop();
    }

    private void resume() {
        Snake.doResume();

        synchronized(this) {
            notifyAll();
        }
        
    }

    public Snake searchBestSnake() {
        int maxAmount = 0,
            maxI = -1;

        for(int i = 0; i < MAX_THREADS; i++) {

            if(snakes[i].getLength() > maxAmount) {
                maxAmount = snakes[i].getLength();
                maxI = i;
            } else if(snakes[i].getLength() == maxAmount) {
                maxI = -1;
            }

        }

        return maxI != -1 ? snakes[maxI] : null;
    }

    public Snake getBestSnake() {
        return bestSnake;
    }

    public void setBestSnake(Snake bestSnake) {
        this.bestSnake = bestSnake;
    }

    public Snake getWorstSnake() {
        return worstSnake;
    }

    public void setWorstSnake(Snake worstSnake) {
        this.worstSnake = worstSnake;
    }

    public static SnakeApp getApp() {
        return app;
    }

}
