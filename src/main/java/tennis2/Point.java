package tennis2;


public enum Point {

    LOVE("00"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("AD");

    Point(String billboardRepresentation) {
        this.billboardRepresentation = billboardRepresentation;
    }

    public String billboardRepresentation(){
        return billboardRepresentation;
    }

    private final String billboardRepresentation;


}
