package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.TaxpayerManager;

public class XMLLogWriter extends LogWriter {
  private String [] has1= {"<Name> ","<AFM> ","<Income> ","<BasicTax> ","<TaxIncrease> ","<TaxDecrease> ","<TotalTax> ","<Receipts> ","<Entertainment> ","<Basic> ","<Travel> ","<Health> ","<Other> "};
  private String [] has2= {" </Name> "," </AFM> "," </Income> "," </BasicTax> "," </TaxIncrease> "," </TaxDecrease> ","  </TotalTax> "," </Receipts> "," </Entertainment> "," </Basic> "," </Travel> "," </Health> "," </Other> "};
  
  public String [] gethas1() {
    return has1;
  }
  public String [] gethas2() {
    return has2;
  }
  public String getextension() {
    return "_LOG.xml";
  }

  

}
