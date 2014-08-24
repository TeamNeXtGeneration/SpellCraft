package de.castelbuilder123.spellcraft.entity;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import de.castelbuilder123.spellcraft.SpellCraftMod;
import de.castelbuilder123.spellcraft.data.PlayerData;
import de.castelbuilder123.spellcraft.data.PlayerNBTData;
import de.castelbuilder123.spellcraft.gui.DecisionScreen;
import de.castelbuilder123.spellcraft.network.PacketHandler;
import de.castelbuilder123.spellcraft.network.PacketKillEntity;
import de.castelbuilder123.spellcraft.network.PacketQueryDecision;
import de.castelbuilder123.spellcraft.proxies.Proxy;
import de.castelbuilder123.spellcraft.registers.ItemRegistery;
import de.castelbuilder123.spellcraft.utils.dict.PlayerDict;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBoss extends EntityMob implements IBossDisplayData, IRangedAttackMob {
    private int summonCooldown = 300;
    public EntityBoss(World p_i1738_1_) {
        super(p_i1738_1_);
        this.setSize(0.9F, 2F);
        BossStatus.setBossStatus(this, true);
        this.getNavigator().setAvoidsWater(true);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag)
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("killer", killer.getDisplayName());
        nbt.setInteger("PlayerDecision", PlayerDecision);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
        NBTTagCompound nbt = tag.getCompoundTag("SpellCraft");
        if (nbt != null)
        {
            String user = nbt.getString("killer");
            if (PlayerDict.playernameToPlayer.containsKey(user))
            {
                killer = PlayerDict.playernameToPlayer.get(user);
                PlayerDecision = nbt.getInteger("PlayerDecision");
            }
        }
    }

    private EntityPlayer killer;
    private int PlayerDecision = 0;
    private int lastHealth = (int) this.getMaxHealth();

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.5D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10);
        this.isImmuneToFire = true;
    }

    //private int PacketQueryCooldown = 0;

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if (killer != null)
        {
            //SpellCraftMod.log.info("[Devil] Killer is not null");
            //SpellCraftMod.log.info("[Devil] PlayerDecision: " + PlayerDecision);
            if (PlayerDecision == 0)
            {
                if (this.getHealth() < this.getMaxHealth() / 5)
                {
                        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0D);
                        summonCooldown = Integer.MAX_VALUE;
                        for (Object entityObj: worldObj.getLoadedEntityList())
                        {
                            if (entityObj instanceof EntityLivingBase)
                            {
                                EntityLivingBase ent = (EntityLivingBase) entityObj;
                                if (ent.getEntityId() != this.getEntityId())
                                {
                                    ent.setDead();
                                }
                            }
                        }
                        PlayerDecision = 3;
                        killer.openGui(SpellCraftMod.instance, DecisionScreen.GUI_ID, worldObj, (int)posX, (int)posY, (int)posZ);
                }
            }
            else if (PlayerDecision == 3)
            {
                /*
                for (Object entityObj: worldObj.getLoadedEntityList())
                {
                    if (entityObj instanceof EntityLivingBase)
                    {
                        EntityLivingBase ent = (EntityLivingBase) entityObj;
                        if (ent.getEntityId() != this.getEntityId())
                        {
                            ent.setDead();
                        }
                    }
                }*/
                for(PlayerNBTData da: PlayerData.playerNBTDatas)
                {
                    if (da.username == killer.getDisplayName())
                    {
                        if (da.decision != 0 && da.decision != 3)
                        {
                            PlayerDecision = da.decision;
                        }
                        break;
                    }
                }
            }
            else if (PlayerDecision == 2)
            {
                killer.addChatMessage(new ChatComponentText("<Devil> Ahh.. ok. here you can have my Trident"));
                killer.inventory.addItemStackToInventory(new ItemStack(ItemRegistery.DevilTridentItem));
                this.worldObj.removeEntity(this);
                PacketKillEntity packet = new PacketKillEntity();
                packet.EntityID = this.getEntityId();
                PacketHandler.INSTANCE.sendToAll(packet);
            }
            else if (PlayerDecision == 1)
            {
                killer.addChatMessage(new ChatComponentText("<Devil> Ok... you wanted it!"));
                this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.5D);
                summonCooldown = 0;
                PlayerDecision = 4;
            }
        }
        else
        {
            //SpellCraftMod.log.info("[Devil] Killer is null");
            if (lastHealth > this.getHealth())
            {
                //SpellCraftMod.log.info("[Devil] Got damaged");
                EntityPlayer attacker = this.attackingPlayer;
                lastHealth = (int)this.getHealth();
                if (attacker != null)
                {
                    killer = attacker;
                }
            }
        }

        if (getAITarget() != null)
        {
            summonCooldown--;
            if (summonCooldown <= 0)
            {
                summonCooldown = this.worldObj.rand.nextInt(100) + 200;
                int count = this.worldObj.rand.nextInt(3) + 1;
                for (int i = 0; i < count; i++)
                {
                    final EntityHellDog dog = new EntityHellDog(this.worldObj);
                    dog.setPosition(this.posX, this.posY, this.posZ);
                    this.worldObj.spawnEntityInWorld(dog);
                }
            }
            double distance;
            if ((distance = getAITarget().getDistance(this.posX, this.posY, this.posZ)) > 3D)
            {
                this.attackEntityWithRangedAttack(this.getAITarget(), (float) distance);
            }
        }
    }

    @Override
    public void onDeath(DamageSource dmgSrc)
    {
        super.onDeath(dmgSrc);
        if (PlayerDecision == 4)
        {
            killer.inventory.addItemStackToInventory(new ItemStack(ItemRegistery.SourceOfLightItem));
        }
    }

    @Override
    protected String getLivingSound()
    {
        return SpellCraftMod.MODID+":boss.devil.DevilLiving";
    }
    /*
    @Override
    protected String getHurtSound()
    {
        return "yourmod:optionalFile.YourSound";//this refers to:yourmod/sound/optionalFile/YourSound
    }*/

    @Override
    protected String getDeathSound()
    {
        return SpellCraftMod.MODID+":boss.devil.DevilDeath";
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float range) {
        EntityFireball fireball = new EntityFireball(this.worldObj) {
            @Override
            protected void onImpact(MovingObjectPosition p_70227_1_) {
                if (p_70227_1_.entityHit != null)
                {
                    p_70227_1_.entityHit.setFire(60);

                    p_70227_1_.entityHit.attackEntityFrom(DamageSource.inFire, 3f);
                    this.setDead();
                }
            }
        };
        fireball.setPosition(this.posX, this.posY, this.posZ);
        fireball.shootingEntity = this;
        Vec3 accel = this.getLookVec();
        accel.xCoord = accel.xCoord*2;
        accel.yCoord = accel.yCoord*2;
        accel.zCoord = accel.zCoord*2;
        fireball.setVelocity(accel.xCoord, accel.yCoord, accel.zCoord);
        this.worldObj.spawnEntityInWorld(fireball);
    }
}
