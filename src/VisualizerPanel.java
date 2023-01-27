import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VisualizerPanel extends JPanel {

    final int maxCol = 15;
    final int maxRow = 10;
    final int nodeSize = 70;
    final int SCREEN_WIDTH = maxCol * nodeSize;
    final int SCREEN_HEIGHT = maxRow * nodeSize;

    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;

    // openList contains all the current open nodes
    ArrayList<Node> openList = new ArrayList<>();
    // checkedList contains the nodes that have already been traversed
    ArrayList<Node> checkedList = new ArrayList<>();

    boolean goalReached = false;

    VisualizerPanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(maxRow, maxCol));
        // the key listener is used to manually traverse nodes
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        // place all nodes on panel
        int col = 0;
        int row = 0;
        while(col < maxCol && row < maxRow) {
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);

            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }

        // starting and end nodes
        setStartNode(2, 8);
        setGoalNode(11, 3);

        // solid nodes
        setSolidNode(10, 2);
        setSolidNode(10, 3);
        setSolidNode(10, 4);
        setSolidNode(10, 5);
        setSolidNode(10, 6);
        setSolidNode(10, 7);
        setSolidNode(6, 2);
        setSolidNode(7, 2);
        setSolidNode(8, 2);
        setSolidNode(9, 2);
        setSolidNode(11, 7);
        setSolidNode(12, 7);
        setSolidNode(6, 1);

        // display the cost of the nodes
        setCostOnNodes();
    }

    private void setStartNode(int col, int row){
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row){
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }

    private void setSolidNode(int col, int row){
        node[col][row].setAsSolid();
    }

    // uses getCost method to calculate all costs of each node
    private void setCostOnNodes(){
        int col = 0;
        int row = 0;
        while(col < maxCol && row < maxRow){
            getCost(node[col][row]);

            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }
    }

    private void getCost(Node node){

        // calculate g cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);

        node.gCost = xDistance + yDistance;

        // calculate h cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);

        node.hCost = xDistance + yDistance;

        // calculate f cost
        node.fCost= node.gCost + node.hCost;

        // display fCost and gCost on nodes
        if(node != startNode && node != goalNode ){
            node.setText("<html>F:" + node.fCost + "<br>G:" + node.gCost + "</html>");
        }
    }

    public void search(){
        // search as long as the goal node has not been reached
        if(!goalReached){
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // open the top node
            if(row - 1 >= 0){
                openNode(node[col][row - 1]);
            }
            // open the left node
            if(col - 1 >= 0){
                openNode(node[col - 1][row]);
            }
            // open the bottom node
            if(row + 1< maxRow){
                openNode(node[col][row + 1]);
            }
            // open the right node
            if(col + 1 < maxCol){
                openNode(node[col + 1][row]);
            }

            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            // loop through each open node
            for(int i = 0; i < openList.size(); i++){
                // find lowest fCost among open nodes
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if(openList.get(i).fCost == bestNodefCost){
                    // find lowest gCost if fCost is the same
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            // move the pointer to the best available node
            currentNode = openList.get(bestNodeIndex);

            // stop searching once goal node is found
            if(currentNode == goalNode){
                goalReached = true;
            }
        }
    }

    private void openNode(Node node){
        if(!node.open && !node.checked && !node.solid){
            // if the node has not been opened, add it to the list
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }
}
