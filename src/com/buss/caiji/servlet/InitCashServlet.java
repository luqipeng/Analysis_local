package com.buss.caiji.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.buss.caiji.common.Cache;

public class InitCashServlet  extends HttpServlet{
	
	/**
     * .
     */
    private static final long serialVersionUID = 1L;
    private static Logger     logger           = Logger.getLogger(InitCashServlet.class);
    

    public void init(ServletConfig config) throws ServletException {
        try {
            super.init(config);
            Cache.initialize();
        } catch (Exception e) {
            logger.info("",e);
        }
    }
}
