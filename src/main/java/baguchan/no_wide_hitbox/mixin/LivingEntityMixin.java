package baguchan.no_wide_hitbox.mixin;

import baguchan.no_wide_hitbox.register.ModTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    protected LivingEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "doPush", at = @At("HEAD"), cancellable = true)
    protected void doPush(Entity entity, CallbackInfo ci) {
        if (entity instanceof LivingEntity && (this.getType().is(ModTags.EntityTypes.COLLIDABLE)) || entity.getType().is(ModTags.EntityTypes.COLLIDABLE)) {
            ci.cancel();
        }
    }

    @Unique
    public boolean canBeCollidedWith() {
        return this.getType().is(ModTags.EntityTypes.COLLIDABLE);
    }
}