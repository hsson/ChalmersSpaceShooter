package lab1;

import java.util.ArrayList;
import java.util.List;

public class SpawnTile {

    private MonsterSpawn monsterSpawn;
    private PowerUpSpawn powerUpSpawn;

    private int width, yPos;

    private static List<Integer> occupiedXPositions = new ArrayList<Integer>();

    public SpawnTile(int width, int yPos) {
        this.width = width;
        this.yPos = yPos;

        monsterSpawn = new MonsterSpawn(width, yPos);
        powerUpSpawn = new PowerUpSpawn(width, yPos);
    }

    public void spawn() {
        monsterSpawn.spawnMonster();
        powerUpSpawn.spawnPowerUp();

        occupiedXPositions.clear();
    }

    public static boolean isXPosOccupied(int xPos) {
        if (occupiedXPositions.contains(xPos)) {
            return true;
        } else {
            return false;
        }
    }

    public static void addOccupiedXPos(int xPos) {
        occupiedXPositions.add(xPos);
    }
}
