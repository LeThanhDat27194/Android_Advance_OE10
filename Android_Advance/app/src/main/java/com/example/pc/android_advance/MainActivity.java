package com.example.pc.android_advance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {

    private final Integer image_ids[] = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<item> items = prepareData();
        MyAdapter adapter = new MyAdapter(getApplicationContext(), items);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<item> prepareData(){
        ArrayList<item> theimage = new ArrayList<>();
        for(int i = 0; i <  image_ids.length; i++){
            item item = new item();
            item.setImg(image_ids[i]);
            theimage.add(item);
        }
        return theimage;
    }
}
