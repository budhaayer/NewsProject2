package csitmnr.newsproject2.Fragments;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.kosalgeek.android.caching.FileCacher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import csitmnr.newsproject2.Adapter.NewsAdapter;
import csitmnr.newsproject2.ConnectionDetector;
import csitmnr.newsproject2.DatabaseHelper.DatabaseNews;
import csitmnr.newsproject2.NewsPojo;
import csitmnr.newsproject2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsAndEvents extends Fragment {
    NewsAdapter adapter;
    ArrayList<NewsPojo> arrayList = new ArrayList<>();
    NewsAdapter recycler;

    boolean isConn ;

    DatabaseNews databaseNews;
    public static final String urlNews = "http://alfabeta.bidheegroup.com/api/v1/news";

    RecyclerView recyclerView;
    List<NewsPojo> newslist = new ArrayList<>();

    ProgressDialog progressDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isConn = ConnectionDetector.isConnected(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_and_events, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewNews);

        databaseNews = new DatabaseNews(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Data is loading... ");
        progressDialog.setCancelable(false);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setProgress(0);

        if (isConn) {
            getNews();
        } else {

            showNews();

        }

        return view;
            ////get data from database and set to the reycler view ///

    }

    private void showNews() {

        newslist =  databaseNews.getAlldata();
        recycler =new NewsAdapter(getActivity(),newslist);

        Log.i("Mydata",""+newslist);

        recyclerView.setAdapter(recycler);



    }






    private void getNews() {
        progressDialog.show();
        StringRequest request = new StringRequest(urlNews, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.hide();
                try {
                    JSONObject object = new JSONObject(response);


                    JSONArray jsonArray = object.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        Integer id = obj.getInt("id");
                        String title = obj.getString("title");
                        String intro_text = obj.getString("intro_text");
                        Integer created_by_id = obj.getInt("created_by_id");
                        String featured_image = obj.getString("featured_image");
                        String detail = obj.getString("detail");
                        String created_at = obj.getString("created_at");
                        String updated_at = obj.getString("updated_at");


                        Boolean isSaved = databaseNews.putData(title, intro_text, detail, created_at, updated_at);

                        Log.e("isSaved", isSaved + "");


                        NewsPojo data = new NewsPojo(id, title, intro_text, created_by_id, featured_image, detail, created_at, updated_at);


                        newslist.add(data);


                    }
                } catch (JSONException e) {

                    e.printStackTrace();
                }


                NewsAdapter adapter = new NewsAdapter(getContext(), newslist);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);


    }

}
