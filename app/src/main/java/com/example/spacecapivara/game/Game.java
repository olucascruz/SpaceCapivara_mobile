package com.example.spacecapivara.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.spacecapivara.objectsmobile.Enemy;
import com.example.spacecapivara.MainActivity;
import com.example.spacecapivara.map.Map;
import com.example.spacecapivara.objectsmobile.Player;

import com.example.spacecapivara.R;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final GameLoop gameLoop;
    private final Context context;
    private final Map map = new Map();
    private final Enemy enemy = new Enemy();
    private final Player player = new Player();
    private int[] positionBntUp;
    private int[] positionBntDown;
    private int[] positionBntRight;
    private int[] positionBntLeft;
    private int[] positionBntShoot;
    private int[] positionCapivaraImg;
    private final Drawable upbtn;
    private final Drawable downbtn;
    private final Drawable rightbtn;
    private final Drawable leftbtn;
    private final Drawable shootbtn;
    private final Drawable capi_feliz;
    private final Drawable capi_raivosa;
    private final Drawable capi_triste;

    private final Drawable heart_1;
    private final Drawable heart_2;
    private final Drawable heart_3;
    private Rect positionHeart_1;
    private Rect positionHeart_2;
    private Rect positionHeart_3;

    @SuppressLint("UseCompatLoadingForDrawables")
    public Game(Context context) {
        super(context);
        this.context = context;
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        enemy.setEnemyImages(context.getResources().getDrawable(R.drawable.enemy),
                context.getResources().getDrawable(R.drawable.explosion));


        player.setImagesPlayer(context.getResources().getDrawable(R.drawable.shipup),
                context.getResources().getDrawable(R.drawable.shipright),
                context.getResources().getDrawable(R.drawable.shipdown),
                context.getResources().getDrawable(R.drawable.shipleft));

        player.getLaser().setImagesLaser(context.getResources().getDrawable(R.drawable.laservertical),
                context.getResources().getDrawable(R.drawable.laserx));

        upbtn = context.getResources().getDrawable(R.drawable.flatupbtn);
        downbtn = context.getResources().getDrawable(R.drawable.flatdownbtn);
        rightbtn = context.getResources().getDrawable(R.drawable.flatleftbtn);
        leftbtn =  context.getResources().getDrawable(R.drawable.flatrightbtn);
        shootbtn = context.getResources().getDrawable(R.drawable.flatshoot);
        capi_feliz = context.getResources().getDrawable(R.drawable.capi_feliz);
        capi_raivosa = context.getResources().getDrawable(R.drawable.capi_raivosa);
        capi_triste = context.getResources().getDrawable(R.drawable.capi_triste);
        heart_1 = context.getResources().getDrawable(R.drawable.heart);
        heart_2 = context.getResources().getDrawable(R.drawable.heart);
        heart_3 = context.getResources().getDrawable(R.drawable.heart);
        gameLoop = new GameLoop(this, surfaceHolder);
        setFocusable(true);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        int x_button = getWidth()/20;
        int y_button = getHeight()/2 + getHeight()/4;
        int size_button = getWidth()/5;
        int gap_buttton = getWidth()/20;
        int size_capivaraImage = getWidth()/3;
        setPositionBntUP(canvas, x_button, y_button, size_button, gap_buttton);
        setPositionBntDown(canvas, x_button, y_button, size_button, gap_buttton);
        setPositionBntRight(canvas, x_button, y_button, size_button, gap_buttton);
        setPositionBntLeft(canvas, x_button, y_button, size_button, gap_buttton);
        setPositionBntShoot(canvas, size_button);
        setPositionCapivaraImg(canvas, size_capivaraImage);
        setPositionHeart_1(canvas,size_capivaraImage/3);
        setPositionHeart_2(canvas,size_capivaraImage/3);
        setPositionHeart_3(canvas,size_capivaraImage/3);

        drawGrid(canvas);
        drawHud(canvas);
        drawMap(canvas);

    }

    public void drawGrid(Canvas canvas){
        Paint paint = new Paint();
        int Color = ContextCompat.getColor(context, R.color.purple_500);
        paint.setColor(Color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                canvas.drawRect(
                        (getWidth()/10f) * i,
                        ((getHeight()/2f)/10f) * j,
                        canvas.getWidth(), //Size
                        getHeight()/2f,
                        paint
                );
            }

        }
    }

    public void drawHud(Canvas canvas){
        Paint paint = new Paint();
        int Color = ContextCompat.getColor(context, R.color.gray);
        paint.setColor(Color);

        canvas.drawRect(0f,
                getHeight()/2f,
                getWidth(),
                getHeight(),
                paint);

        paint.setColor(android.graphics.Color.RED);
        capi_feliz.setBounds(getPositionCapivaraImg()[0],
                getPositionCapivaraImg()[1],
                getPositionCapivaraImg()[2],
                getPositionCapivaraImg()[3]);
        capi_raivosa.setBounds(getPositionCapivaraImg()[0],
                getPositionCapivaraImg()[1],
                getPositionCapivaraImg()[2],
                getPositionCapivaraImg()[3]);
        capi_triste.setBounds(getPositionCapivaraImg()[0],
                getPositionCapivaraImg()[1],
                getPositionCapivaraImg()[2],
                getPositionCapivaraImg()[3]);


        heart_1.setBounds(getPositionHeart_1());
        heart_2.setBounds(getPositionHeart_2());
        heart_3.setBounds(getPositionHeart_3());
        if(player.getLife() > 0) {
            heart_1.draw(canvas);
        }
        if(player.getLife() > 1) {
            heart_2.draw(canvas);
        }
        if(player.getLife() > 2) {
            heart_3.draw(canvas);

        }
        switch (player.getLife()){
            case 3:
                capi_feliz.draw(canvas);
                break;
            case 2:
                capi_raivosa.draw(canvas);
                break;
            case 1:
                capi_triste.draw(canvas);
                break;

            default:
                capi_triste.draw(canvas);
        }

        paint.setColor(ContextCompat.getColor(context, R.color.black));
        String enemyLife = String.valueOf(enemy.getLife());

        paint.setColor(ContextCompat.getColor(context, R.color.white));
        paint.setTextSize(80);
        canvas.drawText(enemyLife+"%", getHeight()/30f, getHeight()/2f+getHeight()/10f, paint);

        upbtn.setBounds(
                getPositionBntUp()[0],
                getPositionBntUp()[1],
                getPositionBntUp()[2],
                getPositionBntUp()[3]);
        upbtn.draw(canvas);
        downbtn.setBounds(
                getPositionBntDown()[0],
                getPositionBntDown()[1],
                getPositionBntDown()[2], //Size
                getPositionBntDown()[3]);
        downbtn.draw(canvas);
        rightbtn.setBounds(
                getPositionBntRight()[0],
                getPositionBntRight()[1],
                getPositionBntRight()[2], //Size
                getPositionBntRight()[3]);
        rightbtn.draw(canvas);
        leftbtn.setBounds(
                getPositionBntLeft()[0],
                getPositionBntLeft()[1],
                getPositionBntLeft()[2], //Size
                getPositionBntLeft()[3]);
        leftbtn.draw(canvas);

        shootbtn.setBounds(
                getPositionBntShoot()[0],
                getPositionBntShoot()[1],
                getPositionBntShoot()[2],
                getPositionBntShoot()[3]);
        shootbtn.draw(canvas);
    }

    public void drawMap(Canvas canvas) {
        map.ClearMap();
        map.setMap_data(player.getPosition_x(), player.getPosition_y(), 1);
        if (map.getMap_data()[enemy.getPosition_x()][enemy.getPosition_y()] == 1) {
            player.loseLife();
            enemy.explosion();
        }
        map.setMap_data(enemy.getPosition_x(), enemy.getPosition_y(), 2);

        if (map.getMap_data()[player.getPosition_x()][player.getPosition_y()] == 2) {
            player.loseLife();
            enemy.explosion();
        }

        if(player.istShootExist()){
            if (map.getMap_data()[player.getLaser().getPosition_x()][player.getLaser().getPosition_y()] == 2) {
                enemy.loseLife();
            }
            map.setMap_data(player.getLaser().getPosition_x(), player.getLaser().getPosition_y(), 3);
        }


        // 3 no map representa o tiro
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (map.getMap_data()[i][j] == 3) {
                    player.getLaser().getImageLaser().setBounds(
                            i * canvas.getWidth()/10,
                            j * ((canvas.getHeight()/2)/10),
                            (i * canvas.getWidth()/10) + (canvas.getWidth()/10),
                            (j * (canvas.getHeight()/2)/10) + (canvas.getHeight()/2)/10
                    );
                    player.getLaser().getImageLaser().draw(canvas);
                }
                // 2 no map representa o inimigo
                if (map.getMap_data()[i][j] == 2) {
                    enemy.getEnemyImage().setBounds(
                            i * canvas.getWidth()/10,
                            j * ((canvas.getHeight()/2)/10),
                            (i * canvas.getWidth()/10) + (canvas.getWidth()/10),
                            (j * (canvas.getHeight()/2)/10) + (canvas.getHeight()/2)/10
                    );

                    enemy.getEnemyImage().draw(canvas);
                }
                // 1 no map representa o player
                if (map.getMap_data()[i][j] == 1) {
                    player.getImagePlayer().setBounds(
                            i * canvas.getWidth()/10,
                            j * ((canvas.getHeight()/2)/10),
                            (i * canvas.getWidth()/10) + (canvas.getWidth()/10),
                            (j * (canvas.getHeight()/2)/10) + (canvas.getHeight()/2)/10
                    );
                    player.getImagePlayer().draw(canvas);


                 }
            }
        }
    }


    public void update() {
        if(player.getPosition_y() > 8) {
            player.setPosition_y(8);
        }
        else if(player.getPosition_y() < 1) {
            player.setPosition_y(1);
        }
        if(player.getPosition_x() > 8) {
            player.setPosition_x(8);
        }
        else if(player.getPosition_x() < 1) {
            player.setPosition_x(1);
        }

        if(player.getLife()<0 || enemy.getLife()<0){
            context.startActivity(new Intent(context, MainActivity.class));
        }

        if(player.getLife()<= 0 || enemy.getLife() <= 0){
            gameLoop.setRunning(false);
        }
    }
    public Player getPlayer(){
        return player;
    }

    public int[] getPositionBntUp() {
        return positionBntUp;
    }

    public int[] getPositionBntDown() {
        return positionBntDown;
    }

    public int[] getPositionBntRight() {
        return positionBntRight;
    }

    public int[] getPositionBntLeft() {
        return positionBntLeft;
    }

    public int[] getPositionBntShoot() {
        return positionBntShoot;
    }

    public void setPositionBntUP(Canvas canvas, int x, int y, int size, int gap){
        positionBntUp = new int[]{
                x+size/2+gap/2,
                y,
                x+size/2+gap/2+size,
                y+size
        };
    }
    public void setPositionBntDown(Canvas canvas, int x, int y, int size, int gap){
        positionBntDown = new int[]{
                x+size/2+gap/2,
                (y+size+gap),
                x+size/2+gap/2+size,
                (y+size+gap)+size
        };
    }
    public void setPositionBntRight(Canvas canvas, int x, int y, int size, int gap){
        positionBntRight = new int[]{
                (x+size+gap),
                y+size/2+gap/2,
                (x+size+gap)+size,
                y+size/2+gap/2+size
        };
    }
    public void setPositionBntLeft(Canvas canvas, int x, int y, int size, int gap){
        positionBntLeft = new int[]{
                x,
                y+size/2+gap/2,
                x+size,
                y+size/2+gap/2+size
        };
    }

    public void setPositionBntShoot(Canvas canvas, int size){
        positionBntShoot = new int[]{
                canvas.getWidth()/2 + canvas.getWidth()/4 - size/2,
                canvas.getHeight()/2 + canvas.getHeight()/3,
                canvas.getWidth()/2 + canvas.getWidth()/4 + size/2,
                canvas.getHeight()/2 + canvas.getHeight()/3 + size
        };
    }

    public int[] getPositionCapivaraImg(){
        return positionCapivaraImg;
    }

    public void setPositionCapivaraImg(Canvas canvas, int size) {
        positionCapivaraImg = new int[]{
                canvas.getWidth()/2 - size/2,
                canvas.getHeight()/2,
                canvas.getWidth()/2 + size/2,
                canvas.getHeight()/2+ size
        };
    }
    public void setPositionHeart_1(Canvas canvas, int size){
        positionHeart_1 = new Rect(
                canvas.getWidth()/2 - size,
                canvas.getHeight()/2 + size*3,
                canvas.getWidth()/2 - size/3,
                canvas.getHeight()/2 + size*3 + size);
    }
    public void setPositionHeart_2(Canvas canvas, int size){
        positionHeart_2 = new Rect(
                canvas.getWidth()/2 - size/3,
                canvas.getHeight()/2+ size*3,
                canvas.getWidth()/2 + size/3,
                canvas.getHeight()/2+ size*3+ size);
    }
    public void setPositionHeart_3(Canvas canvas, int size){
        positionHeart_3 = new Rect(
                canvas.getWidth()/2 + size/3,
                canvas.getHeight()/2+ size*3,
                canvas.getWidth()/2 + size,
                canvas.getHeight()/2+ size*3 + size);
    }

    public Rect getPositionHeart_1(){return positionHeart_1;}
    public Rect getPositionHeart_2(){return positionHeart_2;}
    public Rect getPositionHeart_3(){return positionHeart_3;}

}
