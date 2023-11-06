import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class buttonStart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class buttonStart extends Actor
{
    /**
     * Act - do whatever the buttonStart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        MouseInfo minfo = Greenfoot.getMouseInfo();
        if( minfo != null ){
            int button = minfo.getButton();
            Actor actor = minfo.getActor();
            if (actor == this && button == 1) {
                World game = new MineSweeper();
                Greenfoot.setWorld(game);
                addedToWorld(new MineSweeper());
            }
        }
    }
}
