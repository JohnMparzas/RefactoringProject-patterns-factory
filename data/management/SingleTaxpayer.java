package incometaxcalculator.data.management;

public class SingleTaxpayer extends Taxpayer {
  protected int krhthriaBasic[]= {0,24680,81080,90000,152540};
  protected double syntelestesBasic[]= {0,1320.38,5296.58,5996.80,10906.19};
  protected double syntelestes2Basic[]= {0.0535,0.0705,0.0785 ,0.0785, 0.0985};
  
  public SingleTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    updateConstants(krhthriaBasic,syntelestesBasic,syntelestes2Basic);
  }
  public Taxpayer clone(String fullname, int taxRegistrationNumber,
      float income) {
    
    SingleTaxpayer copied=new SingleTaxpayer(fullname, taxRegistrationNumber, income);
    return copied;
    
   }

  /*public double calculateBasicTax() {
    if (income < 24680) {
      return 0.0535 * income;
    } else if (income < 81080) {
      return 1320.38 + 0.0705 * (income - 24680);
    } else if (income < 90000) {
      return 5296.58 + 0.0785 * (income - 81080);
    } else if (income < 152540) {
      return 5996.80 + 0.0785 * (income - 90000);
    } else {
      return 10906.19 + 0.0985 * (income - 152540);
    }
  }*/

}