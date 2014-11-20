package lab1;

public class DoubleDamage extends PowerUp{
    public DoubleDamage(ImageTile imageTile) {
        super(imageTile);
    }

    @Override
    public void collisionAction(Object collideObject) {
        if(collideObject instanceof Player){
            this.setIsAlive(false);
            ((Player) collideObject).setPlayerBullet(Player.Bullets.DOUBLE);
        }
    }

}
