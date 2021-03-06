package at.thehuters;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


class TestAdvent7 {

    private static class BagProjection{
        String outside;
        LinkedList<Content> content;

        public BagProjection(String outside, LinkedList<Content> content){
            this.outside=outside;
            this.content=content;
        }

        public String getOutside() {
            return outside;
        }

        public LinkedList<Content> getContent() {
            return content;
        }
    }

    private static class Content{
        String bagType;
        int count;

        public Content(String bagType, int count){
            this.bagType=bagType;
            this.count=count;
        }

        public String getBagType() {
            return bagType;
        }

        public int getCount() {
            return count;
        }
    }

    public static Map<String, BagProjection> bagProjections = new HashMap<String, BagProjection>();

    @BeforeAll
    static void initialize(){
        for (String line : data()){
            line = line.replaceAll("bags", "bag");
            var containsSplit = line.split("contain");
            var outer = containsSplit[0].trim();
            if (containsSplit[1].trim().startsWith("no")){
                bagProjections.put(outer,new BagProjection(outer,null));
            }
            else{
                var targets = containsSplit[1].replaceAll("\\.","").split(",");
                var content = new LinkedList<Content>();
                for(String target : targets){
                    var entryArray =target.trim().split(" ",2);
                    content.add(new Content(entryArray[1].trim(),Integer.parseInt(entryArray[0])));
                }
                bagProjections.put(outer, new BagProjection(outer.trim(),content));
            }
        }
    }


    @Test
    void testCase1() {
        System.out.println(canContainBagType("shiny gold bag",bagProjections));
    }

    @Test
    void testCase2() {
        System.out.println(contains("shiny gold bag",bagProjections)-1);
    }


    private int contains(String bag, Map<String, BagProjection> bagProjections) {
        var contents = bagProjections.get(bag).getContent();
        if (contents == null){
            return 1;
        }
        int result = 0;
        for(Content content : contents)
            result+= content.count*contains(content.bagType, bagProjections);

        return result+1;
    }

    private int canContainBagType(
            String bagtype,
            Map<String, BagProjection> bagProjections){
        AtomicInteger result = new AtomicInteger();
        Set<String> knowShortCut = new HashSet<>();
        bagProjections.forEach((outer, bagProjection) -> {
            if(bagProjection.getContent() != null){
                var iterator = new LinkedList<>(bagProjection.getContent());
                while (iterator.size() > 0){
                    var content = iterator.removeFirst();
                    if(bagtype.equals(content.getBagType()) || knowShortCut.contains(bagtype)){
                        result.getAndIncrement();
                        iterator.clear();
                        knowShortCut.add(outer);
                    }
                    else if(content.getBagType() != null){
                        BagProjection tmp = bagProjections.get(content.getBagType());
                        if (tmp != null && tmp.getContent() != null){
                            iterator.addAll(tmp.getContent());
                        }
                    }
                }
            }
        });

        return result.intValue();
    }

    static List<String> data2(){
        return Arrays.asList(("shiny gold bags contain 2 dark red bags.\n" +
                "dark red bags contain 2 dark orange bags.\n" +
                "dark orange bags contain 2 dark yellow bags.\n" +
                "dark yellow bags contain 2 dark green bags.\n" +
                "dark green bags contain 2 dark blue bags.\n" +
                "dark blue bags contain 2 dark violet bags.\n" +
                "dark violet bags contain no other bags.").split("\n"));
    }


