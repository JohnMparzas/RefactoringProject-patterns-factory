package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class HeadOfHouseholdTaxpayer extends Taxpayer {
  protected int krhthriaBasic[]= {0,30390,90000,122110,203390};
  protected double syntelestesBasic[]= {0,1625.87,5828.38,8092.13,14472.61};
  protected double syntelestes2Basic[]= {0.0535,0.0705,0.0705 ,0.0785, 0.0985};
  public HeadOfHouseholdTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    updateConstants(krhthriaBasic,syntelestesBasic,syntelestes2Basic);
  }
  public Taxpayer clone(String fullname, int taxRegistrationNumber,
      float income) {
    
    HeadOfHouseholdTaxpayer copied=new HeadOfHouseholdTaxpayer(fullname, taxRegistrationNumber, income);
    return copied;
    
 }


}
