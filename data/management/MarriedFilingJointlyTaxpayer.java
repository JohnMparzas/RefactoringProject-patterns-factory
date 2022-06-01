package incometaxcalculator.data.management;

public class MarriedFilingJointlyTaxpayer extends Taxpayer {
  protected int krhthriaBasic[]= {0,36080,90000,143350,254240};
  protected double syntelestesBasic[]= {0,1930.28,5731.64,9492.82,18197.69};
  protected double syntelestes2Basic[]= {0.0535,0.0705,0.0705 ,0.0785, 0.0985};
  
  public MarriedFilingJointlyTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    updateConstants(krhthriaBasic,syntelestesBasic,syntelestes2Basic);
  }
  public Taxpayer clone(String fullname, int taxRegistrationNumber,
      float income) {
    
    MarriedFilingJointlyTaxpayer copied=new MarriedFilingJointlyTaxpayer(fullname, taxRegistrationNumber, income);
    return copied;
    
  }



}