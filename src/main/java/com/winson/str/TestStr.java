package com.winson.str;

/**
 * @author com.winson
 * @date 2020/12/15
 **/
public class TestStr {

    public static String getRecommendSql(){
        String sql = "select \n" +
                "t4.name as resume_name,\n" +
                "t4.id as resume_id,\n" +
                "t4.percent as percent,\n" +
                "t2.user_id,\n" +
                "t2.sex,\n" +
                "t2.image,\n" +
                "t2.`name`,\n" +
                "t2.birthday,\n" +
                "t2.area, t_location.longitude as lon, t_location.latitude as lat,\n" +
                "t_city.`name` as city_name,\n" +
                "t2.fgraduate as graduate,\n" +
                "t2.fgraduate_y as graduate_year,\n" +
                "t2.fschool as school,\n" +
                "t2.school_type,\n" +
                "t2.fmajor as major,\n" +
                "t2.fdegree as degree,\n" +
                "t2.is_open,\n" +
                "t3.self_label as labels,\n" +
                "t2.update_time as base_update_time, \n" +
                "t2.mobile as mobile, \n" +
                "t4.update_time as resume_update_time, \n" +
                "(select max(addtime) from ald_djm.djm_action where p_user_id = t2.user_id) as latest_delivery_time, \n" +
                "(select GROUP_CONCAT(DISTINCT user_id) from ald_rsm.rsm_student_resume_browse where resume_id = t4.id) as browse_user_ids,\n" +
                "(select GROUP_CONCAT(DISTINCT b_user_id) from ald_rbs.rbs_b_user_dl_resume_snapshoot where c_user_id = t2.user_id) as download_user_ids,\n" +
                "(select GROUP_CONCAT(DISTINCT c_user_id) from ald_djm.djm_invitation where p_user_id = t2.user_id) as invite_user_ids,\n" +
                "(select GROUP_CONCAT(`type`, '@%@' , `name` SEPARATOR '==1==') from ald_rsm.rsm_student_certificate where resume_id = t4.id ) as certificates, \n" +
                "(select GROUP_CONCAT(`honorname`, '@%@' , `honortime` SEPARATOR '==1==') from ald_rsm.rsm_student_honor where resume_id = t4.id ) as honors,\n" +
                "(select GROUP_CONCAT(`linkadd`, '@%@' , `desc` SEPARATOR '==1==') from ald_rsm.rsm_student_works where resume_id = t4.id ) as portfolios ,\n" +
                "(select GROUP_CONCAT(id, '@%@' , fschool, '@%@', fmajor , '@%@', fdegree ,'@%@', `begin` , '@%@', `end` SEPARATOR '==1==') from ald_rsm.rsm_student_eduexp where user_id = t2.user_id) as nest_edu_exp,\n" +
                "(select GROUP_CONCAT(id, '@%@', `name`, '@%@', partname  , '@%@', `begin` , '@%@', `end` SEPARATOR '==1==') from ald_rsm.rsm_student_actexp where resume_id = t2.id) as nest_act_exp,\n" +
                "(select GROUP_CONCAT(province, '@%@' , address, '@%@', industry_top ,'@%@', industry_two,'@%@', job_top,'@%@', job_two,'@%@', job_three,'@%@', pay,'@%@', fulltime_type,'@%@', job_class SEPARATOR '==1==') from ald_ums.ums_job_intension where user_id = t2.user_id) as intention_job_list,\n" +
                "temp.nest_delivery_actions \n" +
                "from ald_rsm.rsm_student t2 \n" +
                "left join ald_rsm.rsm_student_resume t4 on t2.user_id = t4.user_id\n" +
                "left join \n" +
                "(\n" +
                "select t1.resumeid, GROUP_CONCAT(id,',',jobid,',',job_type,',',sub_job_type,',',min_job_type,',', addtime SEPARATOR '==1==') as nest_delivery_actions\n" +
                "FROM ald_djm.djm_action t1 \n" +
                "where t1.addtime > '" + "2020-01-01 00:00:00" + "' group by t1.resumeid\n" +
                ") as temp on t4.id = temp.resumeid \n" +
                "left join ald_com.com_city t_city on t2.area = t_city.id " +
                "left join ald_com.com_city_location t_location on t_city.id = t_location.id \n" +
                "left join (select max(resume_id) as rid , max(self_label) as self_label from ald_rsm.rsm_label group by resume_id) t3 on t4.id = t3.rid \n " +
                "where t4.id >= " + 905912 + " \n" +
                "and t4.id <= " + 905912;
        return sql;
    }

