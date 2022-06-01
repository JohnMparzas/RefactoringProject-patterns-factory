package incometaxcalculator.data.management;

import java.util.HashMap;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public abstract class Taxpayer {

  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final float income;
  private float amountPerReceiptsKind[] = new float[5];
  private  String []eidos= {"Entertainment","Basic","Travel","Health","Other"};
  private double []krhthria= {0.2,0.4,0.6};
  private double syntelestes []= {0.08 ,0.04 ,-0.15,-0.3};
  protected int krhthriaBasic[]=null;
  protected double syntelestesBasic[]=null;
  protected double syntelestes2Basic[]=null;
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);
  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;

  //public abstract double calculateBasicTax();

  protected Taxpayer(String fullname, int taxRegistrationNumber, float income) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;

  }
  public Taxpayer clone(String fullname, int taxRegistrationNumber,
      float income) {    
    return null;
  }
  public void updateConstants(int krhthriaBasic[],double syntelestesBasic[],double syntelestes2Basic[]) {
    this.krhthriaBasic=krhthriaBasic;
    this.syntelestesBasic=syntelestesBasic;
    this.syntelestes2Basic=syntelestes2Basic;
  }
  public double calculateBasicTax() {
    for(int i=1;i<=4;i++) {
      if(income< krhthriaBasic[i]) {
        return syntelestesBasic[i-1]+syntelestes2Basic[i-1]*(income-krhthriaBasic[i-1]); 
      }  
    }
    return  syntelestesBasic[4]+syntelestes2Basic[4]*(income-krhthriaBasic[4]);
   

  }

  public void addReceipt(Receipt receipt) throws WrongReceiptKindException {
    for(int i=0;i<5;i++) {
      
     if (receipt.getKind().equals(eidos[i])) {
       amountPerReceiptsKind[i] += receipt.getAmount();
     }
     
    
    }
    
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;
  }

  public void removeReceipt(int receiptId) throws WrongReceiptKindException {
    Receipt receipt = receiptHashMap.get(receiptId);
    for(int i=0;i<5;i++) {
     if (receipt.getKind().equals(eidos[i])) {
       amountPerReceiptsKind[i] -= receipt.getAmount();
     }
   
    }
    
    totalReceiptsGathered--;
    receiptHashMap.remove(receiptId);
  }

  public String getFullname() {
    return fullname;
  }

  public int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public float getIncome() {
    return income;
  }

  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }

  public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    for(int i=0;i<3;i++){
      if(totalAmountOfReceipts<krhthria[i]*income) {
        return calculateBasicTax()*syntelestes[i];
      }
    }
    return calculateBasicTax()*syntelestes[3];
    
  
  }

  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += amountPerReceiptsKind[i];
    }
    return sum;
  }

  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public float getAmountOfReceiptKind(short kind) {
    return amountPerReceiptsKind[kind];
  }

  public double getTotalTax() {
    return calculateBasicTax() + getVariationTaxOnReceipts();
  }

  public double getBasicTax() {
    return calculateBasicTax();
  }

}