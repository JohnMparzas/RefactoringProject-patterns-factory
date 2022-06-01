package tests;

import incometaxcalculator.data.management.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MarriedFilingSeparatelyTaxpayerTest {

  @Test
  void test() {
    MarriedFilingSeparatelyTaxpayer xristos = new MarriedFilingSeparatelyTaxpayer("xristos", 0001,
        17000);
    MarriedFilingSeparatelyTaxpayer giorgos = new MarriedFilingSeparatelyTaxpayer("giorgos", 0002,
        70000);
    MarriedFilingSeparatelyTaxpayer giannis1 = new MarriedFilingSeparatelyTaxpayer("giannis", 0003,
        80000);
    MarriedFilingSeparatelyTaxpayer giannis2 = new MarriedFilingSeparatelyTaxpayer("giannis", 0004,
        126000);
    MarriedFilingSeparatelyTaxpayer ali = new MarriedFilingSeparatelyTaxpayer("ali", 0005, 250000);
    assertEquals(xristos.calculateBasicTax(), 0.0535 * 17000);
    assertEquals(giorgos.calculateBasicTax(), 965.14 + 0.0705 * (70000 - 18040));
    assertEquals(giannis1.calculateBasicTax(), 4746.76 + 0.0785 * (80000 - 71680));
    assertEquals(giannis2.calculateBasicTax(), 6184.88 + 0.0785 * (126000 - 90000));
    assertEquals(ali.calculateBasicTax(), 9098.80 + 0.0985 * (250000 - 127120));
  }

}
