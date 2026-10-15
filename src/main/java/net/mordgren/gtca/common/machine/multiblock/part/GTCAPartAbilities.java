package net.mordgren.gtca.common.machine.multiblock.part;

import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;

public final class GTCAPartAbilities {

    public static final PartAbility PH_SENSOR =
            new PartAbility("ph_sensor");

    public static final PartAbility PH_BASE_DOSER =
            new PartAbility("ph_base_doser");

    public static final PartAbility PH_ACID_DOSER =
            new PartAbility("ph_acid_doser");

    public static final PartAbility EXTREME_TEMP_PLASMA_INPUT =
            new PartAbility("extreme_temp_plasma_input");

    public static final PartAbility EXTREME_TEMP_COOLANT_INPUT =
            new PartAbility("extreme_temp_coolant_input");

    public static final PartAbility THERMAL_CONTROL =
            new PartAbility("thermal_control");

    private GTCAPartAbilities() {
    }
}
