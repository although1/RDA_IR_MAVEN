package dao;

import domain.Ir_FuncInfo;
import domain.Ir_Info;

import java.util.List;
import java.util.Map;

public interface Ir_Dao {
    /**
     * 查询所有的遥控名称
     * @return
     */
    public List<Ir_Info> getIrName();

    /**
     * 根据遥控名称查询遥控功能
     * @param irname
     * @return
     */
    List<Ir_FuncInfo> findIrListByName(String irname);

    /**
     * 查询总记录数
     * @param condition
     * @return
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<Ir_Info> findByPage(int start, int rows, Map<String, String[]> condition);

    /**
     * 下拉列表中的remote_fucntion
     * @return
     */
    List<Ir_FuncInfo> getRemote_Function();

    /**
     * 查询总记录数
     * @param condition
     * @return
     */
    int findTotalCountByPage(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<Ir_Info> findIrListByPage(int start, int rows, Map<String, String[]> condition);
}
