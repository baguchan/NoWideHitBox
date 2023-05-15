package baguchan.no_wide_hitbox.mixin;

import baguchan.no_wide_hitbox.register.ModTags;
import baguchan.no_wide_hitbox.util.CombatUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MeleeAttackGoal.class)
public class MeleeAttackGoalMixin {
    @Shadow
    private PathfinderMob mob;
    @Shadow
    private int ticksUntilNextAttack;

    @Inject(method = "checkAndPerformAttack", at = @At("HEAD"), cancellable = true)
    protected void checkAndPerformAttack(LivingEntity p_25557_, double p_25558_, CallbackInfo callbackInfo) {
        double d0 = this.getAttackReachSqr(p_25557_);
        if(this.mob.getType().is(ModTags.EntityTypes.WIDE_MOB)) {
            if (CombatUtil.isCanReachHeight(this.mob, p_25557_) && p_25558_ <= d0 && this.ticksUntilNextAttack <= 0) {
                this.resetAttackCooldown();
                this.mob.swing(InteractionHand.MAIN_HAND);
                this.mob.doHurtTarget(p_25557_);
            }
            callbackInfo.cancel();
        }
    }

    @Shadow
    private void resetAttackCooldown() {
    }

    @Shadow
    protected double getAttackReachSqr(LivingEntity p_25556_) {
        return 0;
    }
}
