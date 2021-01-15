package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by wkk on 2020/9/17.
 */

public class CaiGouQiYeBean {


    /**
     * result : {"code":"200","message":"成功！","data":{}}
     * data : {"totalrows":23,"rows":[{"id":46,"address":"北京","businessLicence":"4862546335","companyDesc":null,"contact":"王五","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765452","name":"农保险服务商","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":2,"userId":null,"regionIds":null,"regionName":null},{"id":44,"address":"北京","businessLicence":"234","companyDesc":null,"contact":"饿","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765441","name":"采收玉米","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null},{"id":43,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试99","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765439","name":"测试99","operationEmp":0,"orgaCode":null,"region":"6502","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null},{"id":42,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试77","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765438","name":"测试77","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null},{"id":41,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试66","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765437","name":"测试66","operationEmp":0,"orgaCode":null,"region":"6521","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null},{"id":40,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试55","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765436","name":"测试55","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":2,"userId":null,"regionIds":null,"regionName":null},{"id":39,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试44","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765435","name":"测试44","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":2,"userId":null,"regionIds":null,"regionName":null},{"id":38,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试采33","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765434","name":"测试33","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":2,"userId":null,"regionIds":null,"regionName":null},{"id":37,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试采22","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765433","name":"测试22","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null},{"id":36,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试采11","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765432","name":"测试采收11","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null}]}
     */

    private ResultBean result;
    private DataBeanX data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class ResultBean {
        /**
         * code : 200
         * message : 成功！
         * data : {}
         */

        private String code;
        private String message;
        private DataBean data;

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

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
        }
    }

    public static class DataBeanX {
        /**
         * totalrows : 23
         * rows : [{"id":46,"address":"北京","businessLicence":"4862546335","companyDesc":null,"contact":"王五","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765452","name":"农保险服务商","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":2,"userId":null,"regionIds":null,"regionName":null},{"id":44,"address":"北京","businessLicence":"234","companyDesc":null,"contact":"饿","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765441","name":"采收玉米","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null},{"id":43,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试99","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765439","name":"测试99","operationEmp":0,"orgaCode":null,"region":"6502","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null},{"id":42,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试77","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765438","name":"测试77","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null},{"id":41,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试66","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765437","name":"测试66","operationEmp":0,"orgaCode":null,"region":"6521","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null},{"id":40,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试55","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765436","name":"测试55","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":2,"userId":null,"regionIds":null,"regionName":null},{"id":39,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试44","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765435","name":"测试44","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":2,"userId":null,"regionIds":null,"regionName":null},{"id":38,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试采33","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765434","name":"测试33","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":2,"userId":null,"regionIds":null,"regionName":null},{"id":37,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试采22","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765433","name":"测试22","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null},{"id":36,"address":"北京","businessLicence":"5632","companyDesc":null,"contact":"测试采11","email":null,"enableTime":null,"groupId":0,"isGroup":0,"isOnlineOrder":0,"lastVisitTime":null,"mob":"13298765432","name":"测试采收11","operationEmp":0,"orgaCode":null,"region":"6501","registerTime":null,"remark":null,"serviceTel":null,"status":2,"symbol":null,"tel":null,"types":3,"userId":null,"regionIds":null,"regionName":null}]
         */

        private int totalrows;
        private List<RowsBean> rows;

        public int getTotalrows() {
            return totalrows;
        }

        public void setTotalrows(int totalrows) {
            this.totalrows = totalrows;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 46
             * address : 北京
             * businessLicence : 4862546335
             * companyDesc : null
             * contact : 王五
             * email : null
             * enableTime : null
             * groupId : 0
             * isGroup : 0
             * isOnlineOrder : 0
             * lastVisitTime : null
             * mob : 13298765452
             * name : 农保险服务商
             * operationEmp : 0
             * orgaCode : null
             * region : 6501
             * registerTime : null
             * remark : null
             * serviceTel : null
             * status : 2
             * symbol : null
             * tel : null
             * types : 2
             * userId : null
             * regionIds : null
             * regionName : null
             */

            private String address;
            private String name;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
