package incometaxcalculator.data.management;

import java.io.IOException;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.LogWriter;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.data.io.XMLInfoWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.exceptions.WrongFileFormatException;

public class LogWriterFactory {
  private String tupos[]= {"txt","xml"};
  private LogWriter writerslog[]=new LogWriter [2];
  public LogWriterFactory() {
    
    writerslog[0]=new TXTLogWriter();
    writerslog[1]=new XMLLogWriter();
    
    
    
  }
  public LogWriter getLogWriter(String format)throws IOException, WrongFileFormatException {
    for(int i=0;i<2;i++) {
      if(format.equals(tupos[i])) {
        return writerslog[i];
        
      }
    }
    throw new WrongFileFormatException();

    
  }

}
