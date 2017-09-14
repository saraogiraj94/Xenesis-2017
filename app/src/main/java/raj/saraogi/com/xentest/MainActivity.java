package raj.saraogi.com.xentest;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.Serializable;

import raj.saraogi.com.xentest.database.*;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout home,navigation,share,about;
    LinearLayout CEIT,Civil,Mechanical,Mca,Electrical,EC;
    private DatabaseHelper databaseHelper;
    int position,NUM_PAGES=4;

    private ViewPager vPager;
      private Handler handler = new Handler();
    private Runnable runnale = new Runnable() {
        public void run() {
            vPager.setCurrentItem(position, true);
            if (position >= NUM_PAGES) position = 0;
            else position++;
            // Move to the next page after 10s
            handler.postDelayed(runnale, 5000);
        }
    };

    // the pager adapter that provides the pages to the view pager widge
    private PagerAdapter pAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        position=0;
        intializeBottomBar();
        intializeDashboard();
        databaseHelper = new DatabaseHelper(this);
        int count = databaseHelper.checkData();
        Log.d("count", String.valueOf(count));
        //  Toast.makeText(getApplicationContext(),count,Toast.LENGTH_LONG).show();
        if(count==0) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    addData();
                    return null;
                }
            }.execute();
        }




    }

    public void intializeDashboard(){
        CEIT=(LinearLayout)findViewById(R.id.llCEIT);
        EC=(LinearLayout)findViewById(R.id.llEC);
        Electrical=(LinearLayout)findViewById(R.id.llElectrical);
        Mechanical=(LinearLayout)findViewById(R.id.llMechanical);
        Mca=(LinearLayout)findViewById(R.id.llMCA);
        Civil=(LinearLayout)findViewById(R.id.llCivil);

        CEIT.setOnClickListener(this);
        EC.setOnClickListener(this);
        Mca.setOnClickListener(this);
        Mechanical.setOnClickListener(this);
        Electrical.setOnClickListener(this);
        Civil.setOnClickListener(this);
        vPager = (ViewPager) findViewById(R.id.pager3);
        pAdapter = new PagerAdapter(getSupportFragmentManager());
        vPager.setAdapter(pAdapter);
    }
    public void intializeBottomBar(){
        home=(LinearLayout)findViewById(R.id.llhome);
        navigation=(LinearLayout)findViewById(R.id.llrate);
        share=(LinearLayout)findViewById(R.id.llshare);
        about=(LinearLayout)findViewById(R.id.llabout);

        home.setOnClickListener(this);
        navigation.setOnClickListener(this);
        share.setOnClickListener(this);
        about.setOnClickListener(this);

    }

    private void addData() {
        databaseHelper.addEvent("1","CI1" ,Constants.Department.COMPUTER_ID, "X-Intern",cd.d.c1, " Rs. 30 /- Per Participant", "24th March", "10:45 A.M", R.drawable.x_intern,"https://xenesis.ldrp.ac.in/events/x-intern/");
        databaseHelper.addEvent("2","CI2" ,Constants.Department.COMPUTER_ID, "X- IT Quiz",cd.d.c2, " Rs 30 per team", "24th March", "10:45 A.M" ,R.drawable.x_it,"https://xenesis.ldrp.ac.in/events/x-it-quiz/");
        databaseHelper.addEvent("3","CI3",Constants.Department.COMPUTER_ID, "- X-Balloon Code",cd.d.c3, " 60 /- per team. (team of two participants).", "25th March", "10:45 AM", R.drawable.x_ballon_code,"https://xenesis.ldrp.ac.in/events/x-balloon-code/");
        databaseHelper.addEvent("4","CI4",Constants.Department.COMPUTER_ID, "X-Code Chase",cd.d.c4, " Rs. 100/- Per Team (3 participants)",  "24th March", "10:45 A.M", R.drawable.x_code_chase,"https://xenesis.ldrp.ac.in/events/x-codechase/");
        databaseHelper.addEvent("5","CI5",Constants.Department.COMPUTER_ID, "X-Web Deco Hunt",cd.d.c5, " Rs.100/- Per Team", "24th March", "10:45 A.M", R.drawable.web_deco_hunt,"https://xenesis.ldrp.ac.in/events/x-webdeco-hunt/");
        databaseHelper.addEvent("6","CI6",Constants.Department.COMPUTER_ID,"X-Pro-Coder",cd.d.c6,"Rs 60/- Per Team","25th March", "10:45 A.M",R.drawable.x_pro_coder,"https://xenesis.ldrp.ac.in/events/x-pro-coder/");
        databaseHelper.addEvent("7","CI7",Constants.Department.COMPUTER_ID,"X-Fifathon 17",cd.d.c7,"Rs.100/- Per person","24th and 25th March","10:45 AM",R.drawable.x_fifathon,"https://xenesis.ldrp.ac.in/events/x-fifathon-17/");
        databaseHelper.addEvent("8","CI8",Constants.Department.COMPUTER_ID,"X-Code Distraction",cd.d.c8,"Rs 50 per person","25th March","10:45 AM",R.drawable.x_code_distract,"https://xenesis.ldrp.ac.in/events/x-code-distraction/");
        databaseHelper.addEvent("9","CI9",Constants.Department.COMPUTER_ID,"X-CounterStrike ",cd.d.c9,"Rs 250 per team(5 players)","24th and 25th March","10:45 AM",R.drawable.banner_cs,"https://xenesis.ldrp.ac.in/events/x-counterstrike/");
        databaseHelper.addEvent("10","CI10",Constants.Department.COMPUTER_ID,"X-Memo Charades",cd.d.c10," 60 Rupees per team","25th March","10:45 AM",R.drawable.x_memo_charades,"https://xenesis.ldrp.ac.in/events/x-memo-charades/");
        databaseHelper.addEvent("11","CI11",Constants.Department.COMPUTER_ID,"NFS Most wanted X-Treme",cd.d.c11,"Rs 50 per person","24th and 25th March","10:45 AM",R.drawable.nfs_banner,"https://xenesis.ldrp.ac.in/events/nfs-wanted-x-treme/");


        databaseHelper.addEvent("12","EC1",Constants.Department.EC_ID,"X-LASER-TAG(Live)",cd.ec.c1,"Rs 50/Participant","24th Mardh","10:45 AM",R.drawable.x_lasertag,"https://xenesis.ldrp.ac.in/events/x-laser-tag/");
        databaseHelper.addEvent("13","EC2",Constants.Department.EC_ID,"X-LAN WAR (Mini Militia)",cd.ec.c2,"Rs 150 per team(Max 3 people)","24th March","10:45 AM",R.drawable.x_minimiltia,"https://xenesis.ldrp.ac.in/events/x-lan-war-mini-militia/");
        databaseHelper.addEvent("14","EC3",Constants.Department.EC_ID,"X-SCRAMPER",cd.ec.c3,"Rs 50 per Participant","24th and 25th March"," 10:45 AM",R.drawable.x_scrammper,"https://xenesis.ldrp.ac.in/events/x-scramper/");
        databaseHelper.addEvent("15","EC4",Constants.Department.EC_ID,"X-MINGLE",cd.ec.c4,"Rs 50 per participant","24th and 25th March"," 10:45 AM",R.drawable.x_mingle,"https://xenesis.ldrp.ac.in/events/x-mingle/");


        databaseHelper.addEvent("16","EE1",Constants.Department.ELECTRICAL_ID,"Plantastic",cd.ee.c1,"100/- per person (2 members max)","24th and 25th March"," 11:00 AM ",R.drawable.plantasic,"https://xenesis.ldrp.ac.in/events/x-plantastic/");
        databaseHelper.addEvent("17","EE2",Constants.Department.ELECTRICAL_ID,"Startup +",cd.ee.c2," Rs 100"," 24th March"," 11:30 PM",R.drawable.startup,"https://xenesis.ldrp.ac.in/events/startup/");
        databaseHelper.addEvent("18","EE3",Constants.Department.ELECTRICAL_ID,"X- Techo buzz ",cd.ee.c3," Rs 100/- per team (Max. 4 candidates)","24th March"," 11:30 AM",R.drawable.x_techo_buzz,"https://xenesis.ldrp.ac.in/events/x-techo-buzz/");
        databaseHelper.addEvent("19","EE4",Constants.Department.ELECTRICAL_ID,"Soldering Workshop",cd.ee.c4," Rs. 150 per team (max 2 candidates)","24th March","10:45 PM",R.drawable.soldering_workshop,"https://xenesis.ldrp.ac.in/events/soldering-workshop/");
        databaseHelper.addEvent("20","EE5",Constants.Department.ELECTRICAL_ID,"Wire-o-Tech",cd.ee.c5,"Rs 100","24th and 25th March","11:00 AM",R.drawable.wire_o_tech,"https://xenesis.ldrp.ac.in/events/wire-o-tech/");

        /*
        databaseHelper.addEvent("21","EE6",Constants.Department.ELECTRICAL_ID,"X-Drag-And-Drop",cd.ee.c6,"Rs 50 per person","1st April","01:00 PM","Akash Shah","7622800636",R.drawable.x_drag_and_drop);
        databaseHelper.addEvent("22","EE7",Constants.Department.ELECTRICAL_ID,"X-Mission-Impossible",cd.ee.c7,"Rs 20 per person","1st April","11:00 AM","Manthan Andharia","9979982870",R.drawable.x_mission_impossible);
        databaseHelper.addEvent("23","EE8",Constants.Department.ELECTRICAL_ID,"X-Technopictionary",cd.ee.c8,"Rs 25 per person","1st & 2nd April","12:00 PM","Suraj Pal","7878159001",R.drawable.x_technopictionary);
*/

      databaseHelper.addEvent("24","MA1",Constants.Department.MECHANICAL_ID,"X-Stream-Runner",cd.me.c1,"RS 200/Team + 100/extra participant","24th and 25th March","10:45 AM",R.drawable.x_stream_runner,"https://xenesis.ldrp.ac.in/events/x-stream-runner/");
        databaseHelper.addEvent("25","MA2",Constants.Department.MECHANICAL_ID,"X-Devil-Of-Death",cd.me.c2," RS 200/Team + 100/extra participant","24th and 25th March","10:45 AM",R.drawable.x_devil_of_death,"https://xenesis.ldrp.ac.in/events/x-devil-death/");
        databaseHelper.addEvent("26","MA3",Constants.Department.MECHANICAL_ID,"X-Aqua_Race",cd.me.c3,"RS 200/Team + 100/extra participant","24th and 25th March","10:45 AM",R.drawable.x_aqua_race,"https://xenesis.ldrp.ac.in/events/x-aqua-race/");
        databaseHelper.addEvent("27","MA4",Constants.Department.MECHANICAL_ID,"Robotics Workshop",cd.me.c4,"Rs.4000/-per team ","24th and 25th March","10:45 AM",R.drawable.robotics_workshop,"https://xenesis.ldrp.ac.in/events/robotics-workshop/");
        databaseHelper.addEvent("28","MA5",Constants.Department.MECHANICAL_ID,"X-Product-Design",cd.me.c5," Rs 100","24th and 25th March","10:45 AM",R.drawable.x_product_design,"https://xenesis.ldrp.ac.in/events/x-product-design/");
        databaseHelper.addEvent("29","MA6",Constants.Department.MECHANICAL_ID,"X Zoooom",cd.me.c6," Rs 100","24th and 25th March","10:45 AM",R.drawable.x_zoooom,"https://xenesis.ldrp.ac.in/events/x-zoooom/");
        //databaseHelper.addEvent("30","MA7",Constants.Department.MECHANICAL_ID,"X-Auto-Workshop",cd.me.c7,"Rs 150 per person","24th and 25th March","10:45 AM","","",R.drawable.x_auto_workshop);

        databaseHelper.addEvent("31","C1",Constants.Department.CIVIL_ID,"CIVIL–X–HUNT",cd.ci.c1,"Rs. 100/- Per Team","24th March","10:00 AM",R.drawable.civil_x_hunt,"https://xenesis.ldrp.ac.in/events/civil-x-hunt/");
        databaseHelper.addEvent("32","C2",Constants.Department.CIVIL_ID,"BRIDGE–X–Mania",cd.ci.c2,"Rs 100 per team","24th March"," 12:15 AM",R.drawable.bridge_x_mania,"https://xenesis.ldrp.ac.in/events/bridge-x-mania/");
        databaseHelper.addEvent("33","C3",Constants.Department.CIVIL_ID,"Build X",cd.ci.c3,"Rs 100 per team (2 to 3 players per team)","24th March"," 02:00 AM",R.drawable.x_build,"https://xenesis.ldrp.ac.in/events/build-x/");
        databaseHelper.addEvent("34","C4",Constants.Department.CIVIL_ID,"X Village",cd.ci.c4,"Rs 100 per team (2 to 3 players per team)","25th March"," 10:00 AM",R.drawable.x_village,"https://xenesis.ldrp.ac.in/events/x-village/");
        databaseHelper.addEvent("35","C5",Constants.Department.CIVIL_ID,"X-Paper-Structure",cd.ci.c5,"Rs 100 per team","25th March"," 12:00 PM",R.drawable.x_paper_structure,"https://xenesis.ldrp.ac.in/events/x-paper-structure/");
        databaseHelper.addEvent("36","C6",Constants.Department.CIVIL_ID,"X Quiz",cd.ci.c6,"Rs 100 per team","25th March"," 02:00 AM",R.drawable.x_quiz,"https://xenesis.ldrp.ac.in/events/x-quiz/");

        databaseHelper.addEvent("38","MCA1",Constants.Department.MCA_ID,"Stall Competition",cd.mca.c1," Rs 100 per person","24th March","10:00 Am-5:00 Pm",R.drawable.stall_competition,"https://xenesis.ldrp.ac.in/events/stall-competition/");
        databaseHelper.addEvent("39","MCA2",Constants.Department.MCA_ID,"Photography Competition",cd.mca.c2,"Rs 100 per person","25th March","11:00 am - 12:00 pm",R.drawable.photography_competetion,"https://xenesis.ldrp.ac.in/events/photography-competition/");
        databaseHelper.addEvent("40","MCA3",Constants.Department.MCA_ID,"Quiz Competition",cd.mca.c3," Rs 100 per person","25th March","9:00 am - 11:00 am",R.drawable.quiz_competition,"https://xenesis.ldrp.ac.in/events/quiz-competition/");

 /*       databaseHelper.addEvent("41","SH1",Constants.Department.SCIENCE_HUMANITY_ID,"X-Mathematica",cd.sc.c1,"Rs 20 per person","1st April"," 11:00 AM","","",R.drawable.x_mathematica);
    */}

    @Override
    public void onClick(View v) {
        Intent in = new Intent(this,DepartmentsEventsList.class);
        switch (v.getId()){
            case (R.id.llhome) :
                break;
            case (R.id.llrate):
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;
            case (R.id.llshare):
                String shareBody = "https://play.google.com/store/apps/details?id="+getPackageName();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
            case (R.id.llabout):
                startActivity(new Intent(this,About.class));
                finish();
                break;
            case(R.id.llCEIT):

                in.putExtra("id","1");
                in.putExtra("name","Computer Department");
                startActivity(in);
                break;
            case(R.id.llCivil):
                in.putExtra("id","2");
                in.putExtra("name","Civil Department");
                startActivity(in);
                break;
            case (R.id.llElectrical):
                in.putExtra("id","4");
                in.putExtra("name","Electrical Department");
                startActivity(in);
                break;
            case (R.id.llEC):
                in.putExtra("id","3");
                in.putExtra("name","Electronics Department");
                startActivity(in);
                break;
            case (R.id.llMechanical):
                in.putExtra("id","5");
                in.putExtra("name","Mechanical Department");
                startActivity(in);
                break;
            case (R.id.llMCA):
                in.putExtra("id","6");
                in.putExtra("name","MBA Department");
                startActivity(in);
        }
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            Bundle bundle =new Bundle();
            bundle.putInt("pos",pos);
            SlidePageFragment slidePageFragment = new SlidePageFragment();
            slidePageFragment.setArguments(bundle);
            return slidePageFragment;
            //return SlidePageFragment.newInstance(pos);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    public void onPause() {
        super.onPause();
        if (handler != null)
            handler.removeCallbacks(runnale);
    }

    public void onResume() {
        super.onResume();
        // Start auto screen slideshow after 1s
        handler.postDelayed(runnale, 1000);
    }
}
