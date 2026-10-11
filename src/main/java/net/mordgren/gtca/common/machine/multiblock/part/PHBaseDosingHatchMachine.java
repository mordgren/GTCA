package net.mordgren.gtca.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.jei.IngredientIO;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.world.item.ItemStack;

public class PHBaseDosingHatchMachine extends MultiblockPartMachine {

    public static final int SLOT_COUNT = 4;

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(
                    PHBaseDosingHatchMachine.class,
                    MultiblockPartMachine.MANAGED_FIELD_HOLDER
            );

    @Persisted
    public final NotifiableItemStackHandler inventory;

    public PHBaseDosingHatchMachine(IMachineBlockEntity holder) {
        super(holder);


        inventory = new NotifiableItemStackHandler(
                this,
                SLOT_COUNT,
                IO.NONE,
                IO.IN
        );

        inventory.setFilter(this::isSodiumHydroxide);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    private boolean isSodiumHydroxide(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }

        ItemStack target = ChemicalHelper.get(
                TagPrefix.dust,
                GTMaterials.SodiumHydroxide
        );

        return !target.isEmpty()
                && stack.is(target.getItem());
    }

    public int getStoredAmount() {
        int total = 0;

        for (int slot = 0;
             slot < inventory.storage.getSlots();
             slot++) {

            total += inventory.storage
                    .getStackInSlot(slot)
                    .getCount();
        }

        return total;
    }

    public int consume(int requested) {
        if (requested <= 0) {
            return 0;
        }

        int remaining = requested;
        int consumed = 0;

        for (int slot = 0;
             slot < inventory.storage.getSlots();
             slot++) {

            if (remaining <= 0) {
                break;
            }

            ItemStack stack =
                    inventory.storage.getStackInSlot(slot);

            if (stack.isEmpty()) {
                continue;
            }

            ItemStack extracted =
                    inventory.storage.extractItem(
                            slot,
                            remaining,
                            false
                    );

            int count = extracted.getCount();

            consumed += count;
            remaining -= count;
        }

        return consumed;
    }

    @Override
    public boolean canShared() {
        return false;
    }

    @Override
    public Widget createUIWidget() {
        WidgetGroup group =
                new WidgetGroup(0, 0, 80, 70);

        group.addWidget(
                new LabelWidget(
                        8,
                        6,
                        "Sodium Hydroxide"
                )
        );

        int index = 0;

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {

                group.addWidget(
                        new SlotWidget(
                                inventory.storage,
                                index++,
                                21 + x * 18,
                                24 + y * 18,
                                true,
                                true
                        )
                                .setBackgroundTexture(
                                        GuiTextures.SLOT
                                )
                                .setIngredientIO(
                                        IngredientIO.INPUT
                                )
                );
            }
        }

        group.setBackground(
                GuiTextures.BACKGROUND_INVERSE
        );

        return group;
    }
}
