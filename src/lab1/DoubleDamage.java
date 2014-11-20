package lab1;

public class DoubleDamage extends PowerUp{
    public DoubleDamage(ImageTile imageTile, Position position) {
        super(imageTile, position);
    }

    @Override
    public void collisionAction(Object collideObject) {
        if(collideObject instanceof Player){
            this.setIsAlive(false);
            ((Player) collideObject).setPlayerBullet(Player.Bullets.DOUBLE);
        }
    }

}
