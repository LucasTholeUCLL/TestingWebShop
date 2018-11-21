package ui.controller;

import domain.model.ShopService;

import java.lang.reflect.InvocationTargetException;

public class HandlerFactory {
    public RequestHandler getController(String key, ShopService service) {
        return this.getHandler(key, service);
    }


    private RequestHandler getHandler(String handlerName, ShopService service) {
        RequestHandler handler = null;

        try {
            Class<?> handlerClass = Class.forName("ui.controller.handlers."+ handlerName + "Handler");
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setService(service);
        } catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Page does not exist");
        }
        return handler;
    }
}
