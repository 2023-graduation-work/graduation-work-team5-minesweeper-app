import greenfoot.*; 
import java.util.List;
import java.util.Random;

public class MineSweeper extends World {
    /** The time in milliseconds that the stopwatch was stopped/paused. */
    public long stoppedTime;
    /** The time in milliseconds that the stopwatch was started/resumed. */
    public long startedTime;
    /** The time in milliseconds that the stopwatch was resumed (by the start button actor). */
    public long startedMiddle;
    /** The time in milliseconds that the stopwatch was paused (by the stop button actor). */
    public long stoppedMiddle;
    /** True when the execution has been stopped. */
    public boolean stopped = false;
    /** True if the stopwatch was paused by the stop button actor. */
    public boolean buttonStopped = true;
    /** True when the stopwatch was stopped by the stop button actor. */
    public boolean middleStop = false;
    /** A reset boolean which is true when the stopwatch should be reset. */
    public boolean reset = true;
    public boolean gameEnd = false; // ゲームが終了したらTrue
    
    private static final int WIDTH = 18;
    private static final int HEIGHT = 14;
    private static final int CELL_SIZE = 32;
    private static final int NUM_MINES = 40;
    private int timecount = 0;
    
    public MineSweeper() {
        super(WIDTH, HEIGHT+3, CELL_SIZE);
        generateBoard();
        Greenfoot.setSpeed(100);
        addObject(new Timer("Time:"), 3, 1);
        addObject( new buttonRestart(), 13, 1 );
        Greenfoot.start();
    }
    private void generateBoard() {
        // Create cells
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 3; y < HEIGHT+3; y++) {
                addObject(new Cell(), x, y);
            }
        }
        // Generate random mines
        Random random = new Random();
        for (int i = 0; i < NUM_MINES; i++) {
            int x, y;
            do {
                x = random.nextInt(WIDTH);
                y = random.nextInt(HEIGHT)+3;
            } while (getObjectsAt(x, y, Cell.class).size() == 0 || getObjectsAt(x, y, Cell.class).get(0).hasMine());
            getObjectsAt(x, y, Cell.class).get(0).setMine();
        }
        // Calculate adjacent mines for each cell
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 3; y < HEIGHT+3; y++) {
                Cell cell = getObjectsAt(x, y, Cell.class).get(0);
                if (!cell.hasMine()) {
                    int count = countAdjacentMines(x, y);
                    cell.setAdjacentMines(count);
                }
            }
        }
    }
    private int countAdjacentMines(int x, int y) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int newX = x + dx;
                int newY = y + dy;
                if (newX >= 0 && newX < WIDTH && newY >= 3 && newY < HEIGHT+3) {
                    if (getObjectsAt(newX, newY, Cell.class).get(0).hasMine()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}