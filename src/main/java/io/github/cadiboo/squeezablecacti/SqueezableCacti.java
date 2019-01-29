package io.github.cadiboo.squeezablecacti;

import io.github.cadiboo.squeezablecacti.config.ModConfig;
import io.github.cadiboo.squeezablecacti.util.ModReference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.github.cadiboo.squeezablecacti.util.ModReference.MOD_ID;

/**
 * Our main mod class
 *
 * @author Cadiboo
 */
@Mod(
		modid = MOD_ID,
		name = ModReference.MOD_NAME,
		version = ModReference.VERSION,
		acceptedMinecraftVersions = ModReference.ACCEPTED_VERSIONS,
		dependencies = ModReference.DEPENDENCIES,
		certificateFingerprint = ModReference.CERTIFICATE_FINGERPRINT
)
public class SqueezableCacti {

	public static final Logger SQUEEZABLE_CACTI_LOG = LogManager.getLogger(MOD_ID);

	private static final Logger LOGGER = LogManager.getLogger();

	@Instance(MOD_ID)
	public static SqueezableCacti instance;

	/**
	 * Run before anything else. <s>Read your config, create blocks, items, etc, and register them with the GameRegistry</s>
	 *
	 * @param event the event
	 * @see ForgeModContainer#preInit(FMLPreInitializationEvent)
	 */
	@EventHandler
	public void onPreInit(final FMLPreInitializationEvent event) {

		// register Capabilities if you have any

	}

	/**
	 * Do your mod setup. Build whatever data structures you care about. Register recipes, send FMLInterModComms messages to other mods.
	 *
	 * @param event the event
	 */
	@EventHandler
	public void onInit(final FMLInitializationEvent event) {
	}

	/**
	 * Mod compatibility, or anything which depends on other mods’ init phases being finished.
	 *
	 * @param event the event
	 * @see ForgeModContainer#postInit(FMLPostInitializationEvent)
	 */
	@EventHandler
	public void onPostInit(final FMLPostInitializationEvent event) {
	}

	/**
	 * Do your invalid fingerprint handling here
	 *
	 * @param event the event
	 * @see "https://tutorials.darkhax.net/tutorials/jar_signing/" and "https://mcforge.readthedocs.io/en/latest/concepts/jarsigning/"
	 */
	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		SQUEEZABLE_CACTI_LOG.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
	}

}
