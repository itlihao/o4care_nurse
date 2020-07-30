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
public class CustomerInfoBean {

    /**
     * birthday : 1957-01-03
     * note : 无其他备注
     * address : 金源小区10楼6单元301
     * disease : 糖尿病
     * lng : 67.8976
     * nation : 汉
     * sex : 男
     * weight : 60
     * blood : O
     * img_url : http://xxxxxx/cust/3412878.jpg
     * idcard : 270865198702162310
     * contact : [{"name":"张四","tel":"18765456754","relation":"父子"},{"name":"张五","tel":"18765456751","relation":"父女"}]
     * name : 张三
     * disease_pic : ["http://xxxxxx/disease/15527165633380.jpg","http://xxxxxx/disease/15527165633381.jpg"]
     * tel : 17865473845
     * age : 65
     * lat : 56.87978
     * height : 165
     */
    private String birthday;
    private String note;
    private String address;
    private String disease;
    private String lng;
    private String nation;
    private String sex;
    private int weight;
    private String blood;
    private String img_url;
    private String idcard;
    private List<ContactEntity> contact;
    private String name;
    private List<String> disease_pic;
    private String tel;
    private int age;
    private String lat;
    private int height;

    public CustomerInfoBean() {
        super();
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public void setContact(List<ContactEntity> contact) {
        this.contact = contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisease_pic(List<String> disease_pic) {
        this.disease_pic = disease_pic;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getNote() {
        return note;
    }

    public String getAddress() {
        return address;
    }

    public String getDisease() {
        return disease;
    }

    public String getLng() {
        return lng;
    }

    public String getNation() {
        return nation;
    }

    public String getSex() {
        return sex;
    }

    public int getWeight() {
        return weight;
    }

    public String getBlood() {
        return blood;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getIdcard() {
        return idcard;
    }

    public List<ContactEntity> getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public List<String> getDisease_pic() {
        return disease_pic;
    }

    public String getTel() {
        return tel;
    }

    public int getAge() {
        return age;
    }

    public String getLat() {
        return lat;
    }

    public int getHeight() {
        return height;
    }

    public static class ContactEntity {
        /**
         * name : 张四
         * tel : 18765456754
         * relation : 父子
         */
        private String name;
        private String tel;
        private String relation;

        public void setName(String name) {
            this.name = name;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getName() {
            return name;
        }

        public String getTel() {
            return tel;
        }

        public String getRelation() {
            return relation;
        }
    }

    @Override
    public String toString() {
        return "CustomerInfoBean{" +
                "birthday='" + birthday + '\'' +
                ", note='" + note + '\'' +
                ", address='" + address + '\'' +
                ", disease='" + disease + '\'' +
                ", lng='" + lng + '\'' +
                ", nation='" + nation + '\'' +
                ", sex='" + sex + '\'' +
                ", weight=" + weight +
                ", blood='" + blood + '\'' +
                ", img_url='" + img_url + '\'' +
                ", idcard='" + idcard + '\'' +
                ", contact=" + contact +
                ", name='" + name + '\'' +
                ", disease_pic=" + disease_pic +
                ", tel='" + tel + '\'' +
                ", age=" + age +
                ", lat='" + lat + '\'' +
                ", height=" + height +
                '}';
    }
}
