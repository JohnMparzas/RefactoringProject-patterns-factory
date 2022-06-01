package tests;

import incometaxcalculator.data.management.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SingleTaxpayerTest {

  @Test
  void test() {
    SingleTaxpayer xristos = new SingleTaxpayer("xristos", 0001, 17000);
    SingleTaxpayer giorgos = new SingleTaxpayer("giorgos", 0002, 70000);
    SingleTaxpayer giannis1 = new SingleTaxpayer("giannis", 0003, 82000);
    SingleTaxpayer giannis2 = new SingleTaxpayer("giannis", 0004, 126000);
    SingleTaxpayer ali = new SingleTaxpayer("ali", 0005, 250000);

    assertEquals(xristos.calculateBasicTax(), 0.0535 * 17000);
    assertEquals(giorgos.calculateBasicTax(), 1320.38 + 0.0705 * (70000 - 24680));
    assertEquals(giannis1.calculateBasicTax(), 5296.58 + 0.0785 * (82000 - 81080));
    assertEquals(giannis2.calculateBasicTax(), 5996.80 + 0.0785 * (126000 - 90000));
    assertEquals(ali.calculateBasicTax(), 10906.19 + 0.0985 * (250000 - 152540));
  }

}
