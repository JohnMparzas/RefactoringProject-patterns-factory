package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public abstract class InfoWriter implements FileWriter{
  protected  abstract  String [] gethast1();
  protected  abstract  String [] gethast2();
  protected abstract String getextension();
  private TaxpayerManager manager;
  private String[] has1;
  private String[] has2;
  public InfoWriter() {
    manager = new TaxpayerManager();
  }
  public void generateFile(int taxRegistrationNumber) throws IOException {
    has1=gethast1();
    has2=gethast2();
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + getextension()));
    outputStream.println(has1[0] + manager.getTaxpayerName(taxRegistrationNumber) + has2[0]);
    outputStream.println(has1[1] + taxRegistrationNumber + has2[1]);
    outputStream.println(has1[2] + manager.getTaxpayerStatus(taxRegistrationNumber) + has2[2]);
    outputStream.println(has1[3] + manager.getTaxpayerIncome(taxRegistrationNumber) + has2[3]);
    outputStream.println();// den mas emfanize to \n se aplo notepad
    outputStream.println(has1[4]);
    outputStream.println();
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    outputStream.close();
  }

  private void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) {

    HashMap<Integer, Receipt> receiptsHashMap = manager.getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      outputStream.println(has1[5] + receipt.getId() + has2[4]);
      outputStream.println(has1[6] + receipt.getIssueDate() + has2[5]);
      outputStream.println(has1[7] + receipt.getKind() + has2[6]);
      outputStream.println(has1[8] + receipt.getAmount() + has2[7]);
      outputStream.println(has1[9]  + receipt.getCompany().getName() + has2[8]);
      outputStream.println(has1[10] + receipt.getCompany().getaddress().getCountry() + has2[9]);
      outputStream.println(has1[11] + receipt.getCompany().getaddress().getCity() + has2[10]);
      outputStream.println(has1[12] + receipt.getCompany().getaddress().getStreet() + has2[11]);
      outputStream.println(has1[13] + receipt.getCompany().getaddress().getNumber() + has2[12]);
      outputStream.println();
    }
  }

}
