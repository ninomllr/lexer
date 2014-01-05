
public enum States {
	S0(0), 
	S1(1), 
	S2(2), 
	S3(3), 
	S4(4), 
	S5(5),
	S6(6),
	S7(7),
	S8(8),
	S9(9),
	S10(10),
	S11(11);
	
	private final int value;
    private States(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
