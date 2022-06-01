package incometaxcalculator.data.io;

import java.io.BufferedReader;
import java.io.IOException;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class XMLFileReader extends FileReader {

  protected int check4Receipt(String values[])
      throws NumberFormatException, IOException {
      if (values[0].equals("<ReceiptID>")) {
        return 1;
      }
      return -1;

  }

  protected String getValueOfFieldHelper(String valueWithTail[]) throws WrongFileFormatException {

      String valueReversed[] = new StringBuilder(valueWithTail[1]).reverse().toString().trim().split(" ", 2);
   

      return new StringBuilder(valueReversed[1]).reverse().toString();

  }

}
