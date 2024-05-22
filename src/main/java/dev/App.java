package dev;

import java.util.ResourceBundle;
import com.github.lalyos.jfiglet.FigletFont;

public class App {
    public static void main(String[] args) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("application");
            String titre = bundle.getString("titre");
            String asciiArtTitre = FigletFont.convertOneLine(titre);
            System.out.println(asciiArtTitre);

            String environnement = bundle.getString("environnement");
            String asciiArtEnv = FigletFont.convertOneLine(environnement);
            System.out.println(asciiArtEnv);
        } catch(Exception e) {
            System.err.println("Erreur: " + e.getMessage());
        }
    }
}
