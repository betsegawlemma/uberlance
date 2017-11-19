package sample;

public class DriverLocation {

    private String carPlateNumber;
    private String driverPhoneNumber;
    private String driverName;
    private String latitude;
    private String longitude;

    public DriverLocation(){}

    public DriverLocation(String carPlateNumber, String driverPhoneNumber, String driverName, String latitude, String longitude) {
        this.carPlateNumber = carPlateNumber;
        this.driverPhoneNumber = driverPhoneNumber;
        this.driverName = driverName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public String getDriverPhoneNumber() {
        return driverPhoneNumber;
    }

    public void setDriverPhoneNumber(String driverPhoneNumber) {
        this.driverPhoneNumber = driverPhoneNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "DriverLocation{" +
                "carPlateNumber='" + carPlateNumber + '\'' +
                ", driverPhoneNumber='" + driverPhoneNumber + '\'' +
                ", driverName='" + driverName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
