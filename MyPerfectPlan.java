/** MyPerfectPlan.java
  * Creates the main frame for the My Perfect Plan application.
  * 
  * Primarily developed by Jeanette Yue.
  * 
  * CS 230 Fall 2014 Final Project
  * 
  * @author Jeanette Yue, Sam Mincheva
  */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MyPerfectPlan {
  
  //-------------------------------------------------------------------------------
  /** Creates a new JFrame with three tabs: About, Add Courses, and Schedule.
    */
  public static void main (String[] args){
    
    JFrame frame = new JFrame("My Perfect Plan");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setSize(900, 750);
    
    Schedule schedule = new Schedule();
    
    JTabbedPane options = new JTabbedPane();
    
    options.addTab("About", new AboutTab());
    
    options.addTab("Add Courses", new AddCoursesTab(schedule));

    options.addTab("See Schedule", new ScheduleTab(schedule));
    
    frame.getContentPane().add(options);
  }
}