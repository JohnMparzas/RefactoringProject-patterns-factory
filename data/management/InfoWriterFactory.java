package incometaxcalculator.data.management;


import java.io.IOException;

import incometaxcalculator.data.io.*;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;


public class InfoWriterFactory {
  private String tupos[]= {"txt","xml"};
  
  
  private InfoWriter writersinfo[]=new InfoWriter[2];
  
  public InfoWriterFactory() {
    

    
    
    
    
  }
 

  public InfoWriter getInfoWriter(String format)throws IOException, WrongFileFormatException {
    
    writersinfo[0]=new TXTInfoWriter();
    writersinfo[1]=new XMLInfoWriter();
    for(int i=0;i<2;i++) {
      if(format.equals(tupos[i])) {
        return writersinfo[i];
        
      }
    }
    throw new WrongFileFormatException();
  }

}
