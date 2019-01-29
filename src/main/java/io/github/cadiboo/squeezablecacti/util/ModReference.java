package io.github.cadiboo.squeezablecacti.util;

import io.github.cadiboo.squeezablecacti.SqueezableCacti;

/**
 * Holds mod-wide constant values
 *
 * @author Cadiboo
 */
@SuppressWarnings("WeakerAccess")
public final class ModReference {

	/**
	 * This is our Mod's Name.
	 */
	public static final String MOD_NAME = "Squeezable Cacti";

	/**
	 * This is our Mod's Mod Id that is used for stuff like resource locations.
	 */
	public static final String MOD_ID = "squeezable-cacti";

	/**
	 * @see "https://maven.apache.org/enforcer/enforcer-rules/versionRanges.html"
	 */
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";

	/**
	 * @see "https://maven.apache.org/enforcer/enforcer-rules/versionRanges.html"
	 */
	public static final String DEPENDENCIES = "" +
			"required-after:minecraft;" +
			"required-after:forge@[14.23.5.2768,);" +
			"";

	/**
	 * "@VERSION@" is replaced by build.gradle with the actual version
	 *
	 * @see <a href= "https://mcforge.readthedocs.io/en/latest/conventions/versioning/">Forge Versioning Docs</a>
	 */
	public static final String VERSION = "@VERSION@";

	/**
	 * "@FINGERPRINT@" is replaced by build.gradle with the actual fingerprint
	 *
	 * @see "https://tutorials.darkhax.net/tutorials/jar_signing/"
	 */
	public static final String CERTIFICATE_FINGERPRINT = "@FINGERPRINT@";

}
