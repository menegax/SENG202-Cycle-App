package seng202.team7.DataTypes;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for a Retailer object to hold parameters and basic getters and setters
 * @author Aidan Smith asm142, Morgan English
 * Last edited 05/09/17
 */
public class Retailer extends Location implements Data, java.io.Serializable{
    /**
     * SQL tablename
     */
    public static String tableName = "retailer";
    /**
     * SQL table columns
     */
    public static String[] columns = {"id","name","city","pAddress","sAddress","state","zipCode","typeID","type","latitude","longitude","datagroup","obj","street"};
    /**
     * SQL table creation script
     */
    public static String tableCreation = "CREATE TABLE IF NOT EXISTS "+tableName+" (\n"
            + columns[0]+" integer PRIMARY KEY NOT NULL ,\n"
            + columns[1]+" text,\n"
            + columns[2]+" text,\n"
            + columns[3]+" text,\n"
            + columns[4]+" text,\n"
            + columns[5]+" text,\n"
            + columns[6]+" integer,\n"
            + columns[7]+" text,\n"
            + columns[8]+" text,\n"
            + columns[9]+" real NOT NULL,\n"
            + columns[10]+" real NOT NULL,\n"
            + columns[11]+" text,\n"
            + columns[12]+" blob,\n"
            + columns[13]+" text"
            + ");";
    /**
     * Retailer name
     */
    private String name;
    /**
     * City
     */
    private String city;
    /**
     * Primary Address
     */
    private String pAddress;
    /**
     * Secondary Address
     */
    private String sAddress;
    /**
     * State
     */
    private String state;
    /**
     * zipCode
     */
    private int zipCode;
    /**
     * Letter representation of type. Could be made an enum
     */
    private String typeID;
    /**
     * Better description of type
     */
    private String type;
    /**
     * Datagroup string for database storing
     */
    private String dataGroup;
    /**
     * Street where the retailer is to be used for filtering
     */
    private String street;
    /**
     * The latitude where the retailer is to be placed
     */
    private double latitude;
    /**
     * The longitude where the retailer is to be placed
     */
    private double longitude;

    /**
     * todo
     * @param name Name of retailer
     * @param city City where retailer is located
     * @param pAddress Primary address of retailer
     * @param sAddress Secondary address of retailer
     * @param state State where retailer is located
     * @param zipCode Zip code of retailer
     * @param typeID ID for type of retailer
     * @param type Type of retailer
     * @param dataGroup Data group that retailer belongs to
     */
    public Retailer(String name, String city, String pAddress, String sAddress, String state, int zipCode, String typeID, String type, String dataGroup)
    {
        this.name = name;
        this.city = city;
        this.pAddress = pAddress;
        boolean streetStart = false;
        String street = "";
        for (int i = 0; i < pAddress.length(); i++) {
            char character = pAddress.charAt(i);
            if (streetStart) {
                street += character;
            } else if (!Character.isDigit(character)) {
                if (!Character.isDigit(pAddress.charAt(i + 1))) {
                    if (i == 0) {
                        street += character;
                        streetStart = true;
                    } else if (character == ' ') {
                        streetStart = true;
                    }
                }
            }
        }
        this.street = street;
        this.sAddress = sAddress;
        this.state = state;
        this.zipCode = zipCode;
        this.type = type;
        Map<String, String> typeMap = new HashMap<>();
        typeMap.put("F", "Food");
        typeMap.put("N", "Nightlife");
        typeMap.put("S", "Shopping");
        typeMap.put("P", "Personal/Professional Services");
        typeMap.put("V", "Visitor Services");
        typeMap.put("C", "Community Resources");
        this.typeID = typeMap.get(typeID);
        this.dataGroup = dataGroup;
        String new_address = pAddress +  ", " + city + ", " + state;
        new_address = new_address.replaceAll(" ", "+");
        try {
            addressToLATLONG(new_address);
        } catch (java.lang.Exception e) {
            System.out.println("Exception raised");
        }
    }


