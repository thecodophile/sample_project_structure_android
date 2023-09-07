package app.sample.utils.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

import java.io.File;

public class GlideImageView extends AppCompatImageView {

    public GlideImageView(Context context) {
        this(context, null);
    }

    public GlideImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GlideImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GlideImageView, defStyle, 0);
//
//        try {
//            String imageUrl = a.getString(R.styleable.GlideImageView_imageUrl);
//
//            if (!isInEditMode())
//                setImageUrl(imageUrl);
//
//        } finally {
//            a.recycle();
//        }
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl == null)
            return;

        Glide.with(getContext())
                .load(imageUrl)
//                .placeholder(R.drawable.profile_default)
                .into(this);
    }

    public void setImageUrlNoPlaceholder(String imageUrl) {
        if (imageUrl == null)
            return;
        Glide.with(getContext())
                .load(imageUrl)
                .into(this);
    }

    public void setImageFile(File file) {
        Glide.with(getContext())
                .load(file)
//                .placeholder(R.drawable.profile_default)
                .into(this);
    }

    public void setDrawable(Drawable drawable){
        Glide.with(getContext())
                .load(drawable)
                .into(this);
    }

}
