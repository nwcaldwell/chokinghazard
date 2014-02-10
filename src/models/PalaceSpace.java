package models;

import helpers.JsonObject;

public class PalaceSpace extends Space{
    private final int value; 
    private String imageSource;

    // {2, 4, 6, 8, 10}
    public PalaceSpace(int value) {
        this.value = value;
        this.type = Space.SpaceType.PALACE;
        setImageSource();
    }

    public Space.SpaceType getSpaceType() {
        return type;
    }    

    public int getValue() {
        return value;
    }
    
    private void setImageSource(){
    	imageSource = "src/resources/oneTile_"+value+".png";
    }
    
    public String getImageSource(){
    	return imageSource;
    }

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public PalaceSpace loadObject(JsonObject json) {
        // TODO Auto-generated method stub
        return null;
    }
}
