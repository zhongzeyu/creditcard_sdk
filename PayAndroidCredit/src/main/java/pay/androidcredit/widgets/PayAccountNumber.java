package pay.androidcredit.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;

import pay.androidcredit.R;


public class PayAccountNumber extends pay.androidcredit.util.PayEditText {

    public PayAccountNumber(Context context) {
        super(context);
        init();
    }

    public PayAccountNumber(Context context, AttributeSet attrs) {
        super(context);
        init();
    }

    public PayAccountNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PayAccountNumber(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context);
        init();
    }

    private void init() {
        setHint(R.string.account_default);

        // Adding the TextWatcher
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int position, int before, int action) {
                if (action == 1) {
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        // The input filters
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                return null;
            }
        };
        // Setting the filters
        setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(12)});
    }



    public String getValue() {
        return getText().toString();//.replace("-", "").trim();
    }


    @Override
    public String getInvalidMsg() {
        String value = getValue();
        if(value != null && value.length() > 12){

            return getResources().getString(R.string.account_invalid);
        }
        return null;
    }
}