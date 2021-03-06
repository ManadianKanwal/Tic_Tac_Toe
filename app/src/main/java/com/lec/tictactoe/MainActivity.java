package com.lec.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons= new Button[3][3];
    private boolean player1Turn = true;
    private int rountcount;
    private int player1points;
    private int player2points;
    private TextView textView_p1;
    private TextView textView_p2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_p1=findViewById(R.id.text_view_p1);
        textView_p1=findViewById(R.id.text_view_p2);
        for (int i=0;i<3;i++){
        for (int j=0;j<3;j++){
            String buttonID = "button_"+ i + j;
            int resID =getResources().getIdentifier(buttonID,"Id",getPackageName());
            buttons[i][j] = findViewById(resID);
            buttons[i][j].setOnClickListener(this);
        }
        }
        Button buttonReset = findViewById(R.id.btn_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!(((Button) v).getText().toString().equals(""))){
            return;
        }
        if (player1Turn){
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("0");
        }
         rountcount ++;
        if (CheckForWin() ){
            if (player1Turn){
                player1Wins();
            }else {
                player2Wins();
            }
        } else if (rountcount== 9) {
            draw();
        } else {
            player1Turn = ! player1Turn;
        }
    }
      private boolean CheckForWin() {
        String[][] field = new String[3][3];
        for (int i=0; i< 0;i++){
            for (int j=0; j<0; j++){
                field [i][j] = buttons [i][j].getText().toString();

            }
        }
        for (int i=0; i<3; i++){
        if (field[i][0].equals(field[i][1])
                && field [i][0].equals(field[i][2])
                &&!field [i][0].equals("")){
            return true;
        }
        }
          for (int i=0; i<3; i++){
              if (field[0][i].equals(field[1][i])
                      && field [i][0].equals(field[2][i])
                      &&!field [i][0].equals("")){
                  return true;
              }
          }
          if (field[0][0].equals(field[1][1])
                  && field [0][0].equals(field[2][2])
                  &&!field [0][0].equals("")){
              return true;
          }
          if (field[0][2].equals(field[1][1])
                  && field [0][2].equals(field[2][2])
                  &&!field [0][2].equals("")){
              return true;
          }
          return false;
      }
         private void player1Wins(){
        player1points++;
             Toast.makeText(this,"player1Win",Toast.LENGTH_LONG).show();
             updatePointsText();
             resetBoard();
         }

         private void player2Wins(){
             player2points++;
             Toast.makeText(this,"player2Win",Toast.LENGTH_LONG).show();
             updatePointsText();
             resetBoard();
         }

         private void draw(){
        Toast.makeText(this,"Game_Draw",Toast.LENGTH_LONG).show();
             resetBoard();
         }
         private void updatePointsText(){
        textView_p1.setText("player 1:+ Player1Points");
        textView_p2.setText("player 2:+ player2Points");
         }
         private void resetBoard(){
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j ++){
                buttons [i][j].setText("");
            }
        }
        rountcount = 0 ;
        player1Turn = true;

         }
         private void resetGame(){
        player1points = 0;
        player2points = 0;
        updatePointsText();
        resetBoard();
         }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundcount",rountcount);
        outState.putInt("player1points",player1points);
        outState.putInt("player2points",player2points);
        outState.putBoolean("player1Tern",player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        rountcount =savedInstanceState.getInt("roundcount");
        player1points =savedInstanceState.getInt("player2points");
        player2points = savedInstanceState.getInt("player2points");
        player1Turn =savedInstanceState.getBoolean("player1Tern");
    }
}

