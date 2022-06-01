package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerFactory {
    private Taxpayer templates[]=new Taxpayer[4];
    public TaxpayerFactory() throws WrongTaxpayerStatusException {
      templates[0]=new MarriedFilingJointlyTaxpayer("Married Filing Jointly", -1, 0);
      templates[1]=new MarriedFilingSeparatelyTaxpayer("Married Filing Separately", -1, 0);
      templates[2]= new SingleTaxpayer("Single", -1, 0);
      templates[3]=new HeadOfHouseholdTaxpayer("Head of Household", -1, 0);
    }
    public Taxpayer createTaxpayerFactory(String fullname, int taxRegistrationNumber, String status,
        float income) throws WrongTaxpayerStatusException {
      for(int i=0;i<4;i++) {
        if(templates[i].getFullname().equals(status)) {
          return (Taxpayer)templates[i].clone (fullname, taxRegistrationNumber, income);
        }
      }
      throw new WrongTaxpayerStatusException();

      
    }

}
