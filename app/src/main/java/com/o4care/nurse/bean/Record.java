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

public class Record {

    /**
     * duration : 56
     * record_id : 13
     * start_time : 2020-07-01 9:00
     * week : 3
     * end_time : 2020-07-01 10:00
     */
    private int duration;
    private int record_id;
    private String start_time;
    private int week;
    private String end_time;

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getDuration() {
        return duration;
    }

    public int getRecord_id() {
        return record_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public int getWeek() {
        return week;
    }

    public String getEnd_time() {
        return end_time;
    }
}
