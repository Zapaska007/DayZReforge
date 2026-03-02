$items = @("medical_chelated_tablets", "medical_disinfectant_spray", "medical_antidote_pox", "medical_alcoholic_tincture", "medical_iodine_tincture", "medical_sewing_kit", "medical_activated_carbon", "medical_codeine_tablets", "medical_chlorine_tablets", "medical_tetracycline_tablets", "medical_multivitamin_pills", "medical_thermometer", "medical_adrenaline", "medical_morphine", "medical_chemical_heating_pad", "medical_bandage", "medical_rags", "food_canned_crab", "food_bottle_filter", "food_chips", "food_salty_sticks", "food_crackers", "food_zagorky", "food_zagorky_chocolate", "food_zagorky_peanuts", "food_pajka", "food_liver_pate", "food_prok_pate", "food_canned_pork", "food_cold_cuts", "food_dog_food", "food_canned_cat_food", "food_mysterious_canned_goods", "food_canned_sardines", "food_strawberry_jam", "food_honey", "food_powdered_milk", "food_rice", "food_canned_spaghetti", "food_braised_beans", "food_canned_peaches", "food_canned_bacon", "food_canned_tuna", "food_mad_monk_kvass", "food_fronta_lemonade", "food_spite_lemonade", "food_nota_cola", "food_pipsi_cola", "food_flakes", "food_flask", "food_plastic_bottle", "food_human_meat", "dayzreforge_medicine", "dayzreforge_food")

$outDir = "c:\java\mods\DayzReforge\src\main\resources\assets\dayzreforge\models\item"
New-Item -ItemType Directory -Force -Path $outDir

foreach ($item in $items) {
    $json = "{`n  `"parent`": `"item/generated`",`n  `"textures`": {`n    `"layer0`": `"dayzreforge:item/$item`"`n  }`n}"
    Set-Content -Path "$outDir\$item.json" -Value $json
}
