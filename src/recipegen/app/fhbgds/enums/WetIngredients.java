package recipegen.app.fhbgds.enums;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum WetIngredients {
	chicken_broth,
	vegetable_broth,
	beef_broth,
	water,
	whole_milk,
	skim_milk,
	low_fat_milk,
	mayonnaise,
	woecestershire_sauce,
	soy_sauce,
	apple_cider_vinegar,
	white_vinegar,
	red_wine_vinegar,
	honey,
	molasses,
	olive_oil,
	canola_oil,
	peanut_oil,
	vanilla_extract,
	almond_extract,
	mint_extract,
	maple_extract,
	orange_extract,
	rum,
	gin,
	bourbon,
	whiskey,
	scotch_whisky,
	lime_juice,
	lemon_juice,
	cream_cheese,
	heavy_cream,
	greek_yogurt,
	plain_yogurt,
	cottage_cheese,
	unsalted_butter,
	salted_butter,
	sour_cream,
	ketchup,
	yellow_mustard,
	dijon_mustard,
	barbecue_sauce,
	thai_sweet_chili_sauce,
	hot_sauce,
	white_wine,
	red_wine;
	
	public static final List<WetIngredients> LARGE_QUANTITY_WET = Arrays.asList(new WetIngredients[]{
		chicken_broth,
		vegetable_broth,
		beef_broth,
		water,
		whole_milk,
		skim_milk,
		low_fat_milk,
		mayonnaise
	});
		
	public static final List<WetIngredients> SMALL_QUANTITY_WET = Arrays.asList(new WetIngredients[]{
		woecestershire_sauce,
		soy_sauce,
		apple_cider_vinegar,
		white_vinegar,
		red_wine_vinegar,
		honey,
		molasses,
		olive_oil,
		canola_oil,
		peanut_oil,
		vanilla_extract,
		almond_extract,
		mint_extract,
		maple_extract,
		orange_extract,
		rum,
		gin,
		bourbon,
		whiskey,
		scotch_whisky,
		lime_juice,
		lemon_juice,
		cream_cheese,
		heavy_cream,
		greek_yogurt,
		plain_yogurt,
		cottage_cheese,
		unsalted_butter,
		salted_butter,
		sour_cream,
		ketchup,
		yellow_mustard,
		dijon_mustard,
		barbecue_sauce,
		thai_sweet_chili_sauce,
		hot_sauce,
		chicken_broth,
		vegetable_broth,
		beef_broth,
		water,
		whole_milk,
		skim_milk,
		low_fat_milk,
		mayonnaise,
		white_wine,
		red_wine
	});
		
	public static final List<WetIngredients> MARINADE_INGREDIENTS = Arrays.asList(new WetIngredients[]{
		woecestershire_sauce,
		soy_sauce,
		apple_cider_vinegar,
		white_vinegar,
		red_wine_vinegar,
		honey,
		molasses,
		rum,
		gin,
		bourbon,
		whiskey,
		scotch_whisky,
		lime_juice,
		lemon_juice,
		ketchup,
		yellow_mustard,
		dijon_mustard,
		barbecue_sauce,
		thai_sweet_chili_sauce,
		hot_sauce,
		chicken_broth,
		vegetable_broth,
		beef_broth,
		water
	});
		
	public static final List<WetIngredients> ACIDS = Arrays.asList(new WetIngredients[]{
		apple_cider_vinegar,
		white_vinegar,
		red_wine_vinegar,
		white_wine,
		red_wine,
		lime_juice,
		lemon_juice
	});
		
	
	static SecureRandom rand = new SecureRandom();
	static List<WetIngredients> stocks = Arrays.asList(new WetIngredients[]{
		chicken_broth,
		vegetable_broth,
		beef_broth,
		water
	});
	static List<WetIngredients> oils = Arrays.asList(new WetIngredients[]{
		olive_oil,
		canola_oil,
		peanut_oil,
		unsalted_butter,
		salted_butter,
	});
	
	public static WetIngredients getRandomLargeQuantityWet(){
		return LARGE_QUANTITY_WET.get(rand.nextInt(LARGE_QUANTITY_WET.size()));
	}
	
	public static WetIngredients getRandomSmallQuantityWet(){
		return SMALL_QUANTITY_WET.get(rand.nextInt(SMALL_QUANTITY_WET.size()));
	}
	
	public static String getRandomStockString(){
		return stocks.get(rand.nextInt(stocks.size())).toString();
	}
	
	public static WetIngredients getRandomStock(){
		return stocks.get(rand.nextInt(stocks.size()));
	}
	
	public static String getRandomOilString(){
		return oils.get(rand.nextInt(oils.size())).toString();
	}
	
	public static WetIngredients getRandomOil(){
		return oils.get(rand.nextInt(oils.size()));
	}
	
	public static WetIngredients getRandomIngredientFromList(List<WetIngredients> pantry) {
		return pantry.get(rand.nextInt(pantry.size()));
	}

	/**
	 * @param amount
	 *            The amount of items you want in the list.
	 * @param maxVariance
	 *            The maximum you are willing to deviate from {@code amount}
	 * @return An {@link ArrayList} containing one or more {@link SmallQuantityWet} items from the {@code MARINADE_INGREDIENTS} list in {@link WetIngredients}.
	 */
	public static List<WetIngredients> getIngredientsFromList(int amount, int maxVariance, List<WetIngredients> pantry) {
		List<WetIngredients> list = new ArrayList<WetIngredients>();

		int variance = 0;
		if (maxVariance != 0) {
			variance = rand.nextInt(maxVariance);
		}
		int total = (rand.nextBoolean() ? amount + variance : amount - variance);
		if (total > pantry.size()) total = pantry.size();
		while (list.size() < total) {
			WetIngredients liquid = getRandomIngredientFromList(pantry);
			while (list.contains(liquid)) {
				liquid = getRandomIngredientFromList(pantry);
			}
			list.add(liquid);
		}

		return list;
	}

	@Override
	public String toString() {
		return this.name().replace("_", " ");
	}
}
