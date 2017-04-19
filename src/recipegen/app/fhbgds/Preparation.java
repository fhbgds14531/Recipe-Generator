package recipegen.app.fhbgds;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.List;

import recipegen.app.fhbgds.enums.DryIngredients;
import recipegen.app.fhbgds.enums.EnumMethod;
import recipegen.app.fhbgds.enums.IngredientType;
import recipegen.app.fhbgds.enums.WetIngredients;

public class Preparation {

	static SecureRandom rand = new SecureRandom();
	static DecimalFormat df = new DecimalFormat("###.#");

	public static Ingredient getRandomAmountLW(WetIngredients ingredient) {
		float amount = (rand.nextInt(500) + 1) * 10;
		String suffix = "mL";
		if (amount > 1000) {
			suffix = "L";
			amount /= 1000;
		}
		return new Ingredient(ingredient.toString(), Double.valueOf(df.format(amount)), suffix);
	}

	public static Ingredient getRandomAmountSW(WetIngredients ingredient) {
		int amount = (int) ((rand.nextInt(50) + 1) * 2.5);
		String suffix = "mL";
		return new Ingredient(ingredient.toString(), Double.valueOf(df.format(amount)), suffix);
	}

	public static Ingredient getRandomAmountLD(DryIngredients ingredient) {
		float amount = (rand.nextInt(100) + 1) * 10;
		String suffix = "g";
		if (amount > 1000) {
			suffix = "Kg";
			amount /= 1000;
		}
		return new Ingredient(ingredient.toString(), amount, suffix);
	}

	public static Ingredient getRandomAmountSD(DryIngredients ingredient) {
		int amount = (rand.nextInt(50) + 1) * 10;
		String suffix = "g";
		return new Ingredient(ingredient.toString(), amount, suffix);
	}
	
	public static Ingredient getRandomAmountSpice(DryIngredients ingredient) {
		float amount = (rand.nextFloat()) * 30;
		String suffix = "g";
		return new Ingredient(ingredient.toString(), Double.valueOf(df.format(amount)), suffix);
	}
	
	public static Ingredient getRandomAmountLiquidSpice(WetIngredients ingredient) {
		float amount = (rand.nextFloat()) * 10;
		String suffix = "mL";
		return new Ingredient(ingredient.toString(), Double.valueOf(df.format(amount)), suffix);
	}

