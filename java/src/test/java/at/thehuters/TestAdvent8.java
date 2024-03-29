package at.thehuters;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


class TestAdvent8 {

    int riddle1(List<String> input){
        Set<Integer> visitedLines = new HashSet<>();
        int accumulatorState = 0;
        int currentState = 0;
        while(!visitedLines.contains(currentState)){
            visitedLines.add(currentState);
            String[] command = input.get(currentState).split(" ");
            var parameter = Integer.parseInt(command[1]);
            switch (command[0]){
                case "acc":
                    accumulatorState+=parameter;
                    currentState++;
                    break;
                case "jmp":
                    currentState+=parameter;
                    break;
                case "nop":
                    currentState++;
                    break;
                default:
                    throw new RuntimeException("Shit append");
            }
        }
        return accumulatorState;
    }



    @Test
    void testCase0(){
        System.out.println(riddle1(data2()));
    }

    @Test
    void testCase1(){
        System.out.println(riddle1(data()));
    }



    static List<String> data2(){
        return Arrays.asList(("nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6").split("\n"));
    }


    static List<String> data(){
        return Arrays.asList(("nop +116\n" +
                "acc +12\n" +
                "acc -8\n" +
                "acc +34\n" +
                "jmp +485\n" +
                "acc +42\n" +
                "jmp +388\n" +
                "acc +36\n" +
                "nop +605\n" +
                "acc +17\n" +
                "jmp +411\n" +
                "acc +49\n" +
                "jmp +1\n" +
                "acc -9\n" +
                "jmp +289\n" +
                "jmp +288\n" +
                "jmp +74\n" +
                "acc +4\n" +
                "acc +42\n" +
                "jmp +258\n" +
                "acc +14\n" +
                "acc -13\n" +
                "nop +106\n" +
                "jmp +280\n" +
                "jmp +534\n" +
                "acc +41\n" +
                "acc +40\n" +
                "jmp +224\n" +
                "acc +43\n" +
                "acc +10\n" +
                "nop +240\n" +
                "jmp +211\n" +
                "acc +7\n" +
                "acc -3\n" +
                "acc +7\n" +
                "jmp +1\n" +
                "jmp +559\n" +
                "jmp +415\n" +
                "jmp +528\n" +
                "acc -16\n" +
                "jmp +568\n" +
                "jmp +442\n" +
                "nop +113\n" +
                "jmp +464\n" +
                "acc +42\n" +
                "jmp +336\n" +
                "acc -2\n" +
                "acc +39\n" +
                "jmp +251\n" +
                "acc -4\n" +
                "acc +42\n" +
                "jmp +528\n" +
                "acc +5\n" +
                "acc +30\n" +
                "nop +429\n" +
                "acc +49\n" +
                "jmp +86\n" +
                "acc +15\n" +
                "nop +145\n" +
                "acc -8\n" +
                "jmp +1\n" +
                "jmp +404\n" +
                "acc +26\n" +
                "acc +50\n" +
                "jmp +251\n" +
                "acc +47\n" +
                "jmp +1\n" +
                "acc +45\n" +
                "acc -5\n" +
                "jmp +357\n" +
                "acc +31\n" +
                "jmp +62\n" +
                "acc +25\n" +
                "nop +540\n" +
                "acc -13\n" +
                "acc +0\n" +
                "jmp +72\n" +
                "acc +28\n" +
                "acc +36\n" +
                "nop +475\n" +
                "acc -17\n" +
                "jmp +166\n" +
                "acc +4\n" +
                "acc +20\n" +
                "acc +30\n" +
                "acc +43\n" +
                "jmp +464\n" +
                "acc +4\n" +
                "jmp +94\n" +
                "jmp +44\n" +
                "nop +446\n" +
                "acc -16\n" +
                "nop +267\n" +
                "acc +30\n" +
                "jmp +519\n" +
                "acc +45\n" +
                "acc +47\n" +
                "jmp +62\n" +
                "acc +28\n" +
                "acc -13\n" +
                "acc +45\n" +
                "jmp +239\n" +
                "acc +12\n" +
                "jmp +1\n" +
                "nop +153\n" +
                "jmp +245\n" +
                "jmp +244\n" +
                "acc -12\n" +
                "jmp +308\n" +
                "jmp +190\n" +
                "jmp -86\n" +
                "acc +45\n" +
                "acc +1\n" +
                "acc +15\n" +
                "acc +30\n" +
                "jmp +350\n" +
                "acc +30\n" +
                "jmp +42\n" +
                "jmp +214\n" +
                "jmp +447\n" +
                "acc +24\n" +
                "jmp +453\n" +
                "acc +29\n" +
                "acc +42\n" +
                "jmp +302\n" +
                "acc -4\n" +
                "acc +33\n" +
                "jmp +447\n" +
                "acc -18\n" +
                "acc +15\n" +
                "acc -2\n" +
                "jmp -24\n" +
                "jmp -4\n" +
                "jmp +35\n" +
                "acc +0\n" +
                "jmp -83\n" +
                "acc -13\n" +
                "nop +437\n" +
                "acc -15\n" +
                "jmp +95\n" +
                "nop +289\n" +
                "jmp +348\n" +
                "acc +17\n" +
                "acc +23\n" +
                "acc +45\n" +
                "jmp +359\n" +
                "acc +18\n" +
                "jmp +352\n" +
                "acc +0\n" +
                "acc +13\n" +
                "acc +25\n" +
                "acc +11\n" +
                "jmp +331\n" +
                "acc -2\n" +
                "jmp +19\n" +
                "jmp -103\n" +
                "acc +34\n" +
                "acc +48\n" +
                "jmp +141\n" +
                "acc +44\n" +
                "jmp +1\n" +
                "acc +42\n" +
                "jmp +374\n" +
                "acc +45\n" +
                "acc +35\n" +
                "nop -37\n" +
                "acc -2\n" +
                "jmp +244\n" +
                "jmp +151\n" +
                "acc +36\n" +
                "acc +4\n" +
                "nop -64\n" +
                "jmp +231\n" +
                "nop +321\n" +
                "nop +291\n" +
                "acc +16\n" +
                "jmp -161\n" +
                "acc +17\n" +
                "nop +412\n" +
                "nop -89\n" +
                "nop +179\n" +
                "jmp -8\n" +
                "nop -167\n" +
                "acc +44\n" +
                "acc +4\n" +
                "jmp +42\n" +
                "acc +22\n" +
                "acc +28\n" +
                "acc +22\n" +
                "jmp +192\n" +
                "acc -18\n" +
                "acc -7\n" +
                "jmp -70\n" +
                "acc +27\n" +
                "acc +25\n" +
                "jmp +312\n" +
                "acc +50\n" +
                "acc -16\n" +
                "jmp -121\n" +
                "acc +14\n" +
                "acc +43\n" +
                "nop -111\n" +
                "jmp -54\n" +
                "nop +39\n" +
                "acc -4\n" +
                "acc +41\n" +
                "jmp +236\n" +
                "acc -11\n" +
                "jmp -118\n" +
                "jmp +150\n" +
                "acc -15\n" +
                "jmp -141\n" +
                "acc +14\n" +
                "jmp +1\n" +
                "acc -8\n" +
                "jmp -96\n" +
                "acc +11\n" +
                "nop -95\n" +
                "jmp +1\n" +
                "acc +47\n" +
                "jmp -113\n" +
                "nop +257\n" +
                "jmp +35\n" +
                "acc +45\n" +
                "acc +25\n" +
                "acc -6\n" +
                "jmp +31\n" +
                "jmp +1\n" +
                "nop +153\n" +
                "nop -39\n" +
                "jmp +25\n" +
                "acc +0\n" +
                "acc +50\n" +
                "jmp +362\n" +
                "acc -15\n" +
                "acc +0\n" +
                "acc +31\n" +
                "acc +22\n" +
                "jmp +69\n" +
                "acc -18\n" +
                "acc +24\n" +
                "jmp -38\n" +
                "acc +39\n" +
                "acc -10\n" +
                "acc +40\n" +
                "jmp +6\n" +
                "jmp +143\n" +
                "jmp -44\n" +
                "acc +32\n" +
                "acc -8\n" +
                "jmp +358\n" +
                "jmp +248\n" +
                "nop +343\n" +
                "nop -11\n" +
                "jmp +116\n" +
                "jmp +74\n" +
                "jmp +120\n" +
                "acc +37\n" +
                "acc -19\n" +
                "acc +36\n" +
                "jmp +341\n" +
                "acc +49\n" +
                "jmp -164\n" +
                "acc +14\n" +
                "acc +13\n" +
                "acc +0\n" +
                "acc +50\n" +
                "jmp +291\n" +
                "jmp +1\n" +
                "jmp -79\n" +
                "acc +19\n" +
                "jmp +243\n" +
                "acc +25\n" +
                "acc -13\n" +
                "acc -12\n" +
                "acc -7\n" +
                "jmp +228\n" +
                "jmp -81\n" +
                "acc +18\n" +
                "nop -163\n" +
                "acc +0\n" +
                "acc +8\n" +
                "jmp +212\n" +
                "acc +38\n" +
                "acc -12\n" +
                "jmp +6\n" +
                "acc +24\n" +
                "acc +42\n" +
                "acc +21\n" +
                "acc +12\n" +
                "jmp +136\n" +
                "acc -12\n" +
                "acc -2\n" +
                "acc +46\n" +
                "acc +35\n" +
                "jmp +290\n" +
                "acc +6\n" +
                "acc +36\n" +
                "jmp -182\n" +
                "acc +14\n" +
                "acc +7\n" +
                "jmp +228\n" +
                "jmp -19\n" +
                "acc +48\n" +
                "acc +25\n" +
                "jmp +106\n" +
                "jmp +70\n" +
                "acc +24\n" +
                "jmp +1\n" +
                "acc +24\n" +
                "acc +29\n" +
                "jmp -156\n" +
                "nop +296\n" +
                "acc +34\n" +
                "jmp +115\n" +
                "acc -12\n" +
                "acc +41\n" +
                "jmp +28\n" +
                "jmp +165\n" +
                "acc +0\n" +
                "acc +24\n" +
                "acc +42\n" +
                "acc +27\n" +
                "jmp +106\n" +
                "acc +24\n" +
                "acc -11\n" +
                "acc +4\n" +
                "acc -6\n" +
                "jmp -180\n" +
                "acc -2\n" +
                "jmp +2\n" +
                "jmp -314\n" +
                "acc -9\n" +
                "acc +1\n" +
                "jmp -327\n" +
                "acc -8\n" +
                "acc +7\n" +
                "acc -6\n" +
                "acc +32\n" +
                "jmp -157\n" +
                "acc +10\n" +
                "acc +10\n" +
                "acc -16\n" +
                "jmp +278\n" +
                "jmp +6\n" +
                "acc +0\n" +
                "nop +178\n" +
                "acc +26\n" +
                "jmp +231\n" +
                "jmp +175\n" +
                "acc +29\n" +
                "acc +36\n" +
                "acc +7\n" +
                "jmp -255\n" +
                "acc +46\n" +
                "acc +45\n" +
                "acc +7\n" +
                "nop -7\n" +
                "jmp -101\n" +
                "jmp +3\n" +
                "acc -13\n" +
                "jmp -140\n" +
                "nop -115\n" +
                "jmp +1\n" +
                "jmp -336\n" +
                "acc +9\n" +
                "acc +9\n" +
                "nop -68\n" +
                "acc -3\n" +
                "jmp -37\n" +
                "acc -13\n" +
                "nop +128\n" +
                "jmp +1\n" +
                "jmp -90\n" +
                "acc +49\n" +
                "jmp -124\n" +
                "acc +16\n" +
                "acc +9\n" +
                "jmp +212\n" +
                "acc -18\n" +
                "jmp -303\n" +
                "acc +33\n" +
                "acc +23\n" +
                "acc +26\n" +
                "jmp +140\n" +
                "acc +25\n" +
                "nop -123\n" +
                "acc +22\n" +
                "jmp +148\n" +
                "acc +1\n" +
                "acc +44\n" +
                "jmp -352\n" +
                "acc -11\n" +
                "jmp +33\n" +
                "acc +16\n" +
                "nop -199\n" +
                "acc +15\n" +
                "jmp -351\n" +
                "jmp +5\n" +
                "jmp -357\n" +
                "nop -284\n" +
                "acc +32\n" +
                "jmp -43\n" +
                "acc +5\n" +
                "acc +23\n" +
                "acc +3\n" +
                "jmp +59\n" +
                "acc -10\n" +
                "nop -266\n" +
                "nop +43\n" +
                "jmp +79\n" +
                "acc +21\n" +
                "jmp -42\n" +
                "acc +35\n" +
                "acc +5\n" +
                "jmp +68\n" +
                "acc +24\n" +
                "acc -4\n" +
                "jmp -155\n" +
                "acc +45\n" +
                "jmp +154\n" +
                "jmp -311\n" +
                "acc +10\n" +
                "acc +17\n" +
                "acc +39\n" +
                "jmp -297\n" +
                "jmp -175\n" +
                "acc +49\n" +
                "jmp -151\n" +
                "acc -4\n" +
                "acc -9\n" +
                "jmp -219\n" +
                "acc +48\n" +
                "acc -17\n" +
                "acc +30\n" +
                "jmp -9\n" +
                "acc +10\n" +
                "jmp -61\n" +
                "nop -396\n" +
                "acc +11\n" +
                "acc +37\n" +
                "jmp -331\n" +
                "acc +14\n" +
                "acc +22\n" +
                "acc +30\n" +
                "acc +2\n" +
                "jmp -43\n" +
                "nop -265\n" +
                "acc +5\n" +
                "acc +40\n" +
                "acc -15\n" +
                "jmp -35\n" +
                "acc -3\n" +
                "acc +24\n" +
                "jmp -415\n" +
                "acc +0\n" +
                "jmp +98\n" +
                "acc +17\n" +
                "acc +25\n" +
                "nop -48\n" +
                "acc -17\n" +
                "jmp -302\n" +
                "acc +11\n" +
                "acc +11\n" +
                "jmp -181\n" +
                "acc +46\n" +
                "acc +19\n" +
                "jmp -331\n" +
                "nop +90\n" +
                "acc +45\n" +
                "acc +8\n" +
                "jmp -237\n" +
                "acc -11\n" +
                "nop -421\n" +
                "jmp -145\n" +
                "acc -16\n" +
                "acc +47\n" +
                "jmp -387\n" +
                "acc +50\n" +
                "jmp -375\n" +
                "acc +38\n" +
                "jmp +1\n" +
                "jmp -225\n" +
                "acc +47\n" +
                "acc +39\n" +
                "jmp +69\n" +
                "acc +46\n" +
                "acc +41\n" +
                "jmp -89\n" +
                "acc +19\n" +
                "jmp -453\n" +
                "nop +63\n" +
                "acc +18\n" +
                "jmp -386\n" +
                "nop -243\n" +
                "acc +48\n" +
                "jmp +70\n" +
                "acc +25\n" +
                "jmp -191\n" +
                "acc +48\n" +
                "acc +31\n" +
                "jmp +40\n" +
                "acc -10\n" +
                "jmp -46\n" +
                "acc +45\n" +
                "jmp -48\n" +
                "jmp -12\n" +
                "acc +16\n" +
                "acc -16\n" +
                "jmp -120\n" +
                "acc -10\n" +
                "jmp +1\n" +
                "acc -10\n" +
                "jmp -124\n" +
                "acc +48\n" +
                "acc +15\n" +
                "acc +8\n" +
                "acc -15\n" +
                "jmp -66\n" +
                "nop -130\n" +
                "acc +16\n" +
                "acc +10\n" +
                "acc +31\n" +
                "jmp -375\n" +
                "acc +9\n" +
                "acc +20\n" +
                "jmp -37\n" +
                "acc +14\n" +
                "jmp -134\n" +
                "acc -9\n" +
                "acc -6\n" +
                "jmp -120\n" +
                "acc +24\n" +
                "acc +17\n" +
                "acc +49\n" +
                "jmp -332\n" +
                "acc +7\n" +
                "acc +35\n" +
                "nop -149\n" +
                "jmp -103\n" +
                "jmp -277\n" +
                "acc -1\n" +
                "acc +28\n" +
                "nop -211\n" +
                "jmp -371\n" +
                "nop -129\n" +
                "acc -15\n" +
                "acc +6\n" +
                "acc +19\n" +
                "jmp -120\n" +
                "acc -6\n" +
                "jmp -79\n" +
                "acc +0\n" +
                "jmp -64\n" +
                "acc +33\n" +
                "acc +33\n" +
                "jmp -440\n" +
                "jmp -85\n" +
                "acc +37\n" +
                "nop -183\n" +
                "acc +24\n" +
                "acc +42\n" +
                "jmp -545\n" +
                "acc +50\n" +
                "acc +6\n" +
                "jmp -7\n" +
                "nop +8\n" +
                "acc +1\n" +
                "jmp -359\n" +
                "acc -1\n" +
                "nop -388\n" +
                "acc -7\n" +
                "acc +28\n" +
                "jmp -211\n" +
                "jmp -384\n" +
                "acc +32\n" +
                "acc +16\n" +
                "acc +40\n" +
                "jmp +17\n" +
                "acc +0\n" +
                "acc +43\n" +
                "acc -14\n" +
                "jmp -512\n" +
                "nop -264\n" +
                "jmp -474\n" +
                "nop -543\n" +
                "acc +17\n" +
                "nop -288\n" +
                "jmp -38\n" +
                "jmp +24\n" +
                "acc -4\n" +
                "jmp -321\n" +
                "acc +49\n" +
                "acc -16\n" +
                "jmp -532\n" +
                "acc +0\n" +
                "acc -11\n" +
                "acc -16\n" +
                "jmp -104\n" +
                "acc -12\n" +
                "jmp -301\n" +
                "acc +6\n" +
                "nop -498\n" +
                "acc +0\n" +
                "jmp -126\n" +
                "nop -127\n" +
                "acc +1\n" +
                "jmp -6\n" +
                "acc +40\n" +
                "jmp -547\n" +
                "acc +16\n" +
                "acc +18\n" +
                "jmp -123\n" +
                "acc -5\n" +
                "acc +27\n" +
                "acc +44\n" +
                "acc +15\n" +
                "jmp -22\n" +
                "acc +48\n" +
                "acc -18\n" +
                "jmp -350\n" +
                "acc -7\n" +
                "acc +30\n" +
                "acc +26\n" +
                "jmp +1\n" +
                "jmp +1").split("\n"));
    }
}
