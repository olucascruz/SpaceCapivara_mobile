package com.example.spacecapivara.game;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.SurfaceHolder;

import com.example.spacecapivara.map.Map;

public class GameLoop extends Thread{
    private Game game;
    private SurfaceHolder surfaceHolder;
    private boolean isRunning = false;
    private  static final int PHYS_FPS = 60;
    Map map = new Map();

    public GameLoop(Game game , SurfaceHolder surfaceholder) {
        this.game = game;
        this.surfaceHolder = surfaceholder;
    }

    public void setRunning(boolean b){
        isRunning = b;
    }
    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run(){
        super.run();

        long mNextGameTick = SystemClock.uptimeMillis();
        int skipTicks = 1000/PHYS_FPS;
        Canvas canvas = null;
        while (isRunning){
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    game.update();
                    game.draw(canvas);
                }

            }catch (IllegalArgumentException e){
                e.printStackTrace();
            } finally {
                try{
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }catch (Exception e){
                   e.printStackTrace();
                }
            }

            mNextGameTick += skipTicks;
            long sleepTime = mNextGameTick - SystemClock.uptimeMillis();
            if(sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }


}
