package com.yi.homework.manager;


public class BaseManager {

    protected int calculatePageNum(int recordNum, int pageSize) {
        return recordNum % pageSize == 0 ? recordNum / pageSize : recordNum / pageSize + 1;
    }

}