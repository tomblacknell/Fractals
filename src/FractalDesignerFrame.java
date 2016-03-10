import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FractalDesignerFrame extends JFrame {

    private JPanel fractalDesignerPanel, otherHalf, controlPanel;
    private MandelbrotPanel mandelBrotPanel;
    private JuliaPanel juliaPanel;
    private int width, height;

    public FractalDesignerFrame(int w, int h) {

        this.width = w;
        this.height = h;

        fractalDesignerPanel = new JPanel(new GridLayout(1, 2));
        fractalDesignerPanel.setPreferredSize(new Dimension(width, height));

        mandelBrotPanel = new MandelbrotPanel(w/2, h);
        otherHalf = new JPanel(new GridLayout(2, 1));
        juliaPanel = new JuliaPanel(otherHalf.getWidth(), otherHalf.getHeight()/2);
        controlPanel = new JPanel();

        otherHalf.add(juliaPanel);
        otherHalf.add(controlPanel);

        fractalDesignerPanel.add(mandelBrotPanel);
        fractalDesignerPanel.add(otherHalf);

        setTitle("Mandelbrot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setContentPane(fractalDesignerPanel);
        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                width = getWidth();
                height = getHeight();

                mandelBrotPanel.updateSize(width/2, height);
            }
        });

    }

}
