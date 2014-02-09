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
    	switch(value){
    		case 2:
    			imageSource = "src/resources/oneTile_2";
    			break;
    		case 4:
    			imageSource = "src/resources/oneTile_4";
    			break;
    		case 6:
    			imageSource = "src/resources/oneTile_6";
    			break;
    		case 8:
    			imageSource = "src/resources/oneTile_8";
    			break;
    		case 10:
    			imageSource = "src/resources/oneTile_10";
    			break;
    	}
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
