package model;

public class VitalSigns {

    private double bloodPressure;
    private double spo2;
    private double heartRate;
    private double temperature;

    public VitalSigns(double bloodPressure, double spo2,
                      double heartRate, double temperature) {

        this.bloodPressure = bloodPressure;
        this.spo2 = spo2;
        this.heartRate = heartRate;
        this.temperature = temperature;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public double getSpo2() {
        return spo2;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "BP: " + bloodPressure +
               ", SpO2: " + spo2 + "%" +
               ", Heart Rate: " + heartRate + " bpm" +
               ", Temperature: " + temperature + "°C";
    }
}