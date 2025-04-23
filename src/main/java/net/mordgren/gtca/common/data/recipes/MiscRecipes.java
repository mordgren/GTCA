package net.mordgren.gtca.common.data.recipes;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.mordgren.gtca.common.data.*;
import net.mordgren.gtca.common.util.GTCAHelper;

import java.util.ArrayList;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static net.mordgren.gtca.common.data.GTCAMaterials.*;
import static net.mordgren.gtca.common.data.GTCAMaterials.HastelloyN;

public class MiscRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {

        ///PROTON CELL
        ASSEMBLY_LINE_RECIPES.recipeBuilder("proton_cell_empty")
                .inputItems(GTCAHelper.getItem("plate", Nitinol60, 16))
                .inputItems(GTCAHelper.getItem("cableGtOctal", YttriumBariumCuprate, 32))
                .inputItems(GTCAHelper.getItem("gear", Nitinol60, 4))
                .inputItems(GTItems.VOLTAGE_COIL_EV.asStack(64))
                .inputFluids(Zeron182.getFluid(1152))
                .inputFluids(HastelloyN.getFluid(2304))
                .inputFluids(LafiumCompound.getFluid(2304))
                .outputItems(GTCAItems.PROTON_CELL_EMPTY.asStack())
                .scannerResearch(b -> b
                        .researchStack(GTCAItems.UnknownParticle.asStack())
                        .duration(1125)
                        .EUt(VA[EV]))
                .duration(2400).EUt(32768)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("proton_cell")
                .inputItems(GTCAItems.PROTON_CELL_EMPTY.asStack())
                .inputItems(GTCAItems.Proton.asStack(16))
                .inputItems(GTCAHelper.getItem("plate", Zeron182, 16))
                .inputItems(CustomTags.LuV_CIRCUITS, 8)
                .inputItems(GTCAHelper.getItem("wireGtHex", YttriumBariumCuprate, 32))
                .inputItems(GTCAHelper.getItem("bolt", TriniumNaquadahCarbonite, 8))
                .inputItems(GTCAHelper.getItem("screw", Nitinol60, 8))
                .inputItems(GTItems.ENERGIUM_DUST.asStack(16))
                .inputFluids(Zeron182.getFluid(2304))
                .inputFluids(HastelloyN.getFluid(4608))
                .inputFluids(LafiumCompound.getFluid(2304))
                .outputItems(GTCAItems.PROTON_CELL.asStack())
                .scannerResearch(b -> b
                        .researchStack(GTCAItems.Proton.asStack())
                        .duration(1125)
                        .EUt(VA[EV]))
                .duration(2400).EUt(32768)
                .save(provider);

        CANNER_RECIPES.recipeBuilder("proton_capacitor").duration(200).EUt(VA[EV])
                .inputItems(GTBlocks.BATTERY_EMPTY_TIER_II.asStack())
                .inputItems(GTCAItems.PROTON_CELL.asStack())
                .outputItems(GTCABlocks.BATTERY_PROTON_CELL).save(provider);
        PACKER_RECIPES.recipeBuilder("proton_capacitor").duration(200).EUt(30).circuitMeta(2)
                .inputItems(GTCABlocks.BATTERY_PROTON_CELL)
                .outputItems(GTBlocks.BATTERY_EMPTY_TIER_II.asStack())
                .outputItems(GTCAItems.PROTON_CELL.asStack()).save(provider);

        /// ELECTRON CELL
        ASSEMBLY_LINE_RECIPES.recipeBuilder("electron_cell_empty")
                .inputItems(GTCAHelper.getItem("plate", Zeron182, 16))
                .inputItems(GTCAHelper.getItem("cableGtOctal", Naquadah, 32))
                .inputItems(GTCAHelper.getItem("gear", Zeron182, 4))
                .inputItems(GTItems.VOLTAGE_COIL_IV.asStack(64))
                .inputItems(GTItems.ENERGIUM_DUST.asStack(16))
                .inputFluids(Pikyonium64Y.getFluid(1152))
                .inputFluids(CinobiteA241.getFluid(2304))
                .outputItems(GTCAItems.ELECTRON_CELL_EMPTY.asStack())
                .scannerResearch(b -> b
                        .researchStack(GTCAItems.PROTON_CELL_EMPTY.asStack())
                        .duration(1125)
                        .EUt(VA[EV]))
                .duration(2400).EUt(131072)
                .save(provider);

