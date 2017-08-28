// TrainingImageSelector.java
// Copyright (c) 2010 William Whitney
// All rights reserved.
// This software is released under the BSD license.
// Please see the accompanying LICENSE.txt for details.
package vista;

import java.io.File;
import java.util.ArrayList;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.CharacterRange;

/**
 * Provides a panel to input training images.
 *
 * @author William Whitney
 */
public class TrainingImageSelector {

    private ArrayList<String> trainingImageLocs;
    private final ArrayList<String> startCharVals;
    private final ArrayList<String> endCharVals;

    public TrainingImageSelector() {
        trainingImageLocs = new ArrayList<>();
        startCharVals = new ArrayList<>();
        endCharVals = new ArrayList<>();
    }

    public void addTrainingImage(String img, String start, String end) {
        trainingImageLocs.add(img);
        startCharVals.add(start);
        endCharVals.add(end);
    }

    public void removeTraningImage() {
        trainingImageLocs.remove(trainingImageLocs.size() - 1);
        startCharVals.remove(startCharVals.size() - 1);
        endCharVals.remove(endCharVals.size() - 1);
    }

    public boolean isTraningImagesValid() {
        for (String imageLoc : trainingImageLocs) {
            File currFile = new File(imageLoc);
            if (!currFile.exists()) {
                return false;
            }
        }

        for (String startChar : startCharVals) {
            String currStr = startChar;

            if (currStr.length() != 1) {
                return false;
            }
        }

        for (String startChar : endCharVals) {
            String currStr = startChar;

            if (currStr.length() != 1) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<TrainingImageSpec> getTrainingImages() {
        ArrayList<TrainingImageSpec> images = new ArrayList<>();

        for (int i = 0; i < trainingImageLocs.size(); i++) {
            String trainingImageLoc = trainingImageLocs.get(i);
            int startChar = startCharVals.get(i).charAt(0);
            int endChar = endCharVals.get(i).charAt(0);

            TrainingImageSpec newImage = new TrainingImageSpec();
            newImage.setFileLocation(trainingImageLoc);
            newImage.setCharRange(new CharacterRange(startChar, endChar));

            images.add(newImage);

        }
        return images;
    }
}
