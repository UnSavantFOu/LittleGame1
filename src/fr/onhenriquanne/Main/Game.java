package fr.onhenriquanne.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Game extends JPanel{
    /**
     * @wbp.nonvisual location=101,289
     */

    private static int[][] obstacle = new int[15][15];

    public static void main(String[] arg) {
        JFrame frame = new JFrame("Game");
        Game panel = new Game();


        frame.setContentPane(panel);
        frame.addKeyListener(new KeyListener() {


            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                panel.onKeyPressed(e.getKeyCode());
            }
        });

        frame.setSize(600,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);

        obstacle = Obstacle.create();

    }

    private static int speed = 100;

    public Game() {
        setBackground(Color.WHITE);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while(game) {
                    repaint();
                    update();

                    if (Obstacle.getT() > 10 & Obstacle.getT() < 20){
                        speed = 75;
                    }

                    if (Obstacle.getT() > 20 & Obstacle.getT() < 30){
                        speed = 60;
                    }

                    if (Obstacle.getT() > 30 & Obstacle.getT() < 40){
                        speed = 50;
                    }

                    if (Obstacle.getT() > 40){
                        speed = 30;
                    }

                    try {
                        Thread.sleep(speed);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }



        }).start();
    }

    private int p_x = 8;
    private int p_y = 11;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        for (int i=0;i<15;i++){
            for (int a=0;a<15;a++){
                switch (obstacle[i][a]){

                    case 0:
                        g.setColor(Color.white);
                        break;

                    case 1:
                        g.setColor(Color.darkGray);

                }

                g.fillRect(i*40, a*40, 40, 40);
            }
        }

        g.setColor(Color.orange);
        g.fillRect(p_x*40, p_y*40, 40, 40);

        collision();
    }

    public void onKeyPressed(int keyCode) {
        switch (keyCode){

            case 38:
                if (p_y > 0){
                    p_y--;
                }

                break;

            case 40:
                if (p_y < 14){
                    p_y++;
                }

                break;

            case 39:
                if (p_x < 14){
                    p_x++;
                }

                break;

            case 37:
                if (p_x > 1){
                    p_x--;
                }

                break;

        }
    }

    private boolean game = true;

    public void collision(){
        if (obstacle[p_x][p_y] == 1){
            game = false;
        }
    }

    public void update(){
        for (int i=0;i<15;i++){
            for (int a=0;a<15;a++){
                if (obstacle [i][a] == 1 & a < 14){
                    obstacle[i][a+1] = 2;
                    obstacle[i][a] = 0;
                }else if(a == 14){
                    obstacle[i][a] = 0;
                }
            }
        }
        boolean create = false;
        for (int i=0;i<15;i++){
            for (int a=0;a<15;a++){
                if (obstacle[i][a] == 2){
                    obstacle[i][a] = 1;
                    if (a == 7){
                        create = true;
                    }
                }
            }
        }

        if (create ){
            obstacle = Obstacle.create();

        }

    }

    public static int[][] getObs(){
        return obstacle;
    }


}
