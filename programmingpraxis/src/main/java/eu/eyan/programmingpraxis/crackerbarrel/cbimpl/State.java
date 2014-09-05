package eu.eyan.programmingpraxis.crackerbarrel.cbimpl;

public enum State
{
    FULL("F"),
    EMPTY("E");

    private String title;

    State(String s)
    {
        this.title = s;
    }

    @Override
    public String toString()
    {
        return title;
    }

    public String getTitle()
    {
        return title;
    }
}