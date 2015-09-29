package comapps.stansbluenote.app;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {

            // default style
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                    "fonts/Marker Felt.ttf");

            setTypeface(tf);
        }
    }
}