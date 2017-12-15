package org.josan.gameapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.josan.gameapp.model.Game;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView mImageViewGameLogo;
    TextView mTextViewGameName;
    TextView mTextViewGamePlatform;
    TextView mTextViewDescription;
    Button mButtonWeb;
    Button mButtonDoc;
    Button mButtonForums;
    Button mButtonBugs;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        game = new Game();

        mImageViewGameLogo = findViewById(R.id.game_logo_image_view);
        mTextViewGameName = findViewById(R.id.game_name_text_view);
        mTextViewGamePlatform = findViewById(R.id.platform_text_view);
        mTextViewDescription = findViewById(R.id.description_text_view);
        mButtonWeb = findViewById(R.id.web_button);
        mButtonDoc = findViewById(R.id.doc_button);
        mButtonForums = findViewById(R.id.forums_button);
        mButtonBugs = findViewById(R.id.bugs_button);

        mButtonWeb.setOnClickListener(this);
        mButtonDoc.setOnClickListener(this);
        mButtonForums.setOnClickListener(this);
        mButtonBugs.setOnClickListener(this);

        // Configuración del Scroll en el textView de descripción
        mTextViewDescription.setMovementMethod(new ScrollingMovementMethod());

        Intent detailIntent = DetailActivity.this.getIntent();

        if(detailIntent.hasExtra("game")){
            game = (Game)detailIntent.getSerializableExtra("game");
        }

        Log.d("DetailActivity", "Image Src: " + game.getImage());

        Picasso.with(this).load(game.getImage()).into(mImageViewGameLogo);
        mTextViewGameName.setText(game.getName());
        mTextViewGamePlatform.setText(game.getPlatform());
        mTextViewDescription.setText(game.getDescription());
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        switch (v.getId()){
            case R.id.web_button:
                openLink(game.getWeb());
                break;
            case R.id.doc_button:
                openLink(game.getDoc());
                break;
            case R.id.forums_button:
                openLink(game.getForums());
                break;
            case R.id.bugs_button:
                String bugs_link = game.getError_tracker();
                if( bugs_link.equals("")){
                    showNoLinkMessage();
                }else{
                    openLink(bugs_link);
                }

                break;
        }
    }

    private void showNoLinkMessage() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Ha ocurrido un problema.");
        alertDialog.setMessage("En enlace no es correcto");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void openLink(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}