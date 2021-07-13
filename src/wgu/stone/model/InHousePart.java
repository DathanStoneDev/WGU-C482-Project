package wgu.stone.model;

/**
 * InHousePart class extends the Part class.
 */
public class InHousePart extends Part{

    /**
     * Added field for an InHouse Part
     */
    private int machineId;

    /**
     * Constructor to create an InHouse Part
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHousePart(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * @return the machineId
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * sets the machineId
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

}
