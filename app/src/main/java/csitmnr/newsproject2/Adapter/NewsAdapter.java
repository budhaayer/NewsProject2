package csitmnr.newsproject2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kosalgeek.android.caching.FileCacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import csitmnr.newsproject2.DatabaseHelper.DatabaseNews;
import csitmnr.newsproject2.NewsAndEventDataShow;
import csitmnr.newsproject2.NewsPojo;
import csitmnr.newsproject2.R;

import static android.R.id.list;

/**
 * Created by Manoj Budha Ayer on 10/12/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    Context context;
    List<NewsPojo> listitem;



    public NewsAdapter(Context context, List<NewsPojo> listitem) {
        this.context = context;
        this.listitem = listitem;

    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_list,parent,false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        DatabaseNews databaseNews = new DatabaseNews(context);

        NewsPojo data = listitem.get(position);

        final String image = data.getFeaturedImage();
        final String title = data.getTitle();
        final String intro_text = data.getIntroText();
        final String detail = data.getDetail();
        final String updated_at = data.getUpdatedAt();
        final String created_at = data.getCreatedAt();


        Glide
                .with(context)
                .load(image)
                .into(holder.imageView);

        holder.title.setText(title);
        holder.intro_text.setText(intro_text);
        holder.detail.setText(detail);
        holder.updated_at.setText(updated_at);
        holder.created_at.setText(created_at);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,NewsAndEventDataShow.class);

                intent.putExtra("Image",image);
                intent.putExtra("Title",title);
                intent.putExtra("IntroText",intro_text);
                intent.putExtra("Detail",detail);
                intent.putExtra("UpdatedAt",updated_at);
                intent.putExtra("CreatedAt",created_at);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title,intro_text,detail,created_at,updated_at;
        ImageView imageView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            intro_text = itemView.findViewById(R.id.news_intro_text);
            detail = itemView.findViewById(R.id.news_detail);
            created_at = itemView.findViewById(R.id.news_created_at);
            updated_at = itemView.findViewById(R.id.news_updated_at);
            imageView = itemView.findViewById(R.id.news_image);

        }
    }
}
