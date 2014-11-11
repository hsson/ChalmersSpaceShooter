package lab1;

import java.util.ArrayList;

public class GreenUfo implements GameEntity {

    private final ImageTile imageTile;
    private Position ufoPosition;
    private boolean isAlive;

    public static ArrayList<GreenUfo> instancsList = new ArrayList<GreenUfo>();

    public GreenUfo(ImageTile imageTile) {
        this.imageTile = imageTile;
        instancsList.add(this);
        GameEntity.allGameEntities.add(this);
        isAlive = true;
    }

    public GreenUfo(ImageTile imageTile, Position ufoPosition) {
        this(imageTile);

        setPos(ufoPosition);
    }

    public Position getNextPos(){
        return new Position(ufoPosition.getX(), ufoPosition.getY() + 1);
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public Position getPos() {
        return this.ufoPosition;
    }

    public void setPos(Position pos) {
        this.ufoPosition = pos;
    }

    public ImageTile getTile() {
        return this.imageTile;
    }
}
