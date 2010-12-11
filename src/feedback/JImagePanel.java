package feedback;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class JImagePanel extends JPanel {

    private static final String NO_SCREENSHOT_FILE = "images/not-available.jpg";
    private static final Image IMAGE_NO_SCREENSHOT = Toolkit.getDefaultToolkit().getImage(NO_SCREENSHOT_FILE);
    private Image image;

    public JImagePanel(Image image, Dimension imageDimension) {
        this(image, imageDimension.width, imageDimension.height);
    }

    public JImagePanel(Image image, int width, int height) {
        if (image == null) {
            image = IMAGE_NO_SCREENSHOT;
        }
        setImage(image);

        add(new JLabel(new ImageIcon(image.getScaledInstance(width, height,0))));
    }

    private void setImage(Image image) {
        this.image = image;
    }

    private Image getImage() {
        return image;
    }


}
