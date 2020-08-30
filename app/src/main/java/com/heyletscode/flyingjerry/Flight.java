package com.heyletscode.flyingjerry;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.heyletscode.flyingjerry.GameView.screenRatioX;
import static com.heyletscode.flyingjerry.GameView.screenRatioY;

public class Flight {

    int toShoot = 0;
    boolean isGoingUp = false;
    int x, y, width, height, wingCounter = 0, shootCounter = 1;
    Bitmap  flight2, shoot,shoot1;
    private GameView gameView;

    Flight (GameView gameView, int screenY, Resources res) {

        this.gameView = gameView;

        flight2 = BitmapFactory.decodeResource(res, R.drawable.shoot1);

        width = flight2.getWidth();
        height = flight2.getHeight();

        width /= 2;
        height /= 2;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        flight2 = Bitmap.createScaledBitmap(flight2, width, height, false);

        shoot = BitmapFactory.decodeResource(res, R.drawable.shoot1);
        shoot = Bitmap.createScaledBitmap(shoot, width, height, false);
        shoot1 = BitmapFactory.decodeResource(res, R.drawable.boom);
        shoot1 = Bitmap.createScaledBitmap(shoot1, width, height, false);

        y = screenY / 2;
        x = (int) (64 * screenRatioX);

    }

    Bitmap getFlight () {

        if (toShoot != 0) {

            if (shootCounter == 1) {
                shootCounter++;
                return shoot;
            }



            shootCounter = 1;
            toShoot--;
            gameView.newBullet();

            return shoot;
        }

        if (wingCounter == 0) {
            wingCounter++;
            return flight2;
        }
        wingCounter--;

        return flight2;
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + width/2, y + height/2);
    }

    Bitmap getDead () {
        return shoot1;
    }

}
