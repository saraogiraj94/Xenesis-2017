package raj.saraogi.com.xentest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import raj.saraogi.com.xentest.holder.Event;
import raj.saraogi.com.xentest.database.*;

public class DepartmentsEventsList extends AppCompatActivity {
    String id;
    private List<Event> events = new ArrayList<>();
    private static DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    //  private RequestQueue requestQueue;
    String deptName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments_events_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_36dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        id=getIntent().getExtras().getString("id");
        deptName=getIntent().getExtras().getString("name");
        TextView textView = (TextView)findViewById(R.id.title);
        textView.setText(deptName);

        databaseHelper = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        events.clear();
        events = databaseHelper.events(Arrays.asList(DatabaseSchema.Events.DEPARTMENT_ID),new String[]{id});
        adapter = new EventCardAdapter(events, this);
        recyclerView.setAdapter(adapter);



    }
}
