package biometricstationserialreceiver;

/**
 *
 * @author nicod
 */
public interface SerialPortLineListener {
    public abstract void serialLineEvent(SerialData data);
}
