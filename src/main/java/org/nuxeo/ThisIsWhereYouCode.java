package org.nuxeo;

import java.util.TreeMap;
/**
 * This is where you have to code.
 * 
 * See javadoc and associated unit tests to understand what is expected
 * 
 * @author tiry
 *
 */
public class ThisIsWhereYouCode {

    interface Equalizer<T> {
        @SuppressWarnings("unused")
        boolean isEqual( T o1, T o2);
    }

    public static final char EXTENSION_SEPARATOR = '.';
    public static final Equalizer<String> STRING_EQUALIZER = new Equalizer<String>() {
        @SuppressWarnings("StringEquality")
        @Override
        public boolean isEqual(String o1, String o2) {
            return ( o1 == null || o2 == null ) ? o1 == o2 : o1.equals(o2);
        }
    };

    /**
     * input will be a string, but it may not have a file extension. return the file
     * extension (with no period) if it has one, otherwise null
     * 
     * @param filename String containing the filename from where we want to remove the extension
     * @return null if input is null or filename has no extension and the
     *         extension without the period otherwise
     */
    public String getFileNameExtension(String filename) {
        int idx = filename == null ? -1 : filename.lastIndexOf(EXTENSION_SEPARATOR);
        return idx == -1 ? null : filename.substring( idx + 1 );
    }

    /**
     * return the longest string contained inside the input array
     * 
     * @param array input Array of values
     * @return null if input is null and the longest string otherwise
     */
    public String getLongestString(Object[] array) {
        String maxString;

        if( array == null || array.length == 0){
            return null;
        }
        maxString = null;
        for( Object value : array ){
            String valueStr = null;
            if( value instanceof Object[] ){
                valueStr = getLongestString((Object[])value);
            }else if( value instanceof String ) {
                valueStr = (String)value;
            }

            if( valueStr != null && (maxString == null || maxString.length() < valueStr.length()) ){
                maxString = valueStr;
            }
        }
        return maxString;
    }

    /**
     * Returns true is both arrays contains the same values
     * 
     * @param array1 first Array to test
     * @param array2 second Array to test
     * @return true if both arrays contains the same values
     */
    public boolean areArraysEquals(String[] array1, String[] array2) {
        if( array1 == array2 ){ // Checks both equal to null or both with same reference.
            return true;
        }
        if( array1 == null || array2 == null || array1.length != array2.length ){
            return false;
        }
        int len = array1.length;
        for( int i = 0; i < len; i++ ){
            if ( !STRING_EQUALIZER.isEqual(array1[i], array2[i]) ) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compress the input string with a very dummy algorithm : repeated letters
     * are replaced by {n}{letter} where n is the number of repetition and
     * {letter} is the letter. n must be superior to 1 (otherwise, simply output
     * the letter)
     * 
     * @param input the input string that can only contains letters
     * @return the compressed String or null if the input is null
     */
    public String getCompressedString(String input) {

        StringBuilder strBuilder;

        if( input == null ){
            return null;
        }

        strBuilder = new StringBuilder();
        for( int i = 0; i < input.length(); ){
            int count = 1;
            char c    = input.charAt(i);
            while( ++i < input.length() && c == input.charAt(i) ){
                count++;
            }
            if( count > 1 ){
                strBuilder.append(count);
            }
            strBuilder.append(c);
        }
        return strBuilder.toString();
    }

    /**
     * Sort the input array of string based on lexicographic order of the
     * corresponding compressed string
     * 
     * @param array the Array to sort
     * @return the sorted array
     */
    public String[] getSortedArray(String[] array) {

        TreeMap<String, String> treeMap;

        if( array == null ){
            return null;
        }

        treeMap = new TreeMap<>();
        for( String value : array){
            treeMap.put(getCompressedString(value), value);
        }
        return treeMap.values().toArray( new String[array.length]);
    }

}
