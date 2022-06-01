package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.TaxpayerManager;

public abstract class LogWriter implements FileWriter {
  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;
  protected abstract String[] gethas1();
  protected abstract String[] gethas2();
  protected abstract String getextension();
  
  public void generateFile(int taxRegistrationNumber) throws IOException {
    TaxpayerManager manager = new TaxpayerManager();
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + getextension()));
    String[] has1 =gethas1();
    String[] has2 =gethas2();
    outputStream.println(has1[0] + manager.getTaxpayerName(taxRegistrationNumber) + has2[0]);
    outputStream.println(has1[1] + taxRegistrationNumber + has2[1]);
    outputStream.println(has1[2] + manager.getTaxpayerIncome(taxRegistrationNumber) + has2[2]);
    outputStream
        .println(has1[3] + manager.getTaxpayerBasicTax(taxRegistrationNumber) + has2[3]);
    if (manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream.println(has1[4]
          + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + has2[4]);
    } else {
      outputStream.println(has1[5]
          + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + has2[5]);
    }
    outputStream
        .println(has1[6] + manager.getTaxpayerTotalTax(taxRegistrationNumber) + has2[6]);
    outputStream.println(
        has1[7] +manager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber) + has2[7]);
    outputStream.println(
        has1[8] + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT)
            + has2[8]);
    outputStream.println(
        has1[9] + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC) + has2[9]);
    outputStream.println(
        has1[10] + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL) + has2[10]);
    outputStream.println(
        has1[11] + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH) + has2[11]);
    outputStream.println(
        has1[12] + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber,  OTHER) + has2[12]);
    outputStream.close();
  }

}
