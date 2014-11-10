package lab1;

import java.util.ArrayList;

public class GreenUfo implements GameEntity {

    private final ImageTile imageTile;
    private Position ufoPosition;

    public static ArrayList<GreenUfo> instancsList = new ArrayList<GreenUfo>();

    public GreenUfo(ImageTile imageTile) {
        this.imageTile = imageTile;
        instancsList.add(this);
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
