public abstract class B implements A {
    private int a = 0;
    protected int b = 0;

    abstract public String calcFirstLetter(Integer a);

    public String calcName(Integer num) {
        calcFirstLetter(num);
        return "";
    }
}
