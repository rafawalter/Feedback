
package feedback;

import javax.swing.JFrame;
import javax.swing.JLabel;

class MessageFrame extends JFrame {

    static MessageFrame show(String message) {
        MessageFrame messageFrame = new MessageFrame(message);
        messageFrame.setVisible(true);
        return messageFrame;
    }

    private MessageFrame(String message) {
        add(new JLabel(message));
        pack();
    }

}
