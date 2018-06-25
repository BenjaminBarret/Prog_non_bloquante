package fr.wcs.prognonbloquante;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isLiftMoving = false;
    private int currentFloor = 0;
    private static final int TIME_DELAY = 3000;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);

        onBoutonClick(btn0,0);
        onBoutonClick(btn1,1);
        onBoutonClick(btn2,2);
        onBoutonClick(btn3,3);
        onBoutonClick(btn4,4);
        onBoutonClick(btn5,5);
        onBoutonClick(btn6,6);
        onBoutonClick(btn7,7);
        onBoutonClick(btn8,8);
        onBoutonClick(btn9,9);

    }

    private void goToFloor(int floor){
        if (!isLiftMoving && floor != currentFloor) {
            moveNextFloor(floor);
            isLiftMoving = false;
        }
    }

    private void moveNextFloor(int floor){
        if (floor != currentFloor) {
            isLiftMoving = true;
            MoveLift moveLift = new MoveLift();
            moveLift.execute(floor);
            isLiftMoving = false;

        }
    }

    private void onBoutonClick(Button button, final int floor){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloor(floor);
            }
        });

    }

    private class MoveLift extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {

            try {
                Thread.sleep(TIME_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentFloor = (integers[0] > currentFloor) ? currentFloor + 1 : currentFloor - 1;

            publishProgress(currentFloor);

            moveNextFloor(integers[0]);

            return currentFloor;
        }

        @Override
        protected void onPostExecute(Integer integer) {

            TextView floorCount = (TextView) findViewById(R.id.floor_count);
            floorCount.setText("Etage : " + String.valueOf(integer));


        }

    }

}


