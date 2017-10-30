package csitmnr.newsproject2.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import csitmnr.newsproject2.Fragments.AgricultureNews;
import csitmnr.newsproject2.Fragments.NewsAndEvents;
import csitmnr.newsproject2.Fragments.Sports;
import csitmnr.newsproject2.Fragments.Videos;

/**
 * Created by Manoj Budha Ayer on 10/11/2017.
 */

public class MyAdapter extends FragmentStatePagerAdapter {
    int noOfTabs=4;

    public MyAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                NewsAndEvents tab1 = new NewsAndEvents();
                return tab1;
            case 1:
                Sports tab2 = new Sports();
                return tab2;
            case 2:
                AgricultureNews tab3 = new AgricultureNews();
                return tab3;
            case 3:
                Videos tab4 = new Videos();
                return tab4;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return noOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                String news_and_events = "News and Events";
                return news_and_events;
            case 1:
                String sports = "Sports";
                return sports;
            case 2:
                String agriculture_news = "Agriculture News";
                return agriculture_news;
            case 3:
                String videos = "Videos";
                return videos;
        }
        return null;
    }
}
