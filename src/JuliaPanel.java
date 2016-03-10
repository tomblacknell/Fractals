import javax.swing.*;
import java.awt.*;

public class JuliaPanel extends JPanel {

    public JuliaPanel(int width, int height) {
        super();
        System.out.println("Creating julia panel");
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEtchedBorder());
    }

    @Override
    public void paintComponent(Graphics graphics) {

        System.out.println("Drawing julia");
        graphics.setColor(Color.BLACK);
        graphics.drawLine(100, 100, 200, 200);

    }

}
