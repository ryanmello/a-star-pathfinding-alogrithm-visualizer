import javax.swing.*;

public class VisualizerFrame extends JFrame {
    VisualizerFrame(){
        this.add(new VisualizerPanel());
        this.setTitle("A* Pathfinding Algorithm Visualizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
