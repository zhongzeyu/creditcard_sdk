package pay.androidcredit.widgets;

import android.annotation.TargetApi;
import android.content.Context;

import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class PayCard extends RelativeLayout {
    public PayCard(Context context) {
        super(context);
    }

    public PayCard(Context context, AttributeSet attrs) {
        super(context);
    }

    public PayCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PayCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context);
    }


}
