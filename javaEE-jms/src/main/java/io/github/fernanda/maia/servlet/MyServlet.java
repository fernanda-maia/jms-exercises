package io.github.fernanda.maia.servlet;

import io.github.fernanda.maia.jms.producer.MyMessageProducer;

import javax.ejb.EJB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 2945992744374936251L;

    @EJB
    MyMessageProducer producer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String message = "Hello from JavaEE Server!";
        producer.sendMessage(message);
        resp.getWriter().write("PUBLISHED MESSAGE: " + message);
    }
}
