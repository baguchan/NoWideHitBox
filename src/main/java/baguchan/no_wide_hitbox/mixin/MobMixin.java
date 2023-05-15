package baguchan.no_wide_hitbox.mixin;

import baguchan.no_wide_hitbox.register.ModTags;
import baguchan.no_wide_hitbox.util.CombatUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
    protected MobMixin(EntityType<? extends Mob> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "isWithinMeleeAttackRange", at = @At("HEAD"), cancellable = true)
    public void isWithinMeleeAttackRange(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (this.getType().is(ModTags.EntityTypes.WIDE_MOB)) {
            if(!CombatUtil.isCanReachHeight(this, livingEntity)){
                callbackInfo.setReturnValue(false);
            }
        }

    }
}