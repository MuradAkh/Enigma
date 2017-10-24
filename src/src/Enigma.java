
package src;

/**
 *
 * @author muradakhn@gmail.com
 * @version 1.5
 */
public class Enigma {
    
    public final Rotors rt;    
    public final Plugboard pb;

    public  Enigma() {
        this.pb = new Plugboard();
        this.rt = new Rotors();
    }
    
    /**
     * Connect 2 letters in the plugboard
     * @param a letter to swap with letter @param b
     * @param b letter  to swap with letter @param a
     */
    public void connectPB(char a, char b){
        pb.connect(a,b);

    }
    /**
     * Disconnect a certain letter from the plugboard, letter it is paired to is also disconnected
     * @param a letter to disconnect
     */
    public void disconnectPB(char a){
        pb.disconnect(a);

    }
    
    /**
     * Get positions of rotors
     * @return Positions[] array with all the rotor positions
     */
    public int[] getPositions(){
        return rt.getPositions();
    }
    
    public void selectRt(int place, int rotor){
        rt.selectRt(place, rotor);

    }
    /**
     * 
     * @param place rotor slot
     * @param setting rotor position
     */
    public void setRt(int place, int setting){
        rt.setRt(place, setting);
    }
    
    /**
     * Resets rotor positions
     */
    public void resetRt(){
        rt.reset();
    }
    
    /**
     * get connections from the plugboard
     * @return Conns[] alphabet with connected letters swapped, as set in the plugboard
     */
    public char[] getConns() {
        return pb.getConns();
    }
    
    /**
     * Select reflector in the rotors object
     * @param reflector choice of reflector
     */
    public void selectRf(int reflector) {
        rt.selectRf(reflector);

    }
    /** Runs the machine using plugboard and rotors
     * 
     * @param input input letter
     * @return letter output letter
     */

    public char run(char input) {
        char letter = input;

        letter = pb.crypt(letter);

        letter = rt.run(letter);

        letter = pb.crypt(letter);

        return letter;

    }
}

