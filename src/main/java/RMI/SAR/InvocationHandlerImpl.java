package RMI.SAR;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class InvocationHandlerImpl implements InvocationHandler, Serializable {
    protected Map map;

    public InvocationHandlerImpl(Map map) {
        this.map = map;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
