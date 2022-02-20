package lab1;
import java.util.*;
import java.util.Map.Entry;

public class Context {
    HashMap<String, Integer> _stat;
    int _word_counter;

    public Context(){
        _word_counter = 0;
        _stat = new HashMap<String, Integer>();
    }

    public void freeStat(){
        _stat.clear();
    }

    public void addWord(String word){
        _word_counter++;
        if(_stat.containsKey(word)){
            int tmp = _stat.get(word);
            _stat.put(word, tmp + 1);
        }
        else{
            _stat.put(word, 1);
        }
    }

    public int getWordCounter(){
        return _word_counter;
    }

    public List<Entry<String, Integer>> getSortedStat() {
        List<Entry<String, Integer>> stat = new ArrayList<Entry<String, Integer>>(_stat.entrySet());
        stat.sort(Entry.comparingByValue(Comparator.reverseOrder()));
        return stat;
    }
}