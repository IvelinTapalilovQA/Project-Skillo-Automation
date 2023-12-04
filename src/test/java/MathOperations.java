import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MathOperations {

    @DataProvider(name = "generateAdditionalData")
    public Object[][] generateAdditionalData() {
        return new Object[][]{
                {8, 8, 16},
                {2, 3, 5},
                {5, 6, 11},
                {10, 4, 14}
        };
    }
    @Test(dataProvider = "generateAdditionalData", groups = "Additional")
    public void addNumbers(int numberOne, int numberTwo, int expectedSum){
        int sum = numberOne + numberTwo;
        Assert.assertEquals(sum, expectedSum);
    }
    @DataProvider(name = "generateSubtractData")
    public Object[][] generateSubtractData(){
        return new Object[][]{
                {5, 4, 1},
                {9, 5, 4},
                {14, 2, 12},
                {15, 5, 10}
        };
    }

    @Test(dataProvider = "generateSubtractData", groups = "Subtract")
    public void subtractNumbers(int numberOne, int numberTwo, int expectedResult){
        int result = numberOne - numberTwo;
        Assert.assertEquals(result, expectedResult);
    }

    @DataProvider(name = "generateMultiplyData")
    public Object[][] generateMultiplyNumbersData(){
        return new Object[][]{
                {8, 8, 64},
                {7, 2, 14},
                {9, 3, 27},
                {5, 9, 45}
        };
    }
    @Test(dataProvider = "generateMultiplyData", groups = "Multiply")
    public void multiplyNumbers(int numberOne, int numberTwo, int expectedResult){
        int result = numberOne * numberTwo;
        Assert.assertEquals(result, expectedResult);
    }

    @DataProvider(name = "generateDivideData")
    public Object[][] generateDivideNumbersData(){
        return new Object[][] {
                {10, 5, 2},
                {20, 4, 5},
                {80, 4, 20},
                {44, 4, 11}
        };
    }

    @Test(dataProvider = "generateDivideData", groups = "Divide")
    public void divideNumbers(int numberOne, int numberTwo, int expectedResult){
        int result = numberOne / numberTwo;
        Assert.assertEquals(result, expectedResult);
    }

    @DataProvider(name = "generateModuloData")
    public Object[][] generateModuloNumberData(){
        return new Object[][]{
                {5, 2, 1},
                {10, 5 ,0},
                {15, 3 , 0},
                {30 , 10, 0}
        };
    }

    @Test(dataProvider = "generateModuloData",groups = "Modulo")
    public void moduloNumbers(int numberOne, int numberTwo, int expectedResult){
        int result = numberOne % numberTwo;
        Assert.assertEquals(result, expectedResult);
    }
}