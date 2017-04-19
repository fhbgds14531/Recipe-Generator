package recipegen.app.fhbgds.enums;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import recipegen.app.fhbgds.Ingredient;
import recipegen.app.fhbgds.Util;

public enum EnumMethod {
	pan_fry,
	deep_fry,
	microwave,
	boil,
	sear,
	saute,
	braise,
	dry_roast,
	flambe,
	grill,
	juice,
	macerate,
	marinate,
	poach,
	puree,
	reduce,
	rice,
	roast,
	simmer,
	sous_vide,
	stew,
	stir_fry;
	
	static SecureRandom rand = new SecureRandom();
	
	public static List<EnumMethod> nonCookingMethods = Arrays.asList(new EnumMethod[] {
			flambe,
			juice,
			macerate,
			marinate,
			puree,
			rice
	});
	
	public static EnumMethod getRandomCookingMethod(){
		EnumMethod m = (EnumMethod) Util.getRandomObjectFromList(Arrays.asList((Object[])EnumMethod.values()));
		switch(m){
			case boil:
				return m;
			case braise:
				return m;
			case deep_fry:
				return m;
			case dry_roast:
				return m;
			case flambe:
				return getRandomCookingMethod();
			case grill:
				return m;
			case juice:
				return getRandomCookingMethod();
			case macerate:
				return getRandomCookingMethod();
			case marinate:
				return getRandomCookingMethod();
			case microwave:
				return m;
			case pan_fry:
				return m;
			case poach:
				return m;
			case puree:
				return getRandomCookingMethod();
			case reduce:
				return m;
			case rice:
				return getRandomCookingMethod();
			case roast:
				return m;
			case saute:
				return m;
			case sear:
				return m;
			case simmer:
				return m;
			case sous_vide:
				return m;
			case stew:
				return m;
			case stir_fry:
				return m;
			default:
				return null;
		}
	}
	
	public static String getTitleFromMethodAndIngredient(EnumMethod method, Ingredient ingredient){
		switch(method){
			case boil:
				return Util.capitalizeFirstLetterOfEveryWord("Boiled " + ingredient.getName());
			case braise:
				return Util.capitalizeFirstLetterOfEveryWord("Braised " + ingredient.getName());
			case deep_fry:
				return Util.capitalizeFirstLetterOfEveryWord("Deep fried " + ingredient.getName());
			case dry_roast:
				return Util.capitalizeFirstLetterOfEveryWord("Dry roasted " + ingredient.getName());
			case flambe:
				if(ingredient.getName().endsWith("s")){
					String newName = ingredient.getName().replace("berrie", "berry").replace("atoe", "ato");
					return Util.capitalizeFirstLetterOfEveryWord(newName.substring(0, newName.length()-1) + " Flambé");
				}else{
					return Util.capitalizeFirstLetterOfEveryWord(ingredient.getName() + " Flambé");
				}
			case grill:
				return Util.capitalizeFirstLetterOfEveryWord("Grilled " + ingredient.getName());
			case juice:
				if(ingredient.getName().endsWith("s")){
					String newName = ingredient.getName().replace("berrie", "berry").replace("atoe", "ato");
					return Util.capitalizeFirstLetterOfEveryWord(newName.substring(0, newName.length()-1) + " Juice");
				}else{
					return Util.capitalizeFirstLetterOfEveryWord(ingredient.getName() + " Juice");
				}
			case macerate:
				return Util.capitalizeFirstLetterOfEveryWord("Macerated " + ingredient.getName());
			case marinate:
				return Util.capitalizeFirstLetterOfEveryWord("Marinated " + ingredient.getName());
			case microwave:
				return Util.capitalizeFirstLetterOfEveryWord("Microwaved " + ingredient.getName());
			case pan_fry:
				return Util.capitalizeFirstLetterOfEveryWord("Pan fried " + ingredient.getName());
			case poach:
				return Util.capitalizeFirstLetterOfEveryWord("Poached " + ingredient.getName());
			case puree:
				return Util.capitalizeFirstLetterOfEveryWord("Pureéd " + ingredient.getName());
			case reduce:
				if(ingredient.getName().endsWith("s")){
					String newName = ingredient.getName().replace("berrie", "berry").replace("atoe", "ato");
					return Util.capitalizeFirstLetterOfEveryWord(newName.substring(0, newName.length()-1) + " Reduction");
				}else{
					return Util.capitalizeFirstLetterOfEveryWord(ingredient.getName() + " Reduction");
				}
			case rice:
				return Util.capitalizeFirstLetterOfEveryWord("Riced " + ingredient.getName());
			case roast:
				return Util.capitalizeFirstLetterOfEveryWord("Roasted " + ingredient.getName());
			case saute:
				return Util.capitalizeFirstLetterOfEveryWord("Sauteéd " + ingredient.getName());
			case sear:
				return Util.capitalizeFirstLetterOfEveryWord("Seared " + ingredient.getName());
			case simmer:
				return Util.capitalizeFirstLetterOfEveryWord("Simmered " + ingredient.getName());
			case sous_vide:
				return Util.capitalizeFirstLetterOfEveryWord("Sous vide " + ingredient.getName());
			case stew:
				if(ingredient.getName().endsWith("s")){
					String newName = ingredient.getName().replace("berrie", "berry").replace("atoe", "ato");
					return Util.capitalizeFirstLetterOfEveryWord(newName.substring(0, newName.length()-1) + " Stew");
				}else{
					return Util.capitalizeFirstLetterOfEveryWord(ingredient.getName() + " Stew");
				}
			case stir_fry:
				return Util.capitalizeFirstLetterOfEveryWord("Stir-Fried " + ingredient.getName());
			default:
				return Util.capitalizeFirstLetterOfEveryWord(ingredient.getName());
		}
	}
}
