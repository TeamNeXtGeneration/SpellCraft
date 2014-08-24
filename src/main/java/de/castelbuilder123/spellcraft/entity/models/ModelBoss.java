package de.castelbuilder123.spellcraft.entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBoss extends ModelBase {
    //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer leftleg;
    ModelRenderer rightleg;
    ModelRenderer tailP1;
    ModelRenderer tailP2;
    ModelRenderer tailP3;
    ModelRenderer tailP4;
    ModelRenderer tailP5;
    ModelRenderer TailEnd;
    ModelRenderer Horns1;
    ModelRenderer Horns2;

    public ModelBoss()
    {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 0F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
        rightarm.setRotationPoint(-5F, 2F, 0F);
        rightarm.setTextureSize(64, 32);
        rightarm.mirror = true;
        setRotation(rightarm, -1.487144F, 0.2230717F, 0.0371786F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(-1F, 0F, -2F, 4, 12, 4);
        leftarm.setRotationPoint(5F, 2F, 0F);
        leftarm.setTextureSize(64, 32);
        leftarm.mirror = true;
        setRotation(leftarm, -1.487144F, -0.2974289F, 0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(2F, 12F, 0F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, -0.0349066F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-2F, 12F, 0F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0.0349066F);
        tailP1 = new ModelRenderer(this, 0, 0);
        tailP1.addBox(0F, 0F, 0F, 2, 2, 3);
        tailP1.setRotationPoint(-1F, 10F, 2F);
        tailP1.setTextureSize(64, 32);
        tailP1.mirror = true;
        setRotation(tailP1, 0F, 0F, 0F);
        tailP2 = new ModelRenderer(this, 0, 0);
        tailP2.addBox(0F, 0F, 0F, 2, 2, 3);
        tailP2.setRotationPoint(-1F, 10F, 5F);
        tailP2.setTextureSize(64, 32);
        tailP2.mirror = true;
        setRotation(tailP2, -0.7807508F, 0F, 0F);
        tailP3 = new ModelRenderer(this, 0, 0);
        tailP3.addBox(0F, 0F, 0F, 2, 2, 3);
        tailP3.setRotationPoint(-1F, 12F, 7F);
        tailP3.setTextureSize(64, 32);
        tailP3.mirror = true;
        setRotation(tailP3, -1.264073F, 0F, 0F);
        tailP4 = new ModelRenderer(this, 0, 0);
        tailP4.addBox(0F, 0F, 0F, 2, 2, 3);
        tailP4.setRotationPoint(-1F, 14F, 7.7F);
        tailP4.setTextureSize(64, 32);
        tailP4.mirror = true;
        setRotation(tailP4, -1.264073F, 0F, 0F);
        tailP5 = new ModelRenderer(this, 0, 0);
        tailP5.addBox(0F, 0F, 0F, 2, 2, 3);
        tailP5.setRotationPoint(-1F, 16.66667F, 8.6F);
        tailP5.setTextureSize(64, 32);
        tailP5.mirror = true;
        setRotation(tailP5, -1.264073F, 0F, 0F);
        TailEnd = new ModelRenderer(this, 0, 0);
        TailEnd.addBox(-0.5F, 0F, 0F, 3, 3, 3);
        TailEnd.setRotationPoint(-1F, 19F, 8F);
        TailEnd.setTextureSize(64, 32);
        TailEnd.mirror = true;
        setRotation(TailEnd, -0.2230717F, 0F, 0F);
        Horns1 = new ModelRenderer(this, 50, 0);
        Horns1.addBox(0F, 0F, 0F, 1, 3, 1);
        Horns1.setRotationPoint(2F, -10F, -3F);
        Horns1.setTextureSize(64, 32);
        Horns1.mirror = true;
        setRotation(Horns1, 0.4363323F, 0F, 0F);
        Horns2 = new ModelRenderer(this, 50, 0);
        Horns2.addBox(0F, 0F, 0F, 1, 3, 1);
        Horns2.setRotationPoint(-3F, -10F, -3F);
        Horns2.setTextureSize(64, 32);
        Horns2.mirror = true;
        setRotation(Horns2, 0.4363323F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5,entity);
        head.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        leftleg.render(f5);
        rightleg.render(f5);
        tailP1.render(f5);
        tailP2.render(f5);
        tailP3.render(f5);
        tailP4.render(f5);
        tailP5.render(f5);
        TailEnd.render(f5);
        Horns1.render(f5);
        Horns2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    }
}
