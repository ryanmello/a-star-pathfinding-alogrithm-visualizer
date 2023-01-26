import javax.swing.*;
import java.awt.*;

public class VisualizerPanel extends JPanel {
    int SCREEN_WIDTH = 800;
    int SCREEN_HEIGHT = 600;
    VisualizerPanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }
}
