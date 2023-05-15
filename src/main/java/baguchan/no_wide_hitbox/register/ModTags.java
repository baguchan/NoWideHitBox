package baguchan.no_wide_hitbox.register;

import baguchan.no_wide_hitbox.NoWideHitbox;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class ModTags {
    public static class EntityTypes {
        public static final TagKey<EntityType<?>> COLLIDABLE = tag("collidable");
        public static final TagKey<EntityType<?>> WIDE_MOB = tag("wide_mob");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(NoWideHitbox.MODID, name));
        }
    }
}
