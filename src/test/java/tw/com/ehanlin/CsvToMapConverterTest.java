package tw.com.ehanlin;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

public class CsvToMapConverterTest {

    @Test
    public void convertTest() {
        List<String> csvLine1  = Arrays.asList("1","A","a","1");
        List<String> csvLine2  = Arrays.asList("1","A","a","2");
        List<String> csvLine3  = Arrays.asList("1","A","a","3","4");
        List<String> csvLine4  = Arrays.asList("1","A","b","1","2");
        List<String> csvLine5  = Arrays.asList("1","A","b","3","4","5");
        List<String> csvLine6  = Arrays.asList("1","B","a","");
        List<String> csvLine7  = Arrays.asList("1","B","b","1");
        List<String> csvLine8  = Arrays.asList("0","B","b","2","3");
        List<List<String>> csvLines = Arrays.asList(csvLine1, csvLine2, csvLine3, csvLine4, csvLine5, csvLine6, csvLine7, csvLine8);

        Map<String, Map<String, List<String>>> map = (Map<String, Map<String, List<String>>>) CsvToMapConverter.convert(csvLines, 2);

        Assert.assertArrayEquals(map.get("A").get("a").toArray(), Arrays.asList("1","2","3","4").toArray());
        Assert.assertArrayEquals(map.get("A").get("b").toArray(), Arrays.asList("1","2","3","4","5").toArray());
        Assert.assertTrue(map.get("B").get("a").isEmpty());
        Assert.assertArrayEquals(map.get("B").get("b").toArray(), Arrays.asList("1").toArray());
    }
}
