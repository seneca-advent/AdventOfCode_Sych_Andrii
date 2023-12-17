package Task05;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapsParser {
    public List<Map> parseMaps(ArrayList<String> lines) {
        List<Map> maps = new ArrayList<>();
        Pattern mapNameRegex = Pattern.compile("[a-z]+");

        for (int i = 1; i < lines.size(); i++) {
            Matcher mapName = mapNameRegex.matcher(lines.get(i));
            List<Map.MapRule> mapRules = new ArrayList<>();

            if(mapName.find()) {
                Map.MapRule rule = Map.MapRule.create(lines.get(++i));
                mapRules.add(rule);
                i++;

                while(rule != null && i < lines.size()) {
                    rule =  Map.MapRule.create(lines.get(i));
                    if(rule != null)
                        mapRules.add(rule);
                    i++;
                }
                --i;
                maps.add(new Map(mapRules));
            }
        }
        return maps;
    }
}
