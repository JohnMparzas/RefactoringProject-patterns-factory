package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class XMLInfoWriter extends InfoWriter {
 
  private String hastag1[]= {"<Name> ","<AFM> ","<Status> ","<Income> ","<Receipts>","<ReceiptID> ","<Date> ","<Kind> ","<Amount> ","<Company> ","<Country> ","<City> ","<Street> ","<Number> "};
  private String hastag2[]= {" </Name> "," </AFM> "," </Status> "," </Income> "," </ReceiptID> "," </Date> "," </Kind> "," </Amount> "," </Company> "," </Country> "," </City> "," </Street> "," </Number> "};
  
  public String [] gethast1() {
    return hastag1;
    
  }
  public String [] gethast2() {
    return hastag2;
    
  }
  public String getextension() {
    return "_INFO.xml";
    
  }

}