import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.sound.sampled.*;



public class Dodge_Lord extends JPanel implements ActionListener, KeyListener{
    int framewidth = 360;
    int frameheight = 640;

    // images
    Image backgroundimg;
    Image Smanimg;
    Image boximg;
    Image buildings;
    Image Explosionimg;

    // box

    int boxX = frameheight/4;               
    int boxY = 0;
    int boxwidth = 64;
    int boxheight = 54;

    class Box {
        double x = boxX;
        double y = boxY;
        int width = boxwidth;
        int height = boxheight;
        Image img;

        Box(Image img) {
            this.img = img;
        }    
    }

    // superman
    int smanX = frameheight/18;
    int smanY = frameheight/3;
    int smanwidth = 64;
    int smanheight = 54;
    
    class Sman {
        int x = smanX;
        int y = smanY;
        int width = smanwidth;
        int height = smanheight;
        Image img;

        Sman(Image img) {
            this.img = img;
        }



    }

          // building
          int buildingX = framewidth;
          int buildingY = 500;
          int builwidth = 180;
          int buildheight = 500;

          class Building {
            int x = buildingX;
            int y = buildingY;
            int width = builwidth;
            int height = buildheight;
            Image img;
            boolean passed = false;

          
           Building(Image img) {
            this.img = img;

           }
        }

           class Explosion {
            int x, y;
            int width, height;
            Image img;
            int lifetime = 30; // lasts 30 frames
        
            Explosion(int x, int y, int width, int height, Image img) {
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
                this.img = img;
            }

            
        
        

        }
        double velx = Math.random();
        int VelocityY = 1;
        int VelocityX = -2 ;
        int score = 0;


          
          




        // game logic
        Sman sman;
        double velocityX = -4;
        double velocityY = 0;
        double gravity = 0.5;




    
        ArrayList<Building> building;
        Random random = new Random();
        ArrayList<Box> box;
        ArrayList<Explosion> explosions;




        Timer gameloop;
        Timer buildtimer;
        Timer boxtimer;
        boolean gameOver = false;
    
    

    

    

    Dodge_Lord() {
        setPreferredSize(new Dimension(framewidth, frameheight));
        setFocusable(true);
        addKeyListener(this);
   

        // loading all images
        backgroundimg = new ImageIcon(getClass().getResource("/backg.png")).getImage();
        Smanimg = new ImageIcon(getClass().getResource("/smanop.png")).getImage();
        boximg = new ImageIcon(getClass().getResource("/box.png")).getImage();
        buildings = new ImageIcon(getClass().getResource("/building.png")).getImage();
        Explosionimg = new ImageIcon(getClass().getResource("/explosion.png")).getImage();
        
        sman = new Sman(Smanimg);
        building = new ArrayList<Building>();
        box = new ArrayList<Box>();
        explosions = new ArrayList<Explosion>();
        
    

        //game timer
        gameloop = new Timer(1000/60, this);
        gameloop.start();

        // building timer
        buildtimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placebuilding();
            }

        });
        buildtimer.start();

        // box timer
        int randomboxtime = (int) (7000 - Math.random()*4000);
        boxtimer = new Timer(randomboxtime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placebox();



            }
        });
        boxtimer.start();
       
    }

    public void restartGame() {
        sman.y = smanY;
        velocityY = 0;
        building.clear();
        box.clear();
        explosions.clear();
        gameOver = false;
        gameloop.start();
        buildtimer.start();
        boxtimer.start();
        score = 0;
    }
    

 

    public void playSound(String soundFile) {
    try {
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getResource(soundFile));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public void placebox() {
    
         Box bo = new Box(boximg);
         bo.x = random.nextInt(framewidth - boxwidth);
         bo.y = 0;
         box.add(bo);

    
    }
    

    public void placebuilding() {
        int randombuildY = (int) (buildingY - buildheight/3 - Math.random()*(buildheight/4));
        // int openspace = frameheight/3;

        Building build = new Building(buildings);
        build.y = randombuildY;
        building.add(build);

       // Building Rainclouds = new Building(rainclouds);
       // Rainclouds.y = build.y - buildheight - openspace;
       // building.add(Rainclouds);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }
    public void draw(Graphics g) {
       // background
       // System.out.println("dance");
        g.drawImage(backgroundimg,  0, 0 ,framewidth, frameheight, null);
        g.drawImage(sman.img, sman.x, sman.y, sman.width, sman.height, null);
        

        // building
        for(int i = 0; i < building.size(); i++) {
            Building b = building.get(i);
            g.drawImage(b.img, b.x, b.y, b.width, b.height, null);
        }

        // box
        for(int j = 0; j < box.size(); j++) {
            Box w = box.get(j);
            g.drawImage(w.img, (int)w.x, (int)w.y, w.width, w.height,null);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 30);


        for (int i = 0; i < explosions.size(); i++) {
            Explosion e = explosions.get(i);
            g.drawImage(e.img, e.x, e.y,e.width,e.height, null);
            e.lifetime--;
            if (e.lifetime <= 0) {
                explosions.remove(i);
                i--;
            }
        }
        
            
        
        // Debug hitbox outlines
//(g.setColor(Color.RED);
//g.drawRect(sman.x, sman.y, sman.width, sman.height);
//g.setColor(Color.BLUE);
//for(Building b : building) {
 //g.drawRect(b.x, b.y, b.width, b.height);
//})

    }
    public void move() {
        velocityY += gravity;
        sman.y += velocityY;
        sman.y = Math.max(sman.y, -40);
        

        // building
        for(int i = 0; i < building.size(); i++) {
            Building B = building.get(i);
            B.x += velocityX;

                // Check for collision
    if (collision(sman, B)) {
        gameOver = true;
        return;
    }

    // Point Gaining Logic
    if (!B.passed && B.x + B.width < sman.x) {  
        B.passed = true;
        score++;
        playSound("/point-smooth-beep-230573.wav");  
    }

    // Remove offscreen buildings
   // if (B.x + B.width < 0) {
        //building.remove(i);
       // i--; 
    //}


            


     
            


        // box
        double boxfallspeed = 0.2;
        double xbox = -0.8;
        for(int j = 0; j < box.size(); j++) {
            Box W = box.get(j);
            W.y += boxfallspeed;
            W.x += xbox;
        

            
            if (collision(sman, W)) {
                int extraSize = 100;  // pixels to make the explosion bigger
                int explosionX = (int)W.x - extraSize / 2;
                int explosionY = (int)W.y - extraSize / 2;
                int explosionWidth = W.width + extraSize;
                int explosionHeight = W.height + extraSize;
            
                explosions.add(new Explosion(explosionX, explosionY, explosionWidth, explosionHeight, Explosionimg));

                playSound("/explosion-312361.wav");
                gameOver = true;
                return;
            }
            
        }
            
            
            

          

        

            if (collision(sman, B)) {
            gameOver = true;
            return;
            
            }

            if (B.x + B.width < 0) {
                building.remove(i);
                i--; 

        }

    }   

        if(sman.y > frameheight) {
            gameOver = true;
        }
    
        

    }

    public boolean collision(Sman a, Box b) {
        return a.x < b.x + b.width &&
               a.x + a.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y;
    }
    

    public boolean collision(Sman a, Building b) {
        return a.x < b.x + b.width &&
               a.x + a.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if(gameOver) {
            buildtimer.stop();
            boxtimer.stop();
            gameloop.stop();
        }
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -6;

        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameOver) {
                restartGame();
            }
                

            
        }
    
    }

    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyReleased(KeyEvent e) {}


    

}
