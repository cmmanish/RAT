package com.lyve.service.object;

/**
 * Created by mmadhusoodan on 3/3/15.
 */
public class AgentObject {

    public String agentId = "";
    public String deviceClass = "";
    public String displayName = "";
    public String devicePlatform = "";
    public String deviceType = "";
    public String lastSeen = "";

    public long storageCapacityTotalBytes;
    public boolean wasOnline = false;
    public boolean isReplicationTarget = false;

    public Long imageCount = 0l;
    public Long videoCount = 0l;

    public PresenceDataObject presenceDataObject;

    public static class PresenceDataObject {
        public static ConnectivityStatus connectivityStatus;
        public static PowerStatus powerStatus;
    }

    public static class ConnectivityStatus {
        public static String primaryConnectivityType;
        public static CellularNetworkInfo cellularNetworkInfo;
    }

    public static class PowerStatus {
        public static String powerSource;
        public static String batteryStatus;
        public static String batteryLevel;

    }
    public static class CellularNetworkInfo{
        public static String  network_operator_name;
        public static String  carrier_iso_country_code;
        public static String  carrier_mcc_mnc_code;
        public static String  carrier_name;

    }
}
