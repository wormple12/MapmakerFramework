package processing.general.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mapmaker.entities.sprites.Sprite;
import processing.ProcessingMapmaker;
import processing.core.PImage;
import processing.core.PVector;
import processing.entities.sprites.SpriteTypeP3;

/**
 *
 * @author TeamOne
 */
public class SpriteLoaderP3 {

    private final ProcessingMapmaker app;

    public SpriteLoaderP3(ProcessingMapmaker app) {
        this.app = app;
    }

    public List<Sprite> loadFromConfig(String configPath) {
        List<Sprite> result = new ArrayList<>();
        try {
            // read config and load sprites
            try (FileReader fr = new FileReader(configPath + "settings.config")) {
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
                int vectorY = 110;

                String line;
                while ((line = br.readLine()) != null) {
                    String[] spriteArgs = line.split(" ");
                    String imagePath = configPath + spriteArgs[0];
                    final PImage sprite = app.loadImage(imagePath);
                    final PVector vector = new PVector(40, vectorY);
                    vectorY += 70;
                    result.add(new SpriteTypeP3(vector, sprite, imagePath, Float.parseFloat(spriteArgs[1]), Float.parseFloat(spriteArgs[2])));
                }
            }
        } catch (IOException ex) {
            System.out.println("Couldn't load " + configPath);
            return null;
        }
        return result;
    }

}
