package wgu.stone.model;

/**
 * Outsource part class.
 */
public class OutsourcedPart extends Part{

    /**
     * Adds a companyName field.
     */
    private String companyName;

    /** RUNTIME ERROR
     * The company name for the outsource part would not populate when the modify button was clicked.
     * This was resolved by adding "this.companyName = companyName;" into the constructor.
     * Constructor that can create outsourced part objects.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @return outsource part's company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * sets the company name.
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
