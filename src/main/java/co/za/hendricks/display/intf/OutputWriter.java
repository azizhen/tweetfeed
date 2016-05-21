package co.za.hendricks.display.intf;

/**
 * Interface for writing content out to an IO mechanism
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-20
 */
public interface OutputWriter {

    /**
     * Prints content out via an IO Mechanism for display to a user
     * @param outputContent The content that must be displayed via the implemented IO mechanism
     */
    void write(String outputContent);
}
