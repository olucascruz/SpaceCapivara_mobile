package com.example.spacecapivara.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.spacecapivara.game.Game;

public class GameActivity extends Activity {
    private static final String TAG = "MyActivity";
    Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        game = new Game(this);
        setContentView(game);
        addTouchListener();
    }




    private void addTouchListener(){
        View image = (View) game;
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                String message = String.format("Cordenadas> (%.2f, %.2f)", x, y);
                Log.d(TAG, message);
                if(x > game.getPositionBntUp()[0] &&
                        x < game.getPositionBntUp()[2] &&
                        y > game.getPositionBntUp()[1] &&
                        y < game.getPositionBntUp()[3]) {
                    game.getPlayer().MoveForward();
                    Log.d(TAG, "aaaaa");
                }else if(x > game.getPositionBntDown()[0] &&
                        x < game.getPositionBntDown()[2] &&
                        y > game.getPositionBntDown()[1] &&
                        y < game.getPositionBntDown()[3]){

                    game.getPlayer().MoveForback();

                }else if(x > game.getPositionBntRight()[0] &&
                        x < game.getPositionBntRight()[2] &&
                        y > game.getPositionBntRight()[1] &&
                        y < game.getPositionBntRight()[3]){
                    game.getPlayer().RotateRight();

                }else if(x > game.getPositionBntLeft()[0] &&
                        x < game.getPositionBntLeft()[2] &&
                        y > game.getPositionBntLeft()[1] &&
                        y < game.getPositionBntLeft()[3]){
                    game.getPlayer().RotateLeft();

                }else if(x > game.getPositionBntShoot()[0] &&
                        x < game.getPositionBntShoot()[2] &&
                        y > game.getPositionBntShoot()[1] &&
                        y < game.getPositionBntShoot()[3]){
                    game.getPlayer().Shoot();
                }



                return false;
            }
        });
    }
}


