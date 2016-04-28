package tw.com.ehanlin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CsvToMapConverter{

    private static final CsvToMapConverter converter = new CsvToMapConverter();

    public static Map convert(List<List<String>> lines, int groupColumnNum){
        return converter.convertByList(lines, groupColumnNum);
    }


    private CsvToMapConverter(){

    }

    private Map convertByList(List<List<String>> lines, int groupColumnNum){
        Map result = new LinkedHashMap<String, Object>();

        for(List<String> line : lines){
            if(line.size() == 0) continue;
            Boolean enabled = line.get(0).trim().equals("1");
            if(!enabled) continue;
            if(line.size() < (groupColumnNum+1)) throw new IllegalArgumentException("line size < groupColumnNum");

            List<String> values = new ArrayList<String>();
            for(int valueColumnIndex=(groupColumnNum+1) ; valueColumnIndex<line.size() ; valueColumnIndex++){
                String value = line.get(valueColumnIndex).trim();
                if(!value.equals("")) values.add(value);
            }

            Map lastGroup = result;
            for(int groupColumnIndex=1 ; groupColumnIndex<groupColumnNum ; groupColumnIndex++){
                lastGroup = getMap(lastGroup, line.get(groupColumnIndex).trim());
            }
            String lastGroupKey = line.get(groupColumnNum).trim();
            if(lastGroup.containsKey(lastGroupKey)){
                ((List<String>) lastGroup.get(lastGroupKey)).addAll(values);
            }else{
                lastGroup.put(lastGroupKey, values);
            }
        }

        return result;
    }

    private Map getMap(Map map, String key){
        if(!map.containsKey(key)){
            map.put(key, new LinkedHashMap<String, Object>());
        }
        return (Map) map.get(key);
    }

}
