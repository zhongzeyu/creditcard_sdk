package com.iot.iotpayandroidcreditdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import iotpay.androidcredit.business.IOTPayCallback;
import iotpay.androidcredit.business.IOTPayService;
import iotpay.androidcredit.config.IOTPayConfig;
import iotpay.androidcredit.util.IOTPayConstants;
import iotpay.androidcredit.widgets.IOTCardInfoView;

public class MainActivity extends AppCompatActivity {

    EditText secureIdPay,secureIdAddCard;
    Switch switchStyle;
    IOTCardInfoView iotCardInfoView;  //the card info from order payment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secureIdPay = findViewById(R.id.editText1);
        secureIdAddCard = findViewById(R.id.editText2);
        switchStyle = findViewById(R.id.switch1);
        switchStyle.setOnCheckedChangeListener(
                ((buttonView, isChecked) -> openStyleSwitch(isChecked))
        );
        iotCardInfoView = IOTPayService.IOTCardInfoView(findViewById(R.id.tripleLine),(int)IOTPayConstants.TripleLine.label);

    }
    private void openStyleSwitch(boolean isChecked){
        iotCardInfoView.switchType(isChecked? (int) IOTPayConstants.TripleLine.label:(int) IOTPayConstants.SingleLine.label);
    }
   public void iotpayOneTimePay(View v){
       String secureId = secureIdPay.getText() + "";

       try{
           IOTPayService.sendRequest(secureId,IOTPayConfig.OneTimePayment, iotCardInfoView,new IOTPayCallback(){
               public void onResultIOTPay(String result) { //result
                   showMsg("Payment Result:" + result);
               }

           });
       }catch (Exception e){
           showMsg("Error:" + e.getMessage());
       }

   }
   private void showMsg(String msg){
       ResultActivity.startAction(this, msg);
   }
   public void iotpayAddCard(View v) throws Exception{
       String secureId = secureIdAddCard.getText() + "";
       /*IOTPayCallback iotPayCallback = new IOTPayCallback(){
           public void onResultIOTPay(String result) { //result

               System.out.println("Add card Result:" + result);
               showMsg("Add card Result:" + result);
           }

       };*/
       try{
           IOTPayService.sendRequest(secureId,IOTPayConfig.AddCard,iotCardInfoView,new IOTPayCallback(){
               public void onResultIOTPay(String result) { //result
                   System.out.println("Add card Result:" + result);
                   showMsg("Add card Result:" + result);
               }

           });
       }catch (Exception e){
           e.printStackTrace();
           showMsg("Error:" + e.getMessage());
       }

   }

}