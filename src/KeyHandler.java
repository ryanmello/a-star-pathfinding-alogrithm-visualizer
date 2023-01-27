import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    VisualizerPanel vp;

    public KeyHandler(VisualizerPanel vp){
        this.vp = vp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_ENTER){
            vp.search();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
