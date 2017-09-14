package raj.saraogi.com.xentest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlidePageFragment extends Fragment {
    ImageView imBanner;
    static int page_pos=0;
    int [] bannerlist={R.drawable.bannermain,R.drawable.a1,R.drawable.a2,R.drawable.a3};
    public SlidePageFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_slide_page, container, false);
        Bundle b=getArguments();
        page_pos=b.getInt("pos");
        imBanner=(ImageView)v.findViewById(R.id.imBanner);
        imBanner.setImageDrawable(getResources().getDrawable(bannerlist[page_pos]));

        return v;
    }

}
