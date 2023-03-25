package eve.wajifeng.industryApp.mvc;

public class Total {

    private double totalVolume = 0.0d;
    private float totalPrice = 0.0f;
    private String totalVolumeString;
    private String totalPriceString;

    public double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalVolumeString() {
        return totalVolumeString;
    }

    public void setTotalVolumeString(String totalVolumeString) {
        this.totalVolumeString = totalVolumeString;
    }

    public String getTotalPriceString() {
        return totalPriceString;
    }

    public void setTotalPriceString(String totalPriceString) {
        this.totalPriceString = totalPriceString;
    }
}
