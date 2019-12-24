package dao.impl;

import dao.Ir_Dao;
import domain.Ir_FuncInfo;
import domain.Ir_Info;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ir_DaoImpl implements Ir_Dao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Ir_FuncInfo> findIrListByName(String irname) {
        try {
            String sql="select header_code,remote_code,remote_function from "+irname;
            List<Ir_FuncInfo> ir_funcInfos = template.query(sql, new BeanPropertyRowMapper<Ir_FuncInfo>(Ir_FuncInfo.class));
            return ir_funcInfos;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        String sql = "select count(*) from irlist where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);

        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<Ir_Info> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from irlist  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);

        return template.query(sql,new BeanPropertyRowMapper<Ir_Info>(Ir_Info.class),params.toArray());
    }

    @Override
    public List<Ir_FuncInfo> getRemote_Function() {
        //使用jdbc数据库
        //查询所有 irname
        String sql="select remote_function from irfunction order by id";
        List<Ir_FuncInfo> remote_function = template.query(sql, new BeanPropertyRowMapper<Ir_FuncInfo>(Ir_FuncInfo.class));
        return remote_function;
    }

    @Override
    public int findTotalCountByPage(Map<String, String[]> condition) {

        String sql_name = "select * from irlist order by id";
        List<Ir_Info> values = template.query(sql_name, new BeanPropertyRowMapper<Ir_Info>(Ir_Info.class));
        System.out.println("fisrt enter : " + values.size());
        ArrayList<String> strings = new ArrayList<>();
        for (Ir_Info ir_info : values) {
            String irname = ir_info.getIrname();
            String sql_list = "select * from "+irname+" where 1 = 1";

            StringBuilder sb = new StringBuilder(sql_list);

            //2.遍历map
            Set<String> keySet = condition.keySet();
            //定义参数的集合
            List<Object> params = new ArrayList<Object>();
            for (String key : keySet) {
                //排除分页条件参数
                if("currentPage".equals(key) || "rows".equals(key)){
                    continue;
                }
                //获取value
                String value = condition.get(key)[0];
                //判断value是否有值
                if(value != null && !"".equals(value)){
                    //有值
                    sb.append(" and "+key+" like ? ");
                    params.add("%"+value+"%");//？条件的值
                }
            }
            System.out.println("count.sql.tostring = "+sb.toString());
            List<Ir_FuncInfo> ir_funcInfo = template.query(sb.toString(), new BeanPropertyRowMapper<Ir_FuncInfo>(Ir_FuncInfo.class), params.toArray());
            for (Ir_FuncInfo irFuncInfo : ir_funcInfo) {
                if (irFuncInfo.getHeader_code()!=null && !"".equals(irFuncInfo.getHeader_code())){
                    //System.out.println(irFuncInfo);
                    strings.add(irname);
                }
            }
        }
        System.out.println("total count : "+strings.size());
        return strings.size();
    }

    @Override
    public List<Ir_Info> findIrListByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from irlist  where  irname = \"";

        StringBuilder sb = new StringBuilder(sql);

        List<String> irnameByFind = getIrnameByFind(condition);
        //2.遍历List

        for (String list : irnameByFind) {

            //判断value是否有值
            if(list != null && !"".equals(list)){
                //有值
                sb.append("\" or  irname =  \""+list);
            }
        }
        //添加分页查询
        sb.append("\" limit ?,? ");
        sql = sb.toString();
        System.out.println(sql);
        System.out.println("start : "+start);
        System.out.println("rows : "+rows);
        return template.query(sql,new BeanPropertyRowMapper<Ir_Info>(Ir_Info.class),start,rows);
    }


    @Override
    public List<Ir_Info> getIrName() {
        //使用jdbc数据库
        //查询所有 irname
        String sql="select irname from irlist order by id";
        List<Ir_Info> irname = template.query(sql, new BeanPropertyRowMapper<Ir_Info>(Ir_Info.class));
        return irname;
    }


    public List<String> getIrnameByFind(Map<String, String[]> condition){
        //找出所有的遥控名称
        String sql_name = "select * from irlist order by id";
        List<Ir_Info> values = template.query(sql_name, new BeanPropertyRowMapper<Ir_Info>(Ir_Info.class));
        ArrayList<String> strings = new ArrayList<>();
        for (Ir_Info ir_info : values) {
            String irname = ir_info.getIrname();
            String sql_list = "select * from "+irname+" where 1=1";

            StringBuilder sb = new StringBuilder(sql_list);
            //2.遍历map
            Set<String> keySet = condition.keySet();
            //定义参数的集合
            List<Object> params = new ArrayList<Object>();
            for (String key : keySet) {

                //排除分页条件参数
                if("currentPage".equals(key) || "rows".equals(key)){
                    continue;
                }

                //获取value
                String value = condition.get(key)[0];
                //判断value是否有值
                if(value != null && !"".equals(value)){
                    //有值
                    sb.append(" and "+key+" like ? ");
                    params.add("%"+value+"%");//？条件的值
                }
            }

            List<Ir_FuncInfo> ir_funcInfo = template.query(sb.toString(), new BeanPropertyRowMapper<Ir_FuncInfo>(Ir_FuncInfo.class), params.toArray());
            for (Ir_FuncInfo irFuncInfo : ir_funcInfo) {
                if (irFuncInfo.getHeader_code()!=null && !"".equals(irFuncInfo.getHeader_code())){
                    //System.out.println(irFuncInfo);
                    strings.add(irname);
                }
            }
        }
        return strings;
    }
}
