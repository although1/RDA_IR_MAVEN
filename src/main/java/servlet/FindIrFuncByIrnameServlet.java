package servlet;



import domain.Ir_FuncInfo;
import domain.Ir_Info;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findIrFuncByIrnameServlet")
public class FindIrFuncByIrnameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String irname = request.getParameter("irname");//获取当前的IRNAME
        if(irname == null || "" .equals(irname) ){
            irname="ircvt00bf";
        }
        //1.调用UserService完成查询
        UserService service = new UserServiceImpl();
        List<Ir_FuncInfo> ir_funcInfoByName =service.findIrListByName(irname);
        List<Ir_Info> ir_name = service.getIrName();
        //2. 将遥控数据存入request
        request.setAttribute("ir_funcInfoByName",ir_funcInfoByName);
        request.setAttribute("irname",ir_name);
        request.setAttribute("irListName",irname);


        //3.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
//        request.getRequestDispatcher("/advancedSearchPhoto1.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