        ASSEMBLY_LINE_RECIPES.recipeBuilder("electron_cell")
                .inputItems(GTCAItems.ELECTRON_CELL_EMPTY.asStack())
                .inputItems(GTCAItems.Electron.asStack(16))
                .inputItems(GTCAHelper.getItem("plate", Pikyonium64Y, 16))
                .inputItems(CustomTags.ZPM_CIRCUITS, 16)
                .inputItems(GTCAHelper.getItem("wireGtHex", Naquadah, 32))
                .inputItems(GTCAHelper.getItem("bolt", NaquadahAlloy, 8))
                .inputItems(GTCAHelper.getItem("screw", Zeron182, 8))
                .inputItems(GTItems.ENERGIUM_DUST.asStack(32))
                .inputFluids(Pikyonium64Y.getFluid(2304))
                .inputFluids(CinobiteA241.getFluid(2304))
                .inputFluids(TriniumNaquadahCarbonite.getFluid(2304))
                .outputItems(GTCAItems.ELECTRON_CELL.asStack())
                .scannerResearch(b -> b
                        .researchStack(GTCAItems.Electron.asStack())
                        .duration(1125)
                        .EUt(VA[EV]))
                .duration(2400).EUt(131072)
                .save(provider);

        CANNER_RECIPES.recipeBuilder("electron_capacitor").duration(400).EUt(VA[EV])
                .inputItems(GTBlocks.BATTERY_EMPTY_TIER_II.asStack())
                .inputItems(GTCAItems.ELECTRON_CELL.asStack())
                .outputItems(GTCABlocks.BATTERY_ELECTRON_CELL).save(provider);
        PACKER_RECIPES.recipeBuilder("electron_capacitor").duration(200).EUt(30).circuitMeta(2)
                .inputItems(GTCABlocks.BATTERY_ELECTRON_CELL)
                .outputItems(GTBlocks.BATTERY_EMPTY_TIER_II.asStack())
                .outputItems(GTCAItems.ELECTRON_CELL.asStack()).save(provider);


        CENTRIFUGE_RECIPES.recipeBuilder("pahoehoe_nugget").duration(40).EUt(VA[EV])
                .inputFluids(PahoehoeLava.getFluid(100))
                .circuitMeta(10)
                .chancedOutput(GTCAHelper.getItem("nugget", Copper, 1), 2000, 0)
                .chancedOutput(GTCAHelper.getItem("nugget", Tin, 1), 1000, 0)
                .chancedOutput(GTCAHelper.getItem("nugget", Silver, 1), 250, 0)
                .chancedOutput(GTCAHelper.getItem("smallDust", Phosphorus, 1), 50, 0)
                .chancedOutput(GTCAHelper.getItem("smallDust", Scheelite, 1), 250, 0)
                .chancedOutput(GTCAHelper.getItem("smallDust", Bauxite, 1), 500, 0)
                .save(provider);

        CENTRIFUGE_RECIPES.recipeBuilder("pahoehoe_ingot").duration(328).EUt(VA[EV])
                .inputFluids(PahoehoeLava.getFluid(3600))
                .circuitMeta(20)
                .chancedOutput(GTCAHelper.getItem("ingot", Copper, 1), 8000, 0)
                .chancedOutput(GTCAHelper.getItem("ingot", Tin, 1), 4000, 0)
                .chancedOutput(GTCAHelper.getItem("ingot", Silver, 1), 1000, 0)
                .chancedOutput(GTCAHelper.getItem("dust", Phosphorus, 1), 450, 0)
                .chancedOutput(GTCAHelper.getItem("dust", Scheelite, 1), 2250, 0)
                .chancedOutput(GTCAHelper.getItem("dust", Bauxite, 1), 4500, 0)
                .save(provider);

        // nanites

        ASSEMBLY_LINE_RECIPES.recipeBuilder("carbon_nanites_assline").duration(1000).EUt(VA[UV])
                .inputItems(CustomTags.UV_CIRCUITS, 16)
                .inputItems(ROBOT_ARM_UV, 16)
                .inputItems(STEM_CELLS, 32)
                .inputItems(GTCAHelper.getItem("ring", NaquadahAlloy, 32))
                .inputItems(GTCAHelper.getItem("rod", NaquadahAlloy, 16))
                .inputItems(GTCAHelper.getItem("dust", Carbon, 64))
                .inputFluids(UUMatter.getFluid(10000))
                .outputItems(GTCAItems.CarbonNanites.asStack(2))
                .scannerResearch(b -> b
                        .researchStack(GTCAHelper.getItem("dust", Carbon, 1))
                        .duration(6000)
                        .EUt(VA[ZPM]))
                .save(provider);

        // PNE resistor

        ASSEMBLER_RECIPES.recipeBuilder("pne_resistor").duration(580).EUt(VA[LuV])
                .inputItems(GTCAHelper.getItem("plate", Neutronex, 2))
                .inputItems(GTCAHelper.getItem("foil", Osmiridium, 8))
                .inputItems(GTCAHelper.getItem("bolt", Berwollium, 4))
                .inputItems(GTCAHelper.getItem("foil", Polybenzimidazole, 24))
                .inputItems(GTItems.QUANTUM_STAR, 4)
                .inputFluids(Naquadah.getFluid(864))
                .outputItems(GTCAItems.PNEresistor, 2)
                .save(provider);


        // strange crystal line

