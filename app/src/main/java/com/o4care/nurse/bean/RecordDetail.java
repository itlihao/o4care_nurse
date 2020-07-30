/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.o4care.nurse.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordDetail {

    /**
     * note : 协助患者翻身擦背,测量生命体征,按医嘱正常服药,服务过程正常
     * week : 2
     * end_lat : 36.081928
     * signature : http://xxxxx/signature/111_20200101101701.jpg
     * end_time : 10:00
     * heart_rate : 70
     * blood_pressure : 75/110
     * video : ["http://xxxxx/videos/111_20200101101700.mp4","http://xxxxx/videos/111_20200101101700.mp4"]
     * photos : ["http://xxxxx/photos/111_20200101101700.jpg","http://xxxxx/photos/111_20200101101701.jpg"]
     * record_id : 1
     * plan_time : 20200721 09:00
     * start_time : 09:00
     * start_lng : 120.361305
     * start_lat : 36.081840
     * temperature : 36.5
     * end_lng : 120.361219
     * audio : ["http://xxxxx/photos/111_20200101101700.mp3","http://xxxxx/photos/111_20200101101701.mp3"]
     * blood_sugar : 5.1
     * items : [{"name":"胃管留置","description":"胃管留置操作","id":11,"category":1},{"name":"输液","description":"输液","id":21,"category":2}]
     */
    private String note;
    private int week;
    private String end_lat;
    private String signature;
    private String end_time;
    private String heart_rate;
    private String blood_pressure;
    private List<String> video;
    private List<String> photos;
    private int record_id;
    private String plan_time;
    private String start_time;
    private String start_lng;
    private String start_lat;
    private String temperature;
    private String end_lng;
    private List<String> audio;
    private String blood_sugar;
    private List<ItemsEntity> items;

    public void setNote(String note) {
        this.note = note;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setEnd_lat(String end_lat) {
        this.end_lat = end_lat;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setHeart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public void setVideo(List<String> video) {
        this.video = video;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public void setPlan_time(String plan_time) {
        this.plan_time = plan_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setStart_lng(String start_lng) {
        this.start_lng = start_lng;
    }

    public void setStart_lat(String start_lat) {
        this.start_lat = start_lat;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setEnd_lng(String end_lng) {
        this.end_lng = end_lng;
    }

    public void setAudio(List<String> audio) {
        this.audio = audio;
    }

    public void setBlood_sugar(String blood_sugar) {
        this.blood_sugar = blood_sugar;
    }

    public void setItems(List<ItemsEntity> items) {
        this.items = items;
    }

    public String getNote() {
        return note;
    }

    public int getWeek() {
        return week;
    }

    public String getEnd_lat() {
        return end_lat;
    }

    public String getSignature() {
        return signature;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getHeart_rate() {
        return heart_rate;
    }

    public String getBlood_pressure() {
        return blood_pressure;
    }

    public List<String> getVideo() {
        return video;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public int getRecord_id() {
        return record_id;
    }

    public String getPlan_time() {
        return plan_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getStart_lng() {
        return start_lng;
    }

    public String getStart_lat() {
        return start_lat;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getEnd_lng() {
        return end_lng;
    }

    public List<String> getAudio() {
        return audio;
    }

    public String getBlood_sugar() {
        return blood_sugar;
    }

    public List<ItemsEntity> getItems() {
        return items;
    }

    public static class ItemsEntity {
        /**
         * name : 胃管留置
         * description : 胃管留置操作
         * id : 11
         * category : 1
         */
        private String name;
        private String description;
        private int id;
        private int category;

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getId() {
            return id;
        }

        public int getCategory() {
            return category;
        }
    }
}
