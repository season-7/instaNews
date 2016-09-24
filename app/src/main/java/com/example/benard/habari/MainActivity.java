package com.example.benard.habari;

import android.app.DownloadManager;
import android.service.voice.VoiceInteractionSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static com.example.benard.habari.R.layout.*;
import static com.example.benard.habari.R.layout.activity_news_item;

public class MainActivity extends AppCompatActivity {
    private List<newsItem> newsFeed = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
//        Button button=(Button)findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
//                StringRequest myReq= new StringRequest(Request.Method.GET
//                        ,"https://www.google.com",new Response.Listener<String>(){
//                    @Override
//                    public void onResponse(String response) {
//                        Log.i("Mytag","Response,:"+response);
//
//                    }
//                },new Response.ErrorListener(){
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.i("Mytag","Error,:"+error);
//
//                    }
//                });
//                queue.add(myReq);
//
//            }
//        });
        JsonObjectRequest myreq= new JsonObjectRequest(Request.Method.GET, "http/10.0.3.1/news.php", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray newsItems = response.getJSONArray("newsArray");


                    for (int i = 0; i <= newsItems.length(); i++) {
                        JSONObject temp = newsItems.getJSONObject(i);
                        String image = temp.getString("image");
                        String title = temp.getString("title");
                        String time = temp.getString("time");
                        String date = temp.getString("date=");
                        String content = temp.getString("content");
                        String link = temp.getString("link");
                        //String image=temp.getString("image");
                        String imageurl = temp.getString("imageurl");
                        newsFeed.add(new newsItem(title, content, date, time, link, imageurl));

                    }
                } catch (JSONException e) {
                   // e.printStackTrace();
                    Log.i("Mytag",e.toString());
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("myTag",error.toString());


            }

        });
        queue.add(myreq);


        //ArrayAdapter<newsItem> adapter = new customAdapter();
        ArrayAdapter adapter = new customAdapter();
        ListView newsItem = (ListView) findViewById(R.id.newsItem);
        newsItem.setAdapter(adapter);
//        newsItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClickL(AdapterView<?> Parent, View view, int position, int id) {
//                Toast.makeText(MainActivity.this, "My List View ", Toast.LENGTH_SHORT).show();
//
//            }
//
//
//        });
        newsItem.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "My List View ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private class customAdapter extends ArrayAdapter<newsItem>{
        public customAdapter(){
            super(MainActivity.this,R.layout.activity_news_item,newsFeed);
        }



//    @Override
//     public View getView() {
//            return getView(, , );
//        }

    @Override
     public View getView(int position, View convertview, ViewGroup parent){
            if (convertview==null){
                convertview=getLayoutInflater().inflate(activity_news_item,parent,false);

            }
            newsItem currentItem=newsFeed.get(position);
            ImageView newsImage=(ImageView)convertview.findViewById(R.id.lefticon);
            TextView heading =(TextView) convertview.findViewById(R.id.heading);
            TextView desc =(TextView) convertview.findViewById(R.id.desc);

            heading.setText(currentItem.getNewsHeading());
            desc.setText(currentItem.getNewsDesc());
            newsImage.setImageResource(R.mipmap.ic_launcher);


            return  convertview;

        }



    }


}
