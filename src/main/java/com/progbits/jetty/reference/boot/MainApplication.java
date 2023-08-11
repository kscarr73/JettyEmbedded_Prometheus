package com.progbits.jetty.reference.boot;

import com.progbits.jetty.embedded.JettyEmbedded;
import com.progbits.jetty.embedded.router.ServletRouter;
import com.progbits.jetty.reference.rest.ApiController;
import com.progbits.jetty.reference.rest.BaseController;

public class MainApplication {
    
    public static final String CONTEXT_PATH = "/reference";
    
    public static void main(String[] args) {
        
        ServletRouter routes = new ServletRouter();
        
        routes.addServletController(new BaseController());
        routes.addServletController(new ApiController());
        
        JettyEmbedded.builder()
                .setContextPath(CONTEXT_PATH)
                .setPort(8080)
                .useServletRoutes(routes)
                .build();          
    }
}
