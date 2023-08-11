package com.progbits.jetty.reference.rest;

import com.progbits.api.model.ApiObject;
import com.progbits.jetty.embedded.router.JettyEmbeddedRequest;
import com.progbits.jetty.embedded.router.JettyEmbeddedResponse;
import com.progbits.jetty.embedded.router.ServletRouter;
import com.progbits.jetty.embedded.servlet.ServletController;
import com.progbits.jetty.reference.boot.MainApplication;

/**
 *
 * @author scarr
 */
public class BaseController implements ServletController {

    @Override
    public void init() {
        // Nothing to do yet
    }

    @Override
    public void routes(ServletRouter sr) {
        sr.get(MainApplication.CONTEXT_PATH + "/healthcheck", this::healthCheck);
        sr.get(MainApplication.CONTEXT_PATH + "/testing/${myValue:int}/something/${url}", this::testingPath);
    }
    
    private void healthCheck(JettyEmbeddedRequest req, JettyEmbeddedResponse resp) throws Exception {
        ApiObject objResp = new ApiObject();
        
        objResp.setString("message", "Hello World");
        
        resp.payload(objResp);
    }
    
    private void testingPath(JettyEmbeddedRequest req, JettyEmbeddedResponse resp) throws Exception {
        resp.payload(req.getRequestInfo());
    }
    
}
