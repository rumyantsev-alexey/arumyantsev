package ru.job4j.magnit;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Класс конвертирует один xml в другой с помощью шаблона
 */
public class ConvertXSQT {
    private static final Logger log = Logger.getLogger(StoreSQL.class.getName());

    /**
     * Метод конвертирует один xml в другой с помощью шаблона
     * @param source исходный файл
     * @param dest результирующий файл
     * @param scheme шаблон
     */
    public void convert(File source, File dest, File scheme) {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (TransformerConfigurationException e) {
            log.log(Level.WARNING, "XSLT conf error", e);
        } catch (TransformerException e) {
            log.log(Level.WARNING, "XSLT error", e);
        }
    }

}
