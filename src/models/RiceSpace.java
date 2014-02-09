package models;

import helpers.JsonObject;

public class RiceSpace extends Space {

    public RiceSpace() {
        this.type = Space.SpaceType.PALACE;
    }

    public Space.SpaceType getSpaceType() {
        return type;
    }    

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public RiceSpace loadObject(JsonObject json) {
        // TODO Auto-generated method stub
        return null;
    }
}
