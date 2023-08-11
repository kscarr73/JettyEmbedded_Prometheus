package com.progbits.jetty.reference.rest;

import com.progbits.jetty.embedded.router.JettyEmbeddedRequest;
import com.progbits.jetty.embedded.router.JettyEmbeddedResponse;
import com.progbits.jetty.embedded.router.ServletRouter;
import com.progbits.jetty.embedded.servlet.ServletController;
import com.progbits.jetty.reference.boot.MainApplication;

/**
 *
 * @author scarr
 */
public class ApiController implements ServletController {

    private String rapiDocHtml;
    private String apiDoc;
    
    @Override
    public void init() {
        rapiDocHtml = HttpHelper.getResourceAsString(this.getClass(), "/rapidoc.html");
        
        rapiDocHtml = rapiDocHtml.replace("%%REPLACEME%%", "/reference/api-doc.yml");
        
        apiDoc = HttpHelper.getResourceAsString(this.getClass(), "/api-doc.yml");
    }

    @Override
    public void routes(ServletRouter sr) {
        sr.get(MainApplication.CONTEXT_PATH + "/api", this::sendApiHtml, ServletRouter.CONTENT_TYPE_HTML);
        sr.get(MainApplication.CONTEXT_PATH + "/api-doc.yml", this::sendApiDoc, ServletRouter.CONTENT_TYPE_YAML);
    }
    
    public void sendApiHtml(JettyEmbeddedRequest req, JettyEmbeddedResponse resp) throws Exception {
        resp.payload(rapiDocHtml);
    }
    
    public void sendApiDoc(JettyEmbeddedRequest req, JettyEmbeddedResponse resp) throws Exception {
        resp.payload(apiDoc);
    }
}
