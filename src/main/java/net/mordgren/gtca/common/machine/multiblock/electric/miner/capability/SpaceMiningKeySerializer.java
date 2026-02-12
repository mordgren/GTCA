package net.mordgren.gtca.common.machine.multiblock.electric.miner.capability;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.gregtechceu.gtceu.api.recipe.content.IContentSerializer;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

public final class SpaceMiningKeySerializer implements IContentSerializer<SpaceMiningKey> {

    public static final SpaceMiningKeySerializer INSTANCE = new SpaceMiningKeySerializer();

    private static final Codec<SpaceMiningKey> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.STRING.optionalFieldOf("asteroid", "").forGetter(SpaceMiningKey::asteroidId),
            Codec.STRING.optionalFieldOf("drone", "").forGetter(SpaceMiningKey::droneKey),
            Codec.STRING.optionalFieldOf("plasma", "").forGetter(SpaceMiningKey::plasmaKey)
    ).apply(inst, SpaceMiningKey::new));

    private SpaceMiningKeySerializer() {}

    @Override
    public @NotNull SpaceMiningKey of(Object o) {
        if (o instanceof SpaceMiningKey k) return k;
        if (o instanceof String s) return new SpaceMiningKey(s);
        return defaultValue();
    }

    @Override
    public @NotNull SpaceMiningKey defaultValue() {
        return new SpaceMiningKey("");
    }

    @Override
    public @NotNull Class<SpaceMiningKey> contentClass() {
        return SpaceMiningKey.class;
    }

    @Override
    public @NotNull Codec<SpaceMiningKey> codec() {
        return CODEC;
    }

    @Override
    public @NotNull JsonElement toJson(@NotNull SpaceMiningKey content) {
        JsonObject obj = new JsonObject();
        obj.addProperty("asteroid", content.asteroidId());
        obj.addProperty("drone", content.droneKey());
        obj.addProperty("plasma", content.plasmaKey());
        return obj;
    }

    @Override
    public @NotNull SpaceMiningKey fromJson(@NotNull JsonElement json) {
        if (json.isJsonObject()) {
            JsonObject o = json.getAsJsonObject();
            String asteroid = o.has("asteroid") ? o.get("asteroid").getAsString() : "";
            String drone = o.has("drone") ? o.get("drone").getAsString() : "";
            String plasma = o.has("plasma") ? o.get("plasma").getAsString() : "";
            return new SpaceMiningKey(asteroid, drone, plasma);
        }
        if (json.isJsonPrimitive()) {
            return new SpaceMiningKey(json.getAsString());
        }
        return defaultValue();
    }

    @Override
    public void toNetwork(@NotNull FriendlyByteBuf buf, @NotNull SpaceMiningKey content) {
        buf.writeUtf(content.asteroidId());
        buf.writeUtf(content.droneKey());
        buf.writeUtf(content.plasmaKey());
    }

    @Override
    public @NotNull SpaceMiningKey fromNetwork(@NotNull FriendlyByteBuf buf) {
        String asteroid = buf.readUtf();
        String drone = buf.readUtf();
        String plasma = buf.readUtf();
        return new SpaceMiningKey(asteroid, drone, plasma);
    }
}
