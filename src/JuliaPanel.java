import javax.swing.*;
import java.awt.*;

public class JuliaPanel extends JPanel {

    private double MIN_X = -2;
    private double MAX_X = 2;
    private double MIN_Y = -1.6;
    private double MAX_Y = 1.6;
    private int ITERATIONS = 100;

    private ComplexNumber c = new ComplexNumber(-0.835, -0.2321);

    private double width, height;

    public JuliaPanel(int width, int height) {
        super();
        System.out.println("Creating julia panel");
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEtchedBorder());
        this.width = width;
        this.height = height;
    }

    public void updateC(ComplexNumber c) {
        this.c = c;
    }

    public void updateSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private ComplexNumber translateToComplex(int x, int y) {
        return new ComplexNumber(MIN_X + ((x / width) * (MAX_X - MIN_X)),  MIN_Y + ((y / height) * (MAX_Y - MIN_Y)));
    }

    @Override
    public void paintComponent(Graphics graphics) {

        System.out.println("Drawing julia");

        super.paintComponent(graphics);

        //draw julia set
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ComplexNumber d = translateToComplex(i, j);

                int pointOfDiverge = Util.getPointOfDiverge(c, d, ITERATIONS);

                graphics.setColor(Util.getColor(pointOfDiverge));
                graphics.drawLine(i, j, i, j);
            }
        }

        //draw axis
        graphics.setColor(Color.WHITE);
        graphics.drawLine((int) width/2, 0, (int) width/2, (int) height);
        graphics.drawLine(0, (int) height/2, (int) width, (int) height/2);

        graphics.drawString(Double.toString(MAX_X), (int) width - 30, (int) ((0 - MIN_Y) / (MAX_Y - MIN_Y) * height) + 15);
        graphics.drawString(Double.toString(MIN_X), 10, (int) ((0 - MIN_Y) / (MAX_Y - MIN_Y) * height) + 15);

        graphics.drawString(Double.toString(MIN_Y), (int) ((0 - MIN_X) / (MAX_X - MIN_X) * width) + 5, (int) height - 30);
        graphics.drawString(Double.toString(MAX_Y), (int) ((0 - MIN_X) / (MAX_X - MIN_X) * width) + 5, 20);

        graphics.drawString("0", (int) ((0 - MIN_X) / (MAX_X - MIN_X) * width) + 5, (int) ((0 - MIN_Y) / (MAX_Y - MIN_Y) * height) + 15);

    }

}
