package csitmnr.newsproject2;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class NewsAndEventDataShow extends AppCompatActivity {
    Toolbar toolbar1;
    TextView title,created_at,updated_at,detail,intro_text;
    ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_and_event_data_show);

        toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        toolbar1.setTitle("News And Events");
        setSupportActionBar(toolbar1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);






         title = (TextView) findViewById(R.id.title1);
         intro_text = (TextView) findViewById(R.id.intro_text1);
         created_at = (TextView) findViewById(R.id.created_at1);
         updated_at = (TextView) findViewById(R.id.updated_at1);
         detail = (TextView) findViewById(R.id.detail1);
         image1 = (ImageView) findViewById(R.id.image1);

        title.setText(getIntent().getExtras().getString("Title"));
        created_at.setText(getIntent().getExtras().getString("CreatedAt"));
        updated_at.setText(getIntent().getExtras().getString("UpdatedAt"));
        intro_text.setText(getIntent().getExtras().getString("IntroText"));
        detail.setText(getIntent().getExtras().getString("Detail"));

      String  image = getIntent().getExtras().getString("Image");

        Glide
                .with(getApplicationContext())
                .load(image)
                .into(image1);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