    public static String getSql(int lt, int gt, String dateStr) {
        String sql =
                "select \n" +
                        "t4.name as resume_name,\n" +
                        "t4.id as resume_id,\n" +
                        "t4.percent as percent,\n" +
                        "t2.user_id,\n" +
                        "t2.sex,\n" +
                        "t2.image,\n" +
                        "t2.`name`,\n" +
                        "t2.birthday,\n" +
                        "t2.area, t_location.longitude as lon, t_location.latitude as lat,\n" +
                        "t_city.`name` as city_name,\n" +
                        "t2.fgraduate as graduate,\n" +
                        "t2.fgraduate_y as graduate_year,\n" +
                        "t2.fschool as school,\n" +
                        "t2.school_type,\n" +
                        "t2.fmajor as major,\n" +
                        "t2.fdegree as degree,\n" +
                        "t2.is_open,\n" +
                        "t3.self_label as labels,\n" +
                        "t2.update_time as base_update_time, \n" +
                        "temp_djm_action.nest_delivery_actions, \n" +
                        "t2.mobile as mobile, \n" +
                        "t4.update_time as resume_update_time, \n" +
                        "sjws.id as sjws_id, \n" +
                        "sjws.latest_entry as sjws_latest_entry, \n" +
                        "sjws.max_work_days as sjws_max_work_days, \n" +
                        "sjws.min_work_days as sjws_min_work_days, \n" +
                        "sjws.type as sjws_type, \n" +
                        "sjws.update_time as sjws_update_time, \n" +
                        "sjws.user_id as sjws_user_id, \n" +
                        "sjws.wanted_level as sjws_wanted_level, \n" +
                        "sjws.work_week_days as sjws_work_week_days, \n" +
                        "sjws.work_week_days_num as sjws_work_week_days_num, \n" +
                        "extra.latest_online_time,\n" +
                        "(select max(addtime) from ald_djm.djm_action where p_user_id = t2.user_id) as latest_delivery_time, \n" +
                        "(select GROUP_CONCAT(DISTINCT user_id) from ald_rsm.rsm_student_resume_browse where resume_id = t4.id) as browse_user_ids,\n" +
                        "(select GROUP_CONCAT(DISTINCT b_user_id) from ald_rbs.rbs_b_user_dl_resume_snapshoot where c_user_id = t2.user_id) as download_user_ids,\n" +
                        "(select GROUP_CONCAT(DISTINCT c_user_id) from ald_djm.djm_invitation where p_user_id = t2.user_id) as invite_user_ids,\n" +
                        "(select GROUP_CONCAT(`type`, '@%@' , `name` SEPARATOR '==1==') from ald_rsm.rsm_student_certificate where resume_id = t4.id ) as certificates, \n" +
                        "(select GROUP_CONCAT(`honorname`, '@%@' , `honortime` SEPARATOR '==1==') from ald_rsm.rsm_student_honor where resume_id = t4.id ) as honors,\n" +
                        "(select GROUP_CONCAT(`linkadd`, '@%@' , `desc` SEPARATOR '==1==') from ald_rsm.rsm_student_works where resume_id = t4.id ) as portfolios ,\n" +
                        "(select GROUP_CONCAT(id, '@%@' , fschool, '@%@', fmajor , '@%@', fdegree ,'@%@', `begin` , '@%@', `end` SEPARATOR '==1==') from ald_rsm.rsm_student_eduexp where user_id = t2.user_id) as nest_edu_exp,\n" +
                        "(select GROUP_CONCAT(id, '@%@', `name`, '@%@', partname  , '@%@',  type  , '@%@',`begin` , '@%@', `end` SEPARATOR '==1==') from ald_rsm.rsm_student_actexp where resume_id = t4.id) as nest_act_exp,\n" +
                        "(select GROUP_CONCAT(province, '@%@' , address, '@%@', industry_top ,'@%@', industry_two,'@%@', job_top,'@%@', job_two,'@%@', job_three,'@%@', pay,'@%@', fulltime_type,'@%@', job_class SEPARATOR '==1==') from ald_ums.ums_job_intension where user_id = t2.user_id) as intention_job_list \n" +
                        "\n" +
                        "from ald_rsm.rsm_student t2 \n" +
                        "left join ald_rsm.rsm_student_resume t4 on t2.user_id = t4.user_id\n" +
                        "\n" +
                        "left join \n" +
                        "(\n" +
                        "select t1.resumeid, GROUP_CONCAT(id,',',company_id,',',jobid,',',job_type,',',sub_job_type,',',min_job_type,',', addtime SEPARATOR '==1==') as nest_delivery_actions\n" +
                        "FROM ald_djm.djm_action t1 \n" +
                        "where t1.addtime > '" + dateStr + "' group by t1.resumeid\n" +
                        ") as temp_djm_action on t4.id = temp_djm_action.resumeid \n" +
                        "left join ald_com.com_city t_city on t2.area = t_city.id \n" +
                        "left join ald_rsm.rsm_student_extra extra on extra.user_id = t2.user_id \n" +
                        "left join ald_rsm.rsm_student_job_wanted_status sjws on sjws.user_id = t2.user_id \n" +
                        "left join ald_com.com_city_location t_location on t_city.id = t_location.id \n" +
                        "left join (select max(resume_id) as rid , max(self_label) as self_label from ald_rsm.rsm_label group by resume_id) t3 on t4.id = t3.rid \n" +
                        "where t4.type = 0 and t2.id > " + lt + " and t2.id <" + gt;
//                        "where t4.type = 0 and t2.id > 0 and t2.id <" + gt;
        return sql;
    }

