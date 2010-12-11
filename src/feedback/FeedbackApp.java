package feedback;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


class FeedbackApp {

    public static final String TRAY_ICON_OFF_FILE = "images/bulb-off.png";
    public static final String TRAY_ICON_ON_FILE = "images/bulb.png";
    public static final Image IMAGE_OFF = Toolkit.getDefaultToolkit().getImage(TRAY_ICON_OFF_FILE);
    public static final Image IMAGE_ON = Toolkit.getDefaultToolkit().getImage(TRAY_ICON_ON_FILE);

    public void mostrarIconeNaBarraDeTarefas() {
        final TrayIcon trayIcon = new TrayIcon(IMAGE_OFF, "Tray Demo");

        if (SystemTray.isSupported()) {

            System.out.println("Mostrando ícone");
            SystemTray tray = SystemTray.getSystemTray();

            MouseListener mouseListener = new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse clicked!");
                    trayIcon.setImage(IMAGE_ON);
                }

                public void mouseEntered(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse entered!");
                    trayIcon.setImage(IMAGE_ON);
                }

                public void mouseExited(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse exited!");
                    trayIcon.setImage(IMAGE_OFF);
                }

                public void mousePressed(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse pressed!");
                    trayIcon.setImage(IMAGE_ON);

                }

                public void mouseReleased(MouseEvent e) {
                    System.out.println("Tray Icon - Mouse released!");
                    trayIcon.setImage(IMAGE_OFF);
                }
            };

            ActionListener exitListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            };

            ActionListener feedbackListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println("Feedback!");
                    giveFeedback();
                }
            };

            PopupMenu popup = new PopupMenu();
            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(exitListener);
            popup.add(exitItem);

            MenuItem feedbackItem = new MenuItem("Feedback!");
            feedbackItem.addActionListener(feedbackListener);
            popup.add(feedbackItem);

            trayIcon.setPopupMenu(popup);

            ActionListener actionListener = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    trayIcon.displayMessage("Action Event",
                            "An Action Event Has Been Performed!",
                            TrayIcon.MessageType.INFO);
                }
            };

            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);
            trayIcon.addMouseListener(mouseListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }

        } else {
            System.err.println("System tray is not supported.");
        }
    }

    private void giveFeedback() {
        ScreenshotHelper screenshotHelper = new ScreenshotHelper();
        Image imagem = screenshotHelper.takeScreenshot();
        showFeedbackScreen(imagem);
    }

    private void showFeedbackScreen(Image screenshot) {
        JFrame feedbackScreen = new FeedbackFrame(screenshot);
        feedbackScreen.setVisible(true);
    }
}
