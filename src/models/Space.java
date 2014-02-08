package models;

class Space implements Serializable<Space> {
  public enum SpaceType {
    RICE, VILLAGE, IRRIGATION, PALACE
  }

  private SpaceType type;
  private String imageSource;

  public Space() {

  }

  public SpaceType getType() {
    return type;
  }

  public void setType(SpaceType type) {
    this.type = type;
  }

  public String getImageSource() {
    return imageSource;
  }

  public void setImageSource(String imageSource) {
    this.imageSource = imageSource;
  }

  public String serialize() {
    // TODO Auto-generated method stub
    return null;
  }

  public Space loadObject(String serial) {
    // TODO Auto-generated method stub
    return null;
  }
}
