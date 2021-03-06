package lab1;

import java.util.*;

public class Bullet extends GameEntityImplementation{
    public static ArrayList<Bullet> instancesList = new ArrayList<Bullet>();
    private int damage;
    public Bullet(ImageTile imageTile, Position bulletPosition, int damage){
        super(imageTile, bulletPosition);
        instancesList.add(this);
        this.damage = damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getDamage(){
        return this.damage;
    }

    @Override
    public Position getNextPos(){
        return new Position(super.getPos().getX(), super.getPos().getY() - 1);
    }

    @Override
    public void collisionAction(Object collideObject) {

    }
}
