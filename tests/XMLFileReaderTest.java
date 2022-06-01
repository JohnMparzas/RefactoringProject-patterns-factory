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

class XMLFileReaderTest {

  @Test
  void test() throws NumberFormatException, IOException, WrongTaxpayerStatusException,
      WrongFileFormatException, WrongReceiptKindException, WrongReceiptDateException,
      WrongFileEndingException, ParseException {
    Taxpayer Apostolos;
    HashMap<Integer, Receipt> hash;
    Receipt receipt_i;
    Company company_i;
    Company companies[] = new Company[4];
    Receipt receipts[] = new Receipt[4];
    companies[1] = new Company("Parta", "Jakusa", "Drama", "Fukosima", 5);
    receipts[1] = new Receipt(1, "10/5/1996", (float) 251.0, "Basic", companies[0]);
    companies[2] = new Company("LOL", "Greece", "Ioannina", "Napolewn Zerva", 12);
    receipts[2] = new Receipt(2, "12/12/2015", (float) 15.0, "Basic", companies[1]);
    companies[0] = new Company("ewre", "werw", "ewr", "were", 4);
    receipts[0] = new Receipt(4, "11/11/2111", (float) 1000.0, "Other", companies[2]);
    
    TaxpayerManager manager = new TaxpayerManager();
    manager.loadTaxpayer("123456789_INFO.xml");
    Apostolos = manager.getTaxpayer(123456789);
    assertEquals(Apostolos.getFullname(), "Apostolos Zarras");
    assertEquals(Apostolos.getTaxRegistrationNumber(), 123456789);
    assertEquals(manager.getTaxpayerStatus(123456789), "Married Filing Jointly");
    assertEquals(Apostolos.getIncome(), 22570.0);
    hash = manager.getReceiptHashMap(123456789);
    Iterator<Entry<Integer, Receipt>> receipts_map = hash.entrySet().iterator();
    int i = 0;
    while (receipts_map.hasNext()) {
      receipt_i = receipts_map.next().getValue();
      company_i = receipt_i.getCompany();
      
      if (receipt_i.getId() == 2) {
        i = 2;
      } else if (receipt_i.getId() == 1) {
        i = 1;
      } else {
        i = 0;
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
