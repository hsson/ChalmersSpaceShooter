package lab1;

public class Player extends GameEntityImplementation{

    /** The starting direction of the player. */
    private Directions playerDirection = Directions.NORTH;

    public static List<PowerUp> inventoryList = new LinkedList<PowerUp>();

    // Starting health
    private int health = 5;

    public enum Directions {
        EAST(1, 0),
        WEST(-1, 0),
        NORTH(0, -1),
        SOUTH(0, 1),
        NONE(0, 0);

        private final int xDelta;
        private final int yDelta;

        Directions(final int xDelta, final int yDelta) {
            this.xDelta = xDelta;
            this.yDelta = yDelta;
        }

        public int getXDelta() {
            return this.xDelta;
        }

        public int getYDelta() {
            return this.yDelta;
        }
    }

    public Player(ImageTile tile, Position pos){
        super(tile, pos);
    }

    public Player(ImageTile tile){
        this(tile, new Position(1,1));
    }

    public void setDirection(Directions dir){
        playerDirection = dir;
    }

    public Directions getDirection(){
        return playerDirection;
    }

    public void decreaseHealth(int amount) {
        this.health -= amount;
    }

    public int getHealth() {
        return this.health;
    }

    /**
     * Get next position of the player.
     */
    @Override
    public Position getNextPos() {
        return new Position(
                super.getPos().getX() + this.playerDirection.getXDelta(),
                super.getPos().getY() + this.playerDirection.getYDelta());
    }

    @Override
    public void collisionAction(Object collideObject) {

    }

}
