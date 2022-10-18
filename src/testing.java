import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * testing: the testing class is using junit to do some method testing of the bank and Cashier Classes
 */
public class testing {

    @Test
    /**
     * checkSetBalanceAndGetBalance1: checks set balance with no rounding
     */
    public void checkSetBalanceAndGetBalance1(){
        bank test = new bank();
        BigDecimal test_1 = new BigDecimal(100.05);
        test.setBalance(test_1);
        assertEquals(test.getBalance(),test_1.setScale(2, RoundingMode.HALF_UP));
    }
    @Test
    /**
     * checkSetBalanceAndGetBalance2: checks set balance with rounding
     */
    public void checkSetBalanceAndGetBalance2(){
        bank test = new bank();
        BigDecimal test_1 = new BigDecimal(100.005);
        test.setBalance(test_1);
        assertEquals(test.getBalance(),test_1.setScale(2, RoundingMode.HALF_UP));
    }
    @Test
    /**
     * checkUpdateBalance1: checks update balance with subtraction
     */
    public void checkUpdateBalance1(){
        bank test = new bank();
        BigDecimal test_1 = new BigDecimal(100);
        test.setBalance(test_1);
        BigDecimal test_2 = new BigDecimal(50);
        test.updateBalance(0,test_2);
        assertEquals(test.getBalance(),test_2.setScale(2, RoundingMode.HALF_UP));
    }
    @Test
    /**
     * checkUpdateBalance2: checks update balance with subtraction and overdraft
     */
    public void checkUpdateBalance2(){
        bank test = new bank();
        BigDecimal test_1 = new BigDecimal(100);
        test.setBalance(test_1);
        BigDecimal test_2 = new BigDecimal(101);
        assertEquals(test.updateBalance(0,test_2),0);
    }
    @Test
    /**
     * checkUpdateBalance3: checks update balance with addition
     */
    public void checkUpdateBalance3(){
        bank test = new bank();
        BigDecimal test_1 = new BigDecimal(100);
        test.setBalance(test_1);
        BigDecimal test_2 = new BigDecimal(50);
        test.updateBalance(1,test_2);
        BigDecimal sol = new BigDecimal(150);
        assertEquals(test.getBalance(),sol.setScale(2,RoundingMode.HALF_UP));
    }
    @Test
    /**
     * checkCalculateChange: tests with a difficult change amount in USD
     */
    public void checkCalculateChange(){
        Cashier test_c = new Cashier();
        BigDecimal test = new BigDecimal(16.16);
        test.setScale(2,RoundingMode.HALF_UP);
        Map<BigDecimal,Integer> map = new LinkedHashMap<BigDecimal,Integer>();
        map.put(new BigDecimal("20.00").setScale(2,RoundingMode.HALF_UP), 0);
        map.put(new BigDecimal("10.00").setScale(2,RoundingMode.HALF_UP), 1);
        map.put(new BigDecimal("5.00").setScale(2,RoundingMode.HALF_UP), 1);
        map.put(new BigDecimal("1.00").setScale(2,RoundingMode.HALF_UP), 1);
        map.put(new BigDecimal("0.25").setScale(2,RoundingMode.HALF_UP), 0);
        map.put(new BigDecimal("0.10").setScale(2,RoundingMode.HALF_UP), 1);
        map.put(new BigDecimal("0.05").setScale(2,RoundingMode.HALF_UP), 1);
        map.put(new BigDecimal("0.01").setScale(2,RoundingMode.HALF_UP), 1);
        assertEquals(test_c.calculateChange(test,false),map);
    }
    @Test
    /**
     * checkCalculateChange2: tests with a difficult change amount in SWD
     */
    public void checkCalculateChange2(){
        Cashier test_c = new Cashier();
        BigDecimal test = new BigDecimal(16.16);
        test.setScale(2,RoundingMode.HALF_UP);
        Map<BigDecimal,Integer> map = new LinkedHashMap<BigDecimal,Integer>();
        map.put(new BigDecimal("25.00").setScale(2,RoundingMode.HALF_UP), 0);
        map.put(new BigDecimal("10.00").setScale(2,RoundingMode.HALF_UP), 1);
        map.put(new BigDecimal("5.00").setScale(2,RoundingMode.HALF_UP), 1);
        map.put(new BigDecimal("1.00").setScale(2,RoundingMode.HALF_UP), 1);
        map.put(new BigDecimal("0.20").setScale(2,RoundingMode.HALF_UP), 0);
        map.put(new BigDecimal("0.08").setScale(2,RoundingMode.HALF_UP), 2);
        map.put(new BigDecimal("0.05").setScale(2,RoundingMode.HALF_UP), 0);
        map.put(new BigDecimal("0.01").setScale(2,RoundingMode.HALF_UP), 0);
        assertEquals(test_c.calculateChange(test,true),map);
    }
    @Test
    /**
     * checkTransaction1: tests with SWD exchange rate and addition.
     */
    public void checkTransaction1(){
        Cashier test = new Cashier();
        BigDecimal val = new BigDecimal(100).setScale(2,RoundingMode.HALF_UP);
        bank testb = new bank();
        testb.setBalance(val);
        test.setExchangeRate(3);
        test.transaction((float)99.99,testb,true,1);
        BigDecimal sol = new BigDecimal(133.33);
        assertEquals(testb.getBalance(),sol.setScale(2,RoundingMode.HALF_UP));

    }
    @Test
    /**
     * checkTransaction1: tests with USD and subtraction.
     */
    public void checkTransaction2(){
        Cashier test = new Cashier();
        BigDecimal val = new BigDecimal(100).setScale(2,RoundingMode.HALF_UP);
        bank testb = new bank();
        testb.setBalance(val);
        test.setExchangeRate(1);
        test.transaction((float)99.99,testb,false,0);
        BigDecimal sol = new BigDecimal(0.01);
        assertEquals(testb.getBalance(),sol.setScale(2,RoundingMode.HALF_UP));

    }
}
