import greenfoot.*;

public class Cell extends Actor {
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
    
    private boolean isMine;
    private boolean isOpen = false;
    private int adjacentMines;
    private boolean onflag;    
    public Cell() {
        isMine = false;
        isOpen = false;
        onflag = false;
        adjacentMines = 0;
        setImage("tile.png"); // セルが閉じられた状態の画像
    }

    public void act() {
        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1 && !isOpen && !onflag) {
            openCell();
        }
        if (Greenfoot.mouseClicked(this)) {
            if (Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getButton() == 3 && !isOpen) {
                if (!onflag) {
                    placeFlag(); // 右クリックで旗を置く
                } else {
                    deleteFlag();
                }
            } 
        }
        gameClear();
    }
    private void placeFlag() {
        setImage("flag.png"); // 旗の画像
        onflag = true;
    }
    private void deleteFlag() {
        setImage("closed_cell.png"); // 旗の画像
        onflag = false;
    }
    public void setMine() {
        isMine = true;
    }
    public boolean hasMine() {
        return isMine;
    }
    public void setAdjacentMines(int count) {
        adjacentMines = count;
    }
    public void openCell() {
        MineSweeper world = (MineSweeper) getWorld();
        if(world.gameEnd) return; // ゲーム終了状態ならReturn
        
        isOpen = true;
        startedTime = System.currentTimeMillis();
        if (Greenfoot.mouseClicked(this))
        {
            world.buttonStopped = false;
            world.reset = false;
            if (world.middleStop)
            {
                world.startedMiddle = System.currentTimeMillis();
            }
        }
        if (isMine) {
            setImage("bomb.png"); // 地雷の画像
            gameOver();
        } else {
            setImage("open_cell.png"); // セルが開かれた状態の画像
            if (adjacentMines == 0) {
                expandEmptyCells();
            } else {
                showAdjacentMines();
            }
        }
    }

    private void showAdjacentMines() {
        if (adjacentMines > 0) {
            GreenfootImage textImage = new GreenfootImage(Integer.toString(adjacentMines), 24, Color.BLACK, Color.WHITE);
            setImage(textImage);
        }
    }
    private void expandEmptyCells() {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int newX = getX() + dx;
                int newY = getY() + dy;
                if (newX >= 0 && newX < getWorld().getWidth() && newY >= 3 && newY < getWorld().getHeight()) {
                    Cell adjacentCell = (Cell) getWorld().getObjectsAt(newX, newY, Cell.class).get(0);
                    if (!adjacentCell.isOpen) {
                        adjacentCell.openCell(); // 隣接するセルを開く
                    }
                }
            }
        }
    }
    private void gameOver() {
        // ゲームオーバー処理を実行
        isOpen = false;
        stopped = true;
        stoppedTime = System.currentTimeMillis();
        MineSweeper world = (MineSweeper) getWorld();
        if (Greenfoot.mouseClicked(this) && !world.buttonStopped)
        {
            world.gameEnd = true; // ゲーム終了通知
            world.buttonStopped = true;
            world.stoppedMiddle = System.currentTimeMillis();
            world.middleStop = true;
        }
        getWorld().addObject( new GameOver(), 8, 8 );
    }
    private void gameClear() {
        int count = 0;
        for (int x = 0; x < 18; x++) {
            for (int y = 3; y < 17; y++) {
                Cell judge = (Cell) getWorld().getObjectsAt(x, y, Cell.class).get(0);
                if((!judge.isMine && judge.isOpen) || (judge.isMine && !judge.isOpen)) {
                    count++;
                }
            }
        }
        if (count == 252) {
            stopped = true;
            stoppedTime = System.currentTimeMillis();
            MineSweeper world = (MineSweeper) getWorld();
            if (Greenfoot.mouseClicked(this) && !world.buttonStopped) {
                world.gameEnd = true; // ゲーム終了通知
                world.buttonStopped = true;
                world.stoppedMiddle = System.currentTimeMillis();
                world.middleStop = true;
            }
            getWorld().addObject( new gameClear(), 8, 8 );
        }
    }
}
