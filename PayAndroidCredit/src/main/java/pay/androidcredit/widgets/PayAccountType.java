package pay.androidcredit.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;

import pay.androidcredit.R;
import pay.androidcredit.util.PayRadioGroup;


public class PayAccountType extends PayRadioGroup {

    public PayAccountType(Context context) {
        super(context);
        init();
    }

    public PayAccountType(Context context, AttributeSet attrs) {
        super(context);
        init();
    }

    public PayAccountType(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PayAccountType(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context);
        init();
    }

    private void init() {
        //setText(R.string.checking_default);

        //setHint(R.string.checking_default);


        // Adding the TextWatcher
        /*addTextChangedListener(new TextWatcher() {
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
        });*/
        // The input filters
        /*InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                return null;
            }
        };
        // Setting the filters
        setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(12)});*/
    }



    public String getValue() {

        int radioId = getCheckedRadioButtonId();
        return (radioId == 1)?"C":"V";
    }


    @Override
    public String getInvalidMsg() {
        String value = getValue();
        if(value != null && value.length() > 1){

            return getResources().getString(R.string.accounttype_invalid);
        }
        return null;
    }
}
