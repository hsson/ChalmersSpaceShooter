package lab1;

import java.util.ArrayList;

public class GreenUfo extends GameEntityImplementation {

    public static ArrayList<GreenUfo> instancesList = new ArrayList<GreenUfo>();
    private int hp = 1;
    private static final int SCORE = 1;

    public GreenUfo(ImageTile imageTile) {
        super(imageTile);
        instancesList.add(this);
    }

    public GreenUfo(ImageTile imageTile, Position ufoPosition) {
        super(imageTile, ufoPosition);
        instancesList.add(this);
    }

    public void collisionAction(Object collidedWith){
        if(collidedWith instanceof Bullet){
            Bullet b = (Bullet)collidedWith;
            decreaseHealth(b.getDamage());
            b.setIsAlive(false);
            if(getHp() <= 0) {
                SpaceShooterModel.increaseScore(SCORE);
                setIsAlive(false);
            }
        }else if(collidedWith instanceof Player && SpaceShooterModel.tickCount % 3 == 0){
            ((Player) collidedWith).decreaseHealth(1);
            this.decreaseHealth(1);
            if(getHp() <= 0){
                SpaceShooterModel.increaseScore(SCORE);
                this.setIsAlive(false);
            }
        }
    }

    public int getHp(){
        return hp;
    }

    public void decreaseHealth(int deceasement){
             hp = hp - deceasement;
         }

    public void collisionEvent(Object collidedObject){
        if(collidedObject instanceof Bullet){
            if(getHp() > 0){
                decreaseHealth(1);
            }else{
                setIsAlive(false);
            }
            ((Bullet) collidedObject).setIsAlive(false);
        }else if(collidedObject instanceof Player){
            setIsAlive(false);
            ((Player) collidedObject).decreaseHealth(1);
        }
    }

    @Override
    public Position getNextPos(){
        return new Position(super.getPos().getX(), super.getPos().getY() + 1);
    }
}
