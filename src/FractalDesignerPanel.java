import javax.swing.*;
import java.awt.*;

public class FractalDesignerPanel extends JPanel {

    public FractalDesignerPanel(int width, int height) {
        super(new GridLayout(1,2));
        add(new MandelbrotPanel(width/2, height));
        add(new ControlPanel());
    }

}
