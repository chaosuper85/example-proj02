package com.example.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhuchao
 * @date 2022/2/6 9:11 上午
 */
public class RegexUtil {

    public static String fetchGroup1(String src, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(src);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static List<String> fetchGroupList(String src,String regex){
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex,Pattern.DOTALL);
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }


    public static String fetchOneNamedGroup(String src, String regex, String groupName) {
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(src);
        if (matcher.find()) {
            return matcher.group(groupName);
        }
        return null;
    }

    public static List<Map<String, String>> fetchNamedGroupList(String src, String regex, String... groupNames) {
        List<Map<String, String>> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(src);

        while (matcher.find()) {
            Map<String, String> map = new HashMap<>();
            for (String groupName : groupNames) {
                map.put(groupName, matcher.group(groupName));
            }
            result.add(map);
        }
        return result;
    }

    public static Map<String, String> fetchNamedGroup(String src, String regex, String... groupNames) {
        Map<String, String> result = new HashMap<>();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(src);
        if (matcher.find()) {
            for (String groupName : groupNames) {
                result.put(groupName, matcher.group(groupName));
            }
        }
        return result;
    }

    public static Map<String, String> fetchNameMap(String src, String regex) {
        Map<String, String> result = new HashMap<>();

        //Pattern.DOTALL表示.*可以匹配任何内容，
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            result.put(matcher.group(1), matcher.group(2));
        }
        return result;
    }


    public static List<String> fetchNameList(String src, String regex) {
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }

    public static String splitResult(String priceInfo) {
        return priceInfo.replace(",", "");
    }


    public static void main(String[] args) {

        List<String> singleRow = fetchGroupList("<tr class=\"NW5DSBOP202001131903159787 defaulthide\" style=\"display: table-row;\">\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t    \n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<td><samp id=\"psgname0\">刘瀚泽</samp></td>\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t    \t\t\t<td><samp class=\"docid\" id=\"docid0\">320722200908052634</samp>\n" +
                "\t\t\t    \t\t\t</td>\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t<td><samp class=\"expireDate\" id=\"expireDate0\"></samp></td>\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<!--军残警残证件号 -->\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t    \t\t<td>18711020161 </td>\n" +
                "\t\t\t     \t\t<td><samp id=\"birthday0\">2009-08-05</samp></td>\n" +
                "\t\t\t    \t\t<td>儿童</td>\n" +
                "\t\t\t\t    \t<td class=\"fc1\" align=\"left\">\n" +
                "\t\t\t\t    \t\t\n" +
                "\t\t\t\t    \t\t\t<a href=\"#\" class=\"tktdetail defaulthide\" tktno=\"731-2428594907\" onclick=\"javascript:return false;\" style=\"display: inline;\">731-2428594907</a>\n" +
                "\t\t\t\t    \t\t\t<input type=\"hidden\" name=\"hiddenPublicKey\" id=\"publicKey_731-2428594907\" value=\"B027452A7A59891EED6E5F8BBC15A70A\">\n" +
                "\t\t\t\t    \t\t\t&nbsp;&nbsp;&nbsp;\n" +
                "\t\t\t\t    \t\t\n" +
                "\t\t\t\t    \t</td>\n" +
                "\t\t\t\t    \t\n" +
                "\t\t\t\t    \t\n" +
                "\t\t\t\t    \t\n" +
                "\t\t\t\t    \t<td></td>\n" +
                "\t\t\t\t    \t\n" +
                "\t\t\t\t    \t\n" +
                "\t\t\t\t    \t\n" +
                "\t\t\t\t\t</tr>", "<tr class=\"NW5DSBOP202001131903159787 defaulthide\" style=\"display: table-row;\">(.*?)</tr>");
        System.out.println(singleRow);

        //System.out.println(fetchNamedGroup("\n   TEST 123", ".*(?<login>\\w+) (?<id>\\d+)", "login", "id"));
        //System.out.println(fetchOneNamedGroup("TEST 123", "(?<login>\\w+) (?<id>\\d+)", "login"));
    }
}