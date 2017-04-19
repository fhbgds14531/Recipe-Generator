package recipegen.app.fhbgds.enums;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DryIngredients {
	apple,
	pear,
	saffron,
	sugar,
	kosher_salt,
	baking_powder,
	baking_soda,
	cream_of_tartar,
	cocoa_powder,
	brown_sugar,
	cinnamon,
	freshly_ground_black_pepper,
	white_pepper,
	basil,
	oregano,
	parsley,
	garlic_powder,
	onion_powder,
	nutmeg,
	cumin,
	chili_powder,
	cayenne_powder,
	red_pepper_flakes,
	thyme,
	fennel,
	chia_seeds,
	minced_garlic,
	sliced_jalapeño,
	sliced_black_olives,
	whole_black_olives,
	sliced_green_olives,
	whole_green_olives,
	pumpkin_seeds,
	sunflower_seeds,
	almonds,
	macadamia_nuts,
	walnuts,
	pine_nuts,
	cashews,
	pecans,
	sun_dried_tomatoes,
	grated_parmesean,
	shredded_parmesean,
	feta,
	shredded_mozzarella,
	powdered_ginger,
	fresh_ginger_root,
	blueberries,
	strawberries,
	raspberries,
	cherries,
	blackberries,
	panko,
	cracker_meal,
	lentils,
	sweetcorn,
	pineapple,
	jasmine_rice,
	brown_rice,
	wild_rice,
	black_beans,
	pinto_beans,
	popcorn,
	ground_beef,
	angel_hair_pasta,
	spaghetti,
	ricotta,
	cheddar_cheese,
	pepperjack_cheese,
	brie,
	green_leaf_lettuce,
	romaine_lettuce,
	cabbage,
	ground_turkey,
	itialian_sausage,
	bacon,
	fettuccine_noodles,
	yakisoba_noodles,
	green_bell_pepper,
	red_bell_pepper,
	yellow_bell_pepper,
	orange_bell_pepper,
	carrots,
	yellow_onion,
	red_onion,
	celery,
	shallot,
	chicken_breast,
	all_purpose_flour,
	bread_flour,
	sliced_pepperoni,
	sliced_Genoa_salami,
	all_beef_hot_dogs,
	green_beans,
	grape_tomatoes,
	kosher_baby_dill_pickles,
	cucumber,
	zuccini,
	spinach,
	portabello_mushrooms,
	champignon_mushrooms,
	iceburg_lettuce,
	flank_steak,
	skirt_steak,
	shrimp,
	tilapia,
	halibut,
	cod,
	chicken_thigh,
	chicken_wing,
	peanuts,
	russet_potatoes;
	
	public static final List<DryIngredients> LARGE_QUANTITY_DRY = Arrays.asList(new DryIngredients[]{
		lentils,
		sweetcorn,
		pineapple,
		jasmine_rice,
		brown_rice,
		wild_rice,
		black_beans,
		pinto_beans,
		popcorn,
		ground_beef,
		angel_hair_pasta,
		spaghetti,
		ricotta,
		cheddar_cheese,
		pepperjack_cheese,
		brie,
		green_leaf_lettuce,
		romaine_lettuce,
		cabbage,
		ground_turkey,
		itialian_sausage,
		bacon,
		fettuccine_noodles,
		yakisoba_noodles,
		green_bell_pepper,
		red_bell_pepper,
		yellow_bell_pepper,
		orange_bell_pepper,
		carrots,
		yellow_onion,
		red_onion,
		celery,
		shallot,
		chicken_breast,
		all_purpose_flour,
		bread_flour,
		sliced_pepperoni,
		sliced_Genoa_salami,
		all_beef_hot_dogs,
		green_beans,
		grape_tomatoes,
		kosher_baby_dill_pickles,
		cucumber,
		zuccini,
		spinach,
		portabello_mushrooms,
		champignon_mushrooms,
		iceburg_lettuce,
		flank_steak,
		skirt_steak,
		shrimp,
		tilapia,
		halibut,
		cod,
		chicken_thigh,
		chicken_wing,
		peanuts,
		almonds,
		cashews,
		russet_potatoes
	});
		
	public static final List<DryIngredients> SMALL_QUANTITY_DRY = Arrays.asList(new DryIngredients[]{
		apple,
		pear,
		saffron,
		sugar,
		kosher_salt,
		baking_powder,
		baking_soda,
		cream_of_tartar,
		cocoa_powder,
		brown_sugar,
		cinnamon,
		freshly_ground_black_pepper,
		white_pepper,
		basil,
		oregano,
		parsley,
		garlic_powder,
		onion_powder,
		nutmeg,
		cumin,
		chili_powder,
		cayenne_powder,
		red_pepper_flakes,
		thyme,
		fennel,
		chia_seeds,
		minced_garlic,
		sliced_jalapeño,
		sliced_black_olives,
		whole_black_olives,
		sliced_green_olives,
		whole_green_olives,
		pumpkin_seeds,
		sunflower_seeds,
		almonds,
		macadamia_nuts,
		walnuts,
		pine_nuts,
		cashews,
		pecans,
		sun_dried_tomatoes,
		grated_parmesean,
		shredded_parmesean,
		feta,
		shredded_mozzarella,
		powdered_ginger,
		fresh_ginger_root,
		blueberries,
		strawberries,
		raspberries,
		cherries,
		blackberries,
		panko,
		cracker_meal,
		russet_potatoes
	});
		
	public static final List<DryIngredients> SPICES = Arrays.asList(new DryIngredients[] {
		sugar,
		kosher_salt,
		saffron,
		cocoa_powder,
		brown_sugar,
		cinnamon,
		freshly_ground_black_pepper,
		white_pepper,
		basil,
		oregano,
		parsley,
		garlic_powder,
		onion_powder,
		nutmeg,
		cumin,
		chili_powder,
		cayenne_powder,
		red_pepper_flakes,
		thyme,
		fennel,
		minced_garlic,
		sliced_jalapeño,
		sun_dried_tomatoes,
		powdered_ginger,
		fresh_ginger_root
	});
		
	public static final List<DryIngredients> ALL_DRY = Arrays.asList(DryIngredients.values());

	public static DryIngredients getRandomSmallQuantityDry() {
		return SMALL_QUANTITY_DRY.get(rand.nextInt(SMALL_QUANTITY_DRY.size()));
	}
	
	public static DryIngredients getRandomLargeQuantityDry() {
		return LARGE_QUANTITY_DRY.get(rand.nextInt(LARGE_QUANTITY_DRY.size()));
	}

	private static SecureRandom rand = new SecureRandom();

	public static DryIngredients getRandomSpice() {
		return SPICES.get(rand.nextInt(SPICES.size()));
	}

	/**
	 * @param amount
	 *            The amount of items you want in the list.
	 * @param maxVariance
	 *            The maximum you are willing to deviate from {@code amount}
	 * @return An {@link ArrayList} containing one or more {@link SmallQuantityDry} items from the {@code SPICES} list in {@link DryIngredients}.
	 */
	public static List<DryIngredients> getSpices(int amount, int maxVariance) {
		List<DryIngredients> list = new ArrayList<DryIngredients>();

		int variance = 0;
		if (maxVariance != 0) {
			variance = rand.nextInt(maxVariance);
		}
		int total = (rand.nextBoolean() ? amount + variance : amount - variance);
		if (total > SPICES.size()) total = SPICES.size();
		while (list.size() < total) {
			DryIngredients spice = getRandomSpice();
			while (list.contains(spice)) {
				spice = getRandomSpice();
			}
			list.add(spice);
		}

		return list;
	}

	@Override
	public String toString() {
		return this.name().replace("_", " ");
	}
}
