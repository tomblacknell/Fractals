import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MandelbrotPanel extends JPanel {

    private static final double MIN_X = -2;
    private static final double MAX_X = 2;
    private static final double MIN_Y = -1.6;
    private static final double MAX_Y = 1.6;
    private static final int ITERATIONS = 100;

    private ComplexNumber mouseCurrent, mouseClicked;
    private double width, height;

    public MandelbrotPanel(int w, int h) {
        super();
        this.width = w;
        this.height = h;

        //System.out.println("Creating mandelbrot panel (w: " + width + " h: " + height + ")");

        setPreferredSize(new Dimension((int) width, (int) height));
        setBorder(BorderFactory.createEtchedBorder());

        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseClicked = translateToComplex(e.getX(), e.getY());
                System.out.println("Mouse Clicked");
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mouseCurrent = translateToComplex(e.getX(), e.getY());
                repaint();
            }
        });

    }

    public void updateSize(int w, int h) {
        this.width = w;
        this.height = h;
    }

    private ComplexNumber translateToComplex(int x, int y) {
        return new ComplexNumber(MIN_X + ((x / width) * (MAX_X - MIN_X)),  MIN_Y + ((y / height) * (MAX_Y - MIN_Y)));
    }

    @Override
    public void paintComponent(Graphics graphics) {

        //System.out.println("Drawing mandelbrot (w: " + width + " h: " + height + ")");

        super.paintComponent(graphics);

        // draw the mandelbrot
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ComplexNumber c = translateToComplex(i, j);

                int pointOfDiverge = getPointOfDiverge(c);

                graphics.setColor(getColor(pointOfDiverge));
                graphics.drawLine(i, j, i, j);
            }
        }

        // draw the axis
        graphics.setColor(Color.WHITE);

        if (MIN_X < 0 && MAX_X > 0) {
            graphics.drawLine((int) ((0 - MIN_X) / (MAX_X - MIN_X) * width), (int) height, (int) ((0 - MIN_X) / (MAX_X - MIN_X) * width), 0);
            graphics.drawString("0", (int) ((0 - MIN_X) / (MAX_X - MIN_X) * width) + 5, (int) ((0 - MIN_Y) / (MAX_Y - MIN_Y) * height) + 15);

            graphics.drawString(Double.toString(MAX_X), (int) width - 30, (int) ((0 - MIN_Y) / (MAX_Y - MIN_Y) * height) + 15);
            graphics.drawString(Double.toString(MIN_X), 10, (int) ((0 - MIN_Y) / (MAX_Y - MIN_Y) * height) + 15);
        }

        if (MIN_Y < 0 && MAX_Y > 0) {
            graphics.drawLine((int) width, (int) ((0 - MIN_Y) / (MAX_Y - MIN_Y) * height), 0, (int) ((0 - MIN_Y) / (MAX_Y - MIN_Y) * height));

            graphics.drawString(Double.toString(MIN_Y), (int) ((0 - MIN_X) / (MAX_X - MIN_X) * width) + 5, (int) height - 30);
            graphics.drawString(Double.toString(MAX_Y), (int) ((0 - MIN_X) / (MAX_X - MIN_X) * width) + 5, 20);
        }

        if (mouseCurrent != null) {
            graphics.drawString("Current: " + String.format("%.2f", mouseCurrent.getImaginaryPart()) + ", " + String.format("%.2f", mouseCurrent.getRealPart()), 10, 20);
        }

        if (mouseClicked != null) {
            graphics.drawString("Clicked: " + String.format("%.2f", mouseClicked.getImaginaryPart()) + ", " + String.format("%.2f", mouseClicked.getRealPart()), 10, 45);
        }

    }

    public int getPointOfDiverge(ComplexNumber c) {
        ComplexNumber z = new ComplexNumber(c.getRealPart(), c.getImaginaryPart());
        for (int i = 0; i < ITERATIONS; i++) {
            if (z.modulusSquared() > 4) {
                return i;
            }
            z.square();
            z.add(c);
        }
        return ITERATIONS;
    }

    public Color getColor(int p) {
        if (p <= 1 ) {
            return Color.BLACK;
        } else if (p <= 2 ) {
            return new Color(0, 20, 0);
        } else if (p <= 3 ) {
            return new Color(2, 40, 0);
        } else if (p <= 4 ) {
            return new Color(2,60, 0);
        } else if (p <= 5 ) {
            return new Color(3,80,0);
        } else if (p <= 6 ) {
            return new Color(4,100,0);
        } else if (p <= 10) {
            return new Color(5,120,0);
        } else if (p <= 20) {
            return new Color(6,140,0);
        } else if (p <= 30) {
            return new Color(7,160,0);
        } else if (p < 100) {
            return new Color(8,230,0);
        } else {
            return new Color(0,0,0);
        }
    }

}
