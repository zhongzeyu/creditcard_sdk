package pay.androidcredit.pojo;

import java.util.List;
import java.util.Map;

import pay.androidcredit.R;

public class PayCreditCard {
    public String code = "unknown";
    public String displayName = "Unknown";
    public int IconID = R.drawable.card;
    public int cvcLength = 4;
    public int cardNumberLength = 26;
    public String cardNumberPattern = null;
    public Map<Integer, String> partialPatterns = null;
    public String cvcLabel = "CVV";
    public int zipLength = 6;
    public String zipLabel = "ZIP";
    public List<Integer> defaultSpacePositions;
}
