package ru.job4j.magnit;

import org.junit.Test;

import java.io.File;
import java.time.LocalTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Magnit {

    @Test
    public void testMagnit() {
        StoreSQL ssql = new StoreSQL("StoreSQLconfig.properties");
        StoreXML sxml = new StoreXML(new File("StoreXML.xml"));
        ConvertXSQT cxsqt = new ConvertXSQT();
        SumSAX ssax = new SumSAX();

//        System.out.println("Step#1 begin:"+LocalTime.now());
        ssql.generate(1000000);
//        System.out.println("Step#1 end:"+ LocalTime.now());
        sxml.generate();
//        System.out.println("Step#2 end:"+ LocalTime.now());
        cxsqt.convert(new File("StoreXML.xml"), new File("ConvertXSQT.xml"), new File("convert.xsl"));
/*        System.out.println("Step#3 end:"+LocalTime.now());
        System.out.println (ssax.sum());
        System.out.println("Step#4 end:"+LocalTime.now()); */
        assertThat(ssax.sum(), is(1784293664));
    }

}
