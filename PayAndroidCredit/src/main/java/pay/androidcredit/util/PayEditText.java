package pay.androidcredit.util;

import android.content.Context;


public class PayEditText extends androidx.appcompat.widget.AppCompatEditText implements PayICardtypeChange {
    public PayEditText(Context context) {
        super(context);
    }

    @Override
    public String getValue() {
        return null;
    }

    @Override
    public String getInvalidMsg() {
        return null;
    }
}
