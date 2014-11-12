package lab1;

import java.util.ArrayList;

public class GreenUfo extends GameEntityImplementation {

    public static ArrayList<GreenUfo> instancsList = new ArrayList<GreenUfo>();

    public GreenUfo(ImageTile imageTile) {
        super(imageTile);
        GameEntity.allGameEntities.add(this);
    }

    public GreenUfo(ImageTile imageTile, Position ufoPosition) {
        super(imageTile, ufoPosition);
    }

    @Override
    public Position getNextPos(){
        return new Position(super.getPos().getX(), super.getPos().getY() + 1);
    }
}