    public static void main(String[] args) {
//        String result = "曙光信息产业（北京）有限公司（下称中科曙光，股票代码：SH 603019）是一家在科技部、中科院大力推动下，以国家\"863\"计划重大科研成果为基础组建的高新技术企业。作为国内高性能计算领域的领军企业，亚洲第一大高性能计算机厂商，中科曙光已连续8年蝉联中国高性能计算机TOP100排行榜市场份额第一，素有中国高性能计算第一股之称。以中科院计算所、国家智能计算机研究开发中心和国家高性能计算机工程中心为技术依托，曙光拥有服务器、存储、云计算、高性能计算、网络安全、数据中心、大数据等健全的产业生态链，并多年潜心发展全国“城市云”中心、先进计算中心和智慧城市的宏伟蓝图，目前已有新疆、成都、无锡、南京、包头等40余个在运行的城市云计算中心、先进计算中心、数据中心，并正在加速 “百城百行”、“数据中国”的战略布局。 20多年来，曙光公司始终倡导着“自主创新服务中国”的品牌理念，以全面、专业、增值的服务为广大中国用户提供良好的应用体验，曙光的硬件产品、解决方案、云计算服务已被广泛应用于政府、能源、互联网、教育、气象、医疗及公共事业等社会各个领域。并在我国西南、华南、华中、东北等地成功实现了“城市云”布局，筹建了成都、无锡、南京、包头等多个城市云计算中心，为快速高效地提升区域经济建设，丰富人们日常生活所需信息资源提供了坚实的科技保障。";
//
//        System.out.println(result.length());
//
//        String content = "要求本科及以上";
//        System.out.println(content.indexOf("本科"));
//        System.out.println(content.contains("本科"));
//        String raw = "{\"index\":{\"_id\":\"641676\"}}";
//        System.out.println("体验".hashCode());
//        System.out.println("实习".hashCode());
//        System.out.println("大学".hashCode());
//        int first = raw.lastIndexOf("\"");
//        int second = raw.lastIndexOf("\"", first - 1);
//        System.out.println(raw.substring(17, raw.lastIndexOf("\"")));

//        String sql = getSql(1087760,1087762,"2020-01-01 00:00:00");
//        System.out.println(sql);


        String recommendSql = getRecommendSql();
        System.out.println(recommendSql);

    }

}
