package com.project.nikhil.predicto;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by nikhil on 9/8/17.
 */

public class adapter_slider extends PagerAdapter {
    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public adapter_slider(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View myImageLayout = inflater.inflate(R.layout.slider, container, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.sider_image);
        myImage.setImageResource(images.get(position));
        container.addView(myImageLayout, 0);
        return myImageLayout;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
