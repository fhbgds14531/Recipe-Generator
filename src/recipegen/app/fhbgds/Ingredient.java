package recipegen.app.fhbgds;

public class Ingredient {

	String name;
	String units;
	double quantity;
	
	boolean includeQuantity = true;
	
	public Ingredient(String name){
		this.includeQuantity = false;
		
		this.name = name;
	}
	
	public Ingredient(String name, double quantity, String units){
		this.includeQuantity = true;
		
		this.name = name;
		this.quantity = quantity;
		this.units = units;
	}
	
	public String toString(){
		if(this.includeQuantity){
			String s = quantity + units + " of " + Util.capitalizeFirstLetterOfEveryWord(name);
			return s.replace(".0", "");
		}else{
			return Util.capitalizeFirstLetterOfEveryWord(name);
		}
	}

	public String getName() {
		return name;
	}
	
}
