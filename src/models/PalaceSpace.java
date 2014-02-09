package models;

public class PalaceSpace extends Space{
    private final int value; 

    // {2, 4, 6, 8, 10}
    public PalaceSpace(int value) {
        this.value = value;
        this.type = Space.SpaceType.PALACE;
    }

    public Space.SpaceType getSpaceType() {
        return type;
    }    

    public int getValue() {
        return value;
    }

    public String serialize() {
        // TODO Auto-generated method stub
        return null;
    }

    public PalaceSpace loadObject(String serial) {
        // TODO Auto-generated method stub
        return null;
    }
}
