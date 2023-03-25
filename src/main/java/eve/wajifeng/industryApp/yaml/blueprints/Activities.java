package eve.wajifeng.industryApp.yaml.blueprints;

public class Activities {

    private Reaction reaction;
    private Copying copying;
    private Invention invention;
    private Manufacturing manufacturing;
    private ResearchMaterial research_material;
    private ResearchTime research_time;

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public Copying getCopying() {
        return copying;
    }

    public void setCopying(Copying copying) {
        this.copying = copying;
    }

    public Invention getInvention() {
        return invention;
    }

    public void setInvention(Invention invention) {
        this.invention = invention;
    }

    public Manufacturing getManufacturing() {
        return manufacturing;
    }

    public void setManufacturing(Manufacturing manufacturing) {
        this.manufacturing = manufacturing;
    }

    public ResearchMaterial getResearch_material() {
        return research_material;
    }

    public void setResearch_material(ResearchMaterial research_material) {
        this.research_material = research_material;
    }

    public ResearchTime getResearch_time() {
        return research_time;
    }

    public void setResearch_time(ResearchTime research_time) {
        this.research_time = research_time;
    }
}
