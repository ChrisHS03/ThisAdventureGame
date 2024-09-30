public class Item {
    private String longname;
    private String shortname;

    public Item (String longname, String shortname){
        this.longname = longname;
        this.shortname = shortname;
    }

    public String getLongname(){
        return longname;
    }
    public String getShortname(){
        return shortname;
    }
    public void setLongname(String longname){
        this.longname = longname;
    }
    public void setShortname(String shortname){
        this.shortname = shortname;
    }
}
