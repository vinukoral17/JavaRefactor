package base;

public class RowOfDigits {
    private int num1;
    private int num2;
    private int count;
    private int spacing;
    private int initialDigit;

    public RowOfDigits(int num1, int num2, int count, int spacing, int initialDigit) {
        this.num1 = num1;
        this.num2 = num2;
        this.count = count;
        this.spacing = spacing;
        this.initialDigit = initialDigit;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getCount() {
        return count;
    }

    public int getSpacing() {
        return spacing;
    }

    public int getInitialDigit() {
        return initialDigit;
    }
}