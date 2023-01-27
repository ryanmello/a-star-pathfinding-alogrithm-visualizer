import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

    Node parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;

        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }

    public void setAsStart(){
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("Start");
        start = true;
    }

    public void setAsGoal(){
        setBackground(new Color(140, 255, 140));
        setForeground(Color.black);
        setText("Goal");
        goal = true;
    }

    public void setAsSolid(){
        setBackground(new Color(0, 71, 100));
        setForeground(new Color(0, 71, 100));
        solid = true;
    }

    public void setAsOpen(){
        open = true;
    }

    public void setAsChecked(){
        if(!start && !goal){
            setBackground(new Color(25, 189, 255));
            setForeground(Color.black);
        }
        checked = true;
    }

    public void setAsPath(){
        setBackground(Color.green);
        setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.red);
    }
}
