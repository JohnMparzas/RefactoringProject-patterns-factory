package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.TaxpayerManager;

public class TXTLogWriter extends LogWriter {
  private String []has= {"Name: ","AFM: ","Income: ","Basic Tax: ","Tax Increase: ","Tax Decrease: ","Total Tax: ","TotalReceiptsGathered: ","Entertainment: ","Basic: ","Travel: ","Health: ","Other: "};
  private String []has2= {"","","","","","","","","","","","",""};
  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;

  public String [] gethas1() {
    return has;
  }
  public String [] gethas2() {
    return has2;
  }
  public String getextension() {
    return "_LOG.txt";
  }

  

}
