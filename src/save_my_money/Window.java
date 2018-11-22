package save_my_money;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

/**
 * Create the window configuration that we will used in  the program.
 * @author alejavilas92
 */
public class Window extends JFrame
{   
    //Border Layout
    public JFrame f = new JFrame();
    
    /**
     * Create a new JFrame where will be the graphic interface of the program
     */
    public Window()
            {
                f.setTitle("Save my money");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setResizable(false);
            }
}