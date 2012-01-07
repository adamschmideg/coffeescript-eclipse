package csep.scoping;

import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Sets;

/**
 * A collection of and utility functions for javascript builtins 
 * @author adam
 *
 */
public class CoffeescriptBuiltins {
	
	protected final static String[] SIMPLE_GLOBALS = new String[]{
		"Infinity",
		"NaN",
		"undefined",
		// functions
		"decodeURI",
		"decodeURIComponent",
		"encodeURI",
		"encodeURIComponent",
		"escape",
		"eval",
		"isFinite",
		"isNaN",
		"Number",
		"parseFloat",
		"parseInt",
		"String",
		"unescape",
	};
	protected final static String[] JAVASCRIPT_COMPOUND_GLOBALS = new String[]{
		"Math.E",
		"Math.LN2",
		"Math.LN10",
		"Math.LOG2E",
		"Math.LOG10E",
		"Math.PI",
		"Math.SQRT1_2",
		"Math.SQRT2",
		// functions
		"Math.abs",
		"Math.acos",
		"Math.asin",
		"Math.atan",
		"Math.atan2",
		"Math.ceil",
		"Math.cos",
		"Math.exp",
		"Math.floor",
		"Math.log",
		"Math.max",
		"Math.min",
		"Math.pow",
		"Math.random",
		"Math.round",
		"Math.sin",
		"Math.sqrt",
		"Math.tan",
	};
	
	protected final static String[] COFFEESCRIPT_COMPOUND_GLOBALS = new String[]{
		"process.title",
		"process.version",
		"process.installPrefix",
		"process.versions",
		"process.platform",
		"process.ARGV",
		"process.argv",
		"process.env",
		"process.ENV",
		"process.pid",
		"process.execPath",
		"process.compile",
		"process._needTickCallback",
		"process.reallyExit",
		"process.chdir",
		"process.cwd",
		"process.getuid",
		"process.setuid",
		"process.setgid",
		"process.getgid",
		"process.umask",
		"process.dlopen",
		"process._kill",
		"process.memoryUsage",
		"process.binding",
		"process.EventEmitter",
		"process.assert",
		"process._tickCallback",
		"process.nextTick",
		"process.stdout",
		"process.stderr",
		"process.stdin",
		"process.openStdin",
		"process.exit",
		"process.kill",
		"process.addListener",
		"process.on",
		"process.removeListener",
		"process.debug",
		"process.error",
		"process.watchFile",
		"process.unwatchFile",
		"process.mixin",
		"process.createChildProcess",
		"process.inherits",
		"process._byteLength",
		"process.mainModule",
		"process.setMaxListeners",
		"process.emit",
		"process.once",
		"process.removeAllListeners",
		"process.listeners",
		"console.log",
		"console.info",
		"console.warn",
		"console.error",
		"console.dir",
		"console.time",
		"console.timeEnd",
		"console.trace",
		"console.assert",
		"require.resolve",
		"require.paths",
		"require.main",
		"require.extensions",
		"require.registerExtension",
		"require.cache",
		"module.id",
		"module.exports",
		"module.parent",
		"module.filename",
		"module.loaded",
		"module.exited",
		"module.children",
		"module.paths",
		"module.load",
		"module._compile",		
	};
	
	protected HashMultimap<String,String> compoundBuiltins;
	protected Set<String> prefixes;
	
	public CoffeescriptBuiltins() {
		compoundBuiltins  = HashMultimap.create();
		for (String compound: Sets.union(
				Sets.newHashSet(JAVASCRIPT_COMPOUND_GLOBALS),
				Sets.newHashSet(COFFEESCRIPT_COMPOUND_GLOBALS))) {
			String[] split = compound.split("\\.");
			compoundBuiltins.put(split[0], split[1]);
		}
		prefixes = Sets.union(compoundBuiltins.keySet(), Sets.newHashSet(SIMPLE_GLOBALS));
	}
	
	/**
	 * Get valid completions for a prefix <var>key</var>
	 * @param key
	 * @return a possibly empty set of completions
	 */
	public Set<String> getCompletions(String key) {
		return compoundBuiltins.get(key);
	}
	
	/**
	 * Get valid prefixes
	 */
	public Set<String> getPrefixes() {
		return prefixes;
	}
}