    /**
     * Converts an address to a latitude and longitude and stores them within the object.
     * Should be ran on instantiation
     * @param address the address in format of zip code, city of retailer
     */
    public void addressToLATLONG(String address) throws Exception, java.lang.Exception
    {
        int responseCode = 0;
        String api = "https://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=false&key=AIzaSyAXiol1RxaCNg_MPY7bU696vfv_8R42xQ0 ";
        System.out.println("URL : "+api);
        URL url = new URL(api);
        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        httpConnection.connect();
        responseCode = httpConnection.getResponseCode();
        if(responseCode == 200) {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            ;
            Document document = builder.parse(httpConnection.getInputStream());
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("/GeocodeResponse/status");
            String status = (String) expr.evaluate(document, XPathConstants.STRING);
            if (status.equals("OK")) {
                expr = xpath.compile("//geometry/location/lat");
                String latitude = (String) expr.evaluate(document, XPathConstants.STRING);
                expr = xpath.compile("//geometry/location/lng");
                String longitude = (String) expr.evaluate(document, XPathConstants.STRING);
                double d_latitude = Double.parseDouble(latitude);
                double d_longitude = Double.parseDouble(longitude);
                this.latitude = d_latitude;
                this.longitude = d_longitude;
            } else {
                throw new Exception("Error from the API - response status: " + status);
            }
        }
    }

    /**
     * Simple getter for retailer object's name
     * @return retailers object's name
     */
    public String getName() {
        return name;
    }

    /**
     * Simple setter for retailer object's name
     * @param name new retailer object's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Simple getter for the retailer object's city
     * @return retailer objects city
     */
    public String getCity() {
        return city;
    }

    /**
     * Simple setter for retailer object's city
     * @param city new retailer object's city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Simple getter for retailer object's primary address
     * @return retailers object's primary address
     */
    public String getPAddress() {
        return pAddress;
    }

    /**
     * Simple setter for retailer object's primary address
     * @param pAddress new retailer object's primary address
     */
    public void setPAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    /**
     * Simple getter for retailer object's secondary address
     * @return retailers object's secondary address
     */
    public String getSAddress() {
        return sAddress;
    }

    /**
     * Simple setter for retailer object's secondary address
     * @param sAddress new retailer object's secondary address
     */
    public void setSAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    /**
     * Simple getter for retailer object's state
     * @return retailers object's state
     */
    public String getState() {
        return state;
    }

    /**
     * Simple setter for retailer object's state
     * @param state new retailer object's state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Simple getter for retailer object's zip code
     * @return retailers object's zip code
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * Simple setter for retailer object's zip code
     * @param zipCode new retailer object's zip code
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Simple getter for retailer object's type id
     * @return retailers object's type id
     */
    public String getTypeID() {
        return typeID;
    }

    /**
     * Simple setter for retailer object's type id
     * @param typeID new retailer object's type id
     */
    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    /**
     * Simple getter for retailer object's type
     * @return retailers object's type
     */
    public String getType() {
        return type;
    }

    /**
     * Simple setter for retailer object's type
     * @param type new retailer object's type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Simple getter for retailer object's data group
     * @return retailers object's data group
     */
    public String getDataGroup() {
        return dataGroup;
    }

    /**
     * Simple setter for retailer object's data group
     * @param dataGroup new retailer object's data group
     */
    public void setDataGroup(String dataGroup) {
        this.dataGroup = dataGroup;
    }

    /**
     * Simple getter for retailer object's latitude
     * @return retailers object's latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Simple setter for retailer object's latitude
     * @param latitude new retailer object's latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Simple getter for retailer object's longitude
     * @return retailers object's longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Simple setter for retailer object's longitude
     * @param longitude new retailer object's longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Simple getter for retailer object's street
     * @return retailers object's street
     */
    public String getStreet() { return street; }

    /**
     * Simple setter for retailer object's street
     * @param street new retailer object's street
     */
    public void setStreet(String street) { this.street = street; }

    public void print(){
        System.out.println("Name: " + name +" Lat: "+latitude + " Lon: "+ longitude);
    }

    /**
     * Converts the retailer object into a hash code
     * @return result fo conversion
     */
    @Override
    public int hashCode() {
        StaticVariables converter = new StaticVariables();
        int result = 0;
        result = ((converter.asciiConverter(name) + zipCode
                + converter.asciiConverter(type) + converter.asciiConverter(pAddress)
                + converter.asciiConverter(sAddress) ) * 37) / 11;

        return result;
    }
}
