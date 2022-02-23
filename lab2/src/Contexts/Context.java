package Contexts;

import Constants.Constants;
import Exceptions.EmptyVarException;
import Exceptions.MapException;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private final Stack<Double> _stack;
    private final Map<String, Double> _vars;

    public Context(){
        _stack = new Stack<>();
        _vars = new HashMap<>();
    }

    public String getStackPeek(){
        return _stack.peek().toString();
    }

    public Map<String, Double> getVars(){
        return _vars;
    }

    public Double pop(){
        return _stack.pop();
    }

    public void push(Double value) {
        _stack.push(value);
    };

    public Boolean isHasValue(String key){
        return _vars.containsKey(key);
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
