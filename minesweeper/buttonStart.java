import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class buttonStart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class buttonStart extends Actor
{
    /** The button object that will have the mouse hover. */
    private buttonStart hoveredButton = null;
    /**
     * Act - do whatever the buttonStart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("space")) {
            World game = new MineSweeper();
            Greenfoot.setWorld(game);
        }
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
        Greenfoot.setSpeed(100);
    }
    private void clicked()
    {
        MineSweeper world = (MineSweeper) getWorld();
        if (Greenfoot.mouseClicked(this))
        {
            world.buttonStopped = false;
            world.reset = false;
            if (world.middleStop)
            {
                world.startedMiddle = System.currentTimeMillis();
            }
        }
    }
    
        private void hoverOwner()
    {
        if ((hoveredButton == null || hoveredButton.getWorld() == null) && Greenfoot.mouseMoved(this))
        {
            hoveredButton = this;
        }
        else if (hoveredButton == this && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
        {
            hoveredButton = null;
        }
    }

    /**
     * This methods returns true if the mouse is hovering over the specified button.
     * @param button the button to see if hovering over
     * @return true, if mouse is over specified button
     */
    public boolean mouseHoveringOver(buttonStart button)
    {
        hoverOwner();
        return hoveredButton == button;
    }

    /**
     * Gets the button that the mouse is hovering over.
     * @return the button that the mouse is over
     */
    public buttonStart getHoverOwner()
    {
        return hoveredButton;
    }
}
