package ru.gulyaev.Contexts;

import ru.gulyaev.Constants.Constants;
import ru.gulyaev.Exceptions.MapException;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Context {
    private final Stack<Double> _stack;
    private final Map<String, Double> _vars;

    public Context(){
        _stack = new Stack<>();
        _vars = new HashMap<>();
    }

    public Double getStackPeek(){
        return _stack.peek();
    }

    public Double pop(){
        return _stack.pop();
    }

    public void push(Double value) {
        _stack.push(value);
    }

    public Boolean isHasValue(String key){
        return _vars.containsKey(key);
    }

    public boolean isNormalName(String name) {
        String regex = Constants.VAR_NAME_FILTER;
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public Double searchValue(String key) throws MapException {
        if(_vars.containsKey(key)){
            return _vars.get(key);
        }
        throw new MapException(Constants.EMPTY_STR);
    }

    public void addValue(String define, Double val) {
        _vars.put(define, val);
    }

    public void addValue(Double val){
        _stack.push(val);
    }

}
