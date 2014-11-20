package lab1;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PowerUpSpawn extends GameTile{

    private static final int[] LEVEL = {0, 5, 15, 40, 100, 250};
    private int level = 1;
    private static final Image DD_IMAGE = new ImageIcon("img/tile-doubledamage.png").getImage();
    private static final Image HP_IMAGE = new ImageIcon("img/tile-hp.png").getImage();

    /** The tile representing the powerUps **/
    private static final ImageTile DD_TILE = new ImageTile(DD_IMAGE);
    private static final ImageTile HP_UP_TILE = new ImageTile(HP_IMAGE);

    private int score;
    private int width;
    private int yPos;

    private double spawnRate;

    private double ddSpawnRate;     //dd = double damage
    private double hpUpSpawnRate;

    Random gen = new Random();

    public PowerUpSpawn(int width, int yPos){
        this.score = SpaceShooterModel.getScore();
        this.width = width;
        this.yPos = yPos;
    }

    public int getDeltaScore(int score) {
        return score - LEVEL[level - 1];
    }

    public void setLevel(int score){
        if(score > LEVEL[level] && level < LEVEL.length - 1){
            level++;
        }
    }
    
    private void setSpawnRate(int score){
        if(level == 1){
            spawnRate = 0.01;
            ddSpawnRate = 1;
            hpUpSpawnRate = 0;
        }else if(level == 2){
            spawnRate = 0.02;
            ddSpawnRate = 0.65;
            hpUpSpawnRate = 0.35;
        }else if(level == 3){
            spawnRate = 0.03;
            ddSpawnRate = 0.3;
            hpUpSpawnRate = 0.5;
        }else if(level == 4){
            spawnRate = 0.25+0.005*getDeltaScore(score);
            ddSpawnRate = 0;
            hpUpSpawnRate = 0.7;
        }else if(level == 5){
            spawnRate = 0.4+0.0025*getDeltaScore(score);
            ddSpawnRate = 0;
            hpUpSpawnRate = 0.5;
        }else{
            spawnRate = 0.6+0.001*getDeltaScore(score);
            ddSpawnRate = 0;
            hpUpSpawnRate = 0;
        }
    }

    public void spawnPowerUp() {
        this.score = SpaceShooterModel.getScore();
        setLevel(score);
        setSpawnRate(score);

        double spawnChance = gen.nextDouble();
        if(spawnChance < spawnRate){
            int xPosition = gen.nextInt(this.width);
            double chanceToSpawnType = gen.nextDouble();
            if (chanceToSpawnType < hpUpSpawnRate){
                // TODO: Spwan HP Up here. "new HealthUp(....);"
            } else if(chanceToSpawnType < hpUpSpawnRate + ddSpawnRate) {
                System.out.println("Spawn");
                new DoubleDamage(DD_TILE, new Position(xPosition, this.yPos));
            }
        }
    }
}
