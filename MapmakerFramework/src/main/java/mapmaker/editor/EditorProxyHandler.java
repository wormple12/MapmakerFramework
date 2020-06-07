package mapmaker.editor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Proxy for an Editor implementation (given with the constructor) that (1)
 * validates @DrawFunction method declarations, and (2) calls the editor's
 * generic draw() method whenever a @DrawFunction is called via the proxy. This
 * class explores the following advanced programming techniques: (1) Java
 * Reflection API (with annotations); (2) Dynamic Proxy (with Java's
 * InvocationHandler).
 *
 * @author TeamOne
 */
public class EditorProxyHandler implements InvocationHandler {

    private final Map<String, Method> methods = new HashMap<>();
    private final Editor editor;

    private EditorProxyHandler(Editor editor, Class<? extends Editor> editorInterface) {
        this.editor = editor;

        if (!editorInterface.isInterface()) {
            throw new IllegalArgumentException("The passed Editor type is required to be an interface, as the proxy is unable to use a non-interface class.");
        }
        if (!editorInterface.isAssignableFrom(editor.getClass())) {
            throw new IllegalArgumentException("The passed Editor object must be of a type that implements the passed Editor interface.");
        }
        for (Method method : editorInterface.getMethods()) {
            if (method.getAnnotation(DrawFunction.class) != null && !validateDrawMethod(method)) {
                throw new IllegalArgumentException("The passed Editor interface was not valid. Any Editor method declared as a @DrawFunction must take x and y integers as its first two parameters.");
            }
            this.methods.put(method.getName(), method);
        }
    }

    private boolean validateDrawMethod(Method method) {
        Parameter[] parameters = method.getParameters();
        if (parameters.length < 2) {
            return false;
        }
        Class<?> paramType_0 = parameters[0].getType();
        Class<?> paramType_1 = parameters[1].getType();
        if (!Integer.class.isAssignableFrom(paramType_0)
                && !Integer.TYPE.equals(paramType_0)) {
            return false;
        }
        if (!Integer.class.isAssignableFrom(paramType_1)
                && !Integer.TYPE.equals(paramType_1)) {
            return false;
        }
        return true;
    }

    /**
     * Converts the given editor object (which must implement the given
     * interface) into a proxy version of that same object. It will now utilise
     * the @DrawFunction annotation to call the generic draw() method
     * automatically when appropiate.
     *
     * @param editor the object to convert to proxy
     * @param interfaceClass the editor interface that the object implements;
     * default: Editor.class
     * @return the given editor object as a proxy version of that same object
     */
    public static Editor getProxyInstance(Editor editor, Class<? extends Editor> interfaceClass) {
        return (Editor) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new EditorProxyHandler(editor, interfaceClass));
    }

    public static Editor getProxyInstance(Editor editor) {
        return getProxyInstance(editor, Editor.class);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getAnnotation(DrawFunction.class) != null) {
            editor.draw((Integer) args[0], (Integer) args[1]);
        }
        Object result = methods.get(method.getName()).invoke(editor, args);
        return result;
    }

}
