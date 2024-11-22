package articles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    /**
     * Redimensiona una imagen a las dimensiones deseadas y la guarda.
     *
     * @param inputFile Archivo de entrada (imagen original).
     * @param outputFile Archivo de salida (imagen redimensionada).
     * @param width Ancho deseado.
     * @param height Alto deseado.
     * @throws IOException Si ocurre algún error al leer o escribir la imagen.
     */
    
    public static void resizeImage(File inputFile, File outputFile, int width, int height) throws IOException {
        // Leer la imagen original
        BufferedImage originalImage = ImageIO.read(inputFile);

        // Crear una nueva imagen con las dimensiones deseadas
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();

        // Dibujar la imagen original redimensionada
        g.drawImage(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, width, height, null);
        g.dispose();

        // Guardar la nueva imagen
        ImageIO.write(resizedImage, "jpg", outputFile); // Cambia "jpg" si deseas otro formato como "png"
    }
}
