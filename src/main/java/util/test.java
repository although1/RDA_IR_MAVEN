package util;

import domain.Ir_FuncInfo;
import domain.Ir_Info;


import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class test {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Test
    public void test1(){
        //找出所有的遥控名称
        String sql_name = "select * from irlist order by id";
        List<Ir_Info> values = template.query(sql_name, new BeanPropertyRowMapper<Ir_Info>(Ir_Info.class));
        for (Ir_Info value : values) {
            String irname = value.getIrname();
            //测试第一个查询条件
            List<String> list = test2(irname, "0x0707", "ui_event_mute", "0x0f");
            for (String irname1 : list) {
                //测试第二个查询条件
                List<String> list1 = test2(irname1, "0x0707", "ui_event_freeze", "0x2c");
                for (String s : list1) {
                    System.out.println(s);
                }
            }

        }
    }

    public List<String> test2(String irname , String header_code,String remote_function,String remote_code){
        ArrayList<String> strings = new ArrayList<>();
        //根据遥控名称来做查询，根据头码以及码值来判断该遥控是否符合规则
        String sql_list = "select * from "+irname+" where header_code like ? and remote_function like ? and remote_code like ?";
        List<Ir_FuncInfo> ir_funcInfo = template.query(sql_list, new BeanPropertyRowMapper<Ir_FuncInfo>(Ir_FuncInfo.class), header_code,remote_function, remote_code);
        for (Ir_FuncInfo irFuncInfo : ir_funcInfo) {
            if (irFuncInfo.getHeader_code()!=null && !"".equals(irFuncInfo.getHeader_code()) ){
                //System.out.println(irFuncInfo);
                strings.add(irname);
            }
        }
        return strings;
    }

    @Test
    public void test3(){
        //找出所有的遥控名称
        String sql_name = "select * from irlist order by id";
        List<Ir_Info> values = template.query(sql_name, new BeanPropertyRowMapper<Ir_Info>(Ir_Info.class));
        ArrayList<String> strings = new ArrayList<>();
        for (Ir_Info value : values) {
            String irname = value.getIrname();
            String sql_list = "select * from "+irname+" where header_code like ? and remote_function like ? and remote_code like ?";
            List<Ir_FuncInfo> ir_funcInfo = template.query(sql_list, new BeanPropertyRowMapper<Ir_FuncInfo>(Ir_FuncInfo.class), "0x0707","ui_event_freeze", "0x2c");
            for (Ir_FuncInfo irFuncInfo : ir_funcInfo) {
                if (irFuncInfo.getHeader_code()!=null && !"".equals(irFuncInfo.getHeader_code())){
                    //System.out.println(irFuncInfo);
                    strings.add(irname);
                }
            }
        }
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void test4(){
        //找出所有的遥控名称
        String sql_name = "select * from irlist order by id";
        List<Ir_Info> values = template.query(sql_name, new BeanPropertyRowMapper<Ir_Info>(Ir_Info.class));
        ArrayList<String> strings = new ArrayList<>();
        for (Ir_Info value : values) {
            String irname = value.getIrname();
            String sql_list = "select * from "+irname+" where header_code like ? and remote_function like ? and remote_code like ?";
            List<Ir_FuncInfo> ir_funcInfo = template.query(sql_list, new BeanPropertyRowMapper<Ir_FuncInfo>(Ir_FuncInfo.class), "0x0707","ui_event_freeze", "0x2c");
            for (Ir_FuncInfo irFuncInfo : ir_funcInfo) {
                if (irFuncInfo.getHeader_code()!=null && !"".equals(irFuncInfo.getHeader_code())){
                    //System.out.println(irFuncInfo);
                    strings.add(irname);
                }
            }
        }
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void test5(){
        //找出所有的遥控名称
        String sql_name = "select * from irlist order by id";
        List<Ir_Info> values = template.query(sql_name, new BeanPropertyRowMapper<Ir_Info>(Ir_Info.class));
        ArrayList<String> strings = new ArrayList<>();
        for (Ir_Info value : values) {
            String irname = value.getIrname();
            String sql_list = "select * from "+irname+" where header_code like ? and remote_function like ? and remote_code like ?";
            List<Ir_FuncInfo> ir_funcInfo = template.query(sql_list, new BeanPropertyRowMapper<Ir_FuncInfo>(Ir_FuncInfo.class), "0x0707","ui_event_mute", "0x0f");
            for (Ir_FuncInfo irFuncInfo : ir_funcInfo) {
                if (irFuncInfo.getHeader_code()!=null && !"".equals(irFuncInfo.getHeader_code())){
                    //System.out.println(irFuncInfo);
                    strings.add(irname);
                }
            }
        }
        String sql_list = "select * from irlist where irname = \"\" ";

        StringBuilder sb = new StringBuilder(sql_list);

        for (String string : strings) {
            //判断value是否有值
            if(string != null && !"".equals(string)){
                //有值
                sb.append("\" or  irname =  \""+string);
//                sb.append(" and "+string+" like ? ");
            }
        }

        sb.append("\" limit ?,? ");
        System.out.println(sb.toString());
//        List<Ir_Info> query = template.query(sb.toString(), new BeanPropertyRowMapper<Ir_Info>(Ir_Info.class), 0, 5);
//        for (Ir_Info ir_info : query) {
//            System.out.println(ir_info);
//        }
    }
}
