package com.example.spacecapivara.objectsmobile;

import android.graphics.drawable.Drawable;

public class Player extends objectMobile {

    private int life = 3;
    private Drawable[] ImagesPlayer;
    private Drawable ImagePlayer;
    private String direction = "UP";
    private int indexImage = 0;
    private boolean shootExist = false;
    private Laser laser = new Laser();


    public Player() {
        super();
        setPosition_x(5);
        setPosition_y(8);
        start();
    }

    public void setImagesPlayer(Drawable imageUp, Drawable imageRight, Drawable imageDown, Drawable imageLeft) {
        ImagesPlayer = new Drawable[]{imageUp, imageRight, imageDown, imageLeft};
        this.ImagePlayer = this.ImagesPlayer[0];
    }


    public boolean istShootExist() {
        return shootExist;
    }

    public void setShootExist(boolean shootExist) {
        this.shootExist = shootExist;
    }



    public void loseLife() {
        try {
            setLife(getLife()-1);
            System.out.println("explosion");
            sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }







    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Drawable getImagePlayer() {
        return ImagePlayer;
    }

    public void setImagePlayer(int num) {

        indexImage = indexImage + (num);

        if (indexImage > 3) {
            indexImage = 0;
        } else if (indexImage < 0) {
            indexImage = 3;
        }

        ImagePlayer = this.ImagesPlayer[indexImage];

    }

    public void MoveForward() {
        if (getDirection().equals("UP")) {
            setPosition_y(getPosition_y() - 1);
        } else if (getDirection().equals("RIGHT")) {
            setPosition_x(getPosition_x() + 1);
        } else if (getDirection().equals("LEFT")) {
            setPosition_x(getPosition_x() - 1);
        } else if (getDirection().equals("DOWN")) {
            setPosition_y(getPosition_y() + 1);
        }

    }

    public void MoveForback() {
        if (getDirection().equals("UP")) {
            setPosition_y(getPosition_y() + 1);
        } else if (getDirection().equals("RIGHT")) {
            setPosition_x(getPosition_x() - 1);
        } else if (getDirection().equals("LEFT")) {
            setPosition_x(getPosition_x() + 1);
        } else if (getDirection().equals("DOWN")) {
            setPosition_y(getPosition_y() - 1);
        }

    }

    public void RotateRight() {
        setImagePlayer(1);
        switch (indexImage) {
            case 0:
                this.setDirection("UP");
                break;
            case 1:
                this.setDirection("RIGHT");
                break;
            case 2:
                this.setDirection("DOWN");
                break;
            case 3:
                this.setDirection("LEFT");
                break;
        }
    }

    public void RotateLeft() {
        setImagePlayer(-1);
        switch (indexImage) {
            case 0:
                this.setDirection("UP");
                break;
            case 1:
                this.setDirection("RIGHT");
                break;
            case 2:
                this.setDirection("DOWN");
                break;
            case 3:
                this.setDirection("LEFT");
                break;
        }
    }

    public void Shoot() {
        this.setShootExist(true);
        laser.Shoot(this.direction, getPosition_x(), getPosition_y());
        this.setShootExist(false);
    }


    public void run() {
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Laser getLaser(){return laser;}
}

