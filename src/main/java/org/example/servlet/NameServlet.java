package org.example.servlet;

import org.example.config.SpringContextConfig;
import org.example.service.NameProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/name")
public class NameServlet extends HttpServlet {
    private final static String SPRING_APP_CONTEXT = "SPRING_CONTEXT";
    private NameProvider nameProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var springContext = (ApplicationContext) req.getServletContext().getAttribute(SPRING_APP_CONTEXT);
        var nameProvider = springContext.getBean(NameProvider.class);

        var writer = resp.getWriter();
        writer.println(String.format("Hello, %s!", nameProvider.getName()));
        writer.flush();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        var springContext = new AnnotationConfigApplicationContext(SpringContextConfig.class);
        config.getServletContext().setAttribute(SPRING_APP_CONTEXT, springContext);
    }
}
