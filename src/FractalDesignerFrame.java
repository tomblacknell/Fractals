import javax.swing.*;

public class FractalDesignerFrame extends JFrame {

    private FractalDesignerPanel panel;

    public FractalDesignerFrame(int width, int height) {

        panel = new FractalDesignerPanel(width, height);

        setTitle("Mandelbrot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setContentPane(panel);
        setVisible(true);

    }

}
