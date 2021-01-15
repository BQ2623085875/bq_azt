package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Created by tl on 2019/1/18.
 */

public class LandTypeResult {
    private Data data;
    private Result result;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Data {
    private List<GengDi> gengdi;
    private List<TuDI> tudi;

        public List<GengDi> getGengdi() {
            return gengdi;
        }

        public void setGengdi(List<GengDi> gengdi) {
            this.gengdi = gengdi;
        }

        public List<TuDI> getTudi() {
            return tudi;
        }

        public void setTudi(List<TuDI> tudi) {
            this.tudi = tudi;
        }

        public class TuDI {
            private int id ;
            private String name;
            private String url;
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public class GengDi {
            private int id ;
            private String name;
            private String url;
            private boolean select;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public class Result {
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
