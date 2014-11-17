package lab1;

import java.util.ArrayList;
import java.util.Random;

public class Ghost extends GameEntityImplementation{
    public static ArrayList<Ghost> instancesList = new ArrayList<Ghost>();
    private int hp = 1;
    Random gen;
    private int tickcount = 0;
    private static final int SCORE = 3;

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
            if(gen.nextFloat() <= 0.5) {
                decreaseHealth(1);
                ((Bullet) collidedWith).setIsAlive(false);
                if (getHp() <= 0) {
                    SpaceShooterModel.increaseScore(SCORE);
                    setIsAlive(false);
                }
            }
        }else if(collidedWith instanceof Player){
            ((Player)collidedWith).decreaseHealth(1);
            this.decreaseHealth(1);

            if (this.hp <= 0) {
                SpaceShooterModel.increaseScore(SCORE);
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

    public Position getNextPos(Position playerPos){
        tickcount++;
        int dX;
        if (getPos().getX() < playerPos.getX() && tickcount % 2 == 0 && getPos().getY() <= playerPos.getY()) {
            dX = 1;
        } else if (getPos().getX() > playerPos.getX() && tickcount % 2 == 0 && getPos().getY() <= playerPos.getY()) {
            dX = -1;
        } else {
            dX = 0;
        }

        return new Position(super.getPos().getX() + dX, super.getPos().getY() + 1);
    }


}
