package com.example.spacecapivara.objectsmobile;

import android.graphics.drawable.Drawable;

import com.example.spacecapivara.objectsmobile.objectMobile;

import java.util.Random;

public class Enemy extends objectMobile {
    private int life = 100;
    private Drawable enemyImage;
    private Drawable[] enemyImages;

    public Enemy() {
        setPosition_x(5);
        setPosition_y(2);
        start();
    }

    public void explosion(){
        setEnemyImage(1);
    }

    public void loseLife() {
        try {
            explosion();
            this.life = this.life-10;
            System.out.println("explosion enemy");
            sleep(500);

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Drawable getEnemyImage() {
        return enemyImage;
    }

    public void setEnemyImage(int num) {
        this.enemyImage = enemyImages[num];
    }


    public void MoveEnemy() {
        Random random_local_x = new Random();
        Random random_local_y = new Random();
        setEnemyImage(0);
        setPosition_x(random_local_x.nextInt(10));
        setPosition_y(random_local_y.nextInt(10));

    }
    public void run(){
        try {
            while(true) {
                Thread.sleep(1200);
                this.MoveEnemy();

            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setEnemyImages(Drawable image, Drawable image2) {
        this.enemyImages = new Drawable[]{image, image2};
        this.enemyImage = this.enemyImages[0];
    }

    public int getLife() {
        return life;
    }
}
