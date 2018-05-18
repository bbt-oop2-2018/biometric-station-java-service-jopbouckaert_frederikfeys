package biometricstationserialreceiver;

/**
 *
 * @author jopbo_000
 */
public class BioMetricStationStringParser {
    
    public SensorData parse(String dataString) {
        final double CHECKSUM_TOLERANCE = 0.01;
        if (!isValidString(dataString)) {
            return null;
        }

        int temperatureStart = dataString.indexOf("ABCDE");
        int heartBeatStart = dataString.indexOf("|");
        int xAcceleroStart = dataString.indexOf("XXX");
        int yAcceleroStart = dataString.indexOf("YYY");
        int zAcceleroStart = dataString.indexOf("ZZZ");
        int checksumStart = dataString.indexOf("CCC");
        int checksumEnd = dataString.indexOf("EDCBA");
        String temperatureString = dataString.substring(temperatureStart + 5, heartBeatStart);
        String heartBeatString = dataString.substring(heartBeatStart + 1, xAcceleroStart);
        String xAcceleroString = dataString.substring(xAcceleroStart + 3, yAcceleroStart);
        String yAcceleroString = dataString.substring(yAcceleroStart + 3, zAcceleroStart);
        String zAcceleroString = dataString.substring(zAcceleroStart + 3, checksumStart);
        String checksumString = dataString.substring(checksumStart + 3, checksumEnd);
        try {
            double temperature = Double.parseDouble(temperatureString);
            double heartBeat = Double.parseDouble(heartBeatString);
            double xAccelero = Double.parseDouble(xAcceleroString);
            double yAccelero = Double.parseDouble(yAcceleroString);
            double zAccelero = Double.parseDouble(zAcceleroString);
            double checksum = Double.parseDouble(checksumString);

            if (Math.abs(temperature + heartBeat + xAccelero + yAccelero + zAccelero - checksum) <= CHECKSUM_TOLERANCE) {
                return new SensorData(temperature, heartBeat, xAccelero, yAccelero, zAccelero);
            }
        } catch (Exception e) {
           System.out.println("Received invalid data has been thrown");
            return null;
        }
        
        return null;
    }

    private boolean isValidString(String dataString) {
        return (dataString.indexOf("ABCDE") != -1
                && dataString.indexOf("EDCBA") != -1
                && dataString.indexOf("XXX") != -1
                && dataString.indexOf("YYY") != -1
                && dataString.indexOf("ZZZ") != -1
                && dataString.indexOf("|") != -1
                && dataString.indexOf("CCC") != -1);
    }

}
