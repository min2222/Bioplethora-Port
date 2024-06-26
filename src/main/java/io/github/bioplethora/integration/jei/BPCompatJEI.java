package io.github.bioplethora.integration.jei;

import io.github.bioplethora.Bioplethora;
import io.github.bioplethora.gui.container.ReinforcingTableContainer;
import io.github.bioplethora.gui.screen.ReinforcingTableScreen;
import io.github.bioplethora.registry.BPBlocks;
import io.github.bioplethora.registry.BPContainerTypes;
import io.github.bioplethora.registry.BPRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class BPCompatJEI implements IModPlugin {
    public static final ResourceLocation pluginId = new ResourceLocation(Bioplethora.MOD_ID, "jei");
    public static final int columns = 4, rows = 9, inventorySize = columns * rows;

    public ResourceLocation getPluginUid() {
        return pluginId;
    }

    private void addDescription(IRecipeRegistration registry, ItemStack itemStack) {
        registry.addIngredientInfo(itemStack, VanillaTypes.ITEM_STACK, Component.translatable(itemStack.getDescriptionId() + ".jei_desc"));
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registry) {
        registry.addRecipeTransferHandler(ReinforcingTableContainer.class, BPContainerTypes.REINFORCING_TABLE_CONTAINER.get(), ReinforcingTableCategory.CATEGORY_ID, 1, 9, 10, inventorySize);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        @SuppressWarnings("resource")
        ClientLevel world = Minecraft.getInstance().level;
        registry.addRecipes(ReinforcingTableCategory.CATEGORY_ID, world.getRecipeManager().getAllRecipesFor(BPRecipes.REINFORCING_TYPE.get()));
        addDescription(registry, new ItemStack(BPBlocks.REINFORCING_TABLE.get()));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ReinforcingTableScreen.class, 79, 35, 20, 20, ReinforcingTableCategory.CATEGORY_ID);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(BPBlocks.REINFORCING_TABLE.get()), ReinforcingTableCategory.CATEGORY_ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new ReinforcingTableCategory(guiHelper));
    }
}
