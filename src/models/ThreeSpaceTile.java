

package models;

import helpers.Json;
import helpers.JsonObject;

class ThreeSpaceTile extends Tile {
	private String imageSource = "images/threeTile.png";
	
	public ThreeSpaceTile(Space[][] spaces) {
	    super(spaces);
	    spaces[0][0] = new VillageSpace();
	    spaces[0][1] = new RiceSpace();
	    spaces[1][0] = new RiceSpace();
	}
    
    //rotates the piece.
	public void rotate() 
   {
		Space tempSpace = spaces[0][0];
		spaces[0][0] = spaces[0][1];
        spaces[0][1] = spaces[1][1];
        spaces[1][1] = spaces[1][0];
        spaces[1][0] = tempSpace;          
	}

	public String getImageSource(){
		return this.imageSource;
	}
	
	public String toString(){
    	return "THREE SPACE TILE";
    }

	public String print() {
		String ret = "";
		for(int x = 0; x < spaces.length; ++x) {
			for(int y = 0; y < spaces[0].length; ++y) {
				ret += " " + spaces[x][y];
			}
		}
		return ret;
	}
	
    public String serialize() {
        return Json.jsonObject(Json.jsonMembers(
            Json.jsonPair("spaces", Json.serializeArray(spaces))
        ));
    }

    public ThreeSpaceTile loadObject(JsonObject json) {
    	Space[][] spaces = new Space[2][2];
    	JsonObject[][] objects = (JsonObject[][]) (Object) json.getJsonObject("spaces");
    	for(int x = 0; x < 4; ++x)
    		spaces[x/2][x%2] = (new Space()).loadObject(objects[x/2][x%2]);
    	this.spaces = spaces;
    	return this;
    }
}

