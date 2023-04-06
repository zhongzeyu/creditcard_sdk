package com.pay.payandroidcreditdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import pay.androidcredit.util.PayConstants;


public class MainActivity extends AppCompatActivity {

    EditText secureIdPay,secureIdAddCard;
    RadioGroup radioGroup,radioGroup2;
    pay.androidcredit.widgets.CardInfoView CardInfoView;  //the card info from order payment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secureIdPay = findViewById(R.id.editText1);
        secureIdAddCard = findViewById(R.id.editText2);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup2 = findViewById(R.id.radioGroup2);
        CardInfoView = pay.androidcredit.business.PayService.CardInfoView(findViewById(R.id.tripleLine),(int) pay.androidcredit.util.PayConstants.TripleLine.label,(int) pay.androidcredit.util.PayConstants.Card.label);
        //CardInfoView.switchType((int) PayConstants.TripleLine.label,(int) PayConstants.Card.label);
    }

   public void simplePurchase(View v){
       String secureId = secureIdPay.getText() + "";

       try{
           pay.androidcredit.business.PayService.sendRequest(secureId, CardInfoView,new pay.androidcredit.business.PayCallback(){
               public void onResultPay(String result) { //result
                   System.out.println("Purchase Result:" + result);
                   showMsg("Purchase Result:" + result);
               }

           });
       }catch (Exception e){
           showMsg("Error:" + e.getMessage());
       }

   }
   private void showMsg(String msg){
       ResultActivity.startAction(this, msg);
   }
   public void addCard(View v) throws Exception{
       String secureId = secureIdAddCard.getText() + "";

       try{
           pay.androidcredit.business.PayService.sendRequest(secureId, CardInfoView,new pay.androidcredit.business.PayCallback(){
               public void onResultPay(String result) { //result
                   System.out.println("Tokenize Result:" + result);
                   showMsg("Tokenize Result:" + result);
               }

           });
       }catch (Exception e){
           e.printStackTrace();
           showMsg("Error:" + e.getMessage());
       }

   }
   public void clickPayType(View v){
       int radioId = radioGroup.getCheckedRadioButtonId();
       int radioId1 = radioGroup2.getCheckedRadioButtonId();
       int payStyle = (radioId1==3)? (int) PayConstants.SingleLine.label:(int) PayConstants.TripleLine.label;
       int payType = (radioId==2)? (int) pay.androidcredit.util.PayConstants.Ach.label:(int) PayConstants.Card.label;
       CardInfoView.switchType(payStyle,payType);
   }

}