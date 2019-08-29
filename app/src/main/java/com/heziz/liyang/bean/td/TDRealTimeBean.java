package com.heziz.liyang.bean.td;

import java.io.Serializable;

/**
 * Created by sws on 2019-08-23.
 * from:
 * describe:
 */

public class TDRealTimeBean implements Serializable {

    /**
     * message : {"relationId":"18052902","venderId":"1","relationType":"0","dataType":"7","dateTime":1566530647411,"weight":"0","height":"2.54","range":"24.37","rotation":"0","ratedMoment":"73.1","obliquity":"0","windSpeed":"0","momentPercent":"5.877472E-39","ratedWeight":"3.0","weightPercent":"0","abnormalPower":"0","lockState":"0","switchValue":"00000000","weightBroken":"0","heightBroken":"0","rangeBroken":"0","angleBroken":"0","windSpeedBroken":"0","verticalityBroken":"0","relayBroken":"0","heightLimitBroken":"0","rangeLimitBroken":"0","angleLimitBroken":"0","weightDanger":"0","momentDanger":"0","heightDanger":"0","rangeDanger":"0","angleDanger":"0","windSpeedDanger":"0","verticalityDanger":"0","areaLimtDanger":"0","fpzdanger":"0","crc":"F8B7"}
     */

    private MessageBean message;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * relationId : 18052902
         * venderId : 1
         * relationType : 0
         * dataType : 7
         * dateTime : 1566530647411
         * weight : 0
         * height : 2.54
         * range : 24.37
         * rotation : 0
         * ratedMoment : 73.1
         * obliquity : 0
         * windSpeed : 0
         * momentPercent : 5.877472E-39
         * ratedWeight : 3.0
         * weightPercent : 0
         * abnormalPower : 0
         * lockState : 0
         * switchValue : 00000000
         * weightBroken : 0
         * heightBroken : 0
         * rangeBroken : 0
         * angleBroken : 0
         * windSpeedBroken : 0
         * verticalityBroken : 0
         * relayBroken : 0
         * heightLimitBroken : 0
         * rangeLimitBroken : 0
         * angleLimitBroken : 0
         * weightDanger : 0
         * momentDanger : 0
         * heightDanger : 0
         * rangeDanger : 0
         * angleDanger : 0
         * windSpeedDanger : 0
         * verticalityDanger : 0
         * areaLimtDanger : 0
         * fpzdanger : 0
         * crc : F8B7
         */

        private String relationId;
        private String venderId;
        private String relationType;
        private String dataType;
        private long dateTime;
        private String weight;
        private String height;
        private String range;
        private String rotation;
        private String ratedMoment;
        private String obliquity;
        private String windSpeed;
        private String momentPercent;
        private String ratedWeight;
        private String weightPercent;
        private String abnormalPower;
        private String lockState;
        private String switchValue;
        private String weightBroken;
        private String heightBroken;
        private String rangeBroken;
        private String angleBroken;
        private String windSpeedBroken;
        private String verticalityBroken;
        private String relayBroken;
        private String heightLimitBroken;
        private String rangeLimitBroken;
        private String angleLimitBroken;
        private String weightDanger;
        private String momentDanger;
        private String heightDanger;
        private String rangeDanger;
        private String angleDanger;
        private String windSpeedDanger;
        private String verticalityDanger;
        private String areaLimtDanger;
        private String fpzdanger;
        private String crc;

        public String getRelationId() {
            return relationId;
        }

        public void setRelationId(String relationId) {
            this.relationId = relationId;
        }

        public String getVenderId() {
            return venderId;
        }

        public void setVenderId(String venderId) {
            this.venderId = venderId;
        }

        public String getRelationType() {
            return relationType;
        }

