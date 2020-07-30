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

import java.util.List;

public class CarePlan {

    /**
     * week : 3
     * time : 09:10:11
     * items : [{"name":"胃管留置","id":1,"manual":"胃管留置操作说明"},{"name":"生命体征检测","id":2,"manual":"胃管留置操作说明"},{"name":"各种输液","id":3,"manual":"胃管留置操作说明"}]
     * plan_id : 21
     */
    private int week;
    private String time;
    private List<ItemsEntity> items;
    private int plan_id;

    public void setWeek(int week) {
        this.week = week;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setItems(List<ItemsEntity> items) {
        this.items = items;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public int getWeek() {
        return week;
    }

    public String getTime() {
        return time;
    }

    public List<ItemsEntity> getItems() {
        return items;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public class ItemsEntity {
        /**
         * name : 胃管留置
         * id : 1
         * manual : 胃管留置操作说明
         */
        private String name;
        private int id;
        private String manual;

        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setManual(String manual) {
            this.manual = manual;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public String getManual() {
            return manual;
        }
    }
}
