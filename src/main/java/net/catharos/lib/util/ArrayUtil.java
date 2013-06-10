/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.catharos.lib.util;

/**
 *
 * @version 1.0
 */
public class ArrayUtil {
	
	/**
	 * Join array elements with a space character (' ')
	 * 
	 * @param pieces The array of strings to implode
	 * 
	 * @return String The joined String
	 */
	public static String implode(String... pieces) {
		return implode(" ", pieces);
	}
	
	/**
	 * Join array elements with a string
	 * 
	 * @param glue The join string
	 * @param pieces The array of strings to implode
	 * 
	 * @return String The joined String
	 */
	public static String implode(String glue, String... pieces) {
		if (pieces.length == 0) return "";
		if (pieces.length == 1) return pieces[0].toString();

		StringBuilder builder = new StringBuilder( pieces[0].toString() );
		for (int i = 1; i < pieces.length; i++) {
			builder.append( glue ).append( pieces[i] );
		}

		return builder.toString();
	}
	
}
