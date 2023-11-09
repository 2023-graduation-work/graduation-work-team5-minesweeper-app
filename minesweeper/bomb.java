import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bomb extends Actor
{
    /**
     * Act - do whatever the bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        int x = getX();
        int y = getY();
        Random random = new Random();
        int a = random.nextInt(x)+1;
        int b = random.nextInt(y)+1;
        setRotation(random.nextInt(361));
        move(0);
        if( isAtEdge() ){
            getWorld().removeObject( this );
        }
    }
}
