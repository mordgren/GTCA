package net.mordgren.gtca.common.machine.multiblock.electric.miner.card;

import net.minecraft.resources.ResourceLocation;
import net.mordgren.gtca.GTCA;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public final class SpaceMiningTargets {

    private SpaceMiningTargets() {}

    private static final List<SpaceMiningTarget> TARGETS = List.of(
            new SpaceMiningTarget(
                    GTCA.id("lunar_regolith"),
                    "Lunar Regolith",
                    "Moon Orbit",
                    1,
                    1,
                    "Basic lunar material with light metals and dust."
            ),
            new SpaceMiningTarget(
                    GTCA.id("metallic_asteroid"),
                    "Metallic Asteroid",
                    "Inner Belt",
                    1,
                    2,
                    "Dense metallic body rich in iron-group materials."
            ),
            new SpaceMiningTarget(
                    GTCA.id("carbonaceous_asteroid"),
                    "Carbonaceous Asteroid",
                    "Inner Belt",
                    1,
                    3,
                    "Carbon-rich asteroid with useful chemical resources."
            ),
            new SpaceMiningTarget(
                    GTCA.id("ice_asteroid"),
                    "Ice Asteroid",
                    "Outer Belt",
                    2,
                    4,
                    "Frozen body containing water ice and volatile compounds."
            ),
            new SpaceMiningTarget(
                    GTCA.id("rare_metal_asteroid"),
                    "Rare Metal Asteroid",
                    "Deep Belt",
                    2,
                    5,
                    "Rare metal deposits with higher module requirements."
            ),
            new SpaceMiningTarget(
                    GTCA.id("platinum_asteroid"),
                    "Platinum Asteroid",
                    "Deep Belt",
                    3,
                    6,
                    "High-value asteroid with platinum-group resources."
            )
    );

    static {
        validateUniqueCircuitConfigurations();
    }

    public static List<SpaceMiningTarget> all() {
        return TARGETS;
    }

    public static Optional<SpaceMiningTarget> get(ResourceLocation id) {
        if (id == null) return Optional.empty();

        return TARGETS.stream()
                .filter(target -> target.id().equals(id))
                .findFirst();
    }

    public static Optional<SpaceMiningTarget> getByCircuitConfiguration(int configuration) {
        return TARGETS.stream()
                .filter(target -> target.circuitConfiguration() == configuration)
                .findFirst();
    }

    private static void validateUniqueCircuitConfigurations() {
        Set<Integer> used = new HashSet<>();

        for (SpaceMiningTarget target : TARGETS) {
            if (!used.add(target.circuitConfiguration())) {
                throw new IllegalStateException(
                        "Duplicate Space Mining circuit configuration: "
                                + target.circuitConfiguration()
                                + " for target "
                                + target.id()
                );
            }
        }
    }
}