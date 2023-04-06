package pay.androidcredit.widgets;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;

import android.graphics.Rect;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import pay.androidcredit.R;
import pay.androidcredit.util.PayConstants;
import pay.androidcredit.util.PayEditText;
import pay.androidcredit.util.PayRadioGroup;

public class CardInfoView extends ViewGroup {
    private int layoutStyle = (int) pay.androidcredit.util.PayConstants.TripleLine.label;
    private int payType = (int) pay.androidcredit.util.PayConstants.Card.label;
    private int cardWidth;
    private int cardHeight,cardHeightReal;
    private int cardMMYYWidth;
    private int cardMinUnitWidth = 36;
    private int cardCVCWidth;
    private int cardZipWidth;
    private int cardNumWidth;
    private int cardItemHeight = 60;
    private int holderWidth;
    private Integer screenWidth = null;
    private final int shadowThick = 3;
    private String focusedClassName = pay.androidcredit.util.PayConstants.PayEditCard.label + "";
    PayCVC etCVV;
    PayHolder etHolder;
    PayEditCard etEditCard;
    PayCard PayCard;
    PayCardShadow PayCardShadow;
    PayMMYY etMMYY;
    PayZip etZip;
    PayRoutingNumber etRouting;
    PayAccountNumber etAccount;
    PayAccountType etPayAccountType;
    private TextView magnetlabel, holderlabel, cvclabel, ziplabel, routingLabel, accountLabel;
    private ImageView cardImage,cardImageShadow;
    private RadioGroup payaccounttypeLabel;
    private int routingWidth;
    private int accountWidth;
    private int paytypeWidth;

