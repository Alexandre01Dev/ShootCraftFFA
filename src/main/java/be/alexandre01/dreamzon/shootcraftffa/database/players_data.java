package be.alexandre01.dreamzon.shootcraftffa.database;

public class players_data {
    private int dreamcoins;
    private String name;

    public String getName() {
        return name;
    }

    public int getDreamcoins() {
        return dreamcoins;
    }

    public void setDreamcoins(int dreamcoins) {
        this.dreamcoins = dreamcoins;
    }

    public players_data(int dreamcoins, String name) {
        this.dreamcoins = dreamcoins;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
