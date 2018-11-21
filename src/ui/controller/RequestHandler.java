package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.ShopService;

public abstract class RequestHandler {

    private ShopService service;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

    public void setService(ShopService service) {
        this.service = service;
    }

    public ShopService getService() {
        return service;
    }


}
