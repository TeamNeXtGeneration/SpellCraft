package de.castelbuilder123.spellcraft.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;

/**
 * Created by Jona on 11.08.14.
 */
public class EntityHellDog extends EntityMob {
    public EntityHellDog(World p_i1696_1_) {
        super(p_i1696_1_);
        this.setSize(0.7F, .7F);
        this.getNavigator().setAvoidsWater(true);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2);
        this.isImmuneToFire = true;
    }


    @Override
    protected String getLivingSound()
    {
        return "mob.wolf.growl";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.wolf.hurt";//this refers to:yourmod/sound/optionalFile/YourSound
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.wolf.death";//etc.
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        for (int i = 0; i < 2; ++i)
        {
            this.worldObj.spawnParticle("largesmoke", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble(), this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }
}
