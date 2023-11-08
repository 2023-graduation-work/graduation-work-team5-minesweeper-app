import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 550, 1);
        addObject( new buttonStart(), 300, 300 );
        addObject( new title(), 300, 100 );
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            setBomb();
        }
    }
    public void setBomb() {
        Random random = new Random();
        int x = random.nextInt(600) + 1;
        int y = random.nextInt(400) + 1;
        addObject( new bomb(), x, y );
    }
}
