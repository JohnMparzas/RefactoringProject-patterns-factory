package incometaxcalculator.data.management;

public class MarriedFilingSeparatelyTaxpayer extends Taxpayer {
  protected int krhthriaBasic[]= {0,18040,71680,90000,127120};
  protected double syntelestesBasic[]= {0,965.14,4746.76,6184.88,9098.80};
  protected double syntelestes2Basic[]= {0.0535,0.0705,0.0785 ,0.0785, 0.0985};
  
  public MarriedFilingSeparatelyTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    updateConstants(krhthriaBasic,syntelestesBasic,syntelestes2Basic);
  }
  public Taxpayer clone(String fullname, int taxRegistrationNumber,
      float income) {
    
    MarriedFilingSeparatelyTaxpayer copied=new MarriedFilingSeparatelyTaxpayer(fullname, taxRegistrationNumber, income);
    return copied;
    
   }


}