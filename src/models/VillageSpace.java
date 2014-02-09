package models;

public class VillageSpace extends Space {
	private String imageSource = "src/resources/oneTile_village.png";
    private int palacex; 
    private int palacey; 

    public VillageSpace() {
        this.type = Space.SpaceType.VILLAGE;
    }

    public Space.SpaceType getSpaceType() {
        return type;
    }

    public int getPalacey() {
        return palacey;
    }

    public void setPalacey(int palacey) {
        this.palacey = palacey;
    }

    public int getPalacex() {
        return palacex;
    }

    public void setPalacex(int palacex) {
        this.palacex = palacex;
    }
    
    public String getImageSource(){
    	return this.imageSource;
    }

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public VillageSpace loadObject(String serial) {
        // TODO Auto-generated method stub
        return null;
    }
}
