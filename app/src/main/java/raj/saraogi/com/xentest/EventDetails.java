package raj.saraogi.com.xentest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import raj.saraogi.com.xentest.database.DatabaseHelper;
import raj.saraogi.com.xentest.holder.Event;

public class EventDetails extends AppCompatActivity {
    String event_id;
    Button b1;
    ImageView im;
    private Toolbar toolbar;
    TextView event_name,event_description,event_date,event_time,cordinate_name,event_rate;
    String mno,ename,url;
    private Event event;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        event_id=getIntent().getExtras().getString("event_id");
        toolbar = (Toolbar) findViewById(R.id.toolbarEvent);
        TextView textView = (TextView)findViewById(R.id.title);
        textView.setText("Event Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_36dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Details", "on Click");
                onBackPressed();
            }
        });
        databaseHelper = new DatabaseHelper(this);

        // Toast.makeText(this,event_id,Toast.LENGTH_LONG).show();

        b1=(Button)findViewById(R.id.btnregister);
        im=(ImageView)findViewById(R.id.eventImage);
        event_name=(TextView)findViewById(R.id.text_event_name);
        event_description=(TextView)findViewById(R.id.text_event_description);
        event_date=(TextView)findViewById(R.id.text_event_date);
        event_time=(TextView)findViewById(R.id.text_event_time);
        event_rate=(TextView)findViewById(R.id.text_event_rate);
        ename=event_name.getText().toString();
        addView();
    }
    public void addView() {
        event = databaseHelper.events(event_id);
        Log.d("event",event+" "+event.eventName+" "+event.eventDescription);
        event_name.setText(event.eventName);
        event_description.setText(event.eventDescription);
        event_date.setText(event.eventDate);
        event_time.setText(event.eventTime);
        event_rate.setText(event.eventPrice);
        url=event.url;
        im.setImageResource(event.imageName);

    }
    public void regForm(View view){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
