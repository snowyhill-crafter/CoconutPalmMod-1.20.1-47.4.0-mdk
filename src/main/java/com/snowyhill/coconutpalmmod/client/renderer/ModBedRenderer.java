package com.snowyhill.coconutpalmmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;

public class ModBedRenderer implements BlockEntityRenderer<BedBlockEntity> {

    private final ModelPart headModel;
    private final ModelPart footModel;

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("coconutpalmmod", "textures/entity/bed/white.png");

    public ModBedRenderer(BlockEntityRendererProvider.Context context) {
        this.headModel = context.bakeLayer(ModelLayers.BED_HEAD);
        this.footModel = context.bakeLayer(ModelLayers.BED_FOOT);
    }

    @Override
    public void render(BedBlockEntity bed, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {

        BlockState state = bed.getBlockState();

        if (!(state.getBlock() instanceof BedBlock)) {
            return;
        }

        BedPart part = state.getValue(BedBlock.PART);
        Direction direction = state.getValue(BedBlock.FACING);

        ModelPart model = part == BedPart.HEAD ? this.headModel : this.footModel;

        poseStack.pushPose();

        poseStack.translate(0.5D, 0.5625D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(-direction.toYRot()));
        poseStack.translate(-0.5D, -0.5625D, -0.5D);

        VertexConsumer vertexConsumer =
                buffer.getBuffer(RenderType.entityCutout(TEXTURE));

        model.render(
                poseStack,
                vertexConsumer,
                packedLight,
                OverlayTexture.NO_OVERLAY
        );

        poseStack.popPose();
    }
}