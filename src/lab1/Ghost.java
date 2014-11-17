package lab1;

import java.util.ArrayList;
import java.util.Random;

/**
  * Created by Criticalstone on 15-Nov-14.
  */
public class Ghost extends GameEntityImplementation{
    public static ArrayList<Ghost> instancesList = new ArrayList<Ghost>();
    private int hp = 1;
    Random gen;

    public Ghost(ImageTile imageTile) {
        super(imageTile);
        gen = new Random();
        instancesList.add(this);
    }

    public Ghost(ImageTile imageTile, Position ufoPosition) {
        super(imageTile, ufoPosition);
        gen = new Random();
        instancesList.add(this);
    }

    public void collisionAction(Object collidedWith){
        if(collidedWith instanceof Bullet){
            if(gen.nextFloat() <= 0.25) {
                decreaseHealth(1);
                ((Bullet) collidedWith).setIsAlive(false);
                if (getHp() <= 0) {
                    setIsAlive(false);
                }
            }
        }else if(collidedWith instanceof Player){
            ((Player)collidedWith).decreaseHealth(1);
            this.decreaseHealth(1);

            if (this.hp <= 0) {
                this.setIsAlive(false);
            }
        }
    }

    public int getHp(){
        return hp;
    }

    public void decreaseHealth(int decreasement){
        hp = hp - decreasement;
    }

    @Override
    public Position getNextPos(){
        return new Position(super.getPos().getX(), super.getPos().getY() + 1);
    }


}
