package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import incometaxcalculator.data.management.*;

import static org.junit.jupiter.api.Assertions.*;

class MarriedFilingJointlyTaxpayerTest {

  @Test
  void test() {
    MarriedFilingJointlyTaxpayer xristos = new MarriedFilingJointlyTaxpayer("xristos", 0001, 30000);
    MarriedFilingJointlyTaxpayer giorgos = new MarriedFilingJointlyTaxpayer("giorgos", 0002, 70000);
    MarriedFilingJointlyTaxpayer giannis1 = new MarriedFilingJointlyTaxpayer("giannis", 0003,
        120000);
    MarriedFilingJointlyTaxpayer giannis2 = new MarriedFilingJointlyTaxpayer("giannis", 0004,
        145000);
    MarriedFilingJointlyTaxpayer ali = new MarriedFilingJointlyTaxpayer("ali", 0005, 255000);

    assertEquals(xristos.calculateBasicTax(), 0.0535 * 30000);
    assertEquals(giorgos.calculateBasicTax(), 1930.28 + 0.0705 * (70000 - 36080));
    assertEquals(giannis1.calculateBasicTax(), 5731.64 + 0.0705 * (120000 - 90000));
    assertEquals(giannis2.calculateBasicTax(), 9492.82 + 0.0785 * (145000 - 143350));
    assertEquals(ali.calculateBasicTax(), 18197.69 + 0.0985 * (255000 - 254240));
  }

}
