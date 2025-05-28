public class Address {

    private String city;
    private String country;
    private String street;
    private int buildingNumber;

    // Constructor to initialize the variables
    public Address(String city, String country, String street, int buildingNum) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.buildingNumber = buildingNum;
    }
    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", street='" + street + '\'' +
                ", buildingNum=" + buildingNumber +
                '}';
    }
    // Getter and Setter for city
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter and Setter for country
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Getter and Setter for street
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    // Getter and Setter for buildingNum
    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
