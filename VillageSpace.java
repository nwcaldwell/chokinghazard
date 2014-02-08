

class VillageSpace extends Space {
  private int palacex; 
  private int palacey; 

  public VillageSpace() {

  }

  public Space.SpaceType getSpaceType() {
    return Space.SpaceType.VILLAGE;
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

  public String serialize() {
    // TODO Auto-generated method stub
    return null;
  }

  public VillageSpace loadObject(String serial) {
    // TODO Auto-generated method stub
    return null;
  }
}
