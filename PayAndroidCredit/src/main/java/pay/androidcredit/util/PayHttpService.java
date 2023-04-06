package pay.androidcredit.util;

import org.json.JSONObject;

public class PayHttpService {
    private static pay.androidcredit.util.PayHttpService hInstance;
    private PayOKHttpWrapper wrapper;
    private PayHttpService() throws Exception{
        wrapper = PayOKHttpWrapper.getInstance();
    }
    public static pay.androidcredit.util.PayHttpService getInstance() throws Exception{
        if(hInstance == null){
            hInstance = new pay.androidcredit.util.PayHttpService();
        }
        return hInstance;
    }
    public void post(String url, JSONObject jsonParam, pay.androidcredit.business.PayCallback PayCallback) throws Exception{
        String jsonString = jsonParam.toString();
        post(url, jsonString,PayCallback);
    }

    private void executeTask(Runnable run) {
            PayThreadPoolManager.executeInCachePool(run);
    }
    public void post(String url, String jsonParam, pay.androidcredit.business.PayCallback PayCallback) throws Exception{
        executeTask(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String response = wrapper.post(url, jsonParam);
                            //Gson gson = new Gson();

                            //HttpRes res = Gson.fromJson(response, HttpRes.class);
                            PayCallback.onResultPay(response);
                        }catch (Exception e){
                            e.printStackTrace();
                            JSONObject jsonObject = new JSONObject();
                            try{
                                jsonObject.put("retCode", PayConstants.FAIL+"");
                                jsonObject.put("retMsg",e.getMessage());
                                PayCallback.onResultPay(jsonObject.toString());

                            }catch (Exception e1){
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }


}
