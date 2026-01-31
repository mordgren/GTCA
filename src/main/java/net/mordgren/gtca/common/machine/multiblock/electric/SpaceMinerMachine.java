package net.mordgren.gtca.common.machine.multiblock.electric;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.ElevatorModuleKind;
import net.mordgren.gtca.common.machine.multiblock.electric.elevator.IElevatorModule;
import net.mordgren.gtca.common.machine.multiblock.electric.miner.capability.SpaceMiningInfoHandlerTrait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SpaceMinerMachine extends WorkableElectricMultiblockMachine implements IElevatorModule {


    private static final boolean DEBUG_FORCE_ENABLED = false;

    public final int moduleTier;
    private boolean enabledByElevator = false;

    @Persisted
    private final NotifiableEnergyContainer wirelessEnergy;

    private static final Logger LOG = LogManager.getLogger("GTCA-SpaceMiner");

    private TickableSubscription debugSub = null;
    private int debugTimer = 0;

    public SpaceMinerMachine(IMachineBlockEntity holder, int tier) {
        super(holder);
        this.moduleTier = tier;

        long cap = wirelessCapacityForTier(tier);
        long voltage = GTValues.V[tier];

        this.wirelessEnergy = NotifiableEnergyContainer.receiverContainer(this, cap, voltage, 1);

        this.wirelessEnergy.setSideInputCondition(side -> false);
        this.wirelessEnergy.setSideOutputCondition(side -> false);

        attachTraits(wirelessEnergy);
        attachTraits(new SpaceMiningInfoHandlerTrait(this));
        this.recipeLogic.setWorkingEnabled(DEBUG_FORCE_ENABLED);
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        if (getLevel() != null && !getLevel().isClientSide) {
            this.recipeLogic.setWorkingEnabled(DEBUG_FORCE_ENABLED || enabledByElevator);
        }

        if (debugSub == null) {
            debugSub = subscribeServerTick(this::debugTick);
        }
        debugTimer = 0;
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        debugSub = null;
        debugTimer = 0;
    }

    @Override
    public void onUnload() {
        super.onUnload();
        debugSub = null;
        debugTimer = 0;
    }

    @Override
    public ElevatorModuleKind getElevatorModuleKind() {
        return ElevatorModuleKind.MINER;
    }

    @Override
    public void setEnabledByElevator(boolean enabled) {
        this.enabledByElevator = enabled;

        if (getLevel() != null && !getLevel().isClientSide) {
            this.recipeLogic.setWorkingEnabled(DEBUG_FORCE_ENABLED || enabled);
        }
    }

    @Override
    public boolean isEnabledByElevator() {
        return enabledByElevator;
    }

    @Override
    public boolean requiresComputation() {
        return true;
    }

    @Override
    public NotifiableEnergyContainer getWirelessEnergyContainer() {
        return wirelessEnergy;
    }

    private static long wirelessCapacityForTier(int tier) {
        int mk = switch (tier) {
            case GTValues.LuV -> 1;
            case GTValues.ZPM -> 2;
            case GTValues.UV  -> 3;
            default -> 1;
        };

        long cap = 786_432L;
        for (int i = 1; i < mk; i++) cap *= 4L;
        return cap;
    }

    private void debugTick() {
        if (getLevel() == null || getLevel().isClientSide) return;
        if (!isFormed()) return;


        if (++debugTimer < 20) return;
        debugTimer = 0;

        String status = safeCallString(recipeLogic, "getStatus");
        Boolean waiting = safeCallBool(recipeLogic, "isWaiting");

        String reason = safeCallFancyTooltip(recipeLogic);
        if (reason == null) reason = "n/a";

        boolean workingEnabled = recipeLogic.isWorkingEnabled();

        String proxies = safeCallBoolObj(this, "hasCapabilityProxies");
        String inCaps = dumpCaps(IO.IN);
        String outCaps = dumpCaps(IO.OUT);

        LOG.info("[SpaceMiner] formed={} enabled={} workEnabled={} status={} waiting={} reason={} | proxies={} | IN_caps={} | OUT_caps={}",
                isFormed(),
                enabledByElevator,
                workingEnabled,
                status,
                waiting,
                reason,
                proxies,
                dumpProxy(IO.IN),
                dumpProxy(IO.OUT)
        );
    }

    private String dumpProxy(IO io) {
        try {
            var proxy = this.getCapabilitiesProxy();
            Object raw = proxy.get(io);

            if (!(raw instanceof List<?> list)) {
                return String.valueOf(raw);
            }

            StringBuilder sb = new StringBuilder("List(size=")
                    .append(list.size()).append(")");

            int idx = 0;
            for (Object o : list) {
                if (!(o instanceof RecipeHandlerList rhl)) {
                    sb.append("\n  #").append(idx++)
                            .append(" ").append(o == null ? "null" : o.getClass().getName());
                    continue;
                }

                sb.append("\n  #").append(idx++)
                        .append(" io=").append(rhl.getHandlerIO())
                        .append(" total=").append(rhl.getTotalContentAmount())
                        .append(" caps=").append(rhl.getCapabilities());

                for (RecipeCapability<?> cap : rhl.getCapabilities()) {
                    sb.append("\n     - ").append(cap)
                            .append(" handlers=").append(rhl.getCapability(cap).size());
                }
            }

            return sb.toString();
        } catch (Throwable t) {
            return "n/a (" + t.getClass().getSimpleName() + ")";
        }
    }


    private String dumpCaps(IO io) {
        try {
            var proxy = this.getCapabilitiesProxy();
            if (proxy == null) return "null-proxy";

            Object bucket = proxy.get(io);
            if (bucket == null) return "null";


            if (bucket instanceof java.util.List<?> list) {
                StringBuilder sb = new StringBuilder();
                sb.append("List(size=").append(list.size()).append(") ");

                int idx = 0;
                for (Object o : list) {
                    sb.append("\n  #").append(idx++).append(" ").append(describeRecipeHandlerList(o));
                }
                return sb.toString();
            }

            return bucket.getClass().getName() + " :: " + bucket;

        } catch (Throwable t) {
            return "n/a (" + t.getClass().getSimpleName() + ")";
        }
    }

    private static String describeRecipeHandlerList(Object rhl) {
        if (rhl == null) return "null";


        Object cap = null;
        cap = tryInvokeNoArg(rhl, "getCapability");
        if (cap == null) cap = tryGetField(rhl, "capability");
        if (cap == null) cap = tryGetField(rhl, "cap");


        Object handlers = null;
        handlers = tryInvokeNoArg(rhl, "getHandlers");
        if (handlers == null) handlers = tryInvokeNoArg(rhl, "handlers");
        if (handlers == null) handlers = tryGetField(rhl, "handlers");
        if (handlers == null) handlers = tryGetField(rhl, "list");

        String capStr = (cap == null) ? "cap=?" : ("cap=" + cap.getClass().getName() + " :: " + cap);
        String handlersStr;

        if (handlers instanceof java.util.List<?> hl) {
            StringBuilder sb = new StringBuilder();
            sb.append("handlers=").append(hl.size()).append(" [");
            int shown = 0;
            for (Object h : hl) {
                if (shown++ >= 6) { sb.append(" ..."); break; }
                sb.append(h == null ? "null" : h.getClass().getSimpleName()).append(", ");
            }
            sb.append("]");
            handlersStr = sb.toString();
        } else if (handlers != null) {
            handlersStr = "handlers=" + handlers.getClass().getName() + "::" + handlers;
        } else {
            handlersStr = "handlers=?";
        }

        return rhl.getClass().getName() + " | " + capStr + " | " + handlersStr;
    }

    private static Object tryInvokeNoArg(Object obj, String methodName) {
        try {
            var m = obj.getClass().getMethod(methodName);
            return m.invoke(obj);
        } catch (Throwable ignored) {
            return null;
        }
    }

    private static Object tryGetField(Object obj, String field) {
        try {
            var f = obj.getClass().getField(field);
            return f.get(obj);
        } catch (Throwable ignored) {
            try {
                var f = obj.getClass().getDeclaredField(field);
                f.setAccessible(true);
                return f.get(obj);
            } catch (Throwable ignored2) {
                return null;
            }
        }
    }

    private static java.util.Map<?, ?> tryInvokeNoArgMap(Object obj, String methodName) {
        try {
            var m = obj.getClass().getMethod(methodName);
            Object r = m.invoke(obj);
            return (r instanceof java.util.Map<?, ?> map) ? map : null;
        } catch (Throwable ignored) {
            return null;
        }
    }

    private static java.util.Map<?, ?> tryFindFirstMapField(Object obj) {
        try {
            Class<?> c = obj.getClass();
            while (c != null && c != Object.class) {
                for (var f : c.getDeclaredFields()) {
                    if (java.util.Map.class.isAssignableFrom(f.getType())) {
                        f.setAccessible(true);
                        Object r = f.get(obj);
                        if (r instanceof java.util.Map<?, ?> map) return map;
                    }
                }
                c = c.getSuperclass();
            }
        } catch (Throwable ignored) {}
        return null;
    }

    private static String safeCallFancyTooltip(Object recipeLogic) {
        if (recipeLogic == null) return null;
        try {
            var m = recipeLogic.getClass().getMethod("getFancyTooltip");
            Object comp = m.invoke(recipeLogic);
            if (comp == null) return null;


            try {
                var gm = comp.getClass().getMethod("getString");
                Object s = gm.invoke(comp);
                return s == null ? null : s.toString();
            } catch (Throwable ignored) {
                return comp.toString();
            }
        } catch (Throwable ignored) {
            return null;
        }
    }

    private static String safeCallString(Object obj, String methodName) {
        if (obj == null) return null;
        try {
            var m = obj.getClass().getMethod(methodName);
            Object r = m.invoke(obj);
            return r == null ? null : r.toString();
        } catch (Throwable ignored) {
            return null;
        }
    }

    private static Boolean safeCallBool(Object obj, String methodName) {
        if (obj == null) return null;
        try {
            var m = obj.getClass().getMethod(methodName);
            Object r = m.invoke(obj);
            return (r instanceof Boolean b) ? b : null;
        } catch (Throwable ignored) {
            return null;
        }
    }

    private static String safeCallBoolObj(Object obj, String methodName) {
        Boolean b = safeCallBool(obj, methodName);
        return b == null ? "n/a" : b.toString();
    }
}