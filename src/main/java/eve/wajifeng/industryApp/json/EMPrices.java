package eve.wajifeng.industryApp.json;

public class EMPrices {

    private int type_id;
    private float median_price;

    public EMPrices() {
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public float getMedian_price() {
        return median_price;
    }

    public void setMedian_price(float median_price) {
        this.median_price = median_price;
    }
}
