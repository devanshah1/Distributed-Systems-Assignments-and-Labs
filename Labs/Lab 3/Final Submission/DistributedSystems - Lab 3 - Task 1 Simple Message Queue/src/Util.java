/**
 * This class is used as a utility class for some generic functions
 * @author Devan Shah 100428864
 *
 */
public class Util
{
    /**
     * This function is used to convert line content into an array of words. 
     * The words are split using a one or many space delimiter.
     * @param lineContent - The line to convert into array of words
     * @return words - array of words
     */
    public static String [] words ( String lineContent )
    {
        // Slpit the line content in to an array of words
        String [] words = lineContent.split ( "[ ]+" ) ;
        
        return words ;
    }

    /**
     * This function is used to find the edit distance to change
     * one string to another. For example change hello to pello
     * would have an edit distance of 1.
     * @param s1 - First string, should be the query string
     * @param s2 - second string, any other string in the line
     * @return the number of edit distance to convert one string to the other
     */
    public static int editDistance ( String s1, String s2 )
    {
        // Grab the lengths of the 2 strings
        int lengthString1 = s1.length () ;
        int lengthString2 = s2.length () ;
        
        // Construct a 2D array of int values that will be used to find the edit distance
        int [][] editDistance = new int[lengthString1][lengthString2] ;
        
        // Loop through and fill the edit distance with the incremental length of string 1
        for ( int i = 0; i < lengthString1; i++ )
        {
            editDistance [i] [0] = i ;
        }
        
        // Loop through and fill the edit distance with the incremental length of string 2
        for ( int j = 0; j < lengthString2; j++ )
        {
            editDistance [0] [j] = j ;
        }

        /**
         *  Loop through the strings and compare character by character to determine the character switches
         *  that are needed to perform the action of changing string two into string 1.
         */
        for ( int i = 1; i < lengthString1; i++ )
        {
            for ( int j = 1; j < lengthString2; j++ )
            {
                if ( s1.charAt ( i - 1 ) == s2.charAt ( j - 1 ) ) 
                {
                    editDistance [i] [j] = editDistance [i - 1] [j - 1] ;
                }
                else 
                {
                    editDistance [i] [j] = 1 + Math.min ( Math.min ( editDistance [i] [j - 1], editDistance [i - 1] [j] ), editDistance [i - 1] [j - 1] ) ;    
                }
            }
        }
        
        // return the final edit distance 
        return editDistance [lengthString1-1] [lengthString2-1] ;
    }
}
