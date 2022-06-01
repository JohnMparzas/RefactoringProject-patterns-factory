package incometaxcalculator.data.management;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.FileWriter;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.data.io.XMLInfoWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerManager {

  
  private static HashMap<Integer, Taxpayer> taxpayerHashMap = new HashMap<Integer, Taxpayer>(0);
  private static HashMap<Integer, Integer> receiptOwnerTRN = new HashMap<Integer, Integer>(0);
  private String tupos[]= {"_INFO.xml","_INFO.txt"};
  private String tupos1[]= {"xml","txt"};
  private InfoWriterFactory infoFactory;
  private LogWriterFactory logFactory;
  private readFactory readFactory;

  public TaxpayerManager() {
    infoFactory=new InfoWriterFactory();
    logFactory=new LogWriterFactory();
    readFactory=new readFactory();
  }

  public void createTaxpayer(String fullname, int taxRegistrationNumber, String status,
      float income) throws WrongTaxpayerStatusException {
    Taxpayer payer;
    TaxpayerFactory it=new TaxpayerFactory();
    payer=it.createTaxpayerFactory(fullname,taxRegistrationNumber,status,income);
    taxpayerHashMap.put(taxRegistrationNumber,payer);
      
  }

  public void createReceipt(int receiptId, String issueDate, float amount, String kind,
      String companyName, String country, String city, String street, int number,
      int taxRegistrationNumber) throws WrongReceiptKindException, WrongReceiptDateException, ParseException {

    Receipt receipt = new Receipt(receiptId, issueDate, amount, kind,
        new Company(companyName, country, city, street, number));
    taxpayerHashMap.get(taxRegistrationNumber).addReceipt(receipt);
    receiptOwnerTRN.put(receiptId, taxRegistrationNumber);
  }

  public void removeTaxpayer(int taxRegistrationNumber) {
    Taxpayer taxpayer = taxpayerHashMap.get(taxRegistrationNumber);
    taxpayerHashMap.remove(taxRegistrationNumber);
    HashMap<Integer, Receipt> receiptsHashMap = taxpayer.getReceiptHashMap();
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      receiptOwnerTRN.remove(receipt.getId());
    }
  }

  public void addReceipt(int receiptId, String issueDate, float amount, String kind,
      String companyName, String country, String city, String street, int number,
      int taxRegistrationNumber) throws IOException, WrongReceiptKindException,
      WrongReceiptDateException, ReceiptAlreadyExistsException, WrongFileFormatException, ParseException {

    if (containsReceipt(receiptId)) {
      throw new ReceiptAlreadyExistsException();
    }
    createReceipt(receiptId, issueDate, amount, kind, companyName, country, city, street, number,
        taxRegistrationNumber);
    updateFiles(taxRegistrationNumber);
  }

  public void removeReceipt(int receiptId) throws IOException, WrongReceiptKindException, WrongFileFormatException {
    taxpayerHashMap.get(receiptOwnerTRN.get(receiptId)).removeReceipt(receiptId);
    updateFiles(receiptOwnerTRN.get(receiptId));
    receiptOwnerTRN.remove(receiptId);
  }

  private void updateFiles(int taxRegistrationNumber) throws IOException, WrongFileFormatException {
    int t=3;
    for(int i =0;i<2;i++) {
      if (new File(taxRegistrationNumber + tupos[i]).exists()) {
        FileWriter writer=infoFactory.getInfoWriter(tupos1[i]);
        writer.generateFile(taxRegistrationNumber);
        t=i;
      }
      if(t==3) {
        FileWriter writer=infoFactory.getInfoWriter(tupos1[1]);
        writer.generateFile(taxRegistrationNumber);
        return;
      }
    }
  }

  public void saveLogFile(int taxRegistrationNumber, String fileFormat)
      throws IOException, WrongFileFormatException {
    FileWriter writer=logFactory.getLogWriter(fileFormat);
    writer.generateFile(taxRegistrationNumber);
  }

  public boolean containsTaxpayer(int taxRegistrationNumber) {
    if (taxpayerHashMap.containsKey(taxRegistrationNumber)) {
      return true;
    }
    return false;
  }

  public boolean containsTaxpayer() {
    if (taxpayerHashMap.isEmpty()) {
      return false;
    }
    return true;
  }

  public boolean containsReceipt(int id) {
    if (receiptOwnerTRN.containsKey(id)) {
      return true;
    }
    return false;

  }

  public Taxpayer getTaxpayer(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber);
  }

  public void loadTaxpayer(String fileName)
      throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
      WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException, ParseException {
    String ending[] = fileName.split("\\.");
    FileReader reader = readFactory.getReader(ending[1]);
    reader.readFile(fileName);

  }

  public String getTaxpayerName(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getFullname();
  }

  public String getTaxpayerStatus(int taxRegistrationNumber) {
    if (taxpayerHashMap.get(taxRegistrationNumber) instanceof MarriedFilingJointlyTaxpayer) {
      return "Married Filing Jointly";
    } else if (taxpayerHashMap
        .get(taxRegistrationNumber) instanceof MarriedFilingSeparatelyTaxpayer) {
      return "Married Filing Separately";
    } else if (taxpayerHashMap.get(taxRegistrationNumber) instanceof SingleTaxpayer) {
      return "Single";
    } else {
      return "Head of Household";
    }
  }

  public String getTaxpayerIncome(int taxRegistrationNumber) {
    return "" + taxpayerHashMap.get(taxRegistrationNumber).getIncome();
  }

  public double getTaxpayerVariationTaxOnReceipts(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getVariationTaxOnReceipts();
  }

  public int getTaxpayerTotalReceiptsGathered(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getTotalReceiptsGathered();
  }

  public float getTaxpayerAmountOfReceiptKind(int taxRegistrationNumber, short kind) {
    return taxpayerHashMap.get(taxRegistrationNumber).getAmountOfReceiptKind(kind);
  }

  public double getTaxpayerTotalTax(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getTotalTax();
  }

  public double getTaxpayerBasicTax(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getBasicTax();
  }

  public HashMap<Integer, Receipt> getReceiptHashMap(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getReceiptHashMap();
  }

}