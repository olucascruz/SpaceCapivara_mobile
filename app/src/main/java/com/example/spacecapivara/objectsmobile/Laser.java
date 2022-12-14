package com.example.spacecapivara.objectsmobile;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.spacecapivara.objectsmobile.objectMobile;

public class Laser extends objectMobile {
    private static final String TAG = "Laser";
    private Drawable[] ImagesLaser;
    private Drawable ImageLaser;

    public Laser(){
        start();
    }

    public void Shoot(String direction, int x, int y){
        setPosition_x(x);
        setPosition_y(y);
        Log.d(TAG, direction);
            switch (direction) {
                case "UP":
                    try {
                        for (int i = 0; i < 5; i++) {
                            this.setImageLaser(0);
                            if (this.getPosition_y() == 0) {
                                break;
                            }
                            this.setPosition_y(this.getPosition_y() - 1);
                            sleep(50);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "RIGHT":
                    try {
                        for (int i = 0; i < 5; i++) {
                            this.setImageLaser(1);
                            if (this.getPosition_x() == 9) {
                                break;
                            }
                            this.setPosition_x(this.getPosition_x() + 1);
                            sleep(50);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "DOWN":
                    try {
                        for (int i = 0; i < 5; i++) {
                            this.setImageLaser(0);
                            if (this.getPosition_y() == 9) {
                                break;
                            }
                            this.setPosition_y(this.getPosition_y() + 1);
                            sleep(50);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "LEFT":
                    try {
                        for (int i = 0; i < 5; i++) {
                            this.setImageLaser(1);
                            if (getPosition_x() == 0) {
                                break;
                            }
                            this.setPosition_x(getPosition_x() - 1);
                            sleep(50);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }


    }

    public void setImagesLaser(Drawable laserY, Drawable laserX) {
        ImagesLaser = new Drawable[]{laserY, laserX};
    }

    public void setImageLaser(int index) {
        ImageLaser = this.ImagesLaser[index];
    }

    public Drawable getImageLaser() {
        return ImageLaser;
    }


    public void run() {

    }
}
