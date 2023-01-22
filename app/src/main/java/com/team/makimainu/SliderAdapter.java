package com.team.makimainu;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public int[] imagesArray = new int[]{
            R.drawable.png_who_are_we
            , R.drawable.ic_launcher_background
            , R.drawable.ic_launcher_background
            , R.drawable.ic_launcher_background};
    public String[] titleArray = {"Who are we"
            ,"What it can do"
            ,"What you get"
            ,"Done"};
    public String[] infoArray = {"asdfasdfasdf"
            ,"asdfasdfa"
            ,"asdfasdfasdfa"
            , "aasdfasdfasdf"};
    public int[] backgroundArray = {
            R.color.A
            , R.color.B
            , R.color.C
            , R.color.D};
    public SliderAdapter(Context context) {

        this.context = context;
    }


    @Override
    public int getCount() {
        return titleArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide,container,false);

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.linearlayout);
        ImageView imageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView txt_title = (TextView) view.findViewById(R.id.slide_title);
        TextView txt_detail = (TextView) view.findViewById(R.id.slide_detail);

        layout.setBackgroundColor(ContextCompat.getColor(context, backgroundArray[position]));
        Picasso.get().load(imagesArray[position]).resize(800, 800).into(imageView);
        txt_title.setText(titleArray[position]);
        txt_detail.setText(infoArray[position]);
        container.addView(view);

        return view;
    }

}
