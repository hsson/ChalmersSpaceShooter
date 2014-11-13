package lab1;

import java.util.ArrayList;

public class GreenUfo extends GameEntityImplementation {

    public static ArrayList<GreenUfo> instancesList = new ArrayList<GreenUfo>();

    public GreenUfo(ImageTile imageTile) {
        super(imageTile);
        instancesList.add(this);
    }

    public GreenUfo(ImageTile imageTile, Position ufoPosition) {
        super(imageTile, ufoPosition);
        instancesList.add(this);
    }

    public void collisionEvent(Object collidedObject){
        if(collidedObject instanceof Bullet){
            setIsAlive(false);
        }
    }

    @Override
    public Position getNextPos(){
        return new Position(super.getPos().getX(), super.getPos().getY() + 1);
    }
}
