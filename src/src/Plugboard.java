
package src;

/**
 *
 * @author muradakhn@gmail.com
 * @version 1.5
 */
public class Plugboard {

    private final char[] conns;
    private final String alphabet;

    public Plugboard() {
        conns = new char[26];
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for(int i = 0; i < 26; i++){

            conns[i] = alphabet.charAt(i);
        }

    }
    /** swaps a letter if it is connected in the plugboard, else returns input
     * 
     * @param letter letter to be swapped 
     * @return swapped letter
     */
    public char crypt(char letter){

        char l = conns[alphabet.indexOf(letter)];

        return l;
    }
    /**Selects which letters should be swapped
     * 
     * @param a first letter
     * @param b second letter
     */
    public void connect(char a, char b) {
        int posA = alphabet.indexOf(a);
        int posB = alphabet.indexOf(b);

        char ac = getConns()[posA];
        char bc = getConns()[posB];

        conns[alphabet.indexOf(bc)] = bc; 
        
        conns[alphabet.indexOf(ac)] = ac;            

        conns[posA] = b;
        conns[posB] = a;
    }
    /**Disconnects a letter
     * 
     * @param a letter to be disconnected
     */
    public void disconnect(char a){
        conns[alphabet.indexOf(getConns()[alphabet.indexOf(a)])] = getConns()[alphabet.indexOf(a)];
        conns[alphabet.indexOf(a)] = a;

    }

    /**Returns all the connections in a character array, to be used for GUI
     * @return 
     */
    public char[] getConns() {
        return conns;
    }
    
    

}
