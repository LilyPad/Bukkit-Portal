package lilypad.bukkit.portal.util;

public class StringUtils {

	public static String[] shift(String[] original, int offset) {
		if(original.length <= offset) {
			throw new ArrayIndexOutOfBoundsException();
		}
		String[] output = new String[original.length - offset];
		for(int i = offset; i < original.length; i++) {
			output[i - offset] = original[i];
		}
		return output;
	}
	
	public static String concat(String[] strings, String separator) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			if (i != 0) {
				builder.append(separator);
			}
			builder.append(strings[i]);
		}
		return builder.toString();
	}
	
	public static String name(String string) {
		return (string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase()).replace('_', ' ');
	}
	
}
