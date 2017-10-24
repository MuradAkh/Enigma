
package src;



/**
 *
 * @author  muradakhn@gmail.com
 * @version 1.5
 */
public class Rotors {

    private final String alphabet;

    private final int[] positions; // positions of rotors 0-25 based on letters
    private final String[] seq;  // sequences of letters in each rotor types alphabet
    private final int[] selections; // rotor selections for each slot
    private final String[] ref; // reflector alphaber letter sequence
    private int reflector; // selected reflector 
    private final int[] notches; //notches on each rotor type  

    /**
     * 
     */
    public Rotors() {
        
        //Create arrays
        positions = new int[3];
        seq = new String[5];
        selections = new int[3];
        ref = new String[3];
        notches = new int[5]; 
        
        reflector = 1; // select reflector B
        
        // Data from original src rotors
        seq[0] = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        seq[1] = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
        seq[2] = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
        seq[3] = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
        seq[4] = "VZBRGITYUPSDNHLXAWMJQOFECK";
        
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // english alphabet to compare to

        // Data from original src reflectors
        ref[0] = "EJMZALYXVBWFCRQUONTSPIKHGD"; 
        ref[1] = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
        ref[2] = "FVPJIAOYEDRZXWGCTKUQSBNMHL";
        
        // Notch positions on original src rotors
        notches[0] = alphabet.indexOf('R');
        notches[1] = alphabet.indexOf('F');
        notches[2] = alphabet.indexOf('W');
        notches[3] = alphabet.indexOf('K');
        notches[4] = alphabet.indexOf('A');

        // automaticly set rotors when the program is initiated
        for (int i = 0; i < 3; i++) {
            selections[i] = i;          
        }

    }
    /**
     * Select a rotor for a specific slot 
     * @param place slot 
     * @param rotor type of the rotor I II etc 
     */
    public void selectRt(int place, int rotor) {
        selections[place] = rotor;

    }
    /**
     * Get positions of the rotors 
     * @return array with positions
     */
    public int[] getPositions(){
        return positions;
    }
    
    /**
     * 
     * @param place slot of the rotor 
     * @param setting positon to be selected 
     */
    public void setRt(int place, int setting){
        positions[place] = setting;
    }
    
    
    /**
     * Choose a reflector
     * @param reflector type ABC
     */
    public void selectRf(int reflector) {
        this.reflector = reflector;

    }
    /**runs the encryption process  
     * 
     * @param letter letter to be encrypted/decrypted
     * @return encrypted/decrypted letter
     */
    public char run(char letter) {
        increment();
        char l = letter;
        for (int i = 0; i < 3; i++) {
            l = shiftB(i, l);
            l = crypt(selections[i], l);
            l = shiftF(i, l);
        }
        l = ref[reflector].charAt(alphabet.indexOf(l));

        for (int i = 2; i >= 0; i--) {
            l = shiftB(i, l);
            l = decrypt(selections[i], l);
            l = shiftF(i, l);

        }

        return l;
    }
    /**
     *  Shifts the pointer to a letter forwards in the alphabet
     *  Adjusts the letter input based on the current position( letter 1-26) of the rotor at a given slot 
     * 
     * @param rotor slot of the rotor (1/2/3) 
     * @param letter input letter into rotor
     * @return returns the letter to imputed into next step
     */
    public char shiftF(int rotor, char letter){
        int pos = alphabet.indexOf(letter)- positions[rotor];
        if(pos < 0) {pos = pos + 26;}
        char l = alphabet.charAt(pos);
        return l;
    }
    
    /**
     *  Shifts the pointer to a letter backwards in the alphabet
     *  Adjusts the letter input based on the current position( letter 1-26) of the rotor at a given slot 
     * 
     * @param rotor slot of the rotor (1/2/3) 
     * @param letter input letter into rotor
     * @return returns the letter to imputed into next step
     */
    public char shiftB(int rotor, char letter){
        int pos = alphabet.indexOf(letter) + positions[rotor];
        if(pos > 25) {pos = pos - 26;}
        char l = alphabet.charAt(pos);
        return l;
    }
        /**
         * Changes the letter based on the rotor on the way back from the reflector
         * 
         * @param rotor Type of the rotor (I, II, etc)
         * @param letter letter to be converted
         * @return converted letter based on the rotor code 
         */
    public char decrypt(int rotor, char letter) {
        int pos = seq[rotor].indexOf(letter);
        // pos = pos - positions[rotor];

        if(pos < 0) {pos = pos + 26;}

        char l = alphabet.charAt(pos);

        return l;
    }
    /**
         * Changes the letter based on the rotor on the way to the reflector
         * 
         * @param rotor Type of the rotor (I, II, etc)
         * @param letter letter to be converted
         * @return converted letter based on the rotor code 
         */
    public char crypt(int rotor, char letter) {

        int pos = alphabet.indexOf(letter);

        // pos = pos + positions[rotor];

        if(pos > 25) {pos = pos - 26;}

        char l = seq[rotor].charAt(pos);

        return l;
    }
    
   /**
    * sets rotor positions to 0
    */
    public void reset() {
        for (int i = 0; i < 3; i++) {
            positions[i] = 0;

        }

    }
    
    /**
     * 
     * @param rotorSlot slot of the rotor to increment 
     * @param plus increment up/down Plus = up
     */
    public void incrementManual(int rotorSlot, boolean plus){
        if(plus){
            positions[rotorSlot]++;
        }
        else if(!plus){positions[rotorSlot]--;}
    
    }
    
    /**
     * automatically increments the rotor positions (when called)
     * Increments the next rotors when a previous rotor hits the notch
     * Resets a rotor when it goes full circle
     */
    public void increment() {
        if (positions[0] < 25) {
            positions[0]++;
        } else {
            positions[0] = 0;            
        }
        if(positions[0] == notches[selections[0]]){
            if (positions[1] < 25) {
                positions[1]++;
            } else {
                positions[1] = 0;            
            }
            if(positions[1] == notches[selections[0]]){
                if (positions[2] < 25) {
                    positions[2]++;
                } else {
                    positions[2] = 0;            
                }
            } 
        } 
    }
}