	public static void executeMethod(Ingredient ingredient, EnumMethod method, Recipe recipe, boolean branch) {
		boolean marinated = false;
		List<DryIngredients> spices = DryIngredients.getSpices(rand.nextInt(2) + 4 + rand.nextInt(3), rand.nextInt(3));
		EnumMethod newMethod = EnumMethod.getRandomCookingMethod();
		if(EnumMethod.nonCookingMethods.contains(method)){
			if(method == EnumMethod.marinate){
				marinated = true;
			}
			if(!marinated){
				executeMethod(ingredient, newMethod, recipe, branch);
				ingredient.name = EnumMethod.getTitleFromMethodAndIngredient(newMethod, ingredient).toLowerCase();
			}
			
		}
		if(rand.nextBoolean() && !marinated && branch){
			if(rand.nextBoolean()){
				if(!EnumMethod.nonCookingMethods.contains(method)){
					newMethod = EnumMethod.nonCookingMethods.get(rand.nextInt(EnumMethod.nonCookingMethods.size()));
					executeMethod(ingredient, newMethod, recipe, branch);
					ingredient.name = EnumMethod.getTitleFromMethodAndIngredient(newMethod, ingredient).toLowerCase();
				}else{
					newMethod = EnumMethod.getRandomCookingMethod();
					executeMethod(ingredient, newMethod, recipe, branch);
					ingredient.name = EnumMethod.getTitleFromMethodAndIngredient(newMethod, ingredient).toLowerCase();
				}
			}
		}
		String pluralizer = (ingredient.getName().endsWith("s") ? "" : "s");
		String inversePluralizer = (ingredient.getName().endsWith("s") ? "" : "s");
		String isAre = ingredient.getName().endsWith("s") ? " are " : " is ";
		String itThem = ingredient.getName().endsWith("s") ? "them" : "it";
		WetIngredients oil;
		WetIngredients stock;
		recipe.newPrepSection(EnumMethod.getTitleFromMethodAndIngredient(method, ingredient));
		switch (method) {
			case boil:
				recipe.addIngredient(WetIngredients.water, false, IngredientType.LARGE_QUANTITY_WET);
				recipe.addStep("Add enough water to a pot to cover the " + ingredient.getName() + ".");
				recipe.addStep("Bring the water to a boil.");
				recipe.addStep("Add the " + ingredient.getName() + " to the boiling water.");
				recipe.addStep("Return to a boil and boil the " + ingredient.getName() + " for " + ((rand.nextInt(5) * 5) + 5) + " minutes.");
				recipe.addStep("Remove from heat and strain out the water.");
				break;
			case braise:
				stock = WetIngredients.getRandomStock();
				oil = WetIngredients.getRandomOil();
				recipe.addIngredient(oil, true, IngredientType.SMALL_QUANTITY_WET);
				recipe.addIngredient(stock, true, IngredientType.LARGE_QUANTITY_WET);
				recipe.beginIngredientCategory("Mirepoix");
				recipe.addIngredient(DryIngredients.carrots, true, IngredientType.LARGE_QUANTITY_DRY);
				recipe.addIngredient(DryIngredients.yellow_onion, true, IngredientType.LARGE_QUANTITY_DRY);
				recipe.addIngredient(DryIngredients.celery, true, IngredientType.LARGE_QUANTITY_DRY);
				recipe.endIngredientCategory();
				recipe.addStep("Large dice the mirepoix.");
				recipe.addStep("Add the " + oil.toString() + " to a large pot over medium-high heat.");
				recipe.addStep("Sear the " + ingredient.getName() + " until evenly browned on all sides.");
				recipe.addStep("Remove the " + ingredient.getName() + " from the pot and set aside.");
				recipe.addStep("Add the mirepoix to the pot and sauté until they begin to brown.");
				recipe.addStep("Add the " + stock.toString() + " and deglaze the pot");
				recipe.addStep("Return the " + ingredient.getName() + " to the pot and bring up to a simmer.");
				recipe.addStep("Move the pot into a 160°C oven for " + Util.getRandomTimeLong() + ".");
				break;
			case deep_fry:
				oil = WetIngredients.getRandomOil();
				recipe.addIngredient(oil, false, IngredientType.SMALL_QUANTITY_WET);
				recipe.addStep("Add enough " + oil.toString() + " to a pot to be able to completely submerge the " + ingredient.getName() + ".");
				recipe.addStep("Heat the " + oil.toString() + " to roughly 175°C.");
				recipe.addStep("Carefully lower the " + ingredient.getName() + " into the hot " + oil.toString() + ".");
				recipe.addStep("Fry until golden brown, flipping the " + ingredient.getName() + " roughly every minute or so.");
				recipe.addStep("Remove from pot and let cool for 1 minute.");
				break;
			case dry_roast:
				recipe.addStep("Place the " + ingredient.getName() + " in a large flat pan over high heat.");
				recipe.addStep("Stir continuously for 5 to 10 minutes until the " + ingredient.getName() + " begin" + pluralizer + " to become fragrant.");
				recipe.addStep("Remove from heat and let sit for several minutes.");
				break;
			case flambe:
				recipe.addStep("Put the " + ingredient.getName() + " in a ramekin.");
				recipe.addStep("Use a blowtorch to lightly brown the top of the " + ingredient.getName() + ".");
				break;
			case grill:
				recipe.addStep("Place the " + ingredient.getName() + " on the hot grill.");
				recipe.addStep("Grill until the " + ingredient.getName() + " reaches an internal temperature of at least 70°C, flipping every 5 minutes.");
				recipe.addStep("Remove the " + ingredient.getName() + " from the heat.");
				break;
			case juice:
				recipe.addStep("Separate the " + ingredient.getName() + " into small quantities.");
				recipe.addStep("Run the " + ingredient.getName() + " through a juicer (or press out the juice), collecting the liquid in a container.");
				recipe.addStep("Discard the solids that are left over.");
				break;
			case macerate:
				recipe.addStep("Separate the " + ingredient.getName() + " into small quantities.");
				recipe.addStep("Run the " + ingredient.getName() + " through a juicer (or press out the juice), collecting the solids in a container.");
				recipe.addStep("Discard the juice that is left over.");
				break;
			case marinate:
				WetIngredients marinadeBase = WetIngredients.getRandomIngredientFromList(WetIngredients.MARINADE_INGREDIENTS);
				List<WetIngredients> marinadeLiquidIngredients = WetIngredients.getIngredientsFromList(3, 1, WetIngredients.MARINADE_INGREDIENTS);
				marinadeLiquidIngredients.add(0, marinadeBase);
				recipe.beginIngredientCategory("Marinade");
				recipe.addSpices(spices, true);
				recipe.addLiquidSpices(marinadeLiquidIngredients, true);
				recipe.endIngredientCategory();
				recipe.addStep("Mix the marinade ingredients together and put them in a bag or container with the " + ingredient.getName());
				recipe.addStep("Put the whole mixture in the fridge for " + Util.getRandomTimeLong() + ".");
				recipe.addStep("Remove from fridge and remove the " + ingredient.getName() + " from the mixture, setting "+ itThem +" aside.");
				recipe.addStep("Discard the remaining mixture.");
				break;
			case microwave:
				int powerlevel = rand.nextInt(3);
				int timeRangeMod = 1;
				String powerLevel = "";
				switch (powerlevel) {
					case 0:
						powerLevel = "low";
						timeRangeMod = 5;
						break;
					case 1:
						powerLevel = "medium";
						timeRangeMod = 3;
						break;
					case 2:
						powerLevel = "high";
						timeRangeMod = 2;
						break;

				}
				recipe.addStep("Place the " + ingredient.getName() + " in a microwave safe container.");
				recipe.addStep(
						"Put the container in the microwave and cook on " + powerLevel + " for " + ((rand.nextInt(5) + 1) * timeRangeMod) + (rand.nextBoolean() ? "" : ".5") + " minutes.");
				break;
			case pan_fry:
				oil = WetIngredients.getRandomOil();
				recipe.addIngredient(oil, false, IngredientType.SMALL_QUANTITY_WET);
				recipe.addStep("To a frying pan, add enough " + oil.toString() + " to cover the bottom.");
				recipe.addStep("Apply medium-high heat and wait until the " + oil.toString() + " is around 170°C.");
				recipe.addStep("Carefully set the " + ingredient.getName() + " into the " + oil.toString() + ".");
				recipe.addStep("Fry until golden brown, turning once each side browns.");
				recipe.addStep("Remove the " + ingredient.getName() + " from the " + oil.toString() + ", and set it on a drying rack or paper towel to remove some of the " + oil.toString() + ".");
				break;
			case poach:
				stock = WetIngredients.getRandomStock();
				WetIngredients acid = WetIngredients.getRandomIngredientFromList(WetIngredients.ACIDS);
				spices = DryIngredients.getSpices(3, 1);
				int boilTime = (rand.nextInt(3) + 1);
				
				recipe.addIngredient(stock, true, IngredientType.LARGE_QUANTITY_WET);
				recipe.addIngredient(acid, true, IngredientType.SMALL_QUANTITY_WET);
				recipe.beginIngredientCategory("Mirepoix");
					recipe.addIngredient(DryIngredients.carrots, true, IngredientType.LARGE_QUANTITY_DRY);
					recipe.addIngredient(DryIngredients.yellow_onion, true, IngredientType.LARGE_QUANTITY_DRY);
					recipe.addIngredient(DryIngredients.celery, true, IngredientType.LARGE_QUANTITY_DRY);
				recipe.endIngredientCategory();
				recipe.beginIngredientCategory("Bouquet Garni");
					recipe.addSpices(spices, true);
				recipe.endIngredientCategory();
				
				recipe.addStep("Large dice the mirepoix.");
				recipe.addStep("Mix the " + acid.toString() + " and " + stock.toString() + " in a large pot.");
				recipe.addStep("Place the spices listed under the boquet garni section in a spice bag and add it to the pot.");
				recipe.addStep("Add the " + ingredient.getName() + " and bring the pot to a boil.");
				recipe.addStep("Boil on high for " + boilTime + " to " + (boilTime + 2) + " minutes.");
				recipe.addStep("Cover the pot and let it sit until the " + ingredient.getName() + " " + isAre + " fully cooked.");
				recipe.addStep("Remove the " + ingredient.getName() + " from the poaching fluid immidiately.");
				break;
			case puree:
				recipe.addStep("Using either a food processor or an immersion blender, blend the " + ingredient.getName() + 
						" until " + itThem.replace("them", "they") + " become" + inversePluralizer + " a paste or liquid.");
				break;
			case reduce:
				int time = rand.nextInt(3) + 1;
				recipe.addIngredient(WetIngredients.water, false, IngredientType.LARGE_QUANTITY_WET);
				recipe.addStep("Dissolve the " + ingredient.getName() + " in a pot of water.");
				recipe.addStep("Continue to heat until the " + ingredient.getName() + isAre + "fully dissolved.");
				recipe.addStep("Let the solution simmer until the volume is reduced by at least three quarters.");
				recipe.addStep("Remove from heat and let stand for " + time + " minute" + (time == 1 ? "" : "s") + ".");
				break;
			case rice:
				recipe.addStep("Separate the " + ingredient.getName() + " into small enough portions to fit into a ricer.");
				recipe.addStep("Pass the portioned out " + ingredient.getName() + " through the ricer into a large mixing bowl.");
				break;
			case roast:
				spices = DryIngredients.getSpices(3, 2);
				oil = WetIngredients.getRandomOil();
				recipe.addIngredient(oil, false, IngredientType.SMALL_QUANTITY_WET);
				recipe.addIngredient(DryIngredients.yellow_onion, true, IngredientType.LARGE_QUANTITY_DRY);
				recipe.addIngredient(DryIngredients.russet_potatoes, true, IngredientType.LARGE_QUANTITY_DRY);
				recipe.addSpices(spices, true);
				recipe.addStep("Preheat the oven to 200°C");
				recipe.addStep("Into an appropriately sized roasting pan, place the " + ingredient.getName() + ", onion, and potato and sprinkle the spices over everything.");
				recipe.addStep("Lightly glaze the surface of everything in the pan with the " + oil.toString() + ".");
				recipe.addStep("Put the pan into the hot oven and let it roast for " + Util.getRandomTimeLong() + ", or until the " + ingredient.getName() + isAre + "cooked through.");
				break;
			case saute:
				oil = WetIngredients.getRandomOil();
				recipe.addIngredient(oil, false, IngredientType.SMALL_QUANTITY_WET);
				spices = DryIngredients.getSpices(2, 1);
				recipe.addSpices(spices, true);
				recipe.addStep("Add just enough " + oil.toString() + " to a pan to cover the bottom and apply medium-high heat.");
				recipe.addStep("Once hot (hot pan, hot oil, food no stick), add the " + ingredient.getName() + " and the spices to the pan.");
				recipe.addStep("Agitate frquently until the " + ingredient.getName() + " achieves a light brown color.");
				recipe.addStep("Remove from heat.");
				break;
			case sear:
				break;
			case simmer:
				break;
			case sous_vide:
				break;
			case stew:
				break;
			case stir_fry:
				oil = WetIngredients.getRandomOil();
				recipe.addIngredient(oil, false, IngredientType.SMALL_QUANTITY_WET);
				recipe.addStep("Apply high heat to an empty wok.");
				recipe.addStep("Just as the wok begins to smoke, add the " + oil.toString() + " down the side of the wok.");
				recipe.addStep("Add the " + ingredient.getName() + " to the wok and agitate continuously until golden brown.");
				recipe.addStep("Remove from heat.");
				break;
			default:
				break;

		}
		if(marinated){
			executeMethod(ingredient, newMethod, recipe, branch);
			ingredient.name = EnumMethod.getTitleFromMethodAndIngredient(newMethod, ingredient).toLowerCase();
		}
	}
}
