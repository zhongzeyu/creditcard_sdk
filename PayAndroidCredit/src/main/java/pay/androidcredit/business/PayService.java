package pay.androidcredit.business;

import android.view.ViewGroup;

import org.json.JSONObject;

import pay.androidcredit.R;
import pay.androidcredit.util.PayConstants;

public class PayService {
    private static String urlRoot = pay.androidcredit.util.PayConstants.URLROOT.label+"";


    public static pay.androidcredit.widgets.CardInfoView CardInfoView(ViewGroup viewGroup, int creditCardStyle, int payType) {

        pay.androidcredit.widgets.CardInfoView creditForm = new pay.androidcredit.widgets.CardInfoView(viewGroup.getContext(), null, creditCardStyle,payType);// inflate(viewGroup.getContext(), null,null);

        viewGroup.addView(creditForm);
        return creditForm;
    }

    public static void sendRequest(String secureId, pay.androidcredit.widgets.CardInfoView creditForm, PayCallback PayCallback) throws Exception{

        String url = urlRoot + "auth";
        httpResult(url,PayCallback,creditForm,secureId);


    }


    private static void httpResult(String url, PayCallback PayCallback, pay.androidcredit.widgets.CardInfoView creditForm, String secureId) throws Exception{
        if (secureId == null || secureId.length() < 1) {
            throw new Exception(creditForm.getResources().getString(R.string.secureid_invalid));
        }
        pay.androidcredit.pojo.PayCardInfo PayCardInfo = creditForm.getCardInfo();
        if(PayCardInfo == null){
            return;
        }


        JSONObject params = new JSONObject();
        params.put(pay.androidcredit.util.PayConstants.PARAMS_SECUREID.label+"",secureId);
        params.put(pay.androidcredit.util.PayConstants.PARAMS_HOLDER.label+"", PayCardInfo.holder);
        if(PayCardInfo.isCard){
            params.put(pay.androidcredit.util.PayConstants.PARAMS_CARD_NUM.label+"", PayCardInfo.cardNumber);
            params.put(pay.androidcredit.util.PayConstants.PARAMS_CVV.label+"", PayCardInfo.cvc);
            params.put(pay.androidcredit.util.PayConstants.PARAMS_EXPIRYDATE.label+"", PayCardInfo.expiryDate);
            params.put(pay.androidcredit.util.PayConstants.PARAMS_ZIP.label+"", PayCardInfo.zip);

        }else{
            params.put(pay.androidcredit.util.PayConstants.PayRounting.label+"", PayCardInfo.routingNum);
            params.put(PayConstants.PayAccount.label+"", PayCardInfo.accountNum);
            params.put(PayConstants.PayAccountType.label+"", PayCardInfo.accountType);

        }

        pay.androidcredit.util.PayHttpService PayHttpService = pay.androidcredit.util.PayHttpService.getInstance();
        PayHttpService.post(url,params,PayCallback);
    }
}
