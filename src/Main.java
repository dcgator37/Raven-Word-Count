import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("1065-h.htm")).useDelimiter("\b(?<![</])(?!>)[^.?!]+[.!?]");
        Map<String, Integer> map = new HashMap<String, Integer>();
        int titleIndex;
        int endIndex;

        while (scanner.hasNext()) {
                String file;
                file = scanner.next();
                String[] lines = file.split("\\R");

                titleIndex = Arrays.asList(lines).indexOf("<h1>The Raven</h1>");
                endIndex = Arrays.asList(lines).indexOf("<span style=\"margin-left: 20%\">Shall be lifted&mdash;nevermore!</span>");

            for (int i = titleIndex+1; i <= endIndex; i++) {
                    String stripped = lines[i].replaceAll("<[^>]*>", "");
                    stripped = stripped.replaceAll("(&mdash;)"," ");
                    stripped = stripped.replaceAll("('s)","");
                    String[] wordArray = stripped.split("[^a-zA-Z]+");

                    for (String word : wordArray) {
                        if (word != "") {
                            String wordLowerCase = word.toLowerCase();
                            if (map.containsKey(wordLowerCase)) {
                                map.put(wordLowerCase, map.get(wordLowerCase) + 1);
                            } else {
                                map.put(wordLowerCase, 1);
                            }
                        }
                    }
            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<Entry<String,Integer>>( map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return a.getValue().compareTo(b.getValue());
            }
        });

        for(int i = 0; i < 20; i++){
            System.out.println(entries.get(entries.size() - i - 1).getKey()+" "+entries.get(entries.size() - i - 1).getValue());
        }
    }
}
