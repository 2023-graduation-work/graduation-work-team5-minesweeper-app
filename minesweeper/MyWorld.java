import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int timecount = 0;
    public MyWorld()
    {    
        super(600, 550, 1);
        drawCheckerPattern();
    }
    private void drawCheckerPattern() {
        int cellSize = 30; // 1つのチェックマスのサイズ
        int numColumns = 18; // 列数
        int numRows = 14; // 行数
        Color[] colors = {new Color(192, 192, 192), new Color(211, 211, 211)}; // チェック柄の色
        // チェック柄を中央に配置
        int centerX = getWidth() / 2 - (numColumns * cellSize) / 2;
        int centerY = getHeight() / 2 - (numRows * cellSize) / 2;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                int colorIndex = (row + col) % 2; // チェック柄の色を交互に切り替え
                Color cellColor = colors[colorIndex];
                for (int x = centerX + col * cellSize; x < centerX + (col + 1) * cellSize; x++) {
                    for (int y = centerY + row * cellSize; y < centerY + (row + 1) * cellSize; y++) {
                        getBackground().setColorAt(x, y, cellColor);
                    }
                }
            }
        }
    }
    public void act() {
       
       timecount += 1;
       showText("Time: " + timecount, 60, 30 );
    }
} 