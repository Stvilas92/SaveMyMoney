package save_my_money;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class Window extends JFrame
{
    public Window()
            {
                this.setTitle("Save my money");
                //this.setSize(500,300);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setResizable(true);
            }
    
    public void setWindowSize(int width,int height)
    {
        this.setSize(width,height);
    }
}
