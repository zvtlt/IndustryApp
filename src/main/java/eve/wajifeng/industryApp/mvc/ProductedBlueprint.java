package eve.wajifeng.industryApp.mvc;

public class ProductedBlueprint {

    private int blueprintTypeID;

    private String productedName;
    private int productedQuantity;
    private int productedTypeID;
    private String productedImage;
    private double productedVolume;
    private float productedPrice = 0.0f;

    private String productedPriceString;
    private String productedVolumeString;
    private String productedQuantityString;

    private int time;
    private int maxProductionLimit;


    public int getBlueprintTypeID() {
        return blueprintTypeID;
    }

    public void setBlueprintTypeID(int blueprintTypeID) {
        this.blueprintTypeID = blueprintTypeID;
    }

    public String getProductedName() {
        return productedName;
    }

    public void setProductedName(String productedName) {
        this.productedName = productedName;
    }

    public int getProductedQuantity() {
        return productedQuantity;
    }

    public void setProductedQuantity(int productedQuantity) {
        this.productedQuantity = productedQuantity;
    }

    public int getProductedTypeID() {
        return productedTypeID;
    }

    public void setProductedTypeID(int productedTypeID) {
        this.productedTypeID = productedTypeID;
    }

    public String getProductedImage() {
        return productedImage;
    }

    public void setProductedImage(String productedImage) {
        this.productedImage = productedImage;
    }

    public double getProductedVolume() {
        return productedVolume;
    }

    public void setProductedVolume(double productedVolume) {
        this.productedVolume = productedVolume;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMaxProductionLimit() {
        return maxProductionLimit;
    }

    public void setMaxProductionLimit(int maxProductionLimit) {
        this.maxProductionLimit = maxProductionLimit;
    }

    public float getProductedPrice() {
        return productedPrice;
    }

    public void setProductedPrice(float productedPrice) {
        this.productedPrice = productedPrice;
    }

    public String getProductedPriceString() {
        return productedPriceString;
    }

    public void setProductedPriceString(String productedPriceString) {
        this.productedPriceString = productedPriceString;
    }

    public String getProductedVolumeString() {
        return productedVolumeString;
    }

    public void setProductedVolumeString(String productedVolumeString) {
        this.productedVolumeString = productedVolumeString;
    }

    public String getProductedQuantityString() {
        return productedQuantityString;
    }

    public void setProductedQuantityString(String productedQuantityString) {
        this.productedQuantityString = productedQuantityString;
    }

    @Override
    public String toString() {
        return "ProductedBlueprint{" +
                "blueprintTypeID=" + blueprintTypeID +
                ", productedName='" + productedName + '\'' +
                ", productedQuantity=" + productedQuantity +
                ", productedTypeID=" + productedTypeID +
                ", productedImage='" + productedImage + '\'' +
                ", productedVolume=" + productedVolume +
                ", productedPrice=" + productedPrice +
                ", productedPriceString='" + productedPriceString + '\'' +
                ", productedVolumeString='" + productedVolumeString + '\'' +
                ", productedQuantityString='" + productedQuantityString + '\'' +
                ", time=" + time +
                ", maxProductionLimit=" + maxProductionLimit +
                '}';
    }
}
