package com.swishsoftwaresolutions.json_1;

/**
 * Created by DELL on 12/10/2017.
 */

public class DataModel {
    String name;
    String version_no;
    String api;

    public DataModel(String name, String version_no, String api) {
        this.name = name;
        this.version_no = version_no;
        this.api = api;
    }

    public String getName() {
        return name;
    }

    public String getVersion_no() {
        return version_no;
    }

    public String getApi() {
        return api;
    }
}
