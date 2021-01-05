package fr.onhenriquanne.Main;

import java.util.Random;

public class Obstacle {

    private static int[][] obstacle = new int[15][15];

    private static int t=0;

    public static int[][] create(){
        obstacle = Game.getObs();

        Random rand = new Random();
        int aléa = rand.nextInt(15);
        int aléa1 = rand.nextInt(15);
        int aléa2 = rand.nextInt(15);
        int aléa3 = rand.nextInt(15);


        obstacle[aléa][0] = 1;
        obstacle[aléa1][0] = 1;
        obstacle[aléa2][0] = 1;
        obstacle[aléa3][0] = 1;

        t++;

        return obstacle;
    }

    public static int getT(){
        return t;
    }

}
