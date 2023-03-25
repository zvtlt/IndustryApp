package eve.wajifeng.industryApp.yaml.blueprints;


public class Blueprints {

    private Activities activities;
    private int blueprintTypeID;
    private int maxProductionLimit;


    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    public int getBlueprintTypeID() {
        return blueprintTypeID;
    }

    public void setBlueprintTypeID(int blueprintTypeID) {
        this.blueprintTypeID = blueprintTypeID;
    }

    public int getMaxProductionLimit() {
        return maxProductionLimit;
    }

    public void setMaxProductionLimit(int maxProductionLimit) {
        this.maxProductionLimit = maxProductionLimit;
    }

}
