package pay.androidcredit.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import pay.androidcredit.R;

public class PayEditCard extends pay.androidcredit.util.PayEditText {
    String type = "UNKNOWN";
    String delimeter = " ";
    private static List<pay.androidcredit.util.PayEditText> PayEditTextList;
    public PayEditCard(Context context) {
        super(context);
        init();
    }
    public static void RegisterpayEditTexts(pay.androidcredit.util.PayEditText PayEditText){
        if(PayEditTextList == null){
            PayEditTextList = new ArrayList<pay.androidcredit.util.PayEditText>();
        }
        if(PayEditTextList.contains(PayEditText)){
            return;
        }
        PayEditTextList.add(PayEditText);
    }

    public PayEditCard(Context context, AttributeSet attrs) {
        super(context);
        init();
    }

    public PayEditCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PayEditCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context);
        init();
    }

    private void init() {
        changeIcon();
        // Adding the TextWatcher
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int position, int before, int action) {
                if (action == 1) {
                    if (type.equals(pay.androidcredit.util.PayConstants.AMERICAN_EXPRESS.label+"") || type.equals(pay.androidcredit.util.PayConstants.DINER_ClUB.label+"")) {
                        if (position == 3 || position == 10) {
                            if (!s.toString().endsWith(delimeter)) {
                                append(delimeter);
                            }
                        }else if (position != 4 & position != 11){
                            if ((s.charAt(position)+"").equals(delimeter)) {
                                setText(s.subSequence(0,position).toString() + s.subSequence(position + 1,s.length()));
                            }
                        }
                    }else{
                        if (position == 3 || position == 8 || position == 13) {
                            if (!s.toString().endsWith(delimeter)) {
                                append(delimeter);
                            }
                        }else if (position != 4 && position != 9 && position != 14) {
                            if ((s.charAt(position)+"").equals(delimeter)) {
                                setText(s.subSequence(0,position).toString() + s.subSequence(position + 1,s.length()));
                            }
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                changeIcon();
            }
        });
        // The input filters
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; ++i) {
                    if (!Pattern.compile("[0-9\\s]*").matcher(String.valueOf(source)).matches()) {
                        return "";
                    }
                }

                return null;
            }
        };
        // Setting the filters
        setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(29)});
    }

    private void changeIcon() {
        String s = getText().toString().replace(delimeter, "").trim();
        pay.androidcredit.pojo.PayCreditCard PayCreditCard = pay.androidcredit.util.PayUtilCommon.getCardByCardNumber(s);
        if(PayCreditCard == null){
            PayCreditCard = new pay.androidcredit.pojo.PayCreditCard();
        }
        type = PayCreditCard.code;
        setCompoundDrawablesWithIntrinsicBounds(0, 0, PayCreditCard.IconID, 0);

        pay.androidcredit.util.PayUtilCommon.currentCreditCard = PayCreditCard;
    }

    public String getValue() {
        return getText().toString().replace(delimeter, "").trim();
    }

    @Override
    public String getInvalidMsg() {
        String value = getValue();
        if(value != null && value.length() > 0 && value.length() < 9){
            return getResources().getString(R.string.cardnumber_invalid);
        }
        return null;
    }

}
