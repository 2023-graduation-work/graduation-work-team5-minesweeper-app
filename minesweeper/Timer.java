import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.text.DecimalFormat;
/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor
{
    private long startTime; // this is the starting time of the scenario
    private int elapsedTime = 00; // this is the total elapsed time
    private int hour = 00;
    private int minute = 00;
    private int second = 00;
    private int hun = 00;
    private boolean running = false;
    private String prefix = "";
    
    public Timer()
    {
        Greenfoot.setSpeed(100);
        prefix = "Time:";
    }
    
    public Timer(String prefix)
    {
        Greenfoot.setSpeed(100);
        this.prefix = prefix;
    }
    @Override
    public void act()
    {
        Greenfoot.setSpeed(100);
        if (running == false)
        {
            startTime = System.currentTimeMillis();
            running = true; // this "if" is so that startTime is initialized only after "Run" is pressed but still only initialized once
        }
        reset();
        setImage(new GreenfootImage(prefix + "\n" + elapsedTime(), 45, Color.BLACK, new Color(0, 0, 0, 0)));
    }
    
    private String elapsedTime()
    {
        MineSweeper world = (MineSweeper) getWorld();
        if (running == true)
        {
            if (world.stopped)
            {
                startTime += world.startedTime - world.stoppedTime;
                world.stopped = false;
            }
            if (world.middleStop && !world.buttonStopped)
            {
                startTime += world.startedMiddle - world.stoppedMiddle;
                world.middleStop = false;
            }
            if (!world.buttonStopped)
            {
                elapsedTime = (int)(System.currentTimeMillis() - startTime); // figures out the total elapsed time
            }
            if (!world.buttonStopped && !world.middleStop)
            {
                hour = elapsedTime / 3600000;
                elapsedTime -= hour * 3600000;
                minute = elapsedTime / 60000;
                elapsedTime -= minute * 60000;
                second = elapsedTime / 1000;
                elapsedTime -= second * 1000;
                hun = elapsedTime / 10;
            }
        }
        return new DecimalFormat("00").format(hour) + ":" + new DecimalFormat("00").format(minute) + ":" + new DecimalFormat("00").format(second) + "." + new DecimalFormat("00").format(hun);
        // the above line makes the timer string, the decimal format makes the timer have two digits (00) (you could do more if you do (000) or (0.0) etc.)
    }

    /**
     * Reset the stopwatch to 00:00:00.00
     */
    private void reset()
    {
        MineSweeper world = (MineSweeper) getWorld();
        if (world.reset)
        {
            running = false;
            elapsedTime = 00;
            hun = 00;
            second = 00;
            minute = 00;
            hour = 00;
        }
    }
}
