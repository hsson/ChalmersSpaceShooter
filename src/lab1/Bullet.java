package lab1;

import java.util.*;

public class Bullet extends GameEntityImplementation{
    public static ArrayList<Bullet> instancesList = new ArrayList<Bullet>();

    public Bullet(ImageTile imageTile, Position bulletPosition){
        super(imageTile, bulletPosition);
        instancesList.add(this);
    }

    public Bullet(ImageTile imageTile){
        super(imageTile);
    }

    @Override
    public Position getNextPos(){
        return new Position(super.getPos().getX(), super.getPos().getY() - 1);
    }
}
