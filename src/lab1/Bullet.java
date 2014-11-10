package lab1;

import java.util.*;

public class Bullet implements GameEntity{
    private ImageTile tile;
    private Position pos;
    public static ArrayList<Bullet> instancesList = new ArrayList<Bullet>();

    public Bullet(Position pos, ImageTile tile){
        this.tile = tile;
        this.pos = pos;
        instancesList.add(this);
    }

    public Bullet(ImageTile tile){
        this.tile = tile;
    }

    public Position getNextPos(){
        return new Position(pos.getX(), pos.getY()-1);
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
