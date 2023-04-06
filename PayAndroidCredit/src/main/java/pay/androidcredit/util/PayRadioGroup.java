package pay.androidcredit.util;

import android.content.Context;
import android.widget.RadioGroup;


public class PayRadioGroup extends RadioGroup implements PayICardtypeChange {
    public PayRadioGroup(Context context) {
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
