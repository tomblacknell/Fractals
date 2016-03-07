import javax.swing.*;
import java.awt.*;

public class MandelbrotPanel extends JPanel {

    private static final double MIN_X = -2;
    private static final double MAX_X = 2;
    private static final double MIN_Y = -1.6;
    private static final double MAX_Y = 1.6;
    private static final int ITERATIONS = 100;

    private double width, height;

    public MandelbrotPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        // draw the mandelbrot
        for (int i = 0; i < width; i++) {
            double x = MIN_X + ((i / width) * (MAX_X - MIN_X));
            for (int j = 0; j < height; j++) {
                double y = MIN_Y + ((j / height) * (MAX_Y - MIN_Y));

                ComplexNumber c = new ComplexNumber(x, y);

                int pointOfDiverge = getPointOfDiverge(c);
                //System.out.println("(" + x + ", " + y + ") diverges at " + pointOfDiverge);

                graphics.setColor(getColor(pointOfDiverge));
                graphics.drawLine(i, j, i, j);
            }
        }

        // draw the axis
        graphics.setColor(Color.WHITE);

        if (MIN_X < 0 && MAX_X > 0) {
            graphics.drawLine((int) ((0 - MIN_X) / (MAX_X - MIN_X) * width), (int) height, (int) ((0 - MIN_X) / (MAX_X - MIN_X) * width), 0);
        }

        if (MIN_Y < 0 && MAX_Y > 0) {
            graphics.drawLine((int) width, (int) ((0 - MIN_Y) / (MAX_Y - MIN_Y) * height), 0, (int) ((0 - MIN_Y) / (MAX_Y - MIN_Y) * height));
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
        if (p == 0) {
            return Color.YELLOW;
        } else if (p == 1) {
            return Color.ORANGE;
        } else if (p <= 15) {
            return Color.RED;
        } else if (p <= 30) {
            return Color.CYAN;
        } else if (p <= 40) {
            return Color.LIGHT_GRAY;
        } else if (p <= 45) {
            return Color.GRAY;
        } else if (p <= 60) {
            return Color.BLACK;
        } else {
            return Color.BLACK;
        }
    }

}
