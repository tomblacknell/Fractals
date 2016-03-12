import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MandelbrotPanel extends JPanel {

    private double MIN_X = -2;
    private double MAX_X = 2;
    private double MIN_Y = -1.6;
    private double MAX_Y = 1.6;
    private int ITERATIONS = 100;

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

    public void updateIterations(int iterations) {
        this.ITERATIONS = iterations;
    }

    public void updateMinX(double minX) {
        this.MIN_X = minX;
    }

    public void updateMinY(double minY) {
        this.MIN_Y = minY;
    }

    public void updateMaxX(double maxX) {
        this.MAX_X = maxX;
    }

    public void updateMaxY(double maxY) {
        this.MAX_Y = maxY;
    }

    private ComplexNumber translateToComplex(int x, int y) {
        return new ComplexNumber(MIN_X + ((x / width) * (MAX_X - MIN_X)),  MIN_Y + ((y / height) * (MAX_Y - MIN_Y)));
    }

    private int[] translateToReal(ComplexNumber c) {
        int[] points = new int[2];

        points[0] = (int) (Math.abs(c.getRealPart() - MIN_X) / (MAX_X - MIN_X) * width);
        points[1] = (int) (Math.abs(c.getImaginaryPart() - MIN_Y) / ( MAX_Y - MIN_Y) * height);

        //System.out.println("Translated complex point (" + c.getRealPart() + ", " + c.getImaginaryPart() + ") to real point + (" + points[0] + ", " + points[1] + ")");

        return points;
    }

    @Override
    public void paintComponent(Graphics graphics) {

        //System.out.println("Drawing mandelbrot (w: " + width + " h: " + height + ")");

        super.paintComponent(graphics);

        // draw the mandelbrot
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ComplexNumber c = translateToComplex(i, j);

                int pointOfDiverge = Util.getPointOfDiverge(c, ITERATIONS);

                graphics.setColor(Util.getColor(pointOfDiverge));
                graphics.drawLine(i, j, i, j);
            }
        }

        // draw the axis
        graphics.setColor(Color.RED);
        if (mouseCurrent != null) {
            graphics.drawLine(translateToReal(mouseCurrent)[0], 0, translateToReal(mouseCurrent)[0], (int) height);
            graphics.drawLine(0, translateToReal(mouseCurrent)[1], (int) width, translateToReal(mouseCurrent)[1]);

        }

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
        } else {
            graphics.drawString("Current: ", 10, 20);
        }

        if (mouseClicked != null) {
            graphics.drawString("Clicked: " + String.format("%.2f", mouseClicked.getImaginaryPart()) + ", " + String.format("%.2f", mouseClicked.getRealPart()), 10, 45);
            graphics.setColor(Color.RED);
            graphics.fillRect(translateToReal(mouseClicked)[0] - 3, translateToReal(mouseClicked)[1] - 3, 6, 6);
        } else {
            graphics.drawString("Clicked: ", 10, 45);
        }
    }


}
