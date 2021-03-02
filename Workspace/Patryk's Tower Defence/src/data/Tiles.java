package data;

public enum Tiles {
	
	Blue("BlueTile",true), Yellow("YellowTile",false), NULL("YellowTile", false);
	
	String textureName;
	boolean buildable;
	
	Tiles(String textureName, boolean buildable) {
		this.textureName = textureName;
		this.buildable = buildable;
	}
}
