package incometaxcalculator.data.io;

import java.io.BufferedReader;
import java.io.IOException;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class TXTFileReader extends FileReader {

  protected int check4Receipt(String values[])
      throws NumberFormatException, IOException {
      if (values[0].equals("Receipt") && values[1].equals("ID:")) {
              return 2;
      }
      return -1;
  }
  

  protected String getValueOfFieldHelper(String values[]) throws WrongFileFormatException {
      values[1] = values[1].trim();
      return values[1];

  }


}