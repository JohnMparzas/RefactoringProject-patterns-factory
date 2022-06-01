package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.management.Company;
import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

class TXTLogWriterTest {

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
    TXTLogWriter writer = new TXTLogWriter();
    writer.generateFile(6789);
    String[] written = new String[24];
    FileInputStream inputStream = new FileInputStream("6789_LOG.txt");
    Scanner inputReader = new Scanner(inputStream);
    String line;
    String[] logComponent;
    int i = 0;
    while (inputReader.hasNext()) {
      line = inputReader.nextLine();
      logComponent = line.split(":");
      written[i++] = logComponent[0].trim();
      written[i++] = logComponent[1].trim();
    }
    Nikos = manager.getTaxpayer(6789);
    assertEquals(Nikos.getFullname(), written[1]);
    assertEquals(Nikos.getTaxRegistrationNumber(), Integer.parseInt(written[3]));
    assertEquals(Nikos.getIncome(), Double.parseDouble(written[5]));
    assertEquals(Nikos.getBasicTax(), Double.parseDouble(written[7]));
    Double difference;
    difference = Nikos.getTotalTax() - Nikos.getBasicTax();
    if (difference > 0) {
      assertEquals("Tax Increase", written[8]);
      assertEquals( Math.round(difference * 10000.00) / 10000.00, Double.parseDouble(written[9]));

    } else {
      assertEquals("Tax Decrease", written[8]);
      assertEquals(Math.round(difference * 10000.00) / 10000.00, Double.parseDouble(written[9]));
    }
    assertEquals(Nikos.getTotalTax(), Double.parseDouble(written[11]));
    assertEquals(Nikos.getTotalReceiptsGathered(), Integer.parseInt(written[13]));
    assertEquals("Entertainment", written[14]);
    assertEquals(Nikos.getAmountOfReceiptKind((short) 0), Double.parseDouble(written[15]));
    assertEquals("Basic", written[16]);
    assertEquals(Nikos.getAmountOfReceiptKind((short) 1), Double.parseDouble(written[17]));
    assertEquals("Travel", written[18]);
    assertEquals(Nikos.getAmountOfReceiptKind((short) 2), Double.parseDouble(written[19]));
    assertEquals("Health", written[20]);
    assertEquals(Nikos.getAmountOfReceiptKind((short) 3), Double.parseDouble(written[21]));
    assertEquals("Other", written[22]);
    assertEquals(Nikos.getAmountOfReceiptKind((short) 4), Double.parseDouble(written[23]));
  }

}
