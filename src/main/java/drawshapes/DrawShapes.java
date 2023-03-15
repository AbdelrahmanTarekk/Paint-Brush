
package drawshapes;

import javax.swing.JFrame;
public class DrawShapes {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Draw Shapes App");
        f.setLocation(200, 100);
        ShapesPanel mp = new ShapesPanel();
        f.setContentPane(mp);
        f.setSize(800, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
