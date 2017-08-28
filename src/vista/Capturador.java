/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.github.sarxos.webcam.Webcam;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import marvin.MarvinPluginCollection;
import marvin.color.MarvinColorModelConverter;
import marvin.image.MarvinImage;
import marvin.image.MarvinSegment;
import marvin.io.MarvinImageIO;
import marvin.math.MarvinMath;

/**
 *
 * @author migue
 */
public abstract class Capturador extends JPanel implements Runnable {

    private Thread hilo;
    Webcam webcam;
    BufferedImage image = null;
    ArrayList<BufferedImage> images = new ArrayList<>();
    int px;
    int py;
    int pw;
    int ph;
    JLabel label = null;
    private MarvinSegment[] segments;

    public Capturador(Webcam webcam) {
        this.webcam = webcam;
        this.px = 0;
        this.py = 0;
        this.pw = -1;
        this.ph = -1;
        configurar();
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setCorte(int x, int y, int w, int h) {
        this.setPosicionCorte(x, y);
        this.pw = w;
        this.ph = h;
    }

    public void setPosicionCorte(int x, int y) {
        this.px = x;
        this.py = y;
        if (x + pw > getWidth()) {
            pw = getWidth() - x - 1;
        }
        if (y + ph > getHeight()) {
            ph = getHeight() - y - 1;
        }
    }

    public void setAnchoCorte(int w, int h) {
        this.pw = w;
        this.ph = h;
    }

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
    }

    public int getPw() {
        return pw;
    }

    public int getPh() {
        return ph;
    }

    private void configurar() {
        hilo = new Thread(this);
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                webcam.open(true);
                hilo.start();
            }
        };
        TimerTask task2 = new TimerTask() {

            @Override
            public void run() {
                if (!images.isEmpty()) {
                    BufferedImage im = images.remove(0);
                    onCaptura(im);
                }
            }
        };
        new Timer().schedule(task, 0);
        new Timer().scheduleAtFixedRate(task2, 10, 60);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    setAnchoCorte(evt.getX() - getX(), evt.getY() - getY());
                } else {
                    setPosicionCorte(evt.getX(), evt.getY());
                }
            }
        });
    }
    int margin = 10;

    @Override
    public void paint(Graphics g) {
        if (image != null) {
            BufferedImage fin = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            fin.getGraphics().drawImage(image, 0, 0, this);
            if (pw < 0 || ph < 0) {
                this.pw = getWidth() - 1;
                this.ph = getHeight() - 1;
            }
            for (int i = 0; i < segments.length; i++) {
                int x1 = segments[i].x1 - (segments[i].x1 - margin >= 0 ? margin : segments[i].x1);
                int y1 = segments[i].y1 - (segments[i].y1 - margin >= 0 ? margin : segments[i].y1);
                int w = (segments[i].width + margin <= fin.getWidth() ? segments[i].width + margin : fin.getWidth() - segments[i].x2);
                int h = (segments[i].height + segments[i].y2 + margin <= fin.getHeight() ? margin : fin.getHeight() - segments[i].y2);
                if (label != null){
                    System.out.println(x1 + "," + y1+ "," + w+ "," + h);
                    System.out.println(image.getWidth() + ", " + image.getHeight());
                    BufferedImage subimage = image.getSubimage(x1, y1, w, h);
                    label.getGraphics().drawImage(subimage, 0, 0, label);
                }
                fin.getGraphics().drawRect(x1, y1, w , h);
            }
            Image fin2 = fin.getScaledInstance(getWidth(), getHeight(), BufferedImage.SCALE_DEFAULT);
            g.drawImage(fin2, 0, 0, this);
            g.setColor(Color.RED);
            g.drawRect(px, py, pw, ph);
        } else {
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.black);
            g.drawString("Esperando la camara...", 10, 50);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                image = webcam.getImage();

                BufferedImage fin = image;
                images.add(image);
                MarvinImage marvinImage = new MarvinImage(image);
                MarvinImage bin = MarvinColorModelConverter.rgbToBinary(marvinImage, 127);
                MarvinPluginCollection.morphologicalClosing(bin.clone(), bin, MarvinMath.getTrueMatrix(30, 30));
                marvinImage = MarvinColorModelConverter.binaryToRgb(bin);
                segments = MarvinPluginCollection.floodfillSegmentation(marvinImage);
                for (int i = 0; i < segments.length; i++) {
                    int x1 = segments[i].x1 - (segments[i].x1 - margin >= 0 ? margin : segments[i].x1);
                    int y1 = segments[i].y1 - (segments[i].y1 - margin >= 0 ? margin : segments[i].y1);
                    int w = segments[i].width + (segments[i].x2 + margin <= fin.getWidth() ? margin : fin.getWidth() - segments[i].x2);
                    int h = segments[i].height + (segments[i].y2 + margin <= fin.getHeight() ? margin : fin.getHeight() - segments[i].y2);

                    BufferedImage subimage = fin.getSubimage(x1, y1, w, h);
                    images.add(subimage);
                }
                repaint();
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(this, "El hilo de la cámara se ha interumpido inesperadamente.", "Error!", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {

            }
        }
    }

    public abstract void onCaptura(BufferedImage image);

}
