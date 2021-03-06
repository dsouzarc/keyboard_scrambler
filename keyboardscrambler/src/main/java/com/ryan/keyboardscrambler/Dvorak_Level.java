package com.ryan.keyboardscrambler;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Dvorak_Level extends KeyboardLevel {

    //private static final String theStr = "1234567890" + DELETE_CHAR + "\",.pyfgcrl?=\\aoeuidhtns:qjkxbmwvz ";
    //private static final String punctuation = "\",.?=\\:";

    private static final String theStr = "1234567890" + DELETE_CHAR + "\",.pyfgcrlaoeuidhtns:qjkxbmwvz ";
    private static final String punctuation = "\",.:";

    private LinearLayout fifthRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, getIntent().getExtras().getStringArray("words"));
        setContentView(R.layout.dvorak__level);

        this.LEVEL = Level.DVORAK;

        firstRow = (LinearLayout) findViewById(R.id.firstRow);
        secondRow = (LinearLayout) findViewById(R.id.secondRow);
        thirdRow = (LinearLayout) findViewById(R.id.thirdRow);
        fourthRow = (LinearLayout) findViewById(R.id.fourthRow);
        fifthRow = (LinearLayout) findViewById(R.id.fifthRow);

        //Shows characters user pressed
        userResponse = (TextView) findViewById(R.id.userResponseTV);
        userResponse.setCursorVisible(true);

        timeTV = (TextView) findViewById(R.id.timeTV);
        setUpTimer(timeTV);

        //Scrambled version of the str
        final String reArranged = this.theStr;
        int counter = 0;

        View view;
        //Add the keys to the screen
        for(byte i = 0; i < 11; i++, counter++) {
            view = getCharTV(reArranged.charAt(counter), 11);
            firstRow.addView(view);
            new RippleView(this, view);
        }
        for(byte i = 0; i < 10; i++, counter++) {
            view = getCharTV(reArranged.charAt(counter), 10);
            secondRow.addView(view);
            new RippleView(this, view);
        }
        for(byte i = 0; i < 10; i++, counter++) {
            view = getCharTV(reArranged.charAt(counter), 10);
            thirdRow.addView(view);
            new RippleView(this, view);
        }
        for(byte i = 0; i < 10; i++, counter++) {
            view = getCharTV(reArranged.charAt(counter), 10);
            fourthRow.addView(view);
            new RippleView(this, view);
        }

        final TextView blank1 = getCharTV(' ', 4);
        final TextView space = getCharTV(' ', 2);
        final TextView blank2 = getCharTV(' ', 4);

        unformatView(blank1);
        unformatView(blank2);

        fifthRow.addView(blank1);
        fifthRow.addView(space);
        fifthRow.addView(blank2);

        new RippleView(this, space);

        //Refresh button --> DEV PURPOSES
        refresh = (TextView) findViewById(R.id.refresh);
        refresh.setText(getLevelWord());
        refresh.setTextColor(Color.parseColor("#ff0099cc"));
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }

    private void unformatView(final TextView theView) {
        theView.setOnClickListener(null);
        theView.setClickable(false);
        theView.setBackgroundResource(0);
        theView.setBackgroundColor(Color.TRANSPARENT);
        theView.setBackground(null);
    }

    protected String getLevelWord() {
        final String word1 = super.getRandomWord();
        final char punctuation1 = punctuation.charAt(theGenerator.nextInt(punctuation.length()));
        final String word2 = super.getRandomWord();

        //Randomly choose if there should be punctuation
        if(theGenerator.nextInt(500) % 2 == 0) {
            final char punctuation2 = punctuation.charAt(theGenerator.nextInt(punctuation.length()));
            return word1 + " " + punctuation1 + " " + word2 + " " + punctuation2;
        }
        else {
            return word1 + " " + punctuation1 + " " + word2;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dvorak__level, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.dvorak_label) {
            log("OMETHING");
        }

        return super.onOptionsItemSelected(item);
    }
}
