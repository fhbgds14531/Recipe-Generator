package recipegen.app.fhbgds;

import java.security.SecureRandom;
import java.util.Arrays;

import recipegen.app.fhbgds.enums.DryIngredients;
import recipegen.app.fhbgds.enums.EnumMethod;
import recipegen.app.fhbgds.enums.WetIngredients;

public class Main {
	
	static SecureRandom rand = new SecureRandom();
	
	public static void main(String[] args){
		Recipe r = new Recipe();
		Object randomIngredient = null;
		if(rand.nextBoolean()){
			if(rand.nextBoolean()){
				randomIngredient = DryIngredients.getRandomLargeQuantityDry();
			}else{
				randomIngredient = DryIngredients.getRandomSmallQuantityDry();
			}
		}else{
			if(rand.nextBoolean()){
				if(rand.nextBoolean()){
					if(rand.nextBoolean()){
						randomIngredient = WetIngredients.getRandomLargeQuantityWet();
					}else{
						randomIngredient = WetIngredients.getRandomSmallQuantityWet();
					}
				}else{
					if(rand.nextBoolean()){
						randomIngredient = DryIngredients.getRandomLargeQuantityDry();
					}else{
						randomIngredient = DryIngredients.getRandomSmallQuantityDry();
					}
				}
			}else{
				if(rand.nextBoolean()){
					randomIngredient = DryIngredients.getRandomLargeQuantityDry();
				}else{
					randomIngredient = DryIngredients.getRandomSmallQuantityDry();
				}
			}
		}
		Ingredient ingredient = r.addIngredient(randomIngredient, true);
		EnumMethod method = EnumMethod.roast;//(EnumMethod) Util.getRandomObjectFromList(Arrays.asList((Object[]) EnumMethod.values()));
		Preparation.executeMethod(ingredient, method, r, false);
		System.out.println(EnumMethod.getTitleFromMethodAndIngredient(method, ingredient) + "\n");
		System.out.println(r.getRecipe());
	}
}
