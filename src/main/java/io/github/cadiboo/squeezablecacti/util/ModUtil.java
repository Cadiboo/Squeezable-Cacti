package io.github.cadiboo.squeezablecacti.util;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Random;

/**
 * Util that is used on BOTH physical sides
 *
 * @author Cadiboo
 */
@SuppressWarnings("WeakerAccess")
public final class ModUtil {

	private static final Random RANDOM = new Random();

	/**
	 * Sets the {@link Impl#setRegistryName(ResourceLocation) Registry Name} and the {@link Item#setTranslationKey(String) Translation Key} (if applicable) for the entry
	 *
	 * @param entry the {@link Impl IForgeRegistryEntry.Impl<?>} to set the names for
	 * @param name  the name for the entry that the registry name is derived from
	 * @return the entry
	 */
	@Nonnull
	public static <T extends Impl<?>> T setRegistryNames(@Nonnull final T entry, @Nonnull final String name) {
		return setRegistryNames(entry, new ResourceLocation(ModReference.MOD_ID, name));
	}

	/**
	 * Sets the {@link Impl#setRegistryName(ResourceLocation) Registry Name} and the {@link Item#setTranslationKey(String) Translation Key} (if applicable) for the entry
	 *
	 * @param entry        the {@link Impl IForgeRegistryEntry.Impl<?>} to set the names for
	 * @param registryName the registry name for the entry that the translation key is also derived from
	 * @return the entry
	 */
	@Nonnull
	public static <T extends Impl<?>> T setRegistryNames(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
		return setRegistryNames(entry, registryName, registryName.getPath());
	}

	/**
	 * Sets the {@link Impl#setRegistryName(ResourceLocation) Registry Name} and the {@link Item#setTranslationKey(String) Translation Key} (if applicable) for the entry
	 *
	 * @param entry          the {@link Impl IForgeRegistryEntry.Impl<?>} to set the names for
	 * @param registryName   the registry name for the entry
	 * @param translationKey the unlocalized name for the entry
	 * @return the entry
	 */
	@Nonnull
	public static <T extends Impl<?>> T setRegistryNames(@Nonnull final T entry, @Nonnull final ResourceLocation registryName, @Nonnull final String translationKey) {
		entry.setRegistryName(registryName);
		if (entry instanceof Block) {
			((Block) entry).setTranslationKey(translationKey);
		}
		if (entry instanceof Item) {
			((Item) entry).setTranslationKey(translationKey);
		}
		return entry;
	}

	@Nonnull
	public static <T extends Impl<?>> T setCreativeTab(@Nonnull final T entry, final CreativeTabs creativeTab) {
		if (entry instanceof Block) {
			((Block) entry).setCreativeTab(creativeTab);
		}
		if (entry instanceof Item) {
			((Item) entry).setCreativeTab(creativeTab);
		}
		return entry;
	}

	/**
	 * Returns a random between the specified values;
	 *
	 * @param min the minimum value of the random number
	 * @param max the maximum value of the random number
	 * @return the random number
	 */
	public static double randomBetween(final int min, final int max) {
		return RANDOM.nextInt((max - min) + 1) + min;
	}

	/**
	 * Maps a value from one range to another range. Taken from https://stackoverflow.com/a/5732117
	 *
	 * @param input       the input
	 * @param inputStart  the start of the input's range
	 * @param inputEnd    the end of the input's range
	 * @param outputStart the start of the output's range
	 * @param outputEnd   the end of the output's range
	 * @return the newly mapped value
	 */
	public static double map(final double input, final double inputStart, final double inputEnd, final double outputStart, final double outputEnd) {
		final double input_range = inputEnd - inputStart;
		final double output_range = outputEnd - outputStart;

		return (((input - inputStart) * output_range) / input_range) + outputStart;
	}

	@Nonnull
	public static Side getLogicalSide(@Nonnull final World world) {
		if (world.isRemote) {
			return Side.CLIENT;
		} else {
			return Side.SERVER;
		}
	}

	public static void logLogicalSide(@Nonnull final Logger logger, @Nonnull final World world) {
		logger.info("Logical Side: " + getLogicalSide(world));
	}

	/**
	 * Logs all {@link Field Field}s and their values of an object with the {@link Level#INFO INFO} level.
	 *
	 * @param logger  the logger to dump on
	 * @param objects the objects to dump.
	 */
	public static void dump(@Nonnull final Logger logger, @Nonnull final Object... objects) {
		for (final Object object : objects) {
			final Field[] fields = object.getClass().getDeclaredFields();
			logger.info("Dump of " + object + ":");
			for (final Field field : fields) {
				try {
					field.setAccessible(true);
					logger.info(field.getName() + " - " + field.get(object));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					logger.info("Error getting field " + field.getName());
					logger.info(e.getLocalizedMessage());
				}
			}
		}
	}

}
