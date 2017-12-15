package org.josan.gameapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.josan.gameapp.model.Game;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView mListViewGames;
    ArrayList<Game> mListaGames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewGames = findViewById(R.id.list_view_games);

        final ArrayAdapter<Game> adapter = new ArrayAdapter<Game>(
                this,
                R.layout.list_item_game,
                R.id.text_view_game,
                mListaGames
        );

        mListViewGames.setAdapter(adapter);

        mListViewGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(
                        MainActivity.this,
                        DetailActivity.class);
                detailIntent.putExtra("game", mListaGames.get(position));
                startActivity(detailIntent);
            }
        });

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://gameapp-alpha.herokuapp.com/",
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.e("MainActivity", "Algo está fallando.");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d("MainActivity", "OK->Response:"+ responseString);
                        Gson gson = new GsonBuilder().create();
                        Game[] games = gson.fromJson(responseString, Game[].class);
                        adapter.clear();
                        for (Game game : games){
                            adapter.add(game);
                        }
                    }
                });
    }

    private void loadGames(){
//        mListaGames.add("League of Legends");
//        mListaGames.add("Minecraft");
    }
}
