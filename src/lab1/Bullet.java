package lab1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bullet implements GameEntity{
    private ImageTile tile;
    private Position pos;
    public static ArrayList<Bullet> bullets;

    public Bullet(Position position, ImageTile tile){
        this.tile = tile;
        bullets.add(this);
    }

    public Bullet(ImageTile tile){
        this.tile = tile;
    }

    public void updatePos(){
        pos = new Position(pos.getX(), pos.getY()-1);
    }

    public ImageTile getTile(){
        return tile;
    }

    public void setPos(Position pos){
        this.pos = pos;
    }

    public Position getPos(){
        return pos;
    }
}
