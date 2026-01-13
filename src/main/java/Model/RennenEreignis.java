package Model;

public class RennenEreignis {
    private int id;
    private int fahrerId;
    private String typ;
    private int basePoints;
    private int lap;

    public RennenEreignis() {}

    public RennenEreignis(int id, int fahrerId, String typ, int basePoints, int lap) {
        this.id = id;
        this.fahrerId = fahrerId;
        this.typ = typ;
        this.basePoints = basePoints;
        this.lap = lap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFahrerId() {
        return fahrerId;
    }

    public void setFahrerId(int fahrerId) {
        this.fahrerId = fahrerId;
    }

    public String gettyp() {
        return typ;
    }

    public void settyp(String typ) {
        this.typ = typ;
    }

    public int getBasePoints() {
        return basePoints;
    }

    public void setBasePoints(int basePoints) {
        this.basePoints = basePoints;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    @Override
    public String toString() {
        return "RennenEreignis{" +
                "id=" + id +
                ", fahrerId=" + fahrerId +
                ", typ='" + typ + '\'' +
                ", basePoints=" + basePoints +
                ", lap=" + lap +
                '}';
    }
}
