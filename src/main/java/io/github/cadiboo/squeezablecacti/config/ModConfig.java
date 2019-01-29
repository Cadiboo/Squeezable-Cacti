package io.github.cadiboo.squeezablecacti.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static io.github.cadiboo.squeezablecacti.util.ModReference.MOD_ID;

/**
 * Our Mod's configuration
 *
 * @author Cadiboo
 */
@SuppressWarnings("WeakerAccess")
@Config(modid = MOD_ID)
@LangKey(MOD_ID + ".config.title")
public final class ModConfig {

	@RequiresMcRestart
	@LangKey(MOD_ID + ".config.isEnabled")
	public static boolean isEnabled = true;

	@RequiresMcRestart
	@LangKey(MOD_ID + ".config.fluidPerCactiMilliBuckets")
	public static int fluidPerCactiMilliBuckets = 250;

	@Mod.EventBusSubscriber(modid = MOD_ID)
	private static class EventHandler {

		/**
		 * Inject the new values and save to the config file when the config has been changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(MOD_ID)) {
				ConfigManager.sync(MOD_ID, Config.Type.INSTANCE);
			}
		}

	}

}
