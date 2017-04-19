package recipegen.app.fhbgds;

import java.util.ArrayList;
import java.util.List;

import recipegen.app.fhbgds.enums.DryIngredients;
import recipegen.app.fhbgds.enums.IngredientType;
import recipegen.app.fhbgds.enums.WetIngredients;

public class Recipe {

	private List<DryIngredients> availableLQDIngredients = new ArrayList<DryIngredients>();
	private List<DryIngredients> availableSQDIngredients = new ArrayList<DryIngredients>();
	private List<WetIngredients> availableLQWIngredients = new ArrayList<WetIngredients>();
	private List<WetIngredients> availableSQWIngredients = new ArrayList<WetIngredients>();

	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	private List<String> ingredientStrings = new ArrayList<String>();
	private List<String> steps = new ArrayList<String>();
	private int step = 0;
	private boolean inIngredientCategory = false;
	private int ingredientCategoryLevel = 0;
	
	public Recipe(){
		this.availableLQDIngredients.addAll(DryIngredients.LARGE_QUANTITY_DRY);
		this.availableSQDIngredients.addAll(DryIngredients.SMALL_QUANTITY_DRY);
		this.availableLQWIngredients.addAll(WetIngredients.LARGE_QUANTITY_WET);
		this.availableSQWIngredients.addAll(WetIngredients.SMALL_QUANTITY_WET);
	}
	
	public void addStep(String step){
		this.step++;
		this.steps.add(this.step + ". " + step);
	}

	public void addSpices(List<DryIngredients> spices, boolean includeAmount){
		this.beginIngredientCategory("Spices");
		for(DryIngredients spice : spices){
			if(includeAmount){
				Ingredient i = Preparation.getRandomAmountSpice(spice);
				this.ingredients.add(i);
				this.addIngredientString(i.toString());
			}else{
				this.ingredients.add(new Ingredient(spice.toString()));
				this.addIngredientString(spice.toString());
			}
		}
		this.endIngredientCategory();
	}
	
	public void addLiquidSpices(List<WetIngredients> spices, boolean includeAmount){
		this.beginIngredientCategory("Liquids");
		for(WetIngredients spice : spices){
			if(includeAmount){
				Ingredient i = Preparation.getRandomAmountLiquidSpice(spice);
				this.ingredients.add(i);
				this.addIngredientString(i.toString());
			}else{
				this.ingredients.add(new Ingredient(spice.toString()));
				this.addIngredientString(spice.toString());
			}
		}
		this.endIngredientCategory();
	}
	
	public void addIngredientString(String s){
		if(this.inIngredientCategory){
			String catString = "";
			for(int i = 1; i < ingredientCategoryLevel; i++){
				catString += "  ";
			}
			catString +="- ";
			s = catString + s;
			this.ingredientStrings.add(s);
		}else{
			this.ingredientStrings.add(s);
		}
	}
	
	public void addNonIngredientString(String s){
		if(this.inIngredientCategory){
			String catString = "";
			for(int i = 1; i < ingredientCategoryLevel; i++){
				catString += "  ";
			}
			catString +="  ";
			s = catString + s;
			this.ingredientStrings.add(s);
		}else{
			this.ingredientStrings.add(s);
		}
	}
	
	public void beginIngredientCategory(String name){
		this.addNonIngredientString(name + ":");
		if(!this.inIngredientCategory) this.inIngredientCategory = true;
		this.ingredientCategoryLevel++;
	}
	
	public void endIngredientCategory(){
		this.ingredientCategoryLevel--;
		if(this.ingredientCategoryLevel < 0) ingredientCategoryLevel = 0;
		if(this.ingredientCategoryLevel == 0) this.inIngredientCategory = false;
		this.ingredientStrings.add("");
	}
	
	public void newPrepSection(String name){
		this.step = 0;
		this.steps.add("");
		this.steps.add(name + ":");
		this.steps.add("");
	}
	
	public Ingredient addIngredient(Object ingredient, boolean includeQuantity, IngredientType type) {
		Ingredient i = null;
		switch(type){
			case LARGE_QUANTITY_WET:
				if(this.availableLQWIngredients.contains(ingredient)){
					this.availableLQWIngredients.remove(this.availableLQWIngredients.indexOf(ingredient));
					if(includeQuantity){
						i = Preparation.getRandomAmountLW((WetIngredients) ingredient);
						this.ingredients.add(i);
						this.addIngredientString(i.toString());
					}else{
						i = new Ingredient(ingredient.toString());
						this.ingredients.add(i);
						this.addIngredientString(i.toString());
					}
				}
			case LARGE_QUANTITY_DRY:
				if(this.availableLQDIngredients.contains(ingredient)){
					this.availableLQDIngredients.remove(this.availableLQDIngredients.indexOf(ingredient));
					if(includeQuantity){
						i = Preparation.getRandomAmountLD((DryIngredients) ingredient);
						this.ingredients.add(i);
						this.addIngredientString(i.toString());
					}else{
						i = new Ingredient(ingredient.toString());
						this.ingredients.add(i);
						this.addIngredientString(i.toString());
					}
				}
				return i;
			case SMALL_QUANTITY_WET:
				if(this.availableSQWIngredients.contains(ingredient)){
					this.availableSQWIngredients.remove(this.availableSQWIngredients.indexOf(ingredient));
					if(includeQuantity){
						i = Preparation.getRandomAmountSW((WetIngredients) ingredient);
						this.ingredients.add(i);
						this.addIngredientString(i.toString());
					}else{
						i = new Ingredient(ingredient.toString());
						this.ingredients.add(i);
						this.addIngredientString(i.toString());
					}
				}
				return i;
			case SMALL_QUANTITY_DRY:
				if(this.availableSQDIngredients.contains(ingredient)){
					this.availableSQDIngredients.remove(this.availableSQDIngredients.indexOf(ingredient));
					if(includeQuantity){
						i = Preparation.getRandomAmountSD((DryIngredients) ingredient);
						this.ingredients.add(i);
						this.addIngredientString(i.toString());
					}else{
						i = new Ingredient(ingredient.toString());
						this.ingredients.add(i);
						this.addIngredientString(i.toString());
					}
				}
				return i;
			default:
				return null;
		}
	}
	
	public String getRecipe(){
		String recipe = "";
		recipe += "Ingredients:\n------------\n";
		for(String s : ingredientStrings){
			recipe += s + "\n";
		}
		recipe += "\nProcedure:\n----------\n";
		for(String s : steps){
			recipe += s + "\n";
		}
		return recipe;
	}
}
