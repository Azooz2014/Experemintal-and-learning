package io.blacketron.imageslider;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Blacketron on 9/25/2017.
 */

public class ImagePagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mInflater;
    int[] mImages;

    /*Constructor that receives context from MainActivity as well as the int array for the images
    * and to initialize the member variables with them.*/
    public ImagePagerAdapter(Context context, int[] images){

        this.mContext = context;
        this.mImages = images;
    }

    @Override
    public int getCount() {

        return mImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView;

        //Initialize mInflater.
        mInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        //Declaring mInflater using new View.
        View viewItem = mInflater.inflate(R.layout.pager_item, container, false);

        //Get a reference to ImageView in pager_item.xml.
        imageView = viewItem.findViewById(R.id.imageView);

        //Set an image to the imageView.
        imageView.setImageResource(mImages[position]);

        //Add pager_item.xml as the current page to the ViewPager.
        ((ViewPager)container).addView(viewItem);

        return viewItem;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        //Remove pager_item.xml from ViewPager.
        ((ViewPager) container).removeView((ConstraintLayout) object);
    }
}