        public void setRelationType(String relationType) {
            this.relationType = relationType;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public long getDateTime() {
            return dateTime;
        }

        public void setDateTime(long dateTime) {
            this.dateTime = dateTime;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public String getRotation() {
            return rotation;
        }

        public void setRotation(String rotation) {
            this.rotation = rotation;
        }

        public String getRatedMoment() {
            return ratedMoment;
        }

        public void setRatedMoment(String ratedMoment) {
            this.ratedMoment = ratedMoment;
        }

        public String getObliquity() {
            return obliquity;
        }

        public void setObliquity(String obliquity) {
            this.obliquity = obliquity;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getMomentPercent() {
            return momentPercent;
        }

        public void setMomentPercent(String momentPercent) {
            this.momentPercent = momentPercent;
        }

        public String getRatedWeight() {
            return ratedWeight;
        }

        public void setRatedWeight(String ratedWeight) {
            this.ratedWeight = ratedWeight;
        }

        public String getWeightPercent() {
            return weightPercent;
        }

        public void setWeightPercent(String weightPercent) {
            this.weightPercent = weightPercent;
        }

        public String getAbnormalPower() {
            return abnormalPower;
        }

        public void setAbnormalPower(String abnormalPower) {
            this.abnormalPower = abnormalPower;
        }

        public String getLockState() {
            return lockState;
        }

        public void setLockState(String lockState) {
            this.lockState = lockState;
        }

        public String getSwitchValue() {
            return switchValue;
        }

        public void setSwitchValue(String switchValue) {
            this.switchValue = switchValue;
        }

        public String getWeightBroken() {
            return weightBroken;
        }

        public void setWeightBroken(String weightBroken) {
            this.weightBroken = weightBroken;
        }

        public String getHeightBroken() {
            return heightBroken;
        }

        public void setHeightBroken(String heightBroken) {
            this.heightBroken = heightBroken;
        }

        public String getRangeBroken() {
            return rangeBroken;
        }

        public void setRangeBroken(String rangeBroken) {
            this.rangeBroken = rangeBroken;
        }

        public String getAngleBroken() {
            return angleBroken;
        }

        public void setAngleBroken(String angleBroken) {
            this.angleBroken = angleBroken;
        }

        public String getWindSpeedBroken() {
            return windSpeedBroken;
        }

        public void setWindSpeedBroken(String windSpeedBroken) {
            this.windSpeedBroken = windSpeedBroken;
        }

        public String getVerticalityBroken() {
            return verticalityBroken;
        }

        public void setVerticalityBroken(String verticalityBroken) {
            this.verticalityBroken = verticalityBroken;
        }

        public String getRelayBroken() {
            return relayBroken;
        }

        public void setRelayBroken(String relayBroken) {
            this.relayBroken = relayBroken;
        }

        public String getHeightLimitBroken() {
            return heightLimitBroken;
        }

        public void setHeightLimitBroken(String heightLimitBroken) {
            this.heightLimitBroken = heightLimitBroken;
        }

        public String getRangeLimitBroken() {
            return rangeLimitBroken;
        }

        public void setRangeLimitBroken(String rangeLimitBroken) {
            this.rangeLimitBroken = rangeLimitBroken;
        }

        public String getAngleLimitBroken() {
            return angleLimitBroken;
        }

        public void setAngleLimitBroken(String angleLimitBroken) {
            this.angleLimitBroken = angleLimitBroken;
        }

        public String getWeightDanger() {
            return weightDanger;
        }

        public void setWeightDanger(String weightDanger) {
            this.weightDanger = weightDanger;
        }

        public String getMomentDanger() {
            return momentDanger;
        }

        public void setMomentDanger(String momentDanger) {
            this.momentDanger = momentDanger;
        }

        public String getHeightDanger() {
            return heightDanger;
        }

        public void setHeightDanger(String heightDanger) {
            this.heightDanger = heightDanger;
        }

        public String getRangeDanger() {
            return rangeDanger;
        }

        public void setRangeDanger(String rangeDanger) {
            this.rangeDanger = rangeDanger;
        }

        public String getAngleDanger() {
            return angleDanger;
        }

        public void setAngleDanger(String angleDanger) {
            this.angleDanger = angleDanger;
        }

        public String getWindSpeedDanger() {
            return windSpeedDanger;
        }

        public void setWindSpeedDanger(String windSpeedDanger) {
            this.windSpeedDanger = windSpeedDanger;
        }

        public String getVerticalityDanger() {
            return verticalityDanger;
        }

        public void setVerticalityDanger(String verticalityDanger) {
            this.verticalityDanger = verticalityDanger;
        }

        public String getAreaLimtDanger() {
            return areaLimtDanger;
        }

        public void setAreaLimtDanger(String areaLimtDanger) {
            this.areaLimtDanger = areaLimtDanger;
        }

        public String getFpzdanger() {
            return fpzdanger;
        }

        public void setFpzdanger(String fpzdanger) {
            this.fpzdanger = fpzdanger;
        }

        public String getCrc() {
            return crc;
        }

        public void setCrc(String crc) {
            this.crc = crc;
        }
    }
}
