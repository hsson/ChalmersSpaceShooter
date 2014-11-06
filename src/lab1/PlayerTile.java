package lab1;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin on 2014-11-06.
 */
public class PlayerTile extends GameTile {
    Image playerImage;
    public PlayerTile(){
        playerImage = new ImageIcon("img/tile-spaceship.png").getImage();
    }

    public void draw(final Graphics g, final int x, final int y, Dimension d) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(playerImage, x, y, d.width, d.height, null);
    }
}
