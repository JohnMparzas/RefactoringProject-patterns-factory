package tests;

import static org.junit.jupiter.api.Assertions.*;
import incometaxcalculator.data.management.*;
import org.junit.jupiter.api.Test;

class HeadOfHouseholdTaxpayerTest {

  @Test
  void test() {

    HeadOfHouseholdTaxpayer xristos = new HeadOfHouseholdTaxpayer("xristos", 0001, 30000);
    HeadOfHouseholdTaxpayer giorgos = new HeadOfHouseholdTaxpayer("giorgos", 0002, 70000);
    HeadOfHouseholdTaxpayer giannis1 = new HeadOfHouseholdTaxpayer("giannis", 0003, 120000);
    HeadOfHouseholdTaxpayer giannis2 = new HeadOfHouseholdTaxpayer("giannis", 0004, 140000);
    HeadOfHouseholdTaxpayer ali = new HeadOfHouseholdTaxpayer("ali", 0005, 250000);
    assertEquals(xristos.calculateBasicTax(), 0.0535 * 30000);
    assertEquals(giorgos.calculateBasicTax(), 1625.87 + 0.0705 * (70000 - 30390));
    assertEquals(giannis1.calculateBasicTax(), 5828.38 + 0.0705 * (120000 - 90000));
    assertEquals(giannis2.calculateBasicTax(), 8092.13 + 0.0785 * (140000 - 122110));
    assertEquals(ali.calculateBasicTax(), 14472.61 + 0.0985 * (250000 - 203390));
  }

}
