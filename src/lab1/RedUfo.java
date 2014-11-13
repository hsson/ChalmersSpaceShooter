package lab1;

import java.util.ArrayList;

/**
 * Created by Criticalstone on 12-Nov-14.
 */
public class RedUfo extends GameEntityImplementation {
    public static ArrayList<RedUfo> instancesList = new ArrayList<RedUfo>();
    private int hp = 5;

    public RedUfo(ImageTile imageTile) {
        super(imageTile);
        instancesList.add(this);
    }

    public RedUfo(ImageTile imageTile, Position ufoPosition) {
        super(imageTile, ufoPosition);
        instancesList.add(this);
    }

    public int getHp(){
        return hp;
    }

    public void decreaseHp(int deceasement){
        hp = hp - deceasement;
    }

    @Override
    public Position getNextPos(){
        return new Position(super.getPos().getX(), super.getPos().getY() + 1);
    }
}
