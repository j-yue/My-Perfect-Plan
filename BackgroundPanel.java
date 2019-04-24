/** BackgroundPanel.java
  * Creates a panel with a background image.
  * 
  * Primarily developed by Jeanette Yue.
  * 
  * CS 230 Fall 2014 Final Project
  * 
  * @author Jeanette Yue, Sam Mincheva
  */

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel{
  
  private Image image;
  
  //-------------------------------------------------------------------------------
  /** Creates a new BackgroundPanel with the specified image.
    * 
    * @param image the desired background image
    */
  public BackgroundPanel(Image image){
    this.image = image;
  }
  
  //-------------------------------------------------------------------------------
  /** Creates a new BackgroundPanel with no image. Used to handle IOExceptions when
    * creating this panel.
    */
  public BackgroundPanel() {
    super();
  }
  
  //-------------------------------------------------------------------------------
  /** Adds the image to the panel.
    */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(image, 0,0, this);
  }
}