package com.droidrank.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, restart;
    LinearLayout ll1, ll2;
    TextView result;
    boolean mState = true;
    int matrix[][] = {
        {-1, -1, -1 },
        {-1, -1, -1 },
        {-1, -1, -1 } };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll1 = (LinearLayout) findViewById(R.id.LL1);
        ll2 = (LinearLayout) findViewById(R.id.LL2);

        bt0 = (Button) findViewById(R.id.bt_block1);
        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentForButton(bt0, 0, 0);
            }
        });

        bt1 = (Button) findViewById(R.id.bt_block2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentForButton(bt1, 0, 1);
            }
        });

        bt2 = (Button) findViewById(R.id.bt_block3);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentForButton(bt2, 0, 2);
            }
        });

        bt3 = (Button) findViewById(R.id.bt_block4);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentForButton(bt3, 1, 0);
            }
        });

        bt4 = (Button) findViewById(R.id.bt_block5);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentForButton(bt4, 1, 1);
            }
        });

        bt5 = (Button) findViewById(R.id.bt_block6);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentForButton(bt5, 1, 2);
            }
        });

        bt6 = (Button) findViewById(R.id.bt_block7);
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentForButton(bt6, 2, 0);
            }
        });

        bt7 = (Button) findViewById(R.id.bt_block8);
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentForButton(bt7, 2, 1);
            }
        });

        bt8 = (Button) findViewById(R.id.bt_block9);
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentForButton(bt8, 2, 2);
            }
        });

        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);






        String msg = "Do you want to restart the game";
        /**
         * Restarts the game
         */
        restart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to restart the game").setTitle("Tic-Tac-Toe")
                //builder.setMessage("Do you want to close this application ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //finish();
                                restart.setText("Restart Game");
                                clearAllButton();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    private boolean getState() {
        return mState;
    }

    private void clearAllButton() {
        bt0.setText("");
        bt1.setText("");
        bt2.setText("");
        bt3.setText("");
        bt4.setText("");
        bt5.setText("");
        bt6.setText("");
        bt7.setText("");
        bt8.setText("");
        result.setText("");
        mState = true;
        ll1 = (LinearLayout) findViewById(R.id.LL1);
        ll2 = (LinearLayout) findViewById(R.id.LL2);
        if (ll1 != null && ll2 != null) {
            makeDisable(ll1, true);
            makeDisable(ll2, true);
        }

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                matrix[i][j] = -1;
            }
        }
    }

    private void setContentForButton(Button bt, int i, int j) {
        if (TextUtils.isEmpty(bt.getText().toString())) {
            if(getState()) {
                mState = false;
                bt.setText("0");
                matrix[i][j] = 0;
            } else {
                mState = true;
                bt.setText("X");
                matrix[i][j] = 1;
            }

            checkWinOrNot(i, j, matrix[i][j]);
        }
    }

    private void checkWinOrNot(int i, int j, int type) {
        boolean win = false;
        //horizontal
        for (int k=0; k<3; k++) {
            if (type == matrix[i][k]) {
                // horizontal is not matching
                win = true;
            } else {
                win = false;
                break;
            }
        }
        if (win) {
            whoWon(type);
        }

        //vertical
        for (int k=0; k<3; k++) {
            if (type == matrix[k][j]) {
                // vertical is not matching
                win = true;
            } else {
                win = false;
                break;
            }
        }

        if (win) {
            whoWon(type);
        }

        //right diagnal
        if ((i == 1 && j == 1) || (i == 2 && j == 0) || (i == 0 && j == 2)) {
            int k = 2;
            for (int l=0; l<3; l++) {
                if (type == matrix[k--][l]) {
                    win = true;
                } else {
                    win = false;
                    break;
                }
            }
        }

        if (win) {
            whoWon(type);
        }

        if (i == j) {
            int k = 0;
            for (int l=0; l<3; l++) {
                if (type == matrix[k++][l]) {
                    win = true;
                } else {
                    win = false;
                    break;
                }
            }
        }

        if (win) {
            whoWon(type);
        }

        //check whether match is tie or not
        boolean tie = false;
        for (int k=0; k<3; k++) {
            for (int l=0; l<3; l++) {
                android.util.Log.d("pezhu","k="+k+", l="+l+", matrix[k][l]="+matrix[k][l]);
                if (matrix[k][l] == 0 || matrix[k][l] == 1) {
                    android.util.Log.d("pezhu","tie is true");
                    tie = true;
                } else {
                    android.util.Log.d("pezhu","tie is false");
                    tie = false;
                    break;
                }
            }
            if (!tie)
                break;
        }
        if (tie) {
            result.setText("Match tied");
        }
    }

    private void whoWon(int type) {
        ll1 = (LinearLayout) findViewById(R.id.LL1);
        ll2 = (LinearLayout) findViewById(R.id.LL2);
        if (ll1 != null && ll2 != null) {
            makeDisable(ll1, false);
            makeDisable(ll2, false);
        }
        if (type == 0) {
            result.setText("Congratulation ! Player 1 Won");
        } else {
            result.setText("Congratulation ! Player 2 Won");
        }
    }

    private void makeDisable(LinearLayout view, boolean state) {
        for (int i = 0; i < view.getChildCount(); i++) {
            View child = view.getChildAt(i);
            child.setEnabled(state);
        }
    }
}
