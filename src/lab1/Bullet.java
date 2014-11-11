package lab1;

import java.util.*;

public class Bullet implements GameEntity{
    private ImageTile tile;
    private Position pos;
    public static ArrayList<Bullet> instancesList = new ArrayList<Bullet>();
    private boolean isAlive;

    public Bullet(Position pos, ImageTile tile){
        this.tile = tile;
        this.pos = pos;
        instancesList.add(this);
        GameEntity.allGameEntities.add(this);
        isAlive = true;
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

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
