package lab1;

/**
 * Created by Kevin on 2014-11-06.
 */
public class Player extends GameEntityImplementation{

    /** The direction of the player. */
    private Directions playerDirection = Directions.NORTH;

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

    public Position getNextPos(){
        return new Position(pos.getX(), pos.getY() + 1);
    }

}