    static List<String> data(){
        return Arrays.asList(("muted tomato bags contain 1 bright brown bag, 1 dotted gold bag, 2 faded gray bags, 1 posh yellow bag.\n" +
                "posh brown bags contain 1 dark lime bag, 5 mirrored crimson bags, 1 striped chartreuse bag.\n" +
                "dotted violet bags contain 4 striped white bags.\n" +
                "mirrored black bags contain 1 clear yellow bag.\n" +
                "light aqua bags contain 3 clear silver bags, 2 shiny coral bags, 2 muted tomato bags, 2 drab turquoise bags.\n" +
                "faded violet bags contain no other bags.\n" +
                "shiny lime bags contain 3 muted brown bags, 4 dull gray bags.\n" +
                "dark green bags contain 3 muted magenta bags.\n" +
                "faded bronze bags contain 5 clear lime bags, 3 muted gold bags.\n" +
                "clear lavender bags contain 4 dark beige bags, 3 mirrored crimson bags, 2 bright blue bags.\n" +
                "striped bronze bags contain 5 muted olive bags, 4 clear chartreuse bags, 2 vibrant blue bags.\n" +
                "dim bronze bags contain 1 clear indigo bag.\n" +
                "dull crimson bags contain 3 dotted violet bags, 2 pale teal bags, 3 plaid bronze bags, 3 faded aqua bags.\n" +
                "mirrored bronze bags contain 3 striped maroon bags, 4 shiny gold bags, 4 light indigo bags, 5 clear orange bags.\n" +
                "vibrant white bags contain 4 light lime bags.\n" +
                "dark blue bags contain 1 faded black bag, 5 mirrored plum bags, 4 muted cyan bags.\n" +
                "drab bronze bags contain 5 mirrored plum bags, 2 bright turquoise bags, 1 clear gray bag, 3 faded chartreuse bags.\n" +
                "mirrored lime bags contain 2 faded red bags.\n" +
                "posh violet bags contain 3 dull chartreuse bags, 5 clear plum bags, 2 mirrored chartreuse bags, 5 dull magenta bags.\n" +
                "dull bronze bags contain 2 wavy purple bags, 4 drab salmon bags, 5 dull chartreuse bags.\n" +
                "dull black bags contain 4 shiny crimson bags, 5 dotted turquoise bags, 4 clear beige bags, 2 striped maroon bags.\n" +
                "striped aqua bags contain 1 faded violet bag, 1 plaid teal bag.\n" +
                "clear silver bags contain 1 faded green bag.\n" +
                "dark yellow bags contain 4 shiny plum bags, 2 dull gray bags, 5 light lime bags, 4 shiny orange bags.\n" +
                "dull coral bags contain 2 striped turquoise bags, 4 mirrored fuchsia bags, 5 faded yellow bags.\n" +
                "shiny yellow bags contain 5 wavy maroon bags, 1 striped fuchsia bag.\n" +
                "shiny black bags contain 2 muted tomato bags, 3 muted maroon bags, 4 shiny orange bags.\n" +
                "posh aqua bags contain 3 wavy cyan bags, 5 posh brown bags, 4 dim plum bags.\n" +
                "plaid maroon bags contain 5 faded aqua bags, 5 mirrored olive bags, 5 plaid gold bags, 2 vibrant gray bags.\n" +
                "mirrored gold bags contain 3 shiny tomato bags, 3 shiny crimson bags.\n" +
                "muted lavender bags contain 2 muted tan bags.\n" +
                "vibrant magenta bags contain 1 dull purple bag, 2 vibrant coral bags, 5 faded silver bags.\n" +
                "striped tomato bags contain 3 faded beige bags, 3 pale tan bags, 2 drab orange bags.\n" +
                "dull turquoise bags contain 3 striped green bags, 2 shiny blue bags, 2 dim purple bags.\n" +
                "shiny aqua bags contain 1 clear salmon bag.\n" +
                "drab silver bags contain 2 shiny gold bags, 4 posh yellow bags.\n" +
                "plaid gold bags contain 3 bright lavender bags, 5 plaid cyan bags, 1 bright brown bag, 5 faded turquoise bags.\n" +
                "wavy lavender bags contain 2 plaid lavender bags, 3 plaid red bags, 4 posh cyan bags, 4 bright aqua bags.\n" +
                "dark bronze bags contain 4 muted cyan bags, 4 clear cyan bags.\n" +
                "plaid lime bags contain 1 posh teal bag.\n" +
                "mirrored magenta bags contain 5 dotted turquoise bags.\n" +
                "light turquoise bags contain 5 posh tan bags, 2 posh aqua bags.\n" +
                "dark indigo bags contain 5 clear crimson bags, 3 posh lime bags.\n" +
                "mirrored brown bags contain 4 posh white bags, 1 dim lime bag, 2 pale magenta bags, 1 mirrored chartreuse bag.\n" +
                "vibrant lavender bags contain 5 posh plum bags, 1 posh aqua bag.\n" +
                "dim crimson bags contain 3 vibrant beige bags, 4 clear indigo bags, 3 dim lime bags, 4 striped olive bags.\n" +
                "vibrant violet bags contain 5 vibrant red bags, 1 striped white bag.\n" +
                "dull silver bags contain 4 striped bronze bags, 1 plaid plum bag, 1 dim tomato bag.\n" +
                "plaid brown bags contain 5 drab blue bags, 2 light turquoise bags, 5 dull lavender bags.\n" +
                "dim aqua bags contain 4 plaid lavender bags, 5 muted maroon bags.\n" +
                "faded plum bags contain 3 striped tan bags.\n" +
                "drab black bags contain 5 clear bronze bags, 4 plaid red bags, 1 drab orange bag, 5 clear lavender bags.\n" +
                "pale bronze bags contain 5 dim plum bags, 1 drab lavender bag.\n" +
                "mirrored purple bags contain 5 mirrored crimson bags.\n" +
                "mirrored red bags contain 5 striped magenta bags, 4 mirrored chartreuse bags, 5 pale cyan bags.\n" +
                "posh lime bags contain 5 dull lavender bags, 1 faded turquoise bag, 2 striped coral bags, 3 striped green bags.\n" +
                "vibrant black bags contain 2 drab salmon bags, 4 clear tomato bags, 2 faded turquoise bags.\n" +
                "striped orange bags contain 2 plaid blue bags, 5 pale teal bags, 1 drab maroon bag.\n" +
                "dotted tomato bags contain 1 dim white bag, 5 dotted magenta bags, 4 dull tomato bags, 1 dull gold bag.\n" +
                "posh orange bags contain 3 posh gold bags, 4 light lime bags, 2 faded violet bags, 5 faded turquoise bags.\n" +
                "muted gold bags contain 4 faded maroon bags, 5 dark turquoise bags, 4 bright turquoise bags, 3 plaid silver bags.\n" +
                "striped yellow bags contain 4 drab fuchsia bags, 1 dim lime bag.\n" +
                "dotted green bags contain 4 clear beige bags.\n" +
                "faded fuchsia bags contain 1 dull blue bag, 4 bright coral bags.\n" +
                "shiny lavender bags contain 3 mirrored beige bags, 1 dark coral bag, 1 muted turquoise bag, 3 light brown bags.\n" +
                "bright maroon bags contain 5 faded lime bags, 3 dim brown bags, 3 vibrant yellow bags.\n" +
                "dark maroon bags contain 2 drab red bags.\n" +
                "dark white bags contain 3 wavy turquoise bags, 4 dotted red bags, 3 plaid fuchsia bags.\n" +
                "dotted aqua bags contain 4 clear green bags, 3 drab magenta bags, 1 dull tomato bag, 3 striped coral bags.\n" +
                "faded red bags contain 3 plaid purple bags, 3 dark lime bags.\n" +
                "wavy yellow bags contain 1 bright yellow bag, 2 light chartreuse bags, 1 dull chartreuse bag.\n" +
                "posh beige bags contain 5 shiny orange bags, 4 drab silver bags, 1 striped cyan bag.\n" +
                "dull fuchsia bags contain 5 faded lavender bags, 1 dark cyan bag, 2 clear tomato bags, 3 mirrored chartreuse bags.\n" +
                "muted silver bags contain 5 muted brown bags, 2 striped brown bags, 4 mirrored bronze bags, 5 faded plum bags.\n" +
                "dim white bags contain 1 dull bronze bag, 3 dotted beige bags, 4 wavy tan bags, 4 vibrant white bags.\n" +
                "faded green bags contain 1 clear indigo bag, 2 pale cyan bags, 1 wavy brown bag.\n" +
                "pale blue bags contain 2 posh turquoise bags.\n" +
                "muted green bags contain 1 dull bronze bag.\n" +
                "dull indigo bags contain 4 shiny silver bags, 1 wavy red bag, 4 muted orange bags.\n" +
                "muted plum bags contain 1 shiny lime bag, 4 plaid tomato bags, 2 faded indigo bags.\n" +
                "striped magenta bags contain 1 mirrored chartreuse bag, 1 faded beige bag, 3 bright blue bags, 2 clear yellow bags.\n" +
                "clear coral bags contain 4 dark turquoise bags, 2 posh magenta bags, 2 shiny aqua bags, 5 pale aqua bags.\n" +
                "pale olive bags contain 4 shiny crimson bags, 3 wavy cyan bags, 1 vibrant beige bag, 3 dark beige bags.\n" +
                "striped plum bags contain 2 light salmon bags.\n" +
                "dim indigo bags contain 2 shiny lavender bags, 4 clear lavender bags, 1 bright tomato bag, 1 vibrant bronze bag.\n" +
                "striped green bags contain 5 muted cyan bags.\n" +
                "shiny salmon bags contain 1 wavy brown bag.\n" +
                "wavy aqua bags contain 5 muted white bags, 1 mirrored gray bag, 5 dull lavender bags.\n" +
                "posh gold bags contain 1 vibrant beige bag, 3 dull chartreuse bags, 4 mirrored brown bags.\n" +
                "wavy blue bags contain 3 shiny gray bags, 4 posh turquoise bags.\n" +
                "dark fuchsia bags contain 2 posh fuchsia bags, 4 bright indigo bags, 3 posh maroon bags, 2 posh aqua bags.\n" +
                "shiny purple bags contain 5 clear maroon bags, 3 bright indigo bags.\n" +
                "dull chartreuse bags contain no other bags.\n" +
                "light beige bags contain 4 dim purple bags, 1 posh red bag, 4 clear aqua bags, 1 striped coral bag.\n" +
                "wavy purple bags contain 3 bright turquoise bags.\n" +
                "clear yellow bags contain 2 posh plum bags.\n" +
                "clear violet bags contain 5 faded chartreuse bags, 5 clear green bags, 3 wavy brown bags.\n" +
                "striped white bags contain 3 light white bags.\n" +
                "clear green bags contain 4 muted bronze bags.\n" +
                "drab crimson bags contain 1 posh tan bag.\n" +
                "dark turquoise bags contain 2 shiny tomato bags, 5 wavy maroon bags, 5 dim aqua bags.\n" +
                "drab magenta bags contain 1 pale gray bag, 4 striped olive bags, 2 posh brown bags.\n" +
                "dotted gray bags contain 1 dull tomato bag, 1 posh yellow bag.\n" +
                "plaid magenta bags contain 5 clear green bags, 2 dotted bronze bags, 5 drab black bags.\n" +
                "striped indigo bags contain 4 mirrored magenta bags, 5 posh beige bags.\n" +
                "bright fuchsia bags contain 4 dotted orange bags, 2 faded green bags, 1 bright turquoise bag.\n" +
                "mirrored teal bags contain 1 mirrored chartreuse bag, 2 drab brown bags, 2 dull black bags.\n" +
                "dotted purple bags contain 5 dotted turquoise bags, 5 dotted orange bags, 5 shiny gold bags.\n" +
                "dim lavender bags contain 3 shiny coral bags, 5 mirrored crimson bags, 2 clear yellow bags, 2 muted orange bags.\n" +
                "shiny gray bags contain 3 vibrant purple bags.\n" +
                "mirrored white bags contain 1 mirrored brown bag, 3 striped purple bags.\n" +
                "light cyan bags contain 4 bright maroon bags, 3 posh violet bags, 4 faded aqua bags, 5 dull tan bags.\n" +
                "wavy gray bags contain 4 mirrored violet bags, 1 muted orange bag.\n" +
                "striped silver bags contain 2 bright silver bags, 5 posh aqua bags.\n" +
                "dull green bags contain 5 mirrored chartreuse bags, 2 dim beige bags.\n" +
                "faded black bags contain 5 wavy turquoise bags, 1 muted tan bag, 1 dull chartreuse bag.\n" +
                "posh crimson bags contain 2 muted tan bags, 4 striped beige bags, 1 mirrored lavender bag, 5 faded white bags.\n" +
                "pale teal bags contain 4 posh aqua bags, 5 mirrored plum bags, 1 muted turquoise bag.\n" +
                "muted tan bags contain 1 striped coral bag, 1 mirrored brown bag, 1 posh gold bag, 4 muted cyan bags.\n" +
                "posh indigo bags contain 5 shiny tomato bags, 1 clear salmon bag, 3 dotted black bags, 1 striped maroon bag.\n" +
                "clear gray bags contain 1 bright violet bag.\n" +
                "striped olive bags contain no other bags.\n" +
                "mirrored silver bags contain 4 shiny maroon bags, 4 muted coral bags.\n" +
                "clear brown bags contain 2 dotted magenta bags, 3 clear aqua bags, 5 faded lime bags.\n" +
                "striped lavender bags contain 4 dull turquoise bags.\n" +
                "plaid teal bags contain 3 muted bronze bags, 2 drab orange bags.\n" +
                "faded blue bags contain 3 bright teal bags, 2 posh brown bags.\n" +
                "drab gold bags contain 3 dotted black bags, 5 striped yellow bags.\n" +
                "plaid gray bags contain 2 drab magenta bags, 4 bright yellow bags, 5 pale beige bags.\n" +
                "mirrored coral bags contain 1 shiny fuchsia bag, 5 vibrant gray bags, 1 wavy maroon bag.\n" +
                "drab fuchsia bags contain 3 dull silver bags, 5 posh brown bags.\n" +
                "wavy olive bags contain 2 plaid lavender bags.\n" +
                "vibrant maroon bags contain 4 drab brown bags, 1 dark indigo bag, 1 clear gray bag.\n" +
                "faded magenta bags contain 1 dim crimson bag.\n" +
                "pale aqua bags contain 3 dotted turquoise bags.\n" +
                "dotted orange bags contain 2 light coral bags.\n" +
                "dim coral bags contain 4 pale gold bags.\n" +
                "faded turquoise bags contain 5 posh gold bags, 2 bright turquoise bags, 1 drab magenta bag.\n" +
                "striped tan bags contain 3 bright red bags.\n" +
                "drab green bags contain 3 muted gold bags, 3 plaid gold bags.\n" +
                "posh red bags contain 3 mirrored red bags, 2 striped fuchsia bags, 3 faded violet bags, 1 bright black bag.\n" +
                "drab lime bags contain 2 dotted brown bags.\n" +
                "plaid tomato bags contain 4 muted olive bags, 2 striped tan bags.\n" +
                "striped blue bags contain 3 drab lavender bags.\n" +
                "bright bronze bags contain 2 clear gold bags, 2 striped gold bags, 4 dim coral bags, 5 vibrant purple bags.\n" +
                "muted white bags contain 4 light olive bags.\n" +
                "pale violet bags contain 1 bright teal bag, 5 bright lavender bags, 2 faded green bags, 5 clear gold bags.\n" +
                "striped violet bags contain 2 drab turquoise bags, 2 bright red bags, 3 vibrant blue bags.\n" +
                "dull beige bags contain 2 vibrant beige bags.\n" +
                "light lime bags contain 1 bright turquoise bag, 3 vibrant beige bags, 2 wavy bronze bags, 1 muted cyan bag.\n" +
                "striped gray bags contain 5 vibrant brown bags, 3 wavy red bags, 3 plaid bronze bags, 4 wavy aqua bags.\n" +
                "dark violet bags contain 5 dim indigo bags, 1 dim chartreuse bag, 5 dim purple bags, 1 striped magenta bag.\n" +
                "dim turquoise bags contain 3 muted tan bags.\n" +
                "pale green bags contain 2 pale olive bags, 5 wavy red bags, 5 pale lime bags.\n" +
                "dark beige bags contain 1 light green bag, 4 drab beige bags, 2 dim cyan bags.\n" +
                "drab maroon bags contain 3 light coral bags, 1 clear crimson bag.\n" +
                "dark gold bags contain 1 dim black bag, 1 dotted gold bag, 3 dim gray bags, 5 wavy red bags.\n" +
                "drab white bags contain 1 pale gray bag.\n" +
                "mirrored olive bags contain 4 mirrored crimson bags, 3 faded beige bags.\n" +
                "pale yellow bags contain 4 shiny maroon bags, 5 dim tomato bags, 4 shiny olive bags, 1 faded olive bag.\n" +
                "striped lime bags contain 2 light lavender bags, 4 shiny aqua bags, 2 pale maroon bags.\n" +
                "mirrored tomato bags contain 2 clear crimson bags, 2 pale beige bags, 5 striped purple bags, 4 clear tomato bags.\n" +
                "dark black bags contain 1 mirrored orange bag, 4 shiny brown bags.\n" +
                "plaid chartreuse bags contain 1 dim beige bag, 2 vibrant red bags, 1 bright lavender bag, 5 mirrored tomato bags.\n" +
                "posh gray bags contain 5 dotted blue bags.\n" +
                "mirrored crimson bags contain no other bags.\n" +
                "pale silver bags contain 3 plaid cyan bags.\n" +
                "shiny green bags contain 4 dotted violet bags, 3 drab olive bags.\n" +
                "light brown bags contain 2 vibrant yellow bags, 3 dim tomato bags.\n" +
                "muted yellow bags contain 1 drab magenta bag, 5 muted olive bags, 3 clear crimson bags, 2 mirrored brown bags.\n" +
                "mirrored plum bags contain 4 muted tan bags, 1 posh brown bag, 3 faded silver bags, 1 dull gold bag.\n" +
                "dark chartreuse bags contain 1 dim orange bag, 2 dull indigo bags, 1 dark beige bag.\n" +
                "wavy brown bags contain 3 striped chartreuse bags.\n" +
                "faded white bags contain 5 striped magenta bags, 2 muted turquoise bags, 2 bright cyan bags, 3 clear chartreuse bags.\n" +
                "bright green bags contain 1 light salmon bag.\n" +
                "bright salmon bags contain 3 posh tan bags, 2 drab orange bags.\n" +
                "drab plum bags contain 2 posh turquoise bags, 4 dark salmon bags, 3 posh lime bags, 4 light coral bags.\n" +
                "drab tomato bags contain 5 faded teal bags, 4 mirrored black bags, 4 mirrored lavender bags, 1 drab salmon bag.\n" +
                "faded olive bags contain 4 light green bags, 4 clear bronze bags.\n" +
                "plaid bronze bags contain 3 dim plum bags, 2 posh white bags, 1 dotted gray bag, 2 dull gray bags.\n" +
                "light salmon bags contain 2 faded violet bags.\n" +
                "bright red bags contain 1 posh plum bag, 4 clear crimson bags.\n" +
                "faded maroon bags contain 3 striped olive bags, 5 wavy bronze bags, 3 bright lavender bags.\n" +
                "mirrored orange bags contain 1 bright silver bag, 3 bright red bags, 1 drab brown bag.\n" +
                "dotted indigo bags contain 1 dull black bag, 2 posh white bags.\n" +
                "wavy teal bags contain 1 dotted tomato bag, 4 faded beige bags, 5 dull beige bags, 1 clear crimson bag.\n" +
                "dim magenta bags contain 2 posh maroon bags, 1 dim yellow bag, 4 faded red bags, 5 pale olive bags.\n" +
                "light red bags contain 3 dim silver bags, 5 drab maroon bags, 3 shiny red bags.\n" +
                "plaid red bags contain 1 posh yellow bag, 5 muted turquoise bags, 1 bright red bag.\n" +
                "pale magenta bags contain 4 dim plum bags, 5 dull tomato bags, 3 bright turquoise bags, 1 dark lime bag.\n" +
                "posh yellow bags contain 5 posh brown bags.\n" +
                "bright lavender bags contain 5 clear aqua bags, 1 posh yellow bag, 4 pale magenta bags.\n" +
                "dim fuchsia bags contain 4 muted green bags, 3 mirrored beige bags, 5 dark brown bags, 1 dull crimson bag.\n" +
                "vibrant salmon bags contain 4 clear lime bags, 5 plaid brown bags, 5 pale white bags.\n" +
                "dim black bags contain 3 faded magenta bags, 5 dull gray bags, 2 vibrant blue bags, 2 vibrant bronze bags.\n" +
                "dotted yellow bags contain 3 drab turquoise bags, 3 muted cyan bags, 3 clear gray bags.\n" +
                "mirrored beige bags contain 1 drab salmon bag, 1 striped olive bag, 5 dotted gold bags, 2 faded beige bags.\n" +
                "striped brown bags contain 5 bright maroon bags, 2 light gold bags.\n" +
                "striped chartreuse bags contain no other bags.\n" +
                "dark lime bags contain 3 dull chartreuse bags, 5 dim violet bags, 2 mirrored crimson bags.\n" +
                "faded salmon bags contain 2 dull chartreuse bags, 4 posh brown bags, 3 mirrored olive bags, 5 light green bags.\n" +
                "bright brown bags contain 2 vibrant bronze bags, 2 dim gray bags.\n" +
                "dull tan bags contain 5 posh indigo bags.\n" +
                "dim silver bags contain 1 vibrant tomato bag, 4 dim maroon bags.\n" +
                "clear lime bags contain 4 clear gray bags, 1 clear tan bag, 4 posh brown bags, 1 dark indigo bag.\n" +
                "shiny beige bags contain 5 plaid lavender bags, 4 drab red bags, 4 striped green bags, 2 vibrant tomato bags.\n" +
                "dim tomato bags contain 3 clear yellow bags, 2 wavy gold bags, 3 pale cyan bags.\n" +
                "dotted tan bags contain 3 pale white bags.\n" +
                "dark crimson bags contain 1 dim coral bag, 4 muted brown bags, 2 clear chartreuse bags.\n" +
                "plaid coral bags contain 1 dark yellow bag, 3 muted tomato bags.\n" +
                "faded lavender bags contain 1 muted lavender bag, 5 mirrored red bags, 1 muted orange bag, 3 light coral bags.\n" +
                "vibrant crimson bags contain 3 plaid plum bags, 2 bright olive bags.\n" +
                "faded chartreuse bags contain 5 dim cyan bags.\n" +
                "dark gray bags contain 5 clear plum bags, 5 faded aqua bags.\n" +
                "shiny crimson bags contain 4 mirrored gray bags, 2 dull green bags.\n" +
                "muted purple bags contain 5 dull tomato bags.\n" +
                "light coral bags contain no other bags.\n" +
                "pale beige bags contain 3 dark indigo bags, 5 bright turquoise bags, 3 vibrant lavender bags, 1 pale magenta bag.\n" +
                "posh turquoise bags contain 4 faded green bags, 2 light coral bags, 1 light teal bag, 3 dim tomato bags.\n" +
                "clear aqua bags contain 2 wavy cyan bags, 1 muted yellow bag.\n" +
                "posh silver bags contain 4 plaid fuchsia bags, 2 dotted violet bags, 3 drab olive bags.\n" +
                "dark silver bags contain 4 vibrant purple bags, 1 vibrant cyan bag.\n" +
                "dull plum bags contain 3 dim red bags, 3 wavy turquoise bags, 2 mirrored beige bags.\n" +
                "muted magenta bags contain 3 faded lime bags, 2 muted crimson bags, 1 mirrored gray bag.\n" +
                "plaid indigo bags contain 4 wavy aqua bags, 1 plaid silver bag, 1 light blue bag, 3 wavy magenta bags.\n" +
                "wavy violet bags contain 2 striped bronze bags.\n" +
                "plaid yellow bags contain 3 dull plum bags, 1 striped blue bag, 5 faded aqua bags.\n" +
                "dotted bronze bags contain 3 bright lavender bags, 1 wavy lime bag, 2 dotted cyan bags, 1 mirrored beige bag.\n" +
                "striped coral bags contain 4 dark cyan bags, 4 wavy bronze bags, 2 dotted magenta bags.\n" +
                "posh tan bags contain 4 mirrored brown bags, 3 dotted teal bags, 5 light lime bags.\n" +
                "bright white bags contain 2 muted indigo bags, 2 wavy aqua bags.\n" +
                "posh cyan bags contain 4 muted tomato bags, 1 posh tan bag, 4 mirrored lavender bags, 2 drab blue bags.\n" +
                "wavy gold bags contain 1 dim beige bag, 5 dotted teal bags, 2 dotted turquoise bags.\n" +
                "mirrored cyan bags contain 2 drab silver bags, 5 muted orange bags, 5 mirrored olive bags.\n" +
                "dim orange bags contain 3 striped blue bags.\n" +
                "muted indigo bags contain 4 clear aqua bags, 2 mirrored tan bags, 2 clear green bags, 2 posh brown bags.\n" +
                "dull tomato bags contain 4 posh plum bags.\n" +
                "plaid orange bags contain 1 striped coral bag.\n" +
                "posh bronze bags contain 3 vibrant purple bags, 2 dim brown bags, 1 clear violet bag, 4 mirrored gold bags.\n" +
                "dotted chartreuse bags contain 3 dull crimson bags.\n" +
                "faded coral bags contain 1 wavy tomato bag.\n" +
                "pale salmon bags contain 2 dull purple bags, 2 dark tomato bags.\n" +
                "wavy crimson bags contain 4 bright fuchsia bags, 5 light lime bags, 1 posh tan bag.\n" +
                "pale fuchsia bags contain 2 wavy black bags, 1 muted crimson bag, 2 dotted aqua bags.\n" +
                "striped fuchsia bags contain 1 mirrored violet bag, 3 dull chartreuse bags, 1 shiny lavender bag.\n" +
                "plaid cyan bags contain 2 vibrant lavender bags.\n" +
                "dotted beige bags contain 4 dim beige bags, 2 vibrant lavender bags.\n" +
                "mirrored turquoise bags contain 4 mirrored beige bags, 3 dotted turquoise bags, 1 striped maroon bag.\n" +
                "faded indigo bags contain 3 dark lime bags, 5 mirrored salmon bags, 3 drab red bags.\n" +
                "dotted silver bags contain 3 dark yellow bags, 4 posh crimson bags.\n" +
                "light silver bags contain 2 wavy orange bags, 2 vibrant maroon bags, 4 wavy beige bags.\n" +
                "vibrant cyan bags contain 1 shiny fuchsia bag, 3 posh plum bags.\n" +
                "pale crimson bags contain 1 dotted gold bag, 4 mirrored brown bags, 1 mirrored olive bag.\n" +
                "vibrant beige bags contain 4 dull white bags.\n" +
                "faded tomato bags contain 4 mirrored salmon bags, 5 faded green bags.\n" +
                "dull maroon bags contain 5 mirrored olive bags, 4 dim crimson bags.\n" +
                "muted aqua bags contain 2 plaid olive bags, 1 shiny indigo bag.\n" +
                "shiny teal bags contain 4 striped bronze bags, 3 dotted aqua bags.\n" +
                "dotted cyan bags contain 3 clear yellow bags, 5 faded maroon bags, 3 dim gray bags.\n" +
                "wavy beige bags contain 1 posh cyan bag, 4 wavy magenta bags, 5 dim green bags.\n" +
                "bright silver bags contain 4 clear beige bags, 2 faded chartreuse bags, 4 clear aqua bags.\n" +
                "striped purple bags contain 2 clear chartreuse bags, 4 dotted magenta bags.\n" +
                "clear gold bags contain 2 vibrant lavender bags, 3 posh aqua bags, 4 light green bags, 3 pale cyan bags.\n" +
                "dull cyan bags contain 5 dim tomato bags, 1 dim orange bag.\n" +
                "light crimson bags contain 1 dull green bag.\n" +
                "faded crimson bags contain 4 plaid coral bags, 3 dotted orange bags.\n" +
                "pale gray bags contain 3 dark lime bags.\n" +
                "faded gold bags contain 1 muted crimson bag, 1 plaid red bag, 5 wavy cyan bags.\n" +
                "plaid plum bags contain 4 bright blue bags.\n" +
                "wavy tomato bags contain 5 posh brown bags, 2 striped maroon bags, 4 faded indigo bags, 3 clear gold bags.\n" +
                "shiny fuchsia bags contain 2 mirrored gray bags.\n" +
                "wavy magenta bags contain 2 posh olive bags.\n" +
                "dim purple bags contain 1 vibrant yellow bag.\n" +
                "wavy black bags contain 1 pale green bag.\n" +
                "wavy silver bags contain 3 dull yellow bags, 5 faded magenta bags, 4 mirrored black bags, 3 dull indigo bags.\n" +
                "shiny cyan bags contain 2 shiny salmon bags, 2 pale olive bags, 1 shiny red bag.\n" +
                "striped cyan bags contain 5 pale magenta bags, 3 faded silver bags.\n" +
                "dim salmon bags contain 2 vibrant aqua bags, 1 drab red bag.\n" +
                "bright chartreuse bags contain 1 shiny tomato bag, 2 wavy yellow bags, 2 wavy silver bags.\n" +
                "drab coral bags contain 1 drab silver bag.\n" +
                "pale white bags contain 2 faded red bags, 4 pale gray bags, 2 mirrored lavender bags.\n" +
                "dim violet bags contain 3 dull white bags.\n" +
                "muted red bags contain 3 shiny lime bags, 3 plaid beige bags, 2 shiny aqua bags.\n" +
                "bright teal bags contain 3 dim tomato bags, 3 mirrored turquoise bags, 5 vibrant yellow bags.\n" +
                "striped maroon bags contain 1 dim plum bag, 2 wavy cyan bags, 1 dull beige bag.\n" +
                "vibrant green bags contain 5 dotted salmon bags, 4 mirrored olive bags.\n" +
                "dim tan bags contain 2 shiny white bags, 3 posh lime bags, 1 dotted teal bag.\n" +
                "muted olive bags contain 4 mirrored chartreuse bags.\n" +
                "muted salmon bags contain 1 clear salmon bag.\n" +
                "dim beige bags contain 2 pale gray bags, 2 mirrored olive bags, 3 clear yellow bags.\n" +
                "light tomato bags contain 1 pale white bag, 2 dark teal bags, 5 shiny lime bags.\n" +
                "striped beige bags contain 4 bright plum bags, 1 plaid olive bag, 2 pale crimson bags.\n" +
                "light indigo bags contain 4 bright fuchsia bags, 1 dull gold bag, 5 dark beige bags, 3 mirrored teal bags.\n" +
                "drab gray bags contain 3 dark coral bags, 5 mirrored teal bags, 5 dim purple bags, 4 mirrored violet bags.\n" +
                "dark teal bags contain 2 muted orange bags.\n" +
                "dim plum bags contain 1 posh brown bag, 4 dotted orange bags, 3 dotted gold bags, 2 drab salmon bags.\n" +
                "dim green bags contain 3 dark beige bags, 3 bright tan bags, 4 striped white bags, 5 faded white bags.\n" +
                "drab teal bags contain 1 light gray bag.\n" +
                "dark aqua bags contain 5 vibrant green bags, 3 shiny teal bags, 3 dull red bags.\n" +
                "wavy lime bags contain 5 mirrored brown bags, 2 dark cyan bags, 4 dull green bags, 1 wavy cyan bag.\n" +
                "muted violet bags contain 3 light plum bags.\n" +
                "posh green bags contain 2 shiny fuchsia bags, 3 posh aqua bags, 5 dotted turquoise bags.\n" +
                "clear blue bags contain 4 vibrant gray bags, 4 shiny plum bags.\n" +
                "muted brown bags contain 1 dark lime bag, 1 mirrored olive bag, 1 faded beige bag.\n" +
                "dotted black bags contain 5 pale beige bags, 1 dotted tomato bag, 2 wavy cyan bags, 3 wavy aqua bags.\n" +
                "muted chartreuse bags contain 5 muted lavender bags, 5 clear aqua bags, 5 bright maroon bags, 4 plaid bronze bags.\n" +
                "bright purple bags contain 4 bright fuchsia bags, 3 pale tomato bags, 5 mirrored tomato bags.\n" +
                "drab orange bags contain 5 light crimson bags, 4 dark beige bags.\n" +
                "clear white bags contain 2 shiny salmon bags.\n" +
                "posh lavender bags contain 4 posh tan bags, 1 posh magenta bag.\n" +
                "faded purple bags contain 2 dull maroon bags, 2 mirrored coral bags, 4 vibrant green bags.\n" +
                "dotted olive bags contain 5 dull chartreuse bags, 2 wavy magenta bags, 1 pale tan bag, 3 dim turquoise bags.\n" +
                "muted crimson bags contain 1 striped olive bag, 3 dull chartreuse bags, 1 light bronze bag.\n" +
                "striped turquoise bags contain 2 light blue bags.\n" +
                "bright indigo bags contain 3 dull green bags, 1 pale teal bag.\n" +
                "drab purple bags contain 3 bright aqua bags, 4 dull tan bags, 2 mirrored indigo bags.\n" +
                "wavy salmon bags contain 4 dark olive bags, 5 dull chartreuse bags, 3 vibrant purple bags, 1 pale lime bag.\n" +
                "drab olive bags contain 5 dim crimson bags, 3 posh green bags, 3 clear brown bags, 1 posh teal bag.\n" +
                "muted orange bags contain 5 shiny orange bags, 3 muted black bags, 5 bright blue bags, 1 mirrored olive bag.\n" +
                "drab salmon bags contain 5 light coral bags.\n" +
                "dark salmon bags contain 4 bright lavender bags, 5 clear gray bags.\n" +
                "posh blue bags contain 1 drab coral bag, 3 faded green bags.\n" +
                "shiny violet bags contain 1 posh yellow bag, 4 light turquoise bags, 4 drab silver bags, 2 muted teal bags.\n" +
                "clear tomato bags contain 3 bright plum bags, 3 muted indigo bags, 2 muted maroon bags, 3 dotted cyan bags.\n" +
                "dotted coral bags contain 4 dark tomato bags, 5 dotted magenta bags, 5 wavy gold bags.\n" +
                "striped teal bags contain 1 plaid brown bag, 2 bright plum bags, 1 mirrored blue bag.\n" +
                "pale maroon bags contain 4 posh gold bags, 5 striped magenta bags, 2 dim brown bags, 1 clear chartreuse bag.\n" +
                "muted lime bags contain 5 dull lavender bags, 3 plaid gray bags, 3 shiny tomato bags, 4 drab tomato bags.\n" +
                "mirrored green bags contain 2 striped purple bags.\n" +
                "posh chartreuse bags contain 4 dull tan bags, 2 plaid coral bags, 1 plaid cyan bag, 4 clear bronze bags.\n" +
                "shiny bronze bags contain 3 striped indigo bags, 4 mirrored white bags, 1 faded coral bag, 1 clear violet bag.\n" +
                "dull olive bags contain 1 muted coral bag, 3 clear tomato bags, 3 bright tan bags.\n" +
                "light maroon bags contain 5 mirrored tomato bags.\n" +
                "light chartreuse bags contain 3 dark indigo bags, 2 mirrored coral bags.\n" +
                "light bronze bags contain 1 bright yellow bag.\n" +
                "dim chartreuse bags contain 3 plaid gray bags, 2 faded teal bags.\n" +
                "dotted lavender bags contain 3 bright teal bags, 3 vibrant green bags.\n" +
                "dark tan bags contain 5 posh black bags.\n" +
                "muted blue bags contain 2 mirrored salmon bags, 4 striped olive bags.\n" +
                "mirrored chartreuse bags contain 2 bright turquoise bags.\n" +
                "clear cyan bags contain 5 dotted gold bags, 3 shiny fuchsia bags, 3 dim violet bags.\n" +
                "light white bags contain 2 vibrant bronze bags.\n" +
                "pale orange bags contain 3 plaid aqua bags.\n" +
                "faded brown bags contain 2 shiny blue bags, 1 dark cyan bag.\n" +
                "plaid salmon bags contain 5 wavy yellow bags, 3 faded aqua bags, 1 dull yellow bag.\n" +
                "shiny blue bags contain 2 bright violet bags, 3 light lime bags, 5 clear blue bags.\n" +
                "dull orange bags contain 4 faded turquoise bags, 2 dim lavender bags.\n" +
                "dull gold bags contain 2 dotted gray bags.\n" +
                "vibrant tan bags contain 2 posh turquoise bags, 2 muted orange bags, 4 plaid blue bags.\n" +
                "shiny white bags contain 1 pale chartreuse bag, 4 dark tomato bags.\n" +
                "light olive bags contain 3 striped bronze bags, 3 posh white bags.\n" +
                "dark lavender bags contain 3 drab magenta bags, 3 plaid black bags, 2 dull red bags.\n" +
                "shiny turquoise bags contain 1 vibrant black bag, 5 wavy fuchsia bags.\n" +
                "clear fuchsia bags contain 2 dull aqua bags, 2 shiny coral bags.\n" +
                "shiny coral bags contain 2 pale magenta bags, 4 muted bronze bags, 4 light coral bags.\n" +
                "striped salmon bags contain 3 posh olive bags.\n" +
                "shiny olive bags contain 5 pale chartreuse bags.\n" +
                "dotted brown bags contain 1 mirrored brown bag.\n" +
                "dull red bags contain 1 faded chartreuse bag, 1 dull chartreuse bag, 5 clear lavender bags, 5 wavy salmon bags.\n" +
                "bright beige bags contain 1 posh crimson bag, 1 posh chartreuse bag.\n" +
                "plaid crimson bags contain 5 wavy magenta bags, 4 muted lime bags, 1 muted maroon bag, 3 dim green bags.\n" +
                "wavy maroon bags contain 1 wavy cyan bag, 1 bright turquoise bag, 4 shiny coral bags, 3 drab beige bags.\n" +
                "vibrant fuchsia bags contain 2 dull tomato bags, 1 light coral bag.\n" +
                "pale brown bags contain 5 striped gray bags, 1 light plum bag, 1 pale chartreuse bag, 2 shiny tomato bags.\n" +
                "light plum bags contain 4 bright yellow bags, 2 dark tomato bags, 4 plaid coral bags.\n" +
                "dull gray bags contain 1 mirrored olive bag.\n" +
                "light yellow bags contain 5 bright magenta bags, 5 pale gray bags, 4 mirrored red bags.\n" +
                "dull lime bags contain 3 dark indigo bags, 3 mirrored salmon bags, 5 striped white bags, 3 posh aqua bags.\n" +
                "drab yellow bags contain 4 shiny white bags, 3 faded green bags.\n" +
                "bright gray bags contain 5 drab olive bags.\n" +
                "bright coral bags contain 3 mirrored brown bags, 5 dim bronze bags, 5 muted white bags, 1 dim crimson bag.\n" +
                "dim maroon bags contain 1 vibrant red bag, 3 faded fuchsia bags, 3 vibrant blue bags.\n" +
                "plaid turquoise bags contain 3 bright yellow bags, 5 dark purple bags.\n" +
                "plaid violet bags contain 1 clear yellow bag, 2 posh violet bags, 3 mirrored bronze bags, 3 vibrant tan bags.\n" +
                "dotted maroon bags contain 2 striped fuchsia bags, 3 striped white bags, 2 light blue bags, 5 plaid cyan bags.\n" +
                "dull blue bags contain 1 wavy brown bag.\n" +
                "drab tan bags contain 1 clear indigo bag, 4 faded violet bags, 3 pale cyan bags.\n" +
                "posh maroon bags contain 5 striped aqua bags, 3 wavy coral bags, 3 mirrored olive bags.\n" +
                "dim blue bags contain 4 dotted maroon bags, 5 striped black bags, 5 plaid gray bags.\n" +
                "faded teal bags contain 3 clear indigo bags, 5 dark beige bags, 1 shiny cyan bag, 3 bright tomato bags.\n" +
                "clear bronze bags contain 1 muted yellow bag.\n" +
                "dark plum bags contain 3 faded white bags, 2 wavy brown bags, 5 pale white bags.\n" +
                "wavy cyan bags contain 3 mirrored brown bags, 5 faded silver bags, 1 dim lime bag, 5 clear indigo bags.\n" +
                "posh plum bags contain no other bags.\n" +
                "wavy plum bags contain 4 dull indigo bags, 3 faded black bags, 4 bright turquoise bags, 5 bright red bags.\n" +
                "dim red bags contain 2 light cyan bags.\n" +
                "pale coral bags contain 2 wavy brown bags, 1 dull gray bag.\n" +
                "posh teal bags contain 2 striped purple bags, 1 dotted gray bag.\n" +
                "clear tan bags contain 5 striped maroon bags.\n" +
                "wavy chartreuse bags contain 5 bright chartreuse bags.\n" +
                "posh black bags contain 2 mirrored gray bags, 3 mirrored beige bags, 2 clear aqua bags, 2 bright plum bags.\n" +
                "muted maroon bags contain 5 vibrant red bags, 1 plaid lavender bag.\n" +
                "vibrant silver bags contain 1 mirrored salmon bag, 5 clear lime bags, 4 shiny violet bags.\n" +
                "pale indigo bags contain 4 clear maroon bags, 4 wavy cyan bags.\n" +
                "mirrored salmon bags contain 4 pale gray bags, 4 dim beige bags.\n" +
                "pale lavender bags contain 1 wavy turquoise bag, 2 striped green bags, 4 light coral bags, 4 clear chartreuse bags.\n" +
                "drab beige bags contain no other bags.\n" +
                "wavy coral bags contain 3 muted cyan bags, 3 dull bronze bags.\n" +
                "plaid black bags contain 2 posh orange bags, 4 shiny tan bags, 5 mirrored brown bags, 3 shiny plum bags.\n" +
                "light gray bags contain 2 dim crimson bags, 1 faded salmon bag.\n" +
                "shiny plum bags contain 2 dull tomato bags, 1 mirrored tan bag, 2 dull green bags, 1 dark lime bag.\n" +
                "drab blue bags contain 5 mirrored chartreuse bags, 5 dull tomato bags, 2 mirrored beige bags, 4 posh plum bags.\n" +
                "faded orange bags contain 5 striped yellow bags, 1 drab gray bag, 5 vibrant purple bags, 3 muted fuchsia bags.\n" +
                "plaid purple bags contain 1 faded silver bag, 4 shiny gold bags, 4 dim cyan bags.\n" +
                "pale tan bags contain 3 shiny gold bags, 2 shiny lavender bags, 3 pale cyan bags.\n" +
                "bright crimson bags contain 5 clear cyan bags.\n" +
                "dotted lime bags contain 2 posh violet bags, 5 striped beige bags, 4 bright teal bags.\n" +
                "posh purple bags contain 5 mirrored silver bags, 1 dim tan bag, 1 pale white bag.\n" +
                "vibrant red bags contain 3 pale chartreuse bags, 2 dim purple bags, 4 wavy maroon bags, 1 dull gray bag.\n" +
                "light fuchsia bags contain 1 drab maroon bag, 2 faded violet bags.\n" +
                "bright blue bags contain 4 drab salmon bags, 4 dotted gold bags.\n" +
                "bright turquoise bags contain 2 dotted gold bags, 2 dim violet bags, 1 vibrant bronze bag.\n" +
                "light orange bags contain 1 plaid olive bag.\n" +
                "light gold bags contain 2 clear plum bags, 2 striped bronze bags, 4 dark yellow bags, 2 posh orange bags.\n" +
                "dotted magenta bags contain 4 wavy cyan bags.\n" +
                "muted teal bags contain 1 clear beige bag, 4 plaid brown bags, 2 posh plum bags.\n" +
                "light black bags contain 3 drab bronze bags, 2 plaid silver bags, 3 mirrored coral bags.\n" +
                "wavy turquoise bags contain 5 dotted orange bags.\n" +
                "muted fuchsia bags contain 3 dull black bags, 1 striped coral bag, 1 bright magenta bag.\n" +
                "posh white bags contain 5 bright blue bags, 2 pale gray bags, 2 dark lime bags, 4 dotted gold bags.\n" +
                "striped black bags contain 5 drab maroon bags, 1 faded green bag, 2 mirrored silver bags, 2 shiny white bags.\n" +
                "striped gold bags contain 4 dim gray bags.\n" +
                "dull violet bags contain 3 light plum bags, 4 faded turquoise bags, 3 mirrored cyan bags.\n" +
                "striped red bags contain 5 plaid orange bags, 1 clear plum bag, 2 vibrant lavender bags.\n" +
                "bright cyan bags contain 1 dim brown bag, 4 mirrored turquoise bags.\n" +
                "dim cyan bags contain 5 bright turquoise bags.\n" +
                "shiny chartreuse bags contain 2 striped white bags.\n" +
                "vibrant blue bags contain 5 drab tan bags, 4 dotted magenta bags.\n" +
                "mirrored aqua bags contain 3 shiny gold bags, 3 drab brown bags, 3 faded black bags, 3 clear tan bags.\n" +
                "dark cyan bags contain 4 pale gray bags, 1 dotted gold bag, 4 dotted orange bags, 4 pale magenta bags.\n" +
                "wavy fuchsia bags contain 5 mirrored teal bags, 1 dotted gray bag.\n" +
                "pale gold bags contain 3 posh lavender bags, 2 drab brown bags.\n" +
                "shiny orange bags contain 2 dim gray bags.\n" +
                "posh tomato bags contain 5 bright brown bags.\n" +
                "clear orange bags contain 4 posh plum bags, 5 dull gray bags.\n" +
                "pale purple bags contain 1 drab purple bag, 5 bright crimson bags.\n" +
                "drab brown bags contain 1 bright red bag, 3 light turquoise bags, 1 posh plum bag, 2 dull chartreuse bags.\n" +
                "shiny tan bags contain 2 dotted gold bags, 2 plaid orange bags, 3 clear cyan bags.\n" +
                "faded cyan bags contain 2 wavy cyan bags, 2 posh teal bags.\n" +
                "clear maroon bags contain 2 light coral bags.\n" +
                "drab cyan bags contain 3 wavy bronze bags, 5 posh aqua bags, 4 dull crimson bags, 1 shiny gold bag.\n" +
                "plaid white bags contain 4 wavy brown bags.\n" +
                "bright tan bags contain 2 shiny silver bags.\n" +
                "drab turquoise bags contain 2 faded beige bags, 4 vibrant lavender bags.\n" +
                "faded yellow bags contain 2 dull black bags, 3 vibrant yellow bags, 4 plaid silver bags, 3 posh teal bags.\n" +
                "bright lime bags contain 3 striped aqua bags, 4 dull tomato bags, 5 dim gray bags.\n" +
                "plaid tan bags contain 2 plaid coral bags, 5 drab blue bags, 4 drab orange bags.\n" +
                "clear purple bags contain 2 drab tomato bags, 3 mirrored bronze bags, 2 dark teal bags, 4 dull green bags.\n" +
                "wavy red bags contain 4 faded tan bags, 4 wavy cyan bags.\n" +
                "vibrant purple bags contain 5 mirrored salmon bags, 1 dark salmon bag.\n" +
                "wavy tan bags contain 1 shiny olive bag, 2 faded maroon bags.\n" +
                "vibrant gray bags contain 1 mirrored olive bag, 2 dotted turquoise bags, 4 striped chartreuse bags, 1 dull tomato bag.\n" +
                "mirrored gray bags contain 4 bright blue bags, 1 striped olive bag.\n" +
                "dull aqua bags contain 1 vibrant purple bag, 1 faded maroon bag, 3 muted yellow bags.\n" +
                "clear red bags contain 1 posh violet bag.\n" +
                "light purple bags contain 2 shiny olive bags, 2 striped yellow bags, 4 dotted turquoise bags, 2 shiny violet bags.\n" +
                "dull brown bags contain 2 wavy red bags, 4 clear green bags.\n" +
                "wavy bronze bags contain 2 dull tomato bags.\n" +
                "muted beige bags contain 5 wavy aqua bags, 4 striped maroon bags, 2 drab tan bags.\n" +
                "muted black bags contain 1 posh green bag, 1 clear green bag, 5 striped bronze bags.\n" +
                "light green bags contain 4 dim lime bags, 1 shiny gold bag, 3 shiny crimson bags, 2 muted yellow bags.\n" +
                "mirrored fuchsia bags contain 1 dull bronze bag.\n" +
                "pale tomato bags contain 2 vibrant maroon bags, 2 muted orange bags, 1 wavy brown bag, 2 dull cyan bags.\n" +
                "drab chartreuse bags contain 5 mirrored magenta bags.\n" +
                "shiny indigo bags contain 4 pale teal bags, 3 dull silver bags, 5 mirrored olive bags.\n" +
                "bright aqua bags contain 3 bright violet bags.\n" +
                "shiny magenta bags contain 2 bright red bags.\n" +
                "vibrant orange bags contain 3 mirrored black bags, 5 mirrored olive bags.\n" +
                "mirrored indigo bags contain 4 vibrant coral bags, 1 mirrored crimson bag, 2 mirrored red bags.\n" +
                "plaid blue bags contain 5 wavy aqua bags, 2 plaid fuchsia bags, 3 wavy purple bags, 1 dim lime bag.\n" +
                "dotted plum bags contain 4 striped chartreuse bags, 4 dotted yellow bags, 4 light yellow bags, 3 striped red bags.\n" +
                "light violet bags contain 5 shiny chartreuse bags, 1 wavy lime bag, 1 vibrant beige bag.\n" +
                "dotted salmon bags contain 2 drab maroon bags, 5 muted black bags, 1 posh violet bag, 1 dull lime bag.\n" +
                "vibrant gold bags contain 5 dull magenta bags, 1 clear aqua bag, 3 mirrored beige bags, 1 pale cyan bag.\n" +
                "mirrored lavender bags contain 3 clear gold bags.\n" +
                "dotted gold bags contain 1 drab beige bag, 1 mirrored crimson bag.\n" +
                "bright magenta bags contain 1 striped maroon bag, 1 striped bronze bag, 5 mirrored beige bags, 1 dotted maroon bag.\n" +
                "clear black bags contain 1 faded chartreuse bag, 5 light blue bags.\n" +
                "bright tomato bags contain 3 mirrored tan bags, 2 mirrored olive bags, 3 muted bronze bags, 1 posh violet bag.\n" +
                "mirrored violet bags contain 3 striped chartreuse bags, 3 dim lime bags, 5 pale chartreuse bags, 1 posh tan bag.\n" +
                "faded gray bags contain 4 light crimson bags, 3 muted turquoise bags.\n" +
                "dark red bags contain 5 muted green bags, 4 mirrored maroon bags.\n" +
                "clear crimson bags contain 2 muted olive bags, 5 striped chartreuse bags.\n" +
                "vibrant lime bags contain 1 dim crimson bag, 2 faded coral bags, 3 light crimson bags.\n" +
                "vibrant bronze bags contain no other bags.\n" +
                "dotted white bags contain 4 dim purple bags, 2 mirrored chartreuse bags.\n" +
                "clear indigo bags contain 3 mirrored olive bags, 2 posh brown bags.\n" +
                "pale lime bags contain 5 faded salmon bags, 5 striped tan bags, 4 light white bags, 3 pale chartreuse bags.\n" +
                "vibrant coral bags contain 1 muted orange bag.\n" +
                "clear teal bags contain 1 posh plum bag.\n" +
                "wavy white bags contain 2 dim chartreuse bags, 1 dull tomato bag, 1 dull fuchsia bag, 4 faded silver bags.\n" +
                "dull yellow bags contain 2 mirrored gray bags, 2 shiny olive bags, 4 clear beige bags, 2 dim crimson bags.\n" +
                "posh fuchsia bags contain 3 dull teal bags.\n" +
                "dotted teal bags contain 3 dull white bags, 5 pale gray bags.\n" +
                "vibrant tomato bags contain 4 dull green bags.\n" +
                "dull purple bags contain 3 shiny red bags, 1 dim crimson bag, 5 drab turquoise bags.\n" +
                "vibrant aqua bags contain 1 bright cyan bag.\n" +
                "clear plum bags contain 5 drab magenta bags, 2 mirrored salmon bags.\n" +
                "dark tomato bags contain 1 dull beige bag, 1 faded violet bag.\n" +
                "bright violet bags contain 3 dim lime bags, 4 muted cyan bags, 4 dull gold bags.\n" +
                "dim teal bags contain 3 mirrored orange bags.\n" +
                "dark magenta bags contain 2 light tan bags, 5 bright violet bags, 4 dim aqua bags, 5 bright teal bags.\n" +
                "faded silver bags contain 2 dotted gold bags.\n" +
                "light lavender bags contain 5 mirrored chartreuse bags.\n" +
                "vibrant chartreuse bags contain 1 pale indigo bag, 2 drab tan bags, 2 striped bronze bags.\n" +
                "dotted blue bags contain 3 faded gray bags, 3 muted coral bags, 4 drab red bags.\n" +
                "drab lavender bags contain 1 dull bronze bag.\n" +
                "wavy green bags contain 2 vibrant brown bags.\n" +
                "bright gold bags contain 5 shiny blue bags.\n" +
                "dull lavender bags contain 4 vibrant beige bags, 4 wavy purple bags.\n" +
                "shiny tomato bags contain 5 plaid olive bags.\n" +
                "faded aqua bags contain 5 mirrored olive bags.\n" +
                "clear beige bags contain 4 shiny fuchsia bags, 5 wavy bronze bags.\n" +
                "pale black bags contain 1 shiny lavender bag, 4 muted olive bags, 3 striped gold bags.\n" +
                "faded beige bags contain 3 clear yellow bags, 1 bright turquoise bag.\n" +
                "dull salmon bags contain 1 plaid tomato bag, 1 dull coral bag, 4 dotted coral bags.\n" +
                "drab aqua bags contain 5 muted olive bags.\n" +
                "plaid fuchsia bags contain 2 wavy lime bags, 3 mirrored gray bags, 3 clear crimson bags.\n" +
                "pale turquoise bags contain 3 bright cyan bags, 3 posh blue bags, 3 dark olive bags.\n" +
                "dotted red bags contain 3 pale black bags, 3 light blue bags, 4 clear gold bags, 4 pale olive bags.\n" +
                "dark olive bags contain 5 shiny coral bags.\n" +
                "dull white bags contain no other bags.\n" +
                "shiny silver bags contain 3 posh plum bags, 4 posh brown bags, 4 dull chartreuse bags, 1 drab beige bag.\n" +
                "muted gray bags contain 5 posh coral bags.\n" +
                "light magenta bags contain 3 vibrant cyan bags, 1 striped tan bag, 5 dark maroon bags, 2 wavy cyan bags.\n" +
                "dim brown bags contain 3 dull green bags, 2 dull beige bags, 2 wavy gold bags, 5 wavy bronze bags.\n" +
                "vibrant brown bags contain 1 striped violet bag, 2 dull beige bags.\n" +
                "pale chartreuse bags contain 5 shiny gold bags, 4 dotted gold bags.\n" +
                "vibrant plum bags contain 4 mirrored gray bags, 1 bright silver bag, 3 bright cyan bags, 2 dull gray bags.\n" +
                "muted bronze bags contain 2 mirrored beige bags.\n" +
                "plaid green bags contain 3 striped tan bags, 4 drab maroon bags.\n" +
                "dark orange bags contain 4 drab beige bags, 1 striped beige bag, 1 dim indigo bag.\n" +
                "bright olive bags contain 4 bright turquoise bags.\n" +
                "drab indigo bags contain 4 striped tan bags, 5 dotted gold bags, 4 shiny crimson bags, 2 dull chartreuse bags.\n" +
                "light teal bags contain 5 dotted orange bags, 3 posh brown bags, 3 shiny gold bags.\n" +
                "dotted crimson bags contain 5 posh turquoise bags, 1 clear salmon bag, 5 faded salmon bags.\n" +
                "shiny red bags contain 4 bright blue bags, 2 posh lime bags, 5 shiny plum bags.\n" +
                "dark brown bags contain 5 posh magenta bags.\n" +
                "drab violet bags contain 5 posh indigo bags, 2 light chartreuse bags, 2 vibrant crimson bags.\n" +
                "plaid beige bags contain 3 drab olive bags, 5 dim fuchsia bags, 5 wavy turquoise bags.\n" +
                "bright plum bags contain 2 mirrored salmon bags, 4 faded tan bags.\n" +
                "plaid lavender bags contain 2 striped coral bags, 1 light coral bag.\n" +
                "shiny brown bags contain 2 wavy tan bags, 1 pale salmon bag, 2 clear bronze bags.\n" +
                "clear turquoise bags contain 2 dotted beige bags.\n" +
                "wavy orange bags contain 4 shiny green bags, 1 pale magenta bag, 4 dark plum bags, 4 mirrored olive bags.\n" +
                "vibrant turquoise bags contain 3 dotted black bags, 4 wavy salmon bags.\n" +
                "posh olive bags contain 1 dull black bag.\n" +
                "pale red bags contain 5 plaid tomato bags.\n" +
                "dim gold bags contain 5 wavy violet bags.\n" +
                "dotted fuchsia bags contain 4 pale plum bags, 2 dim salmon bags, 2 clear salmon bags, 4 vibrant blue bags.\n" +
                "wavy indigo bags contain 3 muted fuchsia bags, 1 drab maroon bag.\n" +
                "clear chartreuse bags contain 3 mirrored olive bags, 1 posh yellow bag, 1 faded salmon bag, 5 drab salmon bags.\n" +
                "drab red bags contain 2 faded lime bags, 3 bright fuchsia bags, 5 bright olive bags, 2 striped maroon bags.\n" +
                "dotted turquoise bags contain 5 dull white bags, 3 bright olive bags, 4 clear crimson bags.\n" +
                "muted coral bags contain 1 posh green bag, 4 dim violet bags, 2 faded silver bags, 3 dim plum bags.\n" +
                "plaid olive bags contain 2 striped cyan bags, 4 drab red bags, 2 clear beige bags, 5 plaid coral bags.\n" +
                "vibrant indigo bags contain 1 vibrant maroon bag.\n" +
                "clear olive bags contain 3 vibrant purple bags, 3 plaid green bags.\n" +
                "light blue bags contain 5 plaid lavender bags, 1 mirrored violet bag, 4 muted yellow bags.\n" +
                "muted turquoise bags contain 3 plaid brown bags, 5 clear aqua bags, 5 drab silver bags.\n" +
                "muted cyan bags contain 5 shiny fuchsia bags.\n" +
                "plaid aqua bags contain 3 posh green bags, 2 dull crimson bags.\n" +
                "dull teal bags contain 2 drab red bags.\n" +
                "dim olive bags contain 3 dark plum bags.\n" +
                "bright black bags contain 4 shiny salmon bags, 3 dull white bags, 4 clear brown bags.\n" +
                "mirrored maroon bags contain 1 drab turquoise bag, 2 plaid red bags, 3 faded black bags.\n" +
                "pale plum bags contain 1 dark salmon bag.\n" +
                "posh magenta bags contain 4 bright tan bags.\n" +
                "faded tan bags contain 1 pale magenta bag, 1 drab beige bag, 2 dull beige bags, 1 shiny silver bag.\n" +
                "dark coral bags contain 4 shiny orange bags, 2 dull green bags, 2 dim crimson bags.\n" +
                "plaid silver bags contain 2 dim black bags, 2 mirrored tan bags, 4 shiny plum bags, 4 wavy teal bags.\n" +
                "dim gray bags contain 3 dull green bags, 3 dull white bags, 2 clear yellow bags.\n" +
                "clear magenta bags contain 5 pale chartreuse bags, 5 light magenta bags, 4 mirrored gray bags.\n" +
                "posh coral bags contain 1 muted plum bag, 4 dim turquoise bags.\n" +
                "dim yellow bags contain 3 striped white bags.\n" +
                "mirrored tan bags contain 1 dim cyan bag, 3 wavy purple bags.\n" +
                "vibrant olive bags contain 5 striped fuchsia bags, 3 shiny silver bags.\n" +
                "pale cyan bags contain 5 vibrant bronze bags, 2 clear crimson bags, 1 dim violet bag.\n" +
                "vibrant teal bags contain 1 pale white bag, 5 clear gold bags, 2 striped white bags.\n" +
                "shiny gold bags contain 1 shiny coral bag, 5 posh white bags, 3 wavy cyan bags.\n" +
                "dim lime bags contain 1 bright turquoise bag.\n" +
                "posh salmon bags contain 1 dotted aqua bag, 3 faded cyan bags.\n" +
                "vibrant yellow bags contain 4 dim violet bags, 2 striped bronze bags, 3 clear crimson bags.\n" +
                "dull magenta bags contain 5 clear green bags.\n" +
                "bright orange bags contain 1 bright indigo bag, 3 clear tan bags, 5 dark brown bags.\n" +
                "mirrored blue bags contain 3 dotted aqua bags, 4 dotted magenta bags.\n" +
                "clear salmon bags contain 5 striped chartreuse bags, 5 drab brown bags.\n" +
                "light tan bags contain 3 drab orange bags, 5 posh purple bags, 3 faded plum bags.\n" +
                "dark purple bags contain 2 dim plum bags, 4 dark beige bags, 1 dull tomato bag, 4 dull gray bags.\n" +
                "shiny maroon bags contain 3 dotted aqua bags.\n" +
                "faded lime bags contain 2 light turquoise bags.\n" +
                "mirrored yellow bags contain 4 muted orange bags, 4 shiny black bags, 1 shiny green bag.\n" +
                "bright yellow bags contain 1 plaid bronze bag, 2 shiny gold bags.\n" +
                "striped crimson bags contain 5 dotted black bags.").split("\n"));
    }
}
