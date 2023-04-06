package pay.androidcredit.util;

public enum PayConstants {
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    URLROOT("https://api.grubpay.io/v4/"),
    VISA("Visa"),
    MASTERCARD("MasterCard"),
    AMERICAN_EXPRESS("American_Express"),
    DISCOVER("Discover"),
    JCB("JCB"),
    DINER_ClUB("Diners_Club"),
    UNIONPAY("UnionPay"),
    UNKNOWN("UNKNOWN"),
    SingleLine(0),
    TripleLine(1),
    Card(0),
    Ach(1),

    PayCard("PayCard"),
    PayCardShadow("PayCardShadow"),
    PayEditCard("PayEditCard"),
    PayHolder("PayHolder"),
    PayCVC("PayCVC"),
    PayMMYY("PayMMYY"),
    PayZip("PayZip"),
    PARAMS_SECUREID("secureId"),
    PARAMS_CARD_NUM("cardNum"),
    PARAMS_HOLDER("holder"),
    PARAMS_CVV("cvv"),
    PARAMS_EXPIRYDATE("expiryDate"),
    PARAMS_ZIP("zip"),
    PayRounting("PayRoutingNumber"),
    PayAccount("PayAccountNumber"),
    PayAccountType("PayAccountType"),
    CreditCardStyle("creditCardStyle")
    ;
    public final Object label;
    private PayConstants(Object label){
        this.label = label;
    }
}
