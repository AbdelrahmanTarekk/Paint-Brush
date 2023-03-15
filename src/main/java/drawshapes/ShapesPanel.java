package drawshapes;

import java.awt.BasicStroke;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ShapesPanel extends JPanel {

    int x1;
    int y1;
    int x2;
    int y2;
    int width, hight;
    int dot;
    Vector<oval> vecCircles = new Vector<oval>();
    Vector<lines> linesarr = new Vector<lines>();
    Vector<rectangle> rec = new Vector<rectangle>();
    Vector<fillRec> fillRectangle = new Vector<fillRec>();
    Vector<fillOval> fillOv = new Vector<fillOval>();
    Vector<eraser> eraser = new Vector<eraser>();
    Vector<hand> hand = new Vector<hand>();

    JComboBox selectShape;
    JRadioButton greenC;
    JRadioButton redC;
    JRadioButton blueC;
    JRadioButton yellowC;
    JButton Clear;
    JButton Eraser;
    JCheckBox Fill;
    JCheckBox Dotted;
    Color color = new Color(0, 0, 0);
    Color bgcol = new Color(0, 0, 0);

    public ShapesPanel() {
       
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
        dot = 0;
        bgcol = Color.white;
        this.setBackground(bgcol);
        this.setFocusable(true);
        greenC = new JRadioButton("GREEN");
        greenC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.GREEN;

            }

        });
        redC = new JRadioButton("RED");
        redC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.RED;

            }

        });
        blueC = new JRadioButton("BLUE");
        blueC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.BLUE;

            }

        });
        yellowC = new JRadioButton("YELLOW");
        yellowC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.YELLOW;

            }

        });

        ButtonGroup colors = new ButtonGroup();
        colors.add(greenC);
        colors.add(redC);
        colors.add(blueC);
        colors.add(yellowC);

        this.add(greenC);
        this.add(redC);
        this.add(blueC);
        this.add(yellowC);

        String[] shapes = {"Line", "Rectangle", "Filled-Rectangle", "Oval", "Filled-Oval", "Free-Hand", "Eraser"};
        selectShape = new JComboBox(shapes);
        this.add(selectShape);
        this.setFocusable(true);

        ////////////////////////////////////
        Dotted = new JCheckBox("Dotted");
        Dotted.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    dot = 1;
                } else {
                    dot = 0;
                }
            }
        });

        ///////////////////////////////////
        Clear = new JButton("clearAll");
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                linesarr.clear();
                rec.clear();
                vecCircles.clear();
                fillRectangle.clear();
                fillOv.clear();
                hand.clear();
                eraser.clear();
                updateUI();

            }

        });
        /////////////////////////////////////
        
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (selectShape.getSelectedItem().toString() == "Line") {
                    x1 = e.getX();
                    y1 = e.getY();
                } else if (selectShape.getSelectedItem().toString() == "Rectangle") {
                    x1 = e.getX();
                    y1 = e.getY();
                } else if (selectShape.getSelectedItem().toString() == "Filled-Rectangle") {
                    x1 = e.getX();
                    y1 = e.getY();
                } else if (selectShape.getSelectedItem().toString() == "Oval") {
                    x1 = e.getX();
                    y1 = e.getY();
                } else if (selectShape.getSelectedItem().toString() == "Filled-Oval") {
                    x1 = e.getX();
                    y1 = e.getY();
                } else if (selectShape.getSelectedItem().toString() == "Free-Hand") {
                    x1 = e.getX();
                    y1 = e.getY();
                    x2 = 5;
                    y2 = 5;
                    hand.add(new hand(x1, y1, x2, y2, color));
                    updateUI();

                } else if (selectShape.getSelectedItem().toString() == "Eraser") {
                    x1 = e.getX();
                    y1 = e.getY();
                    x2 = 6;
                    y2 = 6;
                    eraser.add(new eraser(x1, y1, x2, y2, color));
                    updateUI();

                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectShape.getSelectedItem().toString() == "Line") {
                    x2 = e.getX();
                    y2 = e.getY();
                    linesarr.add(new lines(x1, y1, x2, y2, color, dot));

                    // updateUI();
                } else if (selectShape.getSelectedItem().toString() == "Rectangle") {
                    width = e.getX() - x1;
                    hight = e.getY() - y1;
                    rec.add(new rectangle(x1, y1, width, hight, color, dot));
                    //  updateUI();
                } else if (selectShape.getSelectedItem().toString() == "Filled-Rectangle") {
                    x2 = e.getX() - x1;
                    y2 = e.getY() - y1;
                    fillRectangle.add(new fillRec(x1, y1, x2, y2, color));
                    //   updateUI();
                } else if (selectShape.getSelectedItem().toString() == "Oval") {
                    x2 = e.getX() - x1;
                    y2 = e.getY() - y1;
                    vecCircles.add(new oval(x1, y1, x2, y2, color, dot));
                    //   updateUI();
                } else if (selectShape.getSelectedItem().toString() == "Filled-Oval") {
                    x2 = e.getX() - x1;
                    y2 = e.getY() - y1;
                    fillOv.add(new fillOval(x1, y1, x2, y2, color));
                    //   updateUI();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //   x2=e.getX();
                // y2=e.getY();
                if (selectShape.getSelectedItem().toString() == "Line") {
                    x2 = e.getX();
                    y2 = e.getY();

                    updateUI();
                } else if (selectShape.getSelectedItem().toString() == "Rectangle") {
                    x2 = e.getX();
                    y2 = e.getY();
                    updateUI();
                } else if (selectShape.getSelectedItem().toString() == "Filled-Rectangle") {
                    x2 = e.getX();
                    y2 = e.getY();
                    updateUI();
                } else if (selectShape.getSelectedItem().toString() == "Oval") {
                    x2 = e.getX();
                    y2 = e.getY();
                    updateUI();
                } else if (selectShape.getSelectedItem().toString() == "Filled-Oval") {
                    x2 = e.getX();
                    y2 = e.getY();
                    updateUI();
                } else if (selectShape.getSelectedItem().toString() == "Free-Hand") {
                    x1 = e.getX();
                    y1 = e.getY();
                    x2 = 5;
                    y2 = 5;
                    dot = 0;
                    hand.add(new hand(x1, y1, x2, y2, color));
                    updateUI();
                } else if (selectShape.getSelectedItem().toString() == "Eraser") {
                    x1 = e.getX();
                    y1 = e.getY();
                    x2 = 6;
                    y2 = 6;
                    dot = 0;
                    eraser.add(new eraser(x1, y1, x2, y2, color));

                    updateUI();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });

        this.add(Clear);
        //  this.add(Fill);
        this.add(Dotted);
        // this.add(Eraser);

    }

    class hand {

        protected int x1;
        protected int y1;
        protected int x2;
        protected int y2;
        Color color = new Color(0, 0, 0);

        public hand(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;

        }
    }

    class eraser {

        protected int x1;
        protected int y1;
        protected int x2;
        protected int y2;
        Color color = new Color(0, 0, 0);

        public eraser(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }

    class lines {

        protected int x1;
        protected int y1;
        protected int x2;
        protected int y2;
        protected int d;

        // Color color=new Color(0,0,0);
        Color color;

        public lines(int x1, int y1, int x2, int y2, Color color, int d) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
            this.d = d;
        }
    }
    ////////////////////////////////////

    class oval {

        protected int x1, y1, x2, y2;
        Color color = new Color(0, 0, 0);
        protected int d;

        public oval(int x1, int y1, int x2, int y2, Color color, int d) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
            this.d = d;

        }
    }

    ////////////////////////////////////
    class fillOval {

        protected int x1, y1, x2, y2;
        Color color = new Color(0, 0, 0);

        public fillOval(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;

        }
    }

    ////////////////////////////////////
    class rectangle {

        protected int x1, y1, width, hight;
        Color color = new Color(0, 0, 0);
        protected int d;

        public rectangle(int x1, int y1, int width, int hight, Color color, int d) {
            this.x1 = x1;
            this.y1 = y1;
            this.width = width;
            this.hight = hight;
            this.color = color;
            this.d = d;

        }
    }

    ////////////////////////////////////
    class fillRec {

        protected int x1, y1, x2, y2;
        Color color = new Color(0, 0, 0);

        public fillRec(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;

        }

        ////////////////////////////////////
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (redC.isSelected()) {
            g.setColor(Color.red);
        } else if (greenC.isSelected()) {
            g.setColor(Color.GREEN);
        } else if (blueC.isSelected()) {
            g.setColor(Color.BLUE);
        } else if (yellowC.isSelected()) {
            g.setColor(Color.YELLOW);
        }
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);

        //////////////////////////////////
        if (selectShape.getSelectedItem().toString() == "Line") {

            if (dot == 0) {
                g.drawLine(x1, y1, x2, y2);
            } else if (dot == 1) {
                g2d.drawLine(x1, y1, x2, y2);
            }
        } else if (selectShape.getSelectedItem().toString() == "Free-Hand") {

            g.fillOval(x1, y1, x2, y2);
        } else if (selectShape.getSelectedItem().toString() == "Rectangle") {
            if (dot == 0) {
                g.drawRect(x1, y1, x2 - x1, y2 - y1);
            } else if (dot == 1) {
                g2d.drawRect(x1, y1, x2 - x1, y2 - y1);
            }

        } else if (selectShape.getSelectedItem().toString() == "Filled-Rectangle") {
            
            g.fillRect(Math.min(x1, x2),Math.min(y1, y2), Math.max(x1, x2)-Math.min(x1, x2), Math.max(y1, y2)-Math.min(y1, y2));

        } else if (selectShape.getSelectedItem().toString() == "Oval") {
            if (dot == 0) {
                g.drawOval(x1, y1, x2 - x1, y2 - y1);
            } else if (dot == 1) {
                g2d.drawOval(x1, y1, x2 - x1, y2 - y1);
            }

        } else if (selectShape.getSelectedItem().toString() == "Filled-Oval") {

            g.fillOval(x1, y1, x2 - x1, y2 - y1);
        } else if (selectShape.getSelectedItem().toString() == "Eraser") {
            g.setColor(bgcol);
            g.fillOval(x1, y1, x2, y2);
        }

        //////////////////////////////////////
        for (int i = 0; i < linesarr.size(); i++) {
            g.setColor(linesarr.get(i).color);
            if (linesarr.get(i).d == 0) {
                g.drawLine(linesarr.get(i).x1, linesarr.get(i).y1, linesarr.get(i).x2, linesarr.get(i).y2);
            } else if (linesarr.get(i).d == 1) {
                g2d.setColor(linesarr.get(i).color);
                g2d.drawLine(linesarr.get(i).x1, linesarr.get(i).y1, linesarr.get(i).x2, linesarr.get(i).y2);
            }

        }
        for (int i = 0; i < hand.size(); i++) {
            g.setColor(hand.get(i).color);
            g.fillOval(hand.get(i).x1, hand.get(i).y1, 5, 5);
        }
        for (int i = 0; i < rec.size(); i++) {
            g.setColor(rec.get(i).color);
            if (rec.get(i).d == 0) {
                g.drawRect(rec.get(i).x1, rec.get(i).y1, rec.get(i).width, rec.get(i).hight);
            } else if (rec.get(i).d == 1) {
                g2d.setColor(rec.get(i).color);
                g2d.drawRect(rec.get(i).x1, rec.get(i).y1, rec.get(i).width, rec.get(i).hight);
            }

        }
        for (int i = 0; i < vecCircles.size(); i++) {
            g.setColor(vecCircles.get(i).color);
            if (vecCircles.get(i).d == 0) {
                g.drawOval(vecCircles.get(i).x1, vecCircles.get(i).y1, vecCircles.get(i).x2, vecCircles.get(i).y2);
            } else if (vecCircles.get(i).d == 1) {
                g2d.setColor(vecCircles.get(i).color);
                g2d.drawOval(vecCircles.get(i).x1, vecCircles.get(i).y1, vecCircles.get(i).x2, vecCircles.get(i).y2);
            }
        }
        for (int i = 0; i < fillRectangle.size(); i++) {
            g.setColor(fillRectangle.get(i).color);

            g.fillRect(fillRectangle.get(i).x1, fillRectangle.get(i).y1, fillRectangle.get(i).x2, fillRectangle.get(i).y2);

        }
        for (int i = 0; i < fillOv.size(); i++) {
            g.setColor(fillOv.get(i).color);
            g.fillOval(fillOv.get(i).x1, fillOv.get(i).y1, fillOv.get(i).x2, fillOv.get(i).y2);

        }
        for (int i = 0; i < eraser.size(); i++) {
            g.setColor(white);
            g.fillOval(eraser.get(i).x1, eraser.get(i).y1, 6, 6);
        }
    }
}
