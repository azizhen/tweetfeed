package co.za.hendricks.display.intf;

/**
 * This class is responsible for writing String content out to the users default operating system console
 *
 * @author  Aziz Hendricks
 * @version 1.0
 * @since   2016-5-20
 */

public class ConsoleWriter implements OutputWriter {

    /**
     * Writes a String out using System's console
     *
     * @param outputContent The content that must be displayed via the implemented IO mechanism
     */
    public void write(String outputContent) {
        System.out.println(outputContent);
    }
}
