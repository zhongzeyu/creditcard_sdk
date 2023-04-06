package pay.androidcredit.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class PayCardShadow extends RelativeLayout {
    public PayCardShadow(Context context) {
        super(context);
    }

    public PayCardShadow(Context context, AttributeSet attrs) {
        super(context);
    }

    public PayCardShadow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PayCardShadow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context);
    }


}
