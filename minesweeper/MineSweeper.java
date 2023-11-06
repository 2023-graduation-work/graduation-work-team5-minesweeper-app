import greenfoot.*; 
import java.util.List;
import java.util.Random;

public class MineSweeper extends World {
    private static final int WIDTH = 18;
    private static final int HEIGHT = 14;
    private static final int CELL_SIZE = 32;
    private static final int NUM_MINES = 40;

    public MineSweeper() {
        super(WIDTH, HEIGHT, CELL_SIZE);
        generateBoard();
    }

    private void generateBoard() {
        // Create cells
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                addObject(new Cell(), x, y);
            }
        }

        // Generate random mines
        Random random = new Random();
        for (int i = 0; i < NUM_MINES; i++) {
            int x, y;
            do {
                x = random.nextInt(WIDTH);
                y = random.nextInt(HEIGHT);
            } while (getObjectsAt(x, y, Cell.class).size() == 0 || getObjectsAt(x, y, Cell.class).get(0).hasMine());
            getObjectsAt(x, y, Cell.class).get(0).setMine();
        }

        // Calculate adjacent mines for each cell
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
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
                if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT) {
                    if (getObjectsAt(newX, newY, Cell.class).get(0).hasMine()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
