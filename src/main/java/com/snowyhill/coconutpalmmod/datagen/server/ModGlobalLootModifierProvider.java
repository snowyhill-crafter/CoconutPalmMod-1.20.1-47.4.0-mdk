package com.snowyhill.coconutpalmmod.datagen.server;



import com.snowyhill.coconutpalmmod.CoconutPalmMod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output){
    super(output, CoconutPalmMod.MOD_ID);
}

    @Override
    protected void start() {    }
}