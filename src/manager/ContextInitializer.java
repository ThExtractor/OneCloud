package manager;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import manager.util.HibernateUtil;


@WebListener
public class ContextInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("contextInitialized");
//        ServletContext context = event.getServletContext();
//        Connection conn = DBUtil.getConnection();
//        context.setAttribute("DBConnection", conn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        HibernateUtil.closeSessionFactory();// 不关闭SessionFactory的话，服务器将无法正常关闭
        System.out.println("contextDestroyed");
    }
    
}