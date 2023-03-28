package eve.wajifeng.industryApp.mvc;

public class ComponentBlueprint {

    private String materialName;
    private int materialQuantity;
    private int materialTypeID;
    private String materialImage;
    private double materialVolume;
    private float basePrice = 0.0f;
    private float multPrice = 0.0f;
    private boolean sub = false;

    private String basePriceString;
    private String multPriceString;
    private String materialVolumeString;
    private String materialQuantityString;


    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(int materialQuantity) {
        this.materialQuantity = materialQuantity;
    }

    public int getMaterialTypeID() {
        return materialTypeID;
    }

    public void setMaterialTypeID(int materialTypeID) {
        this.materialTypeID = materialTypeID;
    }

    public String getMaterialImage() {
        return materialImage;
    }

    public void setMaterialImage(String materialImage) {
        this.materialImage = materialImage;
    }

    public double getMaterialVolume() {
        return materialVolume;
    }

    public void setMaterialVolume(double materialVolume) {
        this.materialVolume = materialVolume;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public float getMultPrice() {
        return multPrice;
    }

    public void setMultPrice(float multPrice) {
        this.multPrice = multPrice;
    }

    public String getBasePriceString() {
        return basePriceString;
    }

    public void setBasePriceString(String basePriceString) {
        this.basePriceString = basePriceString;
    }

    public String getMultPriceString() {
        return multPriceString;
    }

    public void setMultPriceString(String multPriceString) {
        this.multPriceString = multPriceString;
    }

    public String getMaterialVolumeString() {
        return materialVolumeString;
    }

    public void setMaterialVolumeString(String materialVolumeString) {
        this.materialVolumeString = materialVolumeString;
    }

    public String getMaterialQuantityString() {
        return materialQuantityString;
    }

    public void setMaterialQuantityString(String materialQuantityString) {
        this.materialQuantityString = materialQuantityString;
    }

    public boolean isSub() {
        return sub;
    }

    public void setSub(boolean sub) {
        this.sub = sub;
    }

    @Override
    public String toString() {
        return "ComponentBlueprint{" +
                "materialName='" + materialName + '\'' +
                ", materialQuantity=" + materialQuantity +
                ", materialTypeID=" + materialTypeID +
                ", materialImage='" + materialImage + '\'' +
                ", materialVolume=" + materialVolume +
                ", basePrice=" + basePrice +
                ", multPrice=" + multPrice +
                ", sub=" + sub +
                ", basePriceString='" + basePriceString + '\'' +
                ", multPriceString='" + multPriceString + '\'' +
                ", materialVolumeString='" + materialVolumeString + '\'' +
                ", materialQuantityString='" + materialQuantityString + '\'' +
                '}';
    }
}
