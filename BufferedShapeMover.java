import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class BufferedShapeMover extends Applet {

    static protected Label label;

    public void init() {
        // Initialize the layout.
        setLayout(new BorderLayout());
        add(new BSMCanvas());
        label = new Label("Drag rectangle around within the area");
        add("South", label);
    }

    public static void main(String s[]) {
        Frame f = new Frame("BufferedShapeMover");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Applet applet = new BufferedShapeMover();
        f.add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(new Dimension(550, 250));
        f.show();
    }

}

class BSMCanvas extends JPanel implements MouseListener, MouseMotionListener {

    Shape rect = new Rectangle2D.Double(0, 0, 200, 50);
    BufferedImage bi;
    Graphics2D big;

    // Holds the coordinates of the user's last mousePressed event.
    int last_x, last_y;
    boolean firstTime = true;
    TexturePaint fillPolka, strokePolka;
    Rectangle area;

    // True if the user pressed, dragged or released the mouse outside of the
    // rectangle; false otherwise.
    boolean pressOut = false;

    public BSMCanvas() {
        setBackground(Color.white);
        addMouseMotionListener(this);
        addMouseListener(this);

        // Creates the fill texture paint pattern.
        // bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
        // big = bi.createGraphics();
        // big.setColor(Color.pink);
        // big.fillRect(0, 0, 7, 7);
        // big.setColor(Color.cyan);
        // big.fillOval(0, 0, 3, 3);
        // Rectangle r = new Rectangle(0,0,5,5);
        // fillPolka = new TexturePaint(bi, r);
        // big.dispose();

        // Creates the stroke texture paint pattern.
        // bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
        // big = bi.createGraphics();
        // big.setColor(Color.cyan);
        // big.fillRect(0, 0, 7, 7);
        // big.setColor(Color.pink);
        // big.fillOval(0, 0, 3, 3);
        // r = new Rectangle(0,0,5,5);
        // strokePolka = new TexturePaint(bi, r);
        // big.dispose();
    }

    // Handles the event of the user pressing down the mouse button.
    public void mousePressed(MouseEvent e) {

        last_x = (int) rect.getX() - e.getX();
        last_y = (int) rect.getY() - e.getY();

        // Checks whether or not the cursor is inside of the rectangle while the user is
        // pressing themouse.
        if (rect.contains(e.getX(), e.getY())) {
            updateLocation(e);
        } else {
            BufferedShapeMover.label.setText("First position the cursor on the rectangle and then drag.");
            pressOut = true;
        }
    }

    // Handles the event of a user dragging the mouse while holding down the mouse
    // button.
    public void mouseDragged(MouseEvent e) {

        if (!pressOut) {
            updateLocation(e);
        } else {
            BufferedShapeMover.label.setText("First position the cursor on the rectangle and then drag.");
        }
    }

    // Handles the event of a user releasing the mouse button.
    public void mouseReleased(MouseEvent e) {

        // Checks whether or not the cursor is inside of the rectangle when the user
        // releases the
        // mouse button.
        if (rect.contains(e.getX(), e.getY())) {
            updateLocation(e);
        } else {
            BufferedShapeMover.label.setText("First position the cursor on the rectangle and then drag.");
            pressOut = false;
        }
    }

    // This method required by MouseListener.
    public void mouseMoved(MouseEvent e) {
    }

    // These methods are required by MouseMotionListener.
    public void mouseClicked(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void updateLocation(MouseEvent e) {

        rect.setRect(last_x + e.getX(), last_y + e.getY(), rect.getWidth(), rect.getHeight());
        /*
         * Updates the label to reflect the location of the
         * current rectangle
         * if checkRect returns true; otherwise, returns error message.
         */
        if (checkRect()) {
            BufferedShapeMover.label.setText("Rectangle located at " +
                    rect.getX() + ", " +
                    rect.getY());
        } else {
            BufferedShapeMover.label.setText("Please don't try to " +
                    " drag outside the area.");
        }
        repaint();
    }

    public void paint(Graphics g) {
        update(g);
    }

    public void update(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (firstTime) {
            Dimension dim = getSize();
            int w = dim.width;
            int h = dim.height;
            area = new Rectangle(dim);
            bi = (BufferedImage) createImage(w, h);
            big = bi.createGraphics();
            rect.setRect(w / 2 - 50, h / 2 - 25, rect.getBounds().getWidth(), rect.getBounds().getHeight());
            // rect.setLocation(w/2-50, h/2-25);=
            big.setStroke(new BasicStroke(8.0f));
            firstTime = false;
        }

        // Clears the rectangle that was previously drawn.
        big.setColor(Color.white);
        big.clearRect(0, 0, area.width, area.height);

        // Draws and fills the newly positioned rectangle to the buffer.
        big.draw(rect);
        // if(!pressOut){
        // big.setPaint(strokePolka);
        // big.draw(rect);
        // big.setPaint(fillPolka);
        // big.fill(rect);
        // }

        // Draws the buffered image to the screen.
        g2.drawImage(bi, 0, 0, this);
    }

    /*
     * Checks if the rectangle is contained within the applet window. If the
     * rectangle
     * is not contained withing the applet window, it is redrawn so that it is
     * adjacent
     * to the edge of the window and just inside the window.
     */
    boolean checkRect() {
        return true;
    //     if (area == null) {
    //         return false;
    //     }
    //     if (area.contains(rect.x, rect.y, rect.getWidth(), rect.getHeight())) {
    //         return true;
    //     }
    //     int new_x = (int) rect.getX();
    //     int new_y = (int) rect.getY();

    //     if ((rect.x + 100) > area.width) {
    //         new_x = area.width - 99;
    //     }
    //     if (rect.x < 0) {
    //         new_x = -1;
    //     }
    //     if ((rect.y + 50) > area.height) {
    //         new_y = area.height - 49;
    //     }
    //     if (rect.y < 0) {
    //         new_y = -1;
    //     }
    //     rect.setRect(new_x, new_y, rect.getWidth(), rect.getHeight());
    //     // rect.setLocation(new_x, new_y);
    //     return false;
    }

}
