package project;

public class CustomerData {
    private String name;
    private String surname;
    private String country;
    private String city;
    private String occupation;

    public CustomerData() {
        this.name = "";
        this.surname = "";
        this.country = "";
        this.city = "";
        this.occupation = "";
    }

   
    // Constructor
    public CustomerData(String name, String surname, String country, String city, String occupation) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.city = city;
        this.occupation = occupation;
    }
    
    // Copy Constructor
    public CustomerData(CustomerData other) {
        this.name = other.name;
        this.surname = other.surname;
        this.country = other.country;
        this.city = other.city;
        this.occupation = other.occupation;
    }
    
    public String toString() {
        return "Customer Data: " + 
                "\nName: " + name +
                "\nSurname: " + surname +
                "\nCountry: " + country +
                "\nCity: " + city +
                "\nOccupation: " + occupation;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation the occupation to set
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}