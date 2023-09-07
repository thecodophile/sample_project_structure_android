package app.sample.utils.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatRadioButton;

import app.sample.R;


public class CustomCheckBox extends AppCompatRadioButton {
    String customFont;
    public CustomCheckBox(Context context) {
        super(context);
    }



    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomRadioButton);
        int cf = a.getInteger(R.styleable.CustomButton_fontName, 0);
        int fontName = 0;
        switch (cf) {
            case 2:
                fontName = R.string.roboto_Medium;
                break;
            case 3:
                fontName = R.string.roboto_Bold;
                break;
            default:
                fontName = R.string.roboto;
                break;
        }

        customFont = getResources().getString(fontName);

        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/" + customFont + ".ttf");
        setTypeface(tf);
        a.recycle();
    }

    @Override
    public void setEnabled(boolean enabled) {
        setAlpha(enabled ? 1 : 0.5f);
        super.setEnabled(enabled);
    }
}
