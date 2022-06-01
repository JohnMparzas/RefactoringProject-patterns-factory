package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import incometaxcalculator.data.management.*;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;
import incometaxcalculator.data.io.*;

class TXTInfoWriterTest {

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
    TaxpayerManager manager1 = new TaxpayerManager();
    
    companies[0] = new Company("Parta", "Jakusa", "Drama", "Fukosima", 5);
    receipts[0] = new Receipt(1, "10/5/1996", (float) 251.0, "Basic", companies[0]);
    companies[1] = new Company("LOL", "Greece", "Ioannina", "Napolewn Zerva", 12);
    receipts[1] = new Receipt(2, "12/12/2015", (float) 15.0, "Basic", companies[1]);
    manager.createTaxpayer("Nikos Papadopoulos", 6789, "Single", (float) 22570.0);
    manager.createReceipt(1, "10/5/1996", (float) 251.0, "Basic", "Parta", "Jakusa", "Drama",
        "Fukosima", 5, 6789);
    manager.createReceipt(2, "12/12/2015", (float) 15.0, "Basic", "LOL", "Greece", "Ioannina",
        "Napolewn Zerva", 12, 6789);
    new TXTInfoWriter().generateFile(6789);
    manager1.loadTaxpayer("6789_INFO.txt");
    Nikos = manager1.getTaxpayer(6789);
    assertEquals(Nikos.getFullname(), "Nikos Papadopoulos");
    assertEquals(Nikos.getTaxRegistrationNumber(), 6789);
    assertEquals(manager1.getTaxpayerStatus(6789), "Single");
    assertEquals(Nikos.getIncome(), 22570.0);
    hash = manager1.getReceiptHashMap(6789);
    Iterator<Entry<Integer, Receipt>> receipts_map = hash.entrySet().iterator();
    int i = 0;
    while (receipts_map.hasNext()) {

      receipt_i = receipts_map.next().getValue();
      company_i = receipt_i.getCompany();
      if (receipt_i.getId() == 1) {
        i = 0;
      } else if (receipt_i.getId() == 2) {
        i = 1;
      }
      assertEquals(receipt_i.getId(), receipts[i].getId());
      assertEquals(receipt_i.getIssueDate(), receipts[i].getIssueDate());
      assertEquals(receipt_i.getAmount(), receipts[i].getAmount());
      assertEquals(receipt_i.getKind(), receipts[i].getKind());
      assertEquals(company_i.getName(), companies[i].getName());
      assertEquals(company_i.getaddress().getCountry(), companies[i].getaddress().getCountry());
      assertEquals(company_i.getaddress().getCity(), companies[i].getaddress().getCity());
      assertEquals(company_i.getaddress().getStreet(), companies[i].getaddress().getStreet());
      assertEquals(company_i.getaddress().getNumber(), companies[i].getaddress().getNumber());

    }

  }

}
