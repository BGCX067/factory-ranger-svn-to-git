/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.utility;

import factory.ranger.model.Map;
import factory.ranger.view.SimulationScreen;
import java.awt.Point;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UjianTimer.java
 *
 * Created on Dec 14, 2011
 *
 */
/**
 *
 * @author axioo
 */
public class SimTimer {
    Timer timer;
    RemindTask remindTask;

    public SimTimer(int minutes) {
        timer = new Timer();
        remindTask = new RemindTask(minutes*60);
    }
    
    public void start(){        
        timer.schedule(remindTask, 0, 1000);
    }
    
    public void stop(){
        timer.cancel();
    }    

    class RemindTask extends TimerTask {
        int duration;
    
        public RemindTask(int duration){
            this.duration = duration;
        }
    
        
        @Override
        public void run() {
            try {
                Map.getSingleton().setTruckPos(new Point(Map.getSingleton().getTruckPos().x-1, Map.getSingleton().getTruckPos().y-1));
                SimulationScreen.getSingleton().getContent().repaint();
                SimulationScreen.getSingleton().showLogRoute();
                
                //tryoutPanel.getSingleton().getJamTextField().setText(getTimeLeft());
                //duration--;
                System.out.println(Map.getSingleton().getTruckPos());
                //  timer.cancel(); //Terminate the timer thread
                  //  timer.cancel(); //Terminate the timer thread
            } catch (IOException ex) {
                Logger.getLogger(SimTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    public static void main(String args[]) {
        System.out.println("About to schedule task.");
        //UjianTimer ujianTimer = new UjianTimer(120);
        //ujianTimer.start();
        System.out.println("Task scheduled.");
    }
}