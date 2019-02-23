package io.github.cadiboo.squeezablecacti;

import io.github.cadiboo.squeezablecacti.config.ModConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static io.github.cadiboo.squeezablecacti.util.ModReference.MOD_ID;

/**
 * Subscribe to events that should be handled on both PHYSICAL sides in this class
 */
@Mod.EventBusSubscriber(modid = MOD_ID)
public final class EventSubscriber {

	public static boolean debug = false;

	@SubscribeEvent
	public static void onRegisterRecipesEvent(@Nonnull final RegistryEvent.Register<IRecipe> event) {
		if (!ModConfig.isEnabled) {
			return;
		}


		final IForgeRegistry<IRecipe> registry = event.getRegistry();

		final Item cactus = Item.getItemFromBlock(Blocks.CACTUS);

		ForgeRegistries.ITEMS.getValues().forEach(item -> {
			if (debug) LogManager.getLogger().info(item.getRegistryName());
			final NonNullList<ItemStack> subItems = NonNullList.create();

			final ArrayList<CreativeTabs> tabs = new ArrayList<>();

			//yay, people override stuff and don't put their items on the search tabs
			tabs.add(CreativeTabs.SEARCH);
			tabs.add(item.getCreativeTab());
			tabs.addAll(Arrays.asList(item.getCreativeTabs()));

			tabs.stream().distinct().filter(Objects::nonNull).forEach(tab -> {
				item.getSubItems(tab, subItems);
			});

			subItems.forEach(stack -> {
				if (debug) LogManager.getLogger().warn(stack.getItem().getRegistryName());
				if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
					if (debug) LogManager.getLogger().error(stack.getItem().getRegistryName());
					for (int cactiCount = 1; cactiCount <= 8; cactiCount++) {
						final int amount = ModConfig.fluidPerCactiMilliBuckets * cactiCount;
						final ItemStack fullStack = stack.copy();
						final IFluidHandlerItem capability = fullStack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
						if (capability != null) {
							if (capability.fill(new FluidStack(FluidRegistry.WATER, amount), true) == amount) {
								final NonNullList<Ingredient> ingredients = NonNullList.create();
								ingredients.add(Ingredient.fromStacks(stack));
								for (int i = 0; i < cactiCount; i++) {
									ingredients.add(Ingredient.fromItem(cactus));
								}

								final ShapelessRecipes recipe = new ShapelessRecipes(new ResourceLocation(MOD_ID, "cactiSqueezing").toString(), capability.getContainer(), ingredients);
								final ResourceLocation stackRegistryName = stack.getItem().getRegistryName();
								assert stackRegistryName != null;
								final String stackRegistryNameString = stackRegistryName.getNamespace() + "_" + stackRegistryName.getPath();
								recipe.setRegistryName(
										new ResourceLocation(MOD_ID, stackRegistryNameString + "_" + fullStack.getMetadata() + "-cacti_" + cactiCount)
								);
								registry.register(recipe);
							}
						}
					}
				}
			});
		});

		SqueezableCacti.SQUEEZABLE_CACTI_LOG.debug("Registered recipes");

	}

}
