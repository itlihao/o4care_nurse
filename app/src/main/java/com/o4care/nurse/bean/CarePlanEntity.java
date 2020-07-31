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

public class CarePlanEntity {

    /**
     * result : 1
     * plan_id : 12
     */
    private int result;
    private int plan_id;

    public void setResult(int result) {
        this.result = result;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public int getResult() {
        return result;
    }

    public int getPlan_id() {
        return plan_id;
    }
}
