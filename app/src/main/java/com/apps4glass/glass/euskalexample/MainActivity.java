package com.apps4glass.glass.euskalexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.apps4glass.glass.euskalexample.R;
import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Card> cards;
    private CardScrollView cardScrollView;
    private List<ExampleCase> examples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cards = new ArrayList<Card>();
        examples = new ArrayList<ExampleCase>();

        populateExamples();

        for (ExampleCase example : examples) cards.add(new Card(this).setText(example.getText()));

        cardScrollView = new CardScrollView(this);
        cardScrollView.setAdapter(new SimpleCardScrollAdapter());
        cardScrollView.activate();

        cardScrollView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int selected, long l) {
                examples.get(selected).onSelected();
            }
        });
        setContentView(cardScrollView);
    }

    private void populateExamples() {

        examples.add(new ExampleCase("Test Toast") {
            @Override
            public void onSelected() {
                Toast.makeText(getBaseContext(), getText(), Toast.LENGTH_SHORT).show();
            }
        });

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

    private abstract class ExampleCase {
        private String text;

        public ExampleCase(String text) { this.text = text; }

        public String getText() { return text; }

        public abstract void onSelected();
    }
}
