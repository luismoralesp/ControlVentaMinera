/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.OCRScanner;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImage;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImageLoader;

/**
 *
 * @author migue
 */
public class Interprete {

    private final TrainingImageSelector selector = new TrainingImageSelector();
    private static Interprete instancia = null;
    
    private Interprete() {
        configurar();
    }
    
    private void configurar(){
        selector.addTrainingImage("DIC/A-Z 1.PNG", "A", "Z");
        selector.addTrainingImage("DIC/A-Z 2.PNG", "A", "Z");
        selector.addTrainingImage("DIC/A-Z 3.PNG", "A", "Z");
        //selector.addTrainingImage("DIC/A-Z 4.PNG", "A", "Z"); 
        //selector.addTrainingImage("DIC/A-Z 5.PNG", "A", "Z");
        //selector.addTrainingImage("DIC/A-Z 6.jpg", "A", "Z");
        //selector.addTrainingImage("DIC/A-Z (7).jpg", "A", "Z");
        //selector.addTrainingImage("DIC/A-Z (8).jpg", "A", "Z");
        //selector.addTrainingImage("DIC/A-Z (9).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (10).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (11).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (12).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (13).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (14).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (15).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (16).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (17).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (18).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (19).jpg", "A", "Z");
//        selector.addTrainingImage("DIC/A-Z (20).jpg", "A", "Z");

        selector.addTrainingImage("DIC/0-9 1.PNG", "0", "9");
        selector.addTrainingImage("DIC/0-9 2.PNG", "0", "9");
        selector.addTrainingImage("DIC/0-9 3.PNG", "0", "9");
        selector.addTrainingImage("DIC/0-9 4.PNG", "0", "9");
        selector.addTrainingImage("DIC/0-9 5.PNG", "0", "9");
        selector.addTrainingImage("DIC/0-9 6.png", "0", "9");
    }

    public static Interprete getInstancia() {
        if (instancia == null){
            instancia = new Interprete();
        }
        return instancia;
    }

    public String performMSEOCR(ArrayList<TrainingImageSpec> imgs, BufferedImage targetImage) throws Exception {
        OCRScanner ocrScanner = new OCRScanner();
        HashMap<Character, ArrayList<TrainingImage>> trainingImages = getTrainingImageHashMap(imgs);
        ocrScanner.addTrainingImages(trainingImages);
        String text = ocrScanner.scan(targetImage, 0, 0, 0, 0, null);
        return text;
    }

    private HashMap<Character, ArrayList<TrainingImage>> getTrainingImageHashMap(ArrayList<TrainingImageSpec> imgs) throws Exception {
        TrainingImageLoader loader = new TrainingImageLoader();
        HashMap<Character, ArrayList<TrainingImage>> trainingImages = new HashMap<>();
        Frame frame = new Frame();

        for (int i = 0; i < imgs.size(); i++) {
            loader.load(frame, imgs.get(i).getFileLocation(), imgs.get(i).getCharRange(), trainingImages);
        }

        return trainingImages;
    }

    public String leer(BufferedImage targetImage) throws Exception {
        ArrayList<TrainingImageSpec> images = selector.getTrainingImages();
        return performMSEOCR(images, targetImage);
    }
}
