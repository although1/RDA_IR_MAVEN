package service;

import domain.Ir_FuncInfo;
import domain.Ir_Info;
import domain.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {

    /**
     * 查询irname
     * @return
     */
    public List<Ir_Info> getIrName();

    /**
     * 查询所有 header_code remote_code remote_function
     * @param irname
     * @return
     */
    List<Ir_FuncInfo> findIrListByName(String irname);

    /**
     * 分页查询遥控列表
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<Ir_Info> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);

    /**
     * 下拉列表中的remote_fucntion
     * @return
     */
    List<Ir_FuncInfo> getRemote_Function();

    /**
     * 分页查询遥控列表
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<Ir_Info> findIrListByPage(String currentPage, String rows, Map<String, String[]> condition);
}
