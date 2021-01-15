package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Created by tl on 2018/8/27.
 */

public class RegionResult {

    private List<Data> data;
    private Result result;

    public RegionResult(List<Data> data, Result result) {
        this.data = data;
        this.result = result;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Data{
        public Data(String fullName, String id, String name) {
            this.fullName = fullName;
            this.id = id;
            this.name = name;
        }

        private String fullName;
        private String id;
        private String name;

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Result{
        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
