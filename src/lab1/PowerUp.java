package lab1;

import java.util.ArrayList;

abstract class PowerUp extends GameEntityImplementation{
    public static ArrayList<PowerUp> instancesList = new ArrayList<PowerUp>();
    public PowerUp(ImageTile imageTile) {
        super(imageTile);
        instancesList.add(this);
    }

    public Position getNextPos(){
        return new Position(this.getPos().getX(), this.getPos().getY()+1);
    }
}
