package System.FootballObjects;

public class Field {

    private int id;
    private String name;
    private int maintenanceCost;

    //<editor-fold desc="constructor">
    public Field(int id, String name, int maintenanceCost) {
        this.id = id;
        this.name = name;
        this.maintenanceCost = maintenanceCost;
    }
    //</editor-fold>

    //<editor-fold desc="getter">
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaintenanceCost() {
        return maintenanceCost;
    }
    //</editor-fold>

    //<editor-fold desc="setter">
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaintenanceCost(int maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }
    //</editor-fold>




}