    public CardInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defPayTypeAttr) {
        super(context, attrs, defStyleAttr);
        this.layoutStyle = defStyleAttr;
        this.payType = defPayTypeAttr;
        init();
    }
    public CardInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CardInfoView(Context context) {
        super(context);
        init();
    }
    private int getCardNumWidthDefault(){
        return cardWidth - pay.androidcredit.util.PayUtilCommon.CreditMargin * 2;
    }
    private int getCardNumWidthMax(){return cardMinUnitWidth * 12;}
    private int getCardNumWidthMin(){
        return cardMinUnitWidth * 6;
    }
    private int getHolderWidthDefault(){
        return cardWidth - pay.androidcredit.util.PayUtilCommon.CreditMargin * 2;
    }

    private int getHolderWidthMax(){
        return cardMinUnitWidth * 5;
    }
    private int getHolderWidthMin(){
        return cardMinUnitWidth * 2 + 10;
    }
    private int getCVCWidthDefault(){
        return cardMinUnitWidth * 3;
    }
    private int getCVCWidthMax(){
        return cardMinUnitWidth * 3;
    }
    private int getCVCWidthMin(){
        return cardMinUnitWidth * 3;
    }
    private int getMMYYWidthDefault(){
        return cardMinUnitWidth * 5;
    }
    private int getMMYYWidthMax(){
        return cardMinUnitWidth * 5;
    }
    private int getMMYYWidthMin(){
        return cardMinUnitWidth * 3;
    }
    private int getZipWidthDefault(){
        return cardMinUnitWidth * 4;
    }
    private int getZipWidthMax(){
        return cardMinUnitWidth * 4;
    }
    private int getZipWidthMin(){
        return cardMinUnitWidth * 3;
    }
    private int getRoutingWidthDefault(){
        return cardMinUnitWidth * 7;
    }
    private int getRoutingWidthMax(){
        return cardMinUnitWidth * 5;
    }
    private int getRoutingWidthMin(){
        return cardMinUnitWidth * 4;
    }
    private int getAccountWidthDefault(){
        return cardMinUnitWidth * 10;
    }
    private int getAccountWidthMax(){
        return cardMinUnitWidth * 6;
    }
    private int getAccountWidthMin(){
        return cardMinUnitWidth * 4;
    }
    private int getAccountTypeWidthDefault(){
        return cardMinUnitWidth * 10;
    }
    private int getAccountTypeWidthMax(){
        return cardMinUnitWidth * 14;
    }
    private int getAccountTypeWidthMin(){
        return cardMinUnitWidth * 8;
    }

    private Rect getLocation(String className){
        if(className == null){
            return null;
        }
        if(className.indexOf(".") > 0){
            className = className.substring(className.lastIndexOf(".") + 1);
        }
        //System.out.println("===========  className is " + className);

        int x=0, y=0, right = 0;

        if(className.equals(pay.androidcredit.util.PayConstants.PayCard.label+"")){
            x = pay.androidcredit.util.PayUtilCommon.CreditMargin;
            y = 0;
            right = x + cardWidth;
            return new Rect(x,y,right ,cardHeightReal);
        }else if(className.equals(pay.androidcredit.util.PayConstants.PayCardShadow.label+"")){
            x = pay.androidcredit.util.PayUtilCommon.CreditMargin + shadowThick;
            y = shadowThick;
            right = x + cardWidth + shadowThick;
            return new Rect(x,y,right ,cardHeightReal + shadowThick);
        }else if(className.equals(pay.androidcredit.util.PayConstants.PayEditCard.label+"")){
            if(isSingle()){
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin -20;
                y = pay.androidcredit.util.PayUtilCommon.CreditMargin;
            }else{
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin * 2;
                y = cardHeight / 3 - cardItemHeight - pay.androidcredit.util.PayUtilCommon.CreditMargin;
            }
            right = x + cardNumWidth;
            magnetlabel.setY(y - pay.androidcredit.util.PayUtilCommon.CreditMargin / 4);
        }else if(className.equals(pay.androidcredit.util.PayConstants.PayHolder.label+"")){
            if(isSingle()){
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin + (isCard()?getCardNumWidthMin():getAccountTypeWidthMin());
                y = pay.androidcredit.util.PayUtilCommon.CreditMargin;
            }else{
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin * 2;
                y = cardHeight * 2 / 3 - cardItemHeight - pay.androidcredit.util.PayUtilCommon.CreditMargin * 2 ;
            }
            right = x + holderWidth;
            holderlabel.setX(x - pay.androidcredit.util.PayUtilCommon.CreditMargin);
            holderlabel.setY(y - pay.androidcredit.util.PayUtilCommon.CreditMargin / 4);
            cvclabel.setY(y - pay.androidcredit.util.PayUtilCommon.CreditMargin / 4);

            //ziplabel.setY(y - pay.androidcredit.util.PayUtilCommon.CreditMargin / 4);
        }else if(className.equals(pay.androidcredit.util.PayConstants.PayMMYY.label+"")){
            if(isSingle()){
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin + getCardNumWidthMin() + getHolderWidthMin()  ;
                y = pay.androidcredit.util.PayUtilCommon.CreditMargin;
            }else{
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin * 2;
                y = cardHeight - cardItemHeight - pay.androidcredit.util.PayUtilCommon.CreditMargin * 3;
            }
            right = x + cardMMYYWidth;
        }else if(className.equals(pay.androidcredit.util.PayConstants.PayZip.label+"")){
            if(isSingle()){
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin + getCardNumWidthMin() + getHolderWidthMin()  + getMMYYWidthMin() ;
                y = pay.androidcredit.util.PayUtilCommon.CreditMargin;
            }else{
                x = x = cardWidth - getCVCWidthDefault() - getZipWidthMin() -50;
                y = cardHeight - cardItemHeight - pay.androidcredit.util.PayUtilCommon.CreditMargin * 3;
            }
            right = x + cardZipWidth;
        }else if(className.equals(pay.androidcredit.util.PayConstants.PayCVC.label+"")){
            if(isSingle()){
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin + getCardNumWidthMin() + getHolderWidthMin()  + getMMYYWidthMin()  + getZipWidthMin();
                y = pay.androidcredit.util.PayUtilCommon.CreditMargin;
            }else{
                x = cardWidth - getCVCWidthDefault();
                y = cardHeight - cardItemHeight - pay.androidcredit.util.PayUtilCommon.CreditMargin * 3;
            }
            right = x + cardCVCWidth;
        }else if(className.equals(PayConstants.PayRounting.label+"")){
            if(isSingle()){
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin + getAccountTypeWidthMin() + getHolderWidthMin() ;
                y = pay.androidcredit.util.PayUtilCommon.CreditMargin;
            }else{
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin * 2;
                y = cardHeight - cardItemHeight*2 ;
            }
            right = x + routingWidth;

        }else if(className.equals(PayConstants.PayAccount.label+"")){
            if(isSingle()){
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin + getAccountTypeWidthMin()  + getHolderWidthMin() + getRoutingWidthMin();
                y = pay.androidcredit.util.PayUtilCommon.CreditMargin;
            }else{
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin * 3 + routingWidth - 10;
                y = cardHeight - cardItemHeight*2 ;
            }
            right = x + accountWidth;
            accountLabel.setX(x - pay.androidcredit.util.PayUtilCommon.CreditMargin);
            accountLabel.setY(y - pay.androidcredit.util.PayUtilCommon.CreditMargin / 4);
        }else if(className.equals(PayConstants.PayAccountType.label+"")){
            if(isSingle()){
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin + routingWidth + accountWidth ;
                y = pay.androidcredit.util.PayUtilCommon.CreditMargin;
            }else{
                x = pay.androidcredit.util.PayUtilCommon.CreditMargin * 2;
                y = cardHeight - cardItemHeight - pay.androidcredit.util.PayUtilCommon.CreditMargin;
            }
            right = x + paytypeWidth;

        }
        return new Rect(x,y,right ,y + cardItemHeight);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        drawItems();
    }
    private void drawItems(){
        setSizes();
        final int count = getChildCount();
        //set every  child view's position
        int index = 0;
        for(int i = 0; i < count; i++){
            final View child = getChildAt(index++);
            if(child.getVisibility() != GONE){
                String className = child.getClass().getName();

                Rect itemLocation = getLocation(className);
                child.layout(itemLocation.left, itemLocation.top, itemLocation.right, itemLocation.bottom);
            }
        }
    }
    private void setSizes(){
        if(screenWidth == null){
            DisplayMetrics dm = new DisplayMetrics();
            Activity activity = (Activity) getContext();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            screenWidth = dm.widthPixels;
        }
        cardWidth = screenWidth - pay.androidcredit.util.PayUtilCommon.CreditMargin * 2;
        cardMinUnitWidth = cardWidth/ 18;
        cardHeight = cardWidth * 3 / 5;
        cardItemHeight = cardWidth /12;
        cardNumWidth = getCardNumWidthDefault();
        holderWidth = getHolderWidthDefault();
        cardMMYYWidth = getMMYYWidthDefault();
        cardCVCWidth = getCVCWidthDefault();
        cardZipWidth = getZipWidthDefault();
        cardHeightReal = cardHeight;
        routingWidth = getRoutingWidthDefault();
        accountWidth = getAccountWidthDefault();
        paytypeWidth = getAccountTypeWidthDefault();
        if(isSingle()){
            cardHeightReal = cardItemHeight + pay.androidcredit.util.PayUtilCommon.CreditMargin * 2;

            if(focusedClassName.equals(pay.androidcredit.util.PayConstants.PayEditCard.label+"")){
                cardNumWidth = getCardNumWidthMax();
            }else{
                cardNumWidth = getCardNumWidthMin();
            }

            if(focusedClassName.equals(pay.androidcredit.util.PayConstants.PayHolder.label+"")){
                holderWidth = getHolderWidthMax();
            }else{
                holderWidth = getHolderWidthMin();
            }

            if(focusedClassName.equals(pay.androidcredit.util.PayConstants.PayCVC.label+"")){
                cardCVCWidth = getCVCWidthMax();
            }else{
                cardCVCWidth = getCVCWidthMin();
            }
            if(focusedClassName.equals(pay.androidcredit.util.PayConstants.PayZip.label+"")){
                cardZipWidth = getZipWidthMax();
            }else{
                cardZipWidth = getZipWidthMin();
            }

            if(focusedClassName.equals(pay.androidcredit.util.PayConstants.PayMMYY.label+"")){
                cardMMYYWidth = getMMYYWidthMax();
            }else{
                cardMMYYWidth = getMMYYWidthMin();
            }
            if(focusedClassName.equals(PayConstants.PayRounting.label+"")){
                routingWidth = getRoutingWidthMax();
            }else{
                routingWidth = getRoutingWidthMin();
            }
            if(focusedClassName.equals(PayConstants.PayAccount.label+"")){
                accountWidth = getAccountWidthMax();
            }else{
                accountWidth = getAccountWidthMin();
            }
            if(focusedClassName.equals(PayConstants.PayAccountType.label+"")){
                paytypeWidth = getAccountTypeWidthMax();
            }else{
                paytypeWidth = getAccountTypeWidthMin();
            }
        }
    }
    private boolean isSingle(){
        return layoutStyle == (int) pay.androidcredit.util.PayConstants.SingleLine.label;
    }
    private boolean isCard(){
        return payType == (int) pay.androidcredit.util.PayConstants.Card.label;
    }
    private void displayBackLabels(boolean show){
        if(isCard()){
            if(show){
                if(magnetlabel != null){
                    if(magnetlabel.getVisibility() == VISIBLE){
                        return;
                    }
                    magnetlabel.setVisibility(VISIBLE);
                }
                if(holderlabel != null)holderlabel.setVisibility(VISIBLE);
                if(cvclabel != null)cvclabel.setVisibility(VISIBLE);
                if(ziplabel != null)ziplabel.setVisibility(INVISIBLE);
                cardImage.setImageResource(R.drawable.paycard_gradient_back);
            }else{
                if(magnetlabel != null){
                    if(magnetlabel.getVisibility() == INVISIBLE){
                        return;
                    }
                    magnetlabel.setVisibility(INVISIBLE);
                }
                if(holderlabel != null)holderlabel.setVisibility(INVISIBLE);
                if(cvclabel != null)cvclabel.setVisibility(INVISIBLE);
                if(ziplabel != null)ziplabel.setVisibility(INVISIBLE);
                cardImage.setImageResource(R.drawable.paycard_gradient);
            }
            ObjectAnimator mAnimatorA1 = ObjectAnimator.ofFloat(cardImage, View.ROTATION_Y, 0, 180).setDuration(500);

            mAnimatorA1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    cardImageShadow.setVisibility(INVISIBLE);
                }
                @Override
                public void onAnimationEnd(Animator animation) {
                    cardImageShadow.setVisibility(VISIBLE);
                }
            });
            mAnimatorA1.start();
        }

    }


    private void init(){
        setSizes();
        {
            PayCardShadow etCardItem = (PayCardShadow) inflate(getContext(), R.layout.paycard_shadow,null);
            PayCardShadow = etCardItem;
            LayoutParams params = new LayoutParams(cardWidth, cardHeightReal);
            PayCardShadow.setLayoutParams(params);
            addView(etCardItem);
        }
        {
            PayCard etCardItem = (PayCard) inflate(getContext(), R.layout.paycard,null);
            PayCard = etCardItem;
            LayoutParams params = new LayoutParams(cardWidth, cardHeightReal);
            PayCard.setLayoutParams(params);
            addView(etCardItem);
        }
        PayEditCard cardNumItem;
        {
            PayEditCard etCardItem = (PayEditCard) inflate(getContext(), R.layout.paycard_num,null);
            setCommonAttr(etCardItem);
            etEditCard = etCardItem;

            etCardItem.setInputType(InputType.TYPE_CLASS_PHONE);
            etCardItem.setTextColor(getResources().getColor(R.color.white));
            etCardItem.setHintTextColor(getResources().getColor(R.color.white));
            LayoutParams params = new LayoutParams(cardNumWidth, cardItemHeight);
            etCardItem.setLayoutParams(params);

            addView(etCardItem);
            setEtItemListener(etCardItem);
            cardNumItem = etCardItem;

        }

        {
            PayHolder etCardItem = (PayHolder) inflate(getContext(), R.layout.payholder,null);
            setCommonAttr(etCardItem);
            etHolder = etCardItem;

            LayoutParams params = new LayoutParams(holderWidth, cardItemHeight);
            etCardItem.setLayoutParams(params);
            addView(etCardItem);
            setEtItemListener(etCardItem);

        }

        {
            PayMMYY etCardItem = (PayMMYY) inflate(getContext(), R.layout.paymmyy,null);
            setCommonAttr(etCardItem);
            etMMYY = etCardItem;
            etCardItem.setHint( R.string.mmyy_default);
            etCardItem.setInputType(InputType.TYPE_CLASS_PHONE);

            LayoutParams params = new LayoutParams(cardMMYYWidth, cardItemHeight);
            etCardItem.setLayoutParams(params);
            addView(etCardItem);

            setEtItemListener(etCardItem);
        }

        {
            PayCVC etCardItem = (PayCVC) inflate(getContext(), R.layout.paycvc,null);
            setCommonAttr(etCardItem);
            etCVV = etCardItem;

            etCardItem.setInputType(InputType.TYPE_CLASS_NUMBER);

            LayoutParams params = new LayoutParams(cardCVCWidth, cardItemHeight);
            etCardItem.setLayoutParams(params);
            addView(etCardItem);
            setEtItemListener(etCardItem);
        }

        {
            PayZip etCardItem = (PayZip) inflate(getContext(), R.layout.payzip,null);
            setCommonAttr(etCardItem);
            etZip = etCardItem;
            etCardItem.setHint( R.string.zip_default);
            etCardItem.setInputType(InputType.TYPE_CLASS_TEXT);

            LayoutParams params = new LayoutParams(cardZipWidth, cardItemHeight);
            etCardItem.setLayoutParams(params);
            addView(etCardItem);

            setEtItemListener(etCardItem);
        }
        {
            PayRoutingNumber etRoutingNumber = (PayRoutingNumber) inflate(getContext(), R.layout.payroutingnumber,null);
            setCommonAttr(etRoutingNumber);
            etRouting = etRoutingNumber;
            etRouting.setHint( R.string.rounting_default);
            etRouting.setInputType(InputType.TYPE_CLASS_NUMBER);

            LayoutParams params = new LayoutParams(routingWidth, cardItemHeight);
            etRouting.setLayoutParams(params);
            addView(etRouting);

            setEtItemListener(etRouting);

        }
        {
            PayAccountNumber etAccountNumber = (PayAccountNumber) inflate(getContext(), R.layout.payaccountnumber,null);
            setCommonAttr(etAccountNumber);
            etAccount = etAccountNumber;
            etAccount.setHint( R.string.account_default);
            etAccount.setInputType(InputType.TYPE_CLASS_TEXT);

            LayoutParams params = new LayoutParams(accountWidth, cardItemHeight);
            etAccount.setLayoutParams(params);
            addView(etAccount);

            setEtItemListener(etAccount);
        }
        {
            etPayAccountType = (PayAccountType) inflate(getContext(), R.layout.payaccounttype,null);



            LayoutParams params = new LayoutParams(paytypeWidth, cardItemHeight);
            etPayAccountType.setLayoutParams(params);
            addView(etPayAccountType);



        }

        magnetlabel = findViewById(R.id.magnetlabel);
        holderlabel = findViewById(R.id.namelabel);
        cvclabel = findViewById(R.id.cvclabel);
        cardImage = findViewById(R.id.cardImage);
        cardImageShadow = findViewById(R.id.cardImageShadow);
        ziplabel = findViewById(R.id.ziplabel);
        routingLabel = findViewById(R.id.routinglabel);
        accountLabel = findViewById(R.id.accountlabel);
        payaccounttypeLabel = findViewById(R.id.radioGroup);

        setHint();
        setVisible(true);
        cardNumItem.requestFocus();

    }
    private void setHint(){
        if(isSingle()){
            etCVV.setHint( R.string.cvc);
            etHolder.setHint( R.string.holder_name);
            etEditCard.setHint( R.string.card_num);
            etZip.setHint( R.string.zip);
            etRouting.setHint( R.string.rounting);
            etAccount.setHint( R.string.account);
            etMMYY.setHint( R.string.mmyy);
            //etAccountTypeCheck.setText(R.string.checking);

        }else{
            etCVV.setHint( R.string.cvc_default);
            etHolder.setHint( R.string.holder_default);
            etEditCard.setHint( R.string.card_num_default);
            etZip.setHint( R.string.zip_default);
            etRouting.setHint( R.string.rounting_default);
            etAccount.setHint( R.string.account_default);
            etMMYY.setHint( R.string.mmyy_default);
            //etAccountTypeCheck.setText(R.string.checking_default);
        }
        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++){
            try{
                String value = null;
                pay.androidcredit.util.PayEditText child = (pay.androidcredit.util.PayEditText)getChildAt(i);
                if(child != null){
                    if(isSingle()){
                        child.setBackground(null);
                    }else{
                        child.setBackground(getResources().getDrawable(R.drawable.bg_edittext));
                    }
                }
            }catch (Exception e){}
        }
    }
    private void setEtItemListener(pay.androidcredit.util.PayEditText etCardNumber0) {
        etCardNumber0.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    focus(etCardNumber0);
                }else{
                    lostFocus(etCardNumber0);
                }
            }
        });
    }
    private void setCommonAttr(pay.androidcredit.util.PayEditText etCardNumber0){
        etCardNumber0.setPadding(20,5,10,5);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //get initial dimension of ViewGroup
        int width = MeasureSpec.getSize(widthMeasureSpec) + getPaddingLeft() + getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) + getPaddingTop() + getPaddingBottom();
        int index = 0;
        int line_height = 0;
        int yPos = getPaddingTop();

        for(int i = 0; i < getChildCount(); i++){
            final View child = getChildAt(index++);
            if(child.getVisibility() != GONE){
                final LayoutParams layoutParams = child.getLayoutParams();
                //calc child width
                int wSpec = MeasureSpec.makeMeasureSpec(layoutParams.width, MeasureSpec.EXACTLY);
                int hSpec = MeasureSpec.makeMeasureSpec(layoutParams.height, MeasureSpec.EXACTLY);
                child.measure(wSpec, hSpec);
                //calc width of view
                line_height = Math.max(line_height, child.getMeasuredHeight());

            }
        }
        //set viewgroup dimension
        height += yPos;
        setMeasuredDimension(width, height);
    }
    public String getInvalidMsg(int i){
        try{
            PayEditText child = (PayEditText)getChildAt(i);
            return child.getInvalidMsg();
        }catch (Exception e){
            try{
                PayRadioGroup child = (PayRadioGroup)getChildAt(i);
                return child.getInvalidMsg();

            }catch (Exception e1){
                return null;
            }

        }

    }
    public String getChildValue(int i){
        try{
            PayEditText child = (PayEditText)getChildAt(i);
            return child.getValue();
        }catch (Exception e){
            try{
                PayRadioGroup child = (PayRadioGroup)getChildAt(i);
                return child.getValue();

            }catch (Exception e1){
                return null;
            }

        }

    }
    public String getChildClassName(int i){
        return getChildAt(i).getClass().getName();
        /*try{
                PayEditText child = (PayEditText)getChildAt(i);
            return child.getClass().getName();
        }catch (Exception e){
            try{
                PayRadioGroup child = (PayRadioGroup)getChildAt(i);
                return child.getClass().getName();

            }catch (Exception e1){
                return null;
            }
        }*/

    }

    public pay.androidcredit.pojo.PayCardInfo getCardInfo() throws Exception{
        pay.androidcredit.pojo.PayCardInfo PayCardInfo = new pay.androidcredit.pojo.PayCardInfo();
        PayCardInfo.isCard = isCard();
        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++){
                String value = null;
                /*if(i == childCount -1){
                    child = getChildAt(i);
                }else{
                    child = getChildAt(i);
                }*/

                String msg = getInvalidMsg(i);
                if(msg != null){
                    //child.setError(msg);
                    throw new Exception(msg);
                    //return null;
                }

                {
                    String className = getChildClassName(i);
                    if(className == null){
                        continue;
                    }
                    if(className.indexOf(".") > 0){
                        className = className.substring(className.lastIndexOf(".") + 1);
                    }
                    value = getChildValue(i);
                    if(className.equals(pay.androidcredit.util.PayConstants.PayEditCard.label + "")){
                        PayCardInfo.cardNumber = value;
                    }else if(className.equals(pay.androidcredit.util.PayConstants.PayHolder.label + "")){
                        PayCardInfo.holder = value;
                    }else if(className.equals(pay.androidcredit.util.PayConstants.PayCVC.label + "")){
                        PayCardInfo.cvc = value;
                    }else if(className.equals(pay.androidcredit.util.PayConstants.PayMMYY.label + "")){
                        PayCardInfo.expiryDate = value;
                    }else if(className.equals(pay.androidcredit.util.PayConstants.PayZip.label + "")){
                        PayCardInfo.zip = value;
                    }else if(className.equals(PayConstants.PayRounting.label + "")){
                        PayCardInfo.routingNum = value;
                    }else if(className.equals(PayConstants.PayAccount.label + "")){
                        PayCardInfo.accountNum = value;
                    }else if(className.equals(PayConstants.PayAccountType.label + "")){
                        PayCardInfo.accountType = value;
                    }
                }
        }

        return PayCardInfo;
    }
    private void focus(EditText etCardNumber0){
        String className = etCardNumber0.getClass().getName();
        if(className == null){
            return;
        }
        if(className.indexOf(".") > 0){
            className = className.substring(className.lastIndexOf(".") + 1);
        }
        if(screenWidth == null){
            return;
        }
        if(!isSingle()){
            if(className.equals(pay.androidcredit.util.PayConstants.PayCVC.label+"")){
                displayBackLabels(true);
            }else{
                displayBackLabels(false);
            }
            /*if(className.equals(pay.androidcredit.util.PayConstants.PayZip.label+"")){
                displayBackLabels(true);
            }*/
            return;
        }

        focusedClassName = className;
        drawItems();
    }
    private void lostFocus(EditText etCardNumber0){
        String className = etCardNumber0.getClass().getName();
        if(className == null){
            return;
        }
        if(className.indexOf(".") > 0){
            className = className.substring(className.lastIndexOf(".") + 1);
        }
        if(!isSingle()){
            if(className.equals(pay.androidcredit.util.PayConstants.PayCVC.label+"")){
                displayBackLabels(false);
            }
        }
    }
    public void setVisible(boolean isCard){
        cardImage.setImageResource(isCard?R.drawable.paycard_gradient:R.drawable.paycard_gradient_auth);
        holderlabel.setVisibility(isCard?VISIBLE:GONE);
        ziplabel.setVisibility(GONE);
        cvclabel.setVisibility(isCard?VISIBLE:GONE);
        magnetlabel.setVisibility(isCard?VISIBLE:GONE);

        etCVV.setVisibility(isCard?VISIBLE:GONE);
        etZip.setVisibility(isCard?VISIBLE:GONE);
        etEditCard.setVisibility(isCard?VISIBLE:GONE);

        //etHolder.setVisibility(isCard?VISIBLE:GONE);
        etMMYY.setVisibility(isCard?VISIBLE:GONE);
        etAccount.setVisibility(isCard?GONE:VISIBLE);
        etRouting.setVisibility(isCard?GONE:VISIBLE);
//        etAccountTypeCheck.setVisibility(isCard?GONE:VISIBLE);

        //checkingBtn.setVisibility(isCard?INVISIBLE:VISIBLE);

        routingLabel.setVisibility(isCard?GONE:VISIBLE);
        accountLabel.setVisibility(isCard?GONE:VISIBLE);
        payaccounttypeLabel.setVisibility(isCard?GONE:VISIBLE);

    }
    public void switchType(int layoutStyle, int payType){
        boolean isSingle = (layoutStyle == (int) pay.androidcredit.util.PayConstants.SingleLine.label);
        boolean isCard = (payType == (int) PayConstants.Card.label);
        if(isSingle == isSingle() && isCard == isCard()){

            return;
        }




        this.layoutStyle = layoutStyle;
        this.payType = payType;
        setHint();

        setVisible(isCard);

        if(isSingle || !isCard){
                displayBackLabels(false);
            }else if(isCard){
                if(etCVV.isFocused()){
                    displayBackLabels(true);
                }
            }
        drawItems();

        LayoutParams params = new LayoutParams(cardWidth, cardHeightReal);
        PayCard.setLayoutParams(params);
        PayCardShadow.setLayoutParams(params);
    }


}
