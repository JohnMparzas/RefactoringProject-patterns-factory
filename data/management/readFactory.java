package incometaxcalculator.data.management;

import java.io.IOException;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.data.io.XMLInfoWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class readFactory {
  private FileReader readers[]=new FileReader[2];
  private String tupos[]= {"txt","xml"};
  
  public readFactory() {
    
    readers[0]=new TXTFileReader();
    readers[1]=new XMLFileReader();
    
   
    
    
  }
  public FileReader getReader(String format)throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
  WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {

    for(int i=0;i<2;i++) {
      if(format.equals(tupos[i])) {
        return readers[i];
        
      }
    }
    throw new WrongFileEndingException();

    
  }

}
