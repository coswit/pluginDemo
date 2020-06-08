package xyz.coswit.plugindemo;


import xyz.coswit.pluginlibrary.IBean;
import xyz.coswit.pluginlibrary.ICallback;

public class Bean implements IBean {
    private String name = "coswit";

    private ICallback callback;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String paramString) {
        this.name = paramString;
    }

    @Override
    public void register(ICallback callback) {
        this.callback = callback;

        clickButton();
    }

    public void clickButton() {
        callback.sendResult("Hello: " + this.name);
    }
}