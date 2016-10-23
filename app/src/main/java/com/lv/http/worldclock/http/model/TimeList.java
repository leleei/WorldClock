package com.lv.http.worldclock.http.model;

import java.util.List;

public class TimeList {

    private String message;
    private String status;

    private List<ZonesBean> zones;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ZonesBean> getZones() {
        return zones;
    }

    public void setZones(List<ZonesBean> zones) {
        this.zones = zones;
    }

    public static class ZonesBean {
        private String countryCode;
        private String countryName;
        private int    gmtOffset;
        private int    timestamp;
        private String zoneName;

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public int getGmtOffset() {
            return gmtOffset;
        }

        public void setGmtOffset(int gmtOffset) {
            this.gmtOffset = gmtOffset;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getZoneName() {
            return zoneName;
        }

        public void setZoneName(String zoneName) {
            this.zoneName = zoneName;
        }

        @Override
        public String toString() {
            return "ZonesBean{" +
                    "countryCode='" + countryCode + '\'' +
                    ", countryName='" + countryName + '\'' +
                    ", gmtOffset=" + gmtOffset +
                    ", timestamp=" + timestamp +
                    ", zoneName='" + zoneName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TimeList{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", zones=" + zones +
                '}';
    }
}
