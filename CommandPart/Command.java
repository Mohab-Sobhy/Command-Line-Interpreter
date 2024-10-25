package CommandPart;

public class Command {
    private String value;
    private boolean isAvailable;

    public void setValue(String Value){
        value = Value;
    }
    public String getValue(){
        return value;
    }
    public void setIsAvailable(boolean IsAvailable){
        isAvailable = IsAvailable;
    }
    public boolean getIsAvailable(){
        return isAvailable;
    }
}
