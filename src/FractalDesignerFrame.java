import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FractalDesignerFrame extends JFrame {

    private JPanel fractalDesignerPanel, otherHalf, controlPanel;
    private MandelbrotPanel mandelBrotPanel;
    public JuliaPanel juliaPanel;
    private int width, height;
    private JSlider iterationSlider;
    private JSpinner minXSpinner, maxXSpinner, minYSpinner, maxYSpinner;

    public FractalDesignerFrame(int w, int h) {

        this.width = w;
        this.height = h;

        fractalDesignerPanel = new JPanel(new GridLayout(1, 2));
        fractalDesignerPanel.setPreferredSize(new Dimension(width, height));

        mandelBrotPanel = new MandelbrotPanel(w/2, h);
        otherHalf = new JPanel(new GridLayout(2, 1));
        juliaPanel = new JuliaPanel(w/2, h/2);
        iterationSlider = new JSlider();
        iterationSlider.setMinimum(0);
        iterationSlider.setMaximum(100);
        iterationSlider.setValue(100);
        iterationSlider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent ce) {
                mandelBrotPanel.updateIterations(iterationSlider.getValue());
                mandelBrotPanel.repaint();
            }
        });

        SpinnerNumberModel minXModel = new SpinnerNumberModel(-2, -5, 5, 0.1);
        (minXSpinner = new JSpinner()).setModel(minXModel);
        minXSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mandelBrotPanel.updateMinX((Double) minXSpinner.getValue());
                mandelBrotPanel.repaint();
            }
        });

        SpinnerNumberModel maxXModel = new SpinnerNumberModel(2, -5, 5, 0.1);
        (maxXSpinner = new JSpinner()).setModel(maxXModel);
        maxXSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mandelBrotPanel.updateMaxX((Double) maxXSpinner.getValue());
                mandelBrotPanel.repaint();
            }
        });

        SpinnerNumberModel minYModel = new SpinnerNumberModel(-1.6, -5, 5, 0.1);
        (minYSpinner = new JSpinner()).setModel(minYModel);
        minYSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mandelBrotPanel.updateMinY((Double) minYSpinner.getValue());
                mandelBrotPanel.repaint();
            }
        });

        SpinnerNumberModel maxYModel = new SpinnerNumberModel(1.6, -5, 5, 0.1);
        (maxYSpinner = new JSpinner()).setModel(maxYModel);
        maxYSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mandelBrotPanel.updateMaxY((Double) maxYSpinner.getValue());
                mandelBrotPanel.repaint();
            }
        });

        controlPanel = new JPanel(new GridLayout(5, 2));
        controlPanel.add(new JLabel("Max X", SwingConstants.RIGHT));
        controlPanel.add(maxXSpinner);
        controlPanel.add(new JLabel("Min X", SwingConstants.RIGHT));
        controlPanel.add(minXSpinner);
        controlPanel.add(new JLabel("Max Y", SwingConstants.RIGHT));
        controlPanel.add(maxYSpinner);
        controlPanel.add(new JLabel("Min Y", SwingConstants.RIGHT));
        controlPanel.add(minYSpinner);
        controlPanel.add(new JLabel("Iterations", SwingConstants.RIGHT));
        controlPanel.add(iterationSlider);

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
                juliaPanel.updateSize(width/2, height/2);
            }
        });

    }

}
