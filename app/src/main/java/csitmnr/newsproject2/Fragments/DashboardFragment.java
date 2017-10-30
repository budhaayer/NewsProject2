package csitmnr.newsproject2.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import csitmnr.newsproject2.Adapter.MyAdapter;
import csitmnr.newsproject2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {


    public DashboardFragment() {
//         Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        final TabLayout tabLayout = view.findViewById(R.id.tab_layout);


        final ViewPager viewPager = view.findViewById(R.id.viewpager);


        MyAdapter adapter = new MyAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return  view;
}





}
