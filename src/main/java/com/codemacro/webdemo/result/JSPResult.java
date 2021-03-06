package com.codemacro.webdemo.result;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codemacro.webdemo.ActionManager;

public class JSPResult extends Result {
  private static final String BASE = "/view/";
  private HttpServletRequest request;
  private String file;
  private Map<String, Object> content;

  public JSPResult(HttpServletRequest req, HttpServletResponse resp, String file, 
      ActionManager actionMgr) {
    super(resp, null);
    this.request = req;
    this.content = new TreeMap<String, Object>();
    this.file = BASE + file; 
    put(JSPRouteTag.ACTION_MGR, actionMgr);
  }

  public JSPResult put(String key, Object value) {
    content.put(key, value);
    return this;
  }

  @Override
  public void render() throws IOException, ServletException {
    for (Map.Entry<String, Object> entry : content.entrySet()) {
      request.setAttribute(entry.getKey(), entry.getValue());
    }
    request.getRequestDispatcher(file).forward(request, response);
  }
}

