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

public class Exchange {

    /**
     * record_id : 134
     * plan_time : 2020-05-01 9:00
     * do_worker_name : 张三
     * do_worker : 13
     * plan_worker_name : 张四
     * end_time : 2020-05-01 10:00
     * cust_address : 金华路28号
     * cust_name : 张春华
     * state : 1
     * cust_id : 34
     * plan_worker : 12
     */
    private int record_id;
    private String plan_time;
    private String do_worker_name;
    private int do_worker;
    private String plan_worker_name;
    private String end_time;
    private String cust_address;
    private String cust_name;
    private int state;
    private int cust_id;
    private int plan_worker;

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public void setPlan_time(String plan_time) {
        this.plan_time = plan_time;
    }

    public void setDo_worker_name(String do_worker_name) {
        this.do_worker_name = do_worker_name;
    }

    public void setDo_worker(int do_worker) {
        this.do_worker = do_worker;
    }

    public void setPlan_worker_name(String plan_worker_name) {
        this.plan_worker_name = plan_worker_name;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public void setPlan_worker(int plan_worker) {
        this.plan_worker = plan_worker;
    }

    public int getRecord_id() {
        return record_id;
    }

    public String getPlan_time() {
        return plan_time;
    }

    public String getDo_worker_name() {
        return do_worker_name;
    }

    public int getDo_worker() {
        return do_worker;
    }

    public String getPlan_worker_name() {
        return plan_worker_name;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getCust_address() {
        return cust_address;
    }

    public String getCust_name() {
        return cust_name;
    }

    public int getState() {
        return state;
    }

    public int getCust_id() {
        return cust_id;
    }

    public int getPlan_worker() {
        return plan_worker;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "record_id=" + record_id +
                ", plan_time='" + plan_time + '\'' +
                ", do_worker_name='" + do_worker_name + '\'' +
                ", do_worker=" + do_worker +
                ", plan_worker_name='" + plan_worker_name + '\'' +
                ", end_time='" + end_time + '\'' +
                ", cust_address='" + cust_address + '\'' +
                ", cust_name='" + cust_name + '\'' +
                ", state=" + state +
                ", cust_id=" + cust_id +
                ", plan_worker=" + plan_worker +
                '}';
    }
}
