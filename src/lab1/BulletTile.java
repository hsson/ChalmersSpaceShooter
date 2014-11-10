package lab1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kevin on 2014-11-10.
 */
public class BulletTile extends ImageTile {
    private Image image;
    private int x;
    public static ArrayList<ImageTile> bullets;
    public BulletTile(){
        image = new ImageIcon("img/tile-bullet.png").getImage();
        bullets.add((ImageTile)this);
    }

    public void update(){
        this.x--;
    }
}