        COMPRESSOR_RECIPES.recipeBuilder("strange_crystal_plates").duration(120).EUt(VA[LV])
                .inputItems(GTCAHelper.getItem("dust", StrangeCrystal, 1))
                .outputItems(GTCAHelper.getItem("plate", StrangeCrystal, 1))
                .save(provider);

        LASER_ENGRAVER_RECIPES.recipeBuilder("strange_crystal_plates_to_crystal").duration(120).EUt(VA[HV])
                .inputItems(GTCAHelper.getItem("plate", StrangeCrystal, 9))
                .notConsumable(TagPrefix.lens, Diamond, 1)
                .outputItems(GTCAHelper.getItem("gem", StrangeCrystal, 1))
                .save(provider);

        LASER_ENGRAVER_RECIPES.recipeBuilder("strange_crystal_to_unknown_flawless").duration(220).EUt(VA[ZPM])
                .inputItems(GTCAHelper.getItem("gem", StrangeCrystal, 3))
                .notConsumable(TagPrefix.lens, Diamond, 1)
                .outputItems(GTCAHelper.getItem("flawlessGem", UnknownCrystal, 1))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);

        LASER_ENGRAVER_RECIPES.recipeBuilder("flawless_unknown_crystal_to_ex").duration(270).EUt(VA[ZPM])
                .inputItems(GTCAHelper.getItem("flawlessGem", UnknownCrystal, 3))
                .notConsumable(TagPrefix.lens, Diamond, 1)
                .outputItems(GTCAHelper.getItem("exquisiteGem", UnknownCrystal, 1))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);

        CHEMICAL_BATH_RECIPES.recipeBuilder("ex_to_lens").duration(370).EUt(VA[UV])
                .inputItems(GTCAHelper.getItem("exquisiteGem", UnknownCrystal, 1))
                .inputFluids(UUMatter.getFluid(144))
                .outputItems(GTCAHelper.getItem("lens", QuiteCertainCrystal, 1))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .save(provider);

        CHEMICAL_BATH_RECIPES.recipeBuilder("strange_dust").duration(990).EUt(VA[IV])
                .inputItems(GTCAHelper.getItem("dust", NetherStar, 1))
                .inputFluids(UUMatter.getFluid(6000))
                .outputItems(GTCAHelper.getItem("dust", StrangeCrystal, 1))
                .save(provider);


        LATHE_RECIPES.recipeBuilder("radon_polymer_lens").EUt(7864320).duration(1800)
                .inputItems(GTCAHelper.getItem("plate", RadonPolymer, 1))
                .outputItems(GTCAHelper.getItem("lens", RadonPolymer, 1))
                .save(provider);

    /// MATTER
        setScraps();
        for (Object[] objectType : Scraps) {
            ItemStack dust = (GTCAHelper.getItem("dust", (Material) objectType[0], 1));
            GTCARecipeTypes.RECYCLER.recipeBuilder(objectType[1].toString()).EUt(1).duration(46)
                    .inputItems(dust)
                    .chancedOutput(GTCAItems.Scrap.asStack(1), 725, 0)
                    .save(provider);
        }
        GTCARecipeTypes.UU_MATTER_AMPLIFICATOR.recipeBuilder("amplifier").duration(100).EUt(VA[IV])
                .inputItems(GTCAItems.Scrap.asStack(9))
                .outputFluids(UUMatterAmplifier.getFluid(1))
                .save(provider);

        GTCARecipeTypes.UU_MATTER_FABRICATOR.recipeBuilder("uumatter_amped").duration(800).EUt(VA[IV])
                .circuitMeta(1)
                .inputFluids(UUMatterAmplifier.getFluid(1))
                .outputFluids(UUMatter.getFluid(1))
                .save(provider);

        GTCARecipeTypes.UU_MATTER_FABRICATOR.recipeBuilder("uumatter_std").duration(1100).EUt(VA[IV])
                .circuitMeta(2)
                .outputFluids(UUMatter.getFluid(1))
                .save(provider);

        // scrapbox

        VanillaRecipeHelper.addShapedRecipe(provider, true, "scrapbox",
                /// Output
                GTCAItems.ScrapBox.asStack(),
                /// Pattern
                "AAA", "AAA", "AAA",
                /// Ingredients definition
                'A', GTCAItems.Scrap.asStack());

        PACKER_RECIPES.recipeBuilder("scrapbox_in_packer").duration(60).EUt(VA[LV])
                .inputItems(GTCAItems.Scrap, 9)
                .outputItems(GTCAItems.ScrapBox, 1)
                .save(provider);
    }



    private static ArrayList<Object[]> Scraps;

    private static void setScraps() {
        Scraps = new ArrayList<>();

        Scraps.add(new Object[]{Stone, "stone"});
        Scraps.add(new Object[]{Endstone, "endstone"});
        Scraps.add(new Object[]{Deepslate, "deepslate"});
        Scraps.add(new Object[]{Netherrack, "netherrack"});
        Scraps.add(new Object[]{Ash, "ash"});
        Scraps.add(new Object[]{DarkAsh, "dash"});
    }

}
