package feedback;

import feedback.adapters.FeedbackData;
import feedback.adapters.FeedbackAdapter;
import feedback.adapters.MantisAdapter;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

class FeedbackFrame extends JFrame {
    public static final double BIG_IMAGE_SCALE = 0.9;

    public static void main(String[] args) {
        FeedbackFrame frame = new FeedbackFrame(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private JTextArea descriptionField;
    private Image screenshot;
    private JTextField titleField;

    public FeedbackFrame(Image screenshot) {
        final JPanel panelPrincipal = montarPanelPrincipal(screenshot);
        add(panelPrincipal);
        setResizable(false);
        pack();
    }

    private JPanel montarPanelPrincipal(Image screenshot) {
        this.screenshot = screenshot;
        this.titleField = new JTextField("Titulo");
        this.descriptionField = new JTextArea("Descricao", 5, 30);

        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(getImagePreviewPanel(screenshot));
        panel.add(new JLabel("Título"));
        panel.add(titleField);
        panel.add(new JLabel("Descrição"));
        descriptionField.setLineWrap(true);
        descriptionField.setRows(5);
        descriptionField.setWrapStyleWord(true);
        panel.add(new JScrollPane(descriptionField));
        panel.add(montarPainelDeBotoes());
        return panel;
    }

    private JImagePanel getImagePreviewPanel(final Image screenshot) {
        final JImagePanel imagePreviewPanel = new JImagePanel(screenshot, 300, 200);
        imagePreviewPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                showBigImage(screenshot);
            }
        });

        return imagePreviewPanel;
    }

    private void showBigImage(Image image) {
        final JFrame jframe = new JFrame();
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
        screenDimensions.setSize(screenDimensions.width * BIG_IMAGE_SCALE, screenDimensions.height * BIG_IMAGE_SCALE);
        final JImagePanel imagePanel = new JImagePanel(image, screenDimensions);
        jframe.add(imagePanel);
        jframe.pack();
        jframe.setVisible(true);

        imagePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imagePanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                jframe.setVisible(false);
            }
        });
    }

    private JPanel montarPainelDeBotoes() {
        final JPanel panel = new JPanel(new FlowLayout());
        panel.add(montarBotaoEnviar());
        panel.add(montarBotaoFechar());
        return panel;
    }

    private JButton montarBotaoEnviar() {
        final JButton jButton = new JButton("Enviar");
        jButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                enviarFeedback();
                fecharJanela();
            }
        });
        return jButton;
    }

    private JButton montarBotaoFechar() {
        final JButton jButton = new JButton("Fechar");
        jButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                fecharJanela();
            }
        });
        return jButton;
    }
    private FeedbackAdapter feedbackAdapter = new MantisAdapter();

    private void enviarFeedback() {
        MessageFrame messageFrame = MessageFrame.show("Enviando feedback...");
        FeedbackData feedbackData = new FeedbackData(screenshot, titleField.getText(), descriptionField.getText());
        feedbackAdapter.send(feedbackData);
        messageFrame.setVisible(false);
        JOptionPane.showMessageDialog(null, "Feedback enviado.");
    }

    private void fecharJanela() {
        setVisible(false);
    }
}
