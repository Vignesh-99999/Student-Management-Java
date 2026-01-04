package PanelCode;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelCode {
    static JFrame frame=null;
     public static <T extends JPanel> void Panel(T acceptRequestPanel) 
     {

        frame = new JFrame("My Application");
        // Add your AcceptRequest panel to the frame
        frame.getContentPane().add(acceptRequestPanel);
        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack(); // Adjusts frame size based on its components
        frame.setLocationRelativeTo(null); // Centers the frame on the screen
        frame.setVisible(true); // Display the frame
        
    }
    public static <T extends JFrame > void FramPanel(T acceptRequestPanel)
    {
        frame = new JFrame("My Application");
        // Add your AcceptRequest panel to the frame
        frame.getContentPane().add(acceptRequestPanel);
        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // Adjusts frame size based on its components
        frame.setLocationRelativeTo(null); // Centers the frame on the screen
        frame.setVisible(true); // Display the frame
    }

    public static void close()
    {
        frame.dispose();
    }
}
