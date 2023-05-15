package baguchan.no_wide_hitbox.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class CombatUtil {
    public static boolean isCanReachHeight(Entity owner, Entity enemy) {
        double d1 = (enemy.getY() - owner.getY());
        return d1 < (owner.getBbHeight() * 0.75F) && d1 > -(owner.getBbHeight() * 0.85F);
    }

    public static double distanceToSqrWithLookAngleXZ(Entity owner, double p_20276_, double p_20277_, double p_20278_) {
        double d0 = owner.getX() + (getNoXLookAngle(owner).x) - p_20276_;
        double d1 = owner.getY() - p_20277_;
        double d2 = owner.getZ() + (getNoXLookAngle(owner).z) - p_20278_;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public static Vec3 getNoXLookAngle(Entity entity) {
        return calculateViewVector(0.0F, entity.getYRot());
    }

    protected static Vec3 calculateViewVector(float p_20172_, float p_20173_) {
        float f = p_20172_ * ((float)Math.PI / 180F);
        float f1 = -p_20173_ * ((float)Math.PI / 180F);
        float f2 = Mth.cos(f1);
        float f3 = Mth.sin(f1);
        float f4 = Mth.cos(f);
        float f5 = Mth.sin(f);
        return new Vec3((double)(f3 * f4), (double)(-f5), (double)(f2 * f4));
    }
}
