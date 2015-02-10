package my.test.vca;



/**
 * Defines the behavior of a VCA Module.
 * @inv
 *  out ==
 *      SIGNAL * Math.pow(10, getAmplification() + AM)
 *  MIN_AMPLIFICATION <= getAmplification() <= MAX_AMPLIFICATION
 *
 */
public interface VCA extends Module {

    double MIN_AMPLIFICATION = 0;
    double MAX_AMPLIFICATION = 10;

    /**
     * Return the amplification value.
     */
    double getAmplification();

    //command

    /**
     *  Set the amplification value.
     *  @pre
     *   MIN_AMPLIFICATION <= amplification <= MAX_AMPLIFICATION
     *  @post
     *   getAmplification() == amplification
     */
    void setAmplification(double amplification);
}
