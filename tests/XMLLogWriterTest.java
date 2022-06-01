package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.data.management.Company;
import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

class XMLLogWriterTest {

  @Test
  void test()
      throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
      WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException, ParseException {
    Taxpayer Nikos;

    TaxpayerManager manager = new TaxpayerManager();
    HashMap<Integer, Receipt> hash;
    Receipt receipt_i;
    Company company_i;
    Company companies[] = new Company[2];
    Receipt receipts[] = new Receipt[2];
    companies[0] = new Company("Parta", "Jakusa", "Drama", "Fukosima", 5);
    receipts[0] = new Receipt(1, "10/5/1996", (float) 251.0, "Basic", companies[0]);
    companies[1] = new Company("LOL", "Greece", "Ioannina", "Napolewn Zerva", 12);
    receipts[1] = new Receipt(2, "12/12/2015", (float) 15.0, "Basic", companies[1]);
    manager.createTaxpayer("Nikos Papadopoulos", 6789, "Single", (float) 22570.0);
    manager.createReceipt(1, "10/5/1996", (float) 251.0, "Basic", "Parta", "Jakusa", "Drama",
        "Fukosima", 5, 6789);
    manager.createReceipt(2, "12/12/2015", (float) 15.0, "Basic", "LOL", "Greece", "Ioannina",
        "Napolewn Zerva", 12, 6789);
    XMLLogWriter writer = new XMLLogWriter();
    writer.generateFile(6789);
    String[] written = new String[37];
    FileInputStream inputStream = new FileInputStream("6789_LOG.xml");
    Scanner inputReader = new Scanner(inputStream);
    String line;
    String[] logComponent;
    int i = 0;
    line = inputReader.nextLine();
    logComponent = line.split(" ",4);
    
    String name_tag1 = logComponent[0];
    String name_tag2 = logComponent[3];
    
    String full_name = logComponent[1]+" "+logComponent[2];
    
    
    
    
    while (inputReader.hasNext()) {
      line = inputReader.nextLine();
      
      logComponent = line.split(" ",3);
      written[i++] = logComponent[0].trim();
      written[i++] = logComponent[1].trim();
      written[i++] = logComponent[2].trim();

    }
    Nikos = manager.getTaxpayer(6789);
    assertEquals("<Name>", name_tag1);
    assertEquals(Nikos.getFullname(), full_name);
    assertEquals("</Name>", name_tag2.trim());
    assertEquals("<AFM>", written[0]);
    assertEquals(Nikos.getTaxRegistrationNumber(), Integer.parseInt(written[1]));
    assertEquals("</AFM>", written[2]);
    assertEquals("<Income>", written[3]);
    assertEquals(Nikos.getIncome(), Double.parseDouble(written[4]));
    assertEquals("</Income>", written[5]);
    assertEquals("<BasicTax>", written[6]);
    assertEquals(Nikos.getBasicTax(), Double.parseDouble(written[7]));
    assertEquals("</BasicTax>", written[8]);
    Double difference;
    difference = Nikos.getTotalTax() - Nikos.getBasicTax();
    if (difference > 0) {
      assertEquals("<TaxIncrease>", written[9]);
      difference = Nikos.getTotalTax() - Nikos.getBasicTax();
      assertEquals(Math.round(difference * 10000.00) / 10000.00, Double.parseDouble(written[10]));
      assertEquals("</TaxIncrease>", written[11]);

    } else {
      assertEquals("<TaxDecrease>", written[12]);
      difference = Nikos.getTotalTax() - Nikos.getBasicTax();
      assertEquals(Math.round(difference * 10000.00) / 10000.00, Double.parseDouble(written[10]));
      assertEquals("</TaxIncrease>", written[11]);
    }
    assertEquals("<TotalTax>", written[12]);
    assertEquals(Nikos.getTotalTax(), Double.parseDouble(written[13]));
    assertEquals("</TotalTax>", written[14]);
    assertEquals("<Receipts>", written[15]);
    assertEquals(Nikos.getTotalReceiptsGathered(), Integer.parseInt(written[16]));
    assertEquals("</Receipts>", written[17]);
    assertEquals("<Entertainment>", written[18]);
    assertEquals(Nikos.getAmountOfReceiptKind((short) 0), Double.parseDouble(written[19]));
    assertEquals("</Entertainment>", written[20]);
    assertEquals("<Basic>", written[21]);
    assertEquals(Nikos.getAmountOfReceiptKind((short) 1), Double.parseDouble(written[22]));
    assertEquals("</Basic>", written[23]);
    assertEquals("<Travel>", written[24]);
    assertEquals(Nikos.getAmountOfReceiptKind((short) 2), Double.parseDouble(written[25]));
    assertEquals("</Travel>", written[26]);
    assertEquals("<Health>", written[27]);
    assertEquals(Nikos.getAmountOfReceiptKind((short) 3), Double.parseDouble(written[28]));
    assertEquals("</Health>", written[29]);
    assertEquals("<Other>", written[30]);
    assertEquals(Nikos.getAmountOfReceiptKind((short) 4), Double.parseDouble(written[31]));
    assertEquals("</Other>", written[32]);
  }

}
