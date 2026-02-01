package net.mordgren.gtca.common.machine.multiblock.electric.miner.capability;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import org.apache.commons.lang3.mutable.MutableInt;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class SpaceMiningInfoRecipeCapability extends RecipeCapability<SpaceMiningInfo> {

    public static final SpaceMiningInfoRecipeCapability CAP = new SpaceMiningInfoRecipeCapability();


    private static final int X_BASE = 0;
    private static final int LINE_H = 11;

    private SpaceMiningInfoRecipeCapability() {
        super(
                "gtca:space_mining_info",
                0xFF66CCFF,
                false,
                1000,
                SpaceMiningInfoSerializer.INSTANCE
        );
    }

    @Override
    public void addXEIInfo(WidgetGroup group,
                           int xOffset,
                           GTRecipe recipe,
                           List<Content> contents,
                           boolean perTick,
                           boolean isInput,
                           MutableInt yOffset) {

        if (contents == null || contents.isEmpty()) return;


        Object raw = contents.get(0).getContent();
        if (!(raw instanceof SpaceMiningInfo info)) return;

        int x = X_BASE - xOffset;


        group.addWidget(new LabelWidget(x, yOffset.getAndAdd(LINE_H),
                "Needs: MK-" + info.requiredModuleMk()));

        group.addWidget(new LabelWidget(x, yOffset.getAndAdd(LINE_H),
                "Distance: " + info.distanceMin() + "-" + info.distanceMax()));

        group.addWidget(new LabelWidget(x, yOffset.getAndAdd(LINE_H),
                "Size: " + info.sizeMinStacks() + "-" + info.sizeMaxStacks()));

        group.addWidget(new LabelWidget(x, yOffset.getAndAdd(LINE_H),
                "Weight: " + info.weight()));
    }

    public static void putInfo(Object recipeBuilder, SpaceMiningInfo info) {
        if (recipeBuilder == null || info == null) return;

        try {
            Field f = findField(recipeBuilder.getClass(), "input", "inputs");
            if (f == null) f = findField(recipeBuilder.getClass(), "output", "outputs");
            if (f == null) return;
            f.setAccessible(true);

            Object mapObj = f.get(recipeBuilder);
            if (!(mapObj instanceof Map<?, ?>)) return;

            @SuppressWarnings("unchecked")
            Map<RecipeCapability<?>, List<Content>> map =
                    (Map<RecipeCapability<?>, List<Content>>) mapObj;

            int max = ChanceLogic.getMaxChancedValue();
            Content c = new Content(info, max, max, 0);

            map.computeIfAbsent(CAP, k -> new ArrayList<>()).add(c);

        } catch (Throwable ignored) {

        }
    }

    private static Field findField(Class<?> cls, String... names) {
        for (String n : names) {
            try { return cls.getField(n); } catch (Throwable ignored) {}
            try {
                Field f = cls.getDeclaredField(n);
                f.setAccessible(true);
                return f;
            } catch (Throwable ignored) {}
        }
        return null;
    }
}