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
}
