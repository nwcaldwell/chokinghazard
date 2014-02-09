package models;

public class IrrigationSpace extends Space {
    
    public IrrigationSpace() {
        this.type = Space.SpaceType.IRRIGATION;
    }

    public Space.SpaceType getSpaceType() {
        return type;
    }

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public IrrigationSpace loadObject(String serial) {
        // TODO Auto-generated method stub
        return null;
    }
}
