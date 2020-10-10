package cy.jdkdigital.productivebees.client.render.entity;

import cy.jdkdigital.productivebees.ProductiveBees;
import cy.jdkdigital.productivebees.client.render.entity.layers.AbdomenLayer;
import cy.jdkdigital.productivebees.client.render.entity.layers.ColorLayer;
import cy.jdkdigital.productivebees.client.render.entity.layers.PollenLayer;
import cy.jdkdigital.productivebees.client.render.entity.model.ProductiveBeeModel;
import cy.jdkdigital.productivebees.entity.bee.ConfigurableBeeEntity;
import cy.jdkdigital.productivebees.entity.bee.ProductiveBeeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ProductiveBeeRenderer extends MobRenderer<ProductiveBeeEntity, ProductiveBeeModel<ProductiveBeeEntity>>
{
    public ProductiveBeeRenderer(EntityRendererManager renderManagerIn, ProductiveBeeModel<ProductiveBeeEntity> model) {
        super(renderManagerIn, model, 0.7F);
    }

    public ProductiveBeeRenderer(EntityRendererManager renderManagerIn) {
        this(renderManagerIn, new ProductiveBeeModel<>());
        addLayer(new ColorLayer(this));
        addLayer(new AbdomenLayer(this));
        addLayer(new PollenLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(ProductiveBeeEntity bee) {
        String textureLocation = ProductiveBees.MODID + ":textures/entity/bee/" + bee.getBeeName() + "/bee";

        if (bee.getColor(0) != null) {
            textureLocation = ProductiveBees.MODID + ":textures/entity/bee/base/bee";
        }

        if (bee instanceof ConfigurableBeeEntity) {
            if (((ConfigurableBeeEntity) bee).hasBeeTexture()) {
                textureLocation = ((ConfigurableBeeEntity) bee).getBeeTexture();
            }
        }

        if (bee.isAngry()) {
            textureLocation = textureLocation + "_angry";
        }

        if (bee.hasNectar()) {
            textureLocation = textureLocation + "_nectar";
        }

        return new ResourceLocation(textureLocation + ".png");
    }
}
