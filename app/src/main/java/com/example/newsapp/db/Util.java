package com.example.newsapp.db;



public class Util {
    private static String[] nameArray = new String[]{
            "党委办公室", "继续教育学院", "保卫处"
    };
    private static String[] contentArray = new String[]{
          "\n" +
                  "定于2021年6月22日（星期二）下午15：40在学术交流中心二楼报告厅召开干部大会，进行干部选拔任用会议推荐，请全体校领导、中层干部、党支部书记、科长（主任科员），各级人大代表、政协委员，民主党派和统战团体负责人，部分党外高级知识分子、教学部（教研室）主任、专业带头人、具有高级专业技术职务教师代表（具体名单以各二级党组织通知为准）准时参加。",
            " 2021年安全评价师职业技能等级认定考试将于6月5日（星期六）在我校仙林校区举行，本次考试使用的考场安排在乐群楼。为了做好考前准备和考试组织工作，学校定于6月4日下午17：10至6月5日下午18：00对乐群楼进行封闭",
            "6月5日，我校仙林校区将举行安全评价师资格鉴定考试，考场设在乐群楼，仙林校区将实行临时交通管制，通知如下：1、因疫情防控需要，仙林校区南门将布置成考生专用通道。为避免人员、车辆混杂，定于6月5日6：00- 18：00对仙林校区南门实行临时交通管制，在此期间，请所有教职工从西门、北门进出校园，外来车辆从北门进校。6月5日18：00起，南门恢复开放。2、乐群楼及周边道路以及相邻的湖边区域仅限考生使用，其他无关人员均不得进入此区域，需要去其它教学楼及图书馆工作、学习的师生，务必从松山湖湖边北侧道路或任之北路通行。"




    };

    /**
     * 获取文本内容根据下标
     *
     */

    public static String getContent(int position) {
        return contentArray[position % contentArray.length];
    }

    /**
     * 获取名称根据下标
     */
    public static String getName(int position) {
        return nameArray[position % contentArray.length];
    }
}


