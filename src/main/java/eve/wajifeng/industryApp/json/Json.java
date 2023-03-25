package eve.wajifeng.industryApp.json;

public class Json {

    private int type_id;
    private float average_price;
    private float adjusted_price;

    public Json() {
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public float getAverage_price() {
        return average_price;
    }

    public void setAverage_price(float average_price) {
        this.average_price = average_price;
    }

    public float getAdjusted_price() {
        return adjusted_price;
    }

    public void setAdjusted_price(float adjusted_price) {
        this.adjusted_price = adjusted_price;
    }

    @Override
    public String toString() {
        return "Json{" +
                "type_id=" + type_id +
                ", average_price=" + average_price +
                ", adjusted_price=" + adjusted_price +
                "}\n";
    }
}
