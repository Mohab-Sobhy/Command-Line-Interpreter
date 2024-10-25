package CommandPart;

public class Option {
    private char value;
    private boolean isAvailable;

    public void setValue(char Value){
        value = Value;
    }
    public char getValue(){
        return value;
    }
    public void setIsAvailable(boolean IsAvailable){
        isAvailable = IsAvailable;
    }
    public boolean getIsAvailable(){
        return isAvailable;
    }
}
