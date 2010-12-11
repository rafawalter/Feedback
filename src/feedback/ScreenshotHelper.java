package feedback;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ScreenshotHelper {

    public Image takeScreenshot() {
        esperar();
        Rectangle screenRect = calcularResolucao();
        BufferedImage imagem = takeScreenshot(screenRect);
        return imagem;
    }

    public void salvarImagem(BufferedImage imagem, String nomeImagem) {
        try {
            ImageIO.write(imagem, "png", new File(nomeImagem));
        } catch (IOException ex) {
            ExceptionHelper.tratarException(ex);
        }
    }


    private BufferedImage takeScreenshot(Rectangle screenRect) {
        BufferedImage imagem = null;
        try {
            Robot robot = new Robot();
            imagem = robot.createScreenCapture(screenRect);
        } catch (AWTException ex) {
            ExceptionHelper.tratarException(ex);
        }
        return imagem;
    }

    private Rectangle calcularResolucao() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);
        return screenRect;
    }

    private void esperar() {
        try {
            long tempoEspera = Long.parseLong("2") * 1000L;
            Thread.sleep(tempoEspera);
        } catch (InterruptedException ex) {
            ExceptionHelper.tratarException(ex);
        }
    }
}
