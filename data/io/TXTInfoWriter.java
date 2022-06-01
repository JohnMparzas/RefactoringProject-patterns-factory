package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class TXTInfoWriter extends InfoWriter {
 private TaxpayerManager manager = new TaxpayerManager();
 private String hastag1[]= {"Name: ","AFM: ","Status: ","Income: ","Receipts:","Receipt ID: ","Date: ","Kind: ","Amount: ","Company: ","Country: ","City: ","Street: ","Number: "};
 private String hastag2[]= {"","","","","","","","","","","","",""};
 public String [] gethast1() {
   return hastag1;
   
 }
   public TXTInfoWriter () {
   
 }
 public String [] gethast2() {
   return hastag2;
   
 }
 public String getextension() {
   return "_INFO.txt";
   
 }


}