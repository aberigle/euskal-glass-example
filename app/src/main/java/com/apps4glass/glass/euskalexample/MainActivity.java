package com.apps4glass.glass.euskalexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.apps4glass.glass.euskalexample.R;
import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Card> cards;
    private CardScrollView cardScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cards = new ArrayList<Card>();

        cards.add(new Card(this).setText("First card"));

        cardScrollView = new CardScrollView(this);
        cardScrollView.setAdapter(new SimpleCardScrollAdapter());
        cardScrollView.activate();
        setContentView(cardScrollView);
    }

    private class SimpleCardScrollAdapter extends CardScrollAdapter {

        @Override
        public int getCount() { return cards.size(); }

        @Override
        public Object getItem(int i) { return cards.get(i); }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) { return cards.get(i).getView(); }

        @Override
        public int getPosition(Object o) { return cards.indexOf(o); }

    }
}
