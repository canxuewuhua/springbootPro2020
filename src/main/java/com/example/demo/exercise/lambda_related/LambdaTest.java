package com.example.demo.exercise.lambda_related;

import com.example.demo.util.DateUtil;
import com.example.demo.util.SerialNumberUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream的使用过程有着固定的模式
 *
 *  1、创建Stream
 * 2、通过中间操作，对原始Stream进行“变化”并生成新的Stream
 * 3、使用完结操作，生成最终结果  创建——>变化——>完结
 *  lambda表达式一般用filter表示条件过滤，判断某个属性的值
 *  过滤器（Filter）
 *  Filter对流对象中的所有元素进行过滤,该操作是一个中间操作，这意味着你可以在操作返回结果的基础上进行其他操作。
 *   sorted排序
 *  排序（Sorted）
 *  该操作返回一个排序过后的流的视图，原始流的顺序不会改变
 *  map的话，表示将list对象转化为list<String>
 * 映射（Map） 该操作能将流对象中的每个元素映射为另一种元素，实现元素类型的转换
 *  collect的话，看collect()括号里面的是什么类型，返回就是什么类型
 */
public class LambdaTest {
    public static void main(String[] args) {
//        printToLowerCase();
//        printListName();
        printNotEmptyUser();
//        printListDTOToMapKeyValue();
//        printListDTOToListString();
//        printUserCopyToStudent();
//        printSortUserAge();
//        printFilterAndSortedAndMap();
//        printListAgeToCount();
//        printGroupByUserProperties();
//          printGroupByMapping();
//        printGroupByMoreElements();
    }

    /**
     * 将列表中的字符串转换为全小写
     */
    public static void printToLowerCase(){
        List<String> proNames = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
        List<String> lowercaseNames1 = proNames.stream().map(name -> {return name.toLowerCase();}).collect(Collectors.toList());
        System.out.println(lowercaseNames1);
//        Thread a = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    /**
     * lambda表达式将对象list的某个属性转化为set集合
     */
    public static void printListName(){
        User user01 = new User();
        User user02 = new User();
        user01.setUserName("zhangsan");
        user01.setAge(18);
        user02.setUserName("lisi");
        user02.setAge(25);
        List<User> userList = new ArrayList<>();
        userList.add(user01);
        userList.add(user02);
        Set<String> customerIdList = userList.stream().map(User::getUserName).collect(Collectors.toSet());
        System.out.println(customerIdList);
    }

    /**
     * 判断User对象的identyID属性是否为空，将不为空的对象放进一个新的对象list中
     * 其中，!StringUtils.isEmpty(usl.getIdentyID()这个表达式可以根据具体的判断逻辑进行填写
     */
    public static void printNotEmptyUser(){
        User user01 = new User();
        User user02 = new User();
        user01.setUserName("zhangsan");
        user01.setAge(27);
        user01.setIdentyID("1138919881004");
        user02.setUserName("lisi");
        user02.setAge(28);
        List<User> userList = new ArrayList<>();
        userList.add(user01);
        userList.add(user02);
        userList = userList.stream().filter(usl -> !StringUtils.isEmpty(usl.getIdentyID())).collect(Collectors.toList());
        System.out.println(userList);
    }

    /**
     * 将对象list的某两个属性放进map的key和value中，达到将list转化为map的目的
     * 收集（Collect）
     */
    public static void printListDTOToMapKeyValue(){
        User user01 = new User();
        User user02 = new User();
        user01.setUserName("zhangsanfeng");
        user01.setAge(27);
        user01.setIdentyID("1138919881004");
        user02.setUserName("lisiguang");
        user02.setAge(28);
        List<User> userList = new ArrayList<>();
        userList.add(user01);
        userList.add(user02);
        user02.setIdentyID("4138919881004");
        Map<String, String> stillCollectMap = userList.stream().collect(Collectors.toMap(User::getIdentyID, User::getUserName));
        System.out.println(stillCollectMap);
    }

    /**
     * 使用lambda表达式将对象list转化为list<String>
     */
    public static void printListDTOToListString(){
        User user01 = new User();
        User user02 = new User();
        user01.setUserName("zhangsanfeng");
        user01.setAge(27);
        user01.setIdentyID("1138919881004");
        user02.setUserName("lisiguang");
        user02.setAge(28);
        List<User> userList = new ArrayList<>();
        userList.add(user01);
        userList.add(user02);
        user02.setIdentyID("4138919881004");
        List<String> userNames = userList.stream().map(User::getUserName).collect(Collectors.toList());
        System.out.println(userNames);
    }

    /**
     * 通过lambda表达式将user对象转化为student对象，可以忽略某些属性的复制，int类型忽略后，默认值为0，string类型的为null
     */
    public static void printUserCopyToStudent(){
        List<User> userList= Arrays.asList(
                new User("zhangsan",18,"4128196"),new User("lisi",30,"4128196"),new User("wangwu",29,"4128196"));
        String[] ignoreArray = new String[]{"userName"};
        List<Student> stuList = userList.stream()
                .map(vo -> {
                    Student stuVO = new Student();
                    BeanUtils.copyProperties(vo, stuVO, ignoreArray);
                    stuVO.setIdentyID(SerialNumberUtil.getPrimaryId());
                    return stuVO;
                }).collect(Collectors.toList());
        System.out.println(stuList);
    }

    /**
     * 根据年龄，打印排序后的user对象
     */
    public static void printSortUserAge(){
        List<User> userList= Arrays.asList(
                new User("zhangsan",18,"4128196"),new User("lisi",30,"4128196"),new User("wangwu",29,"4128196"));
        List<User> userAgeList = userList.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
        System.out.println(userAgeList);
    }

    /**
     * 根据判空条件，根据年龄排序，将DTO日期属性格式化
     * 将对象list转化为list<String>
     */
    public static void printFilterAndSortedAndMap(){
        List<User> userList= Arrays.asList(
                new User("zhangsan",18,"4128196",DateUtil.addDays(new Date(),-1)),new User("lisi",30,"4128196", DateUtil.addDays(new Date(),0)),new User("wangwu",29,"4128196",DateUtil.addDays(new Date(),1)));
        List<String> listDate = userList.stream()
                .filter(vo -> vo.getIdentyID() != null || vo.getUserName() != null)
                .sorted(Comparator.comparing(User::getAge))
                .map(vo -> DateUtil.dateToStr(vo.getPlanDate(), "yyyy-MM-dd"))
                .collect(Collectors.toList());
        System.out.println(listDate);
    }
    /**
     * Collectors.groupingBy进行分组、排序等操作
     * https://blog.csdn.net/beirdu/article/details/78585945
     */
    private static void printListAgeToCount(){

        //1.分组计数
        List<User> list1= Arrays.asList(
                new User("zhangsan",18,"4128195"),new User("wamhwu",29,"4128196"),new User("wangwu",29,"4128196"));

        // 根据客户年龄进行分组 18、29、 30
        Map<Integer,List<User>> result1=list1.stream().collect(Collectors.groupingBy(User::getAge));
        System.out.println(result1);
        //1.1根据某个属性分组计数
        Map<Integer,Long> result2=list1.stream().collect(Collectors.groupingBy(User::getAge, Collectors.counting()));
        System.out.println(result2);

        Map<String,List<User>> result3=list1.stream().collect(Collectors.groupingBy(o -> o.getAge() + "_" + o.getUserName() + "_" + o.getIdentyID()));
        List<User> assembledUserList = new ArrayList<>();
        result3.forEach((k, v) -> assembleData(v, assembledUserList));
        System.out.println(assembledUserList);
    }

    public static void assembleData(List<User> userList, List<User> assembledUserList){
        for (User user : userList){
            String userName = user.getUserName() + "_O";
            user.setUserName(userName);
            assembledUserList.add(user);
        }
    }

    /**
     * 循环列表，将对象User的名称小写转化为大写
     */
    public static  void printGroupByUserProperties(){

        //1.分组计数
        List<User> list1= Arrays.asList(
                new User("zhangsan",18,"4128195"),new User("wamhwu",29,"4128196"),new User("wangwu",29,"4128196"));

        // 循环列表，将user的用户名称小写转大写
        list1.stream().forEach( a -> toUpperCase(a));
        System.out.println(list1);
    }
    public static void toUpperCase(User user){
        String userName = user.getUserName();
        String userNameUpperCase = userName.toUpperCase();
        user.setUserName(userNameUpperCase);
    }


    /**
     * 循环列表，将对象User的名称小写转化为大写
     */
    public static  void printGroupByMapping(){

        //1.分组计数
        List<User> list1= Arrays.asList(
                new User("zhangsan",18,"4128195"),new User("wamhwu",29,"4128196"),new User("wangwu",29,"4128196"));

        // DOTO("待处理")
        Map<String, Set<String>> collectMap = list1.stream().collect(Collectors.groupingBy(n -> n.getIdentyID(), Collectors.mapping(m -> m.getUserName(), Collectors.toSet())));
        System.out.println(collectMap);
    }

    /**
     *  根据年龄分组，根据姓名计数
     */
    public static void printGroupByMoreElements(){
        // user集合
        List<User> userList= Arrays.asList(
                new User("zhangsan",18,"4128195"),new User("lixiaomin",29,"4128196"),new User("lixiaomin2",29,"4128196"));

        // 根据客户年龄进行分组 18、29、30
        // 根据年龄分组，根据姓名计数
//        Map<Integer,Long> result2=userList.stream().collect(Collectors.groupingBy(User::getAge, Collectors.counting()));
//        System.out.println(result2);
        //Map<Integer, List<User>> countMap = userList.stream().collect(Collectors.groupingBy(o -> o.getAge()));

        Map<Integer, Map<String, Long>> caseTotal = userList.stream().collect(Collectors.groupingBy(User::getAge, Collectors.groupingBy(User::getUserName, Collectors.counting())));
        System.out.println(caseTotal);

        // 定义一个listMap
        List<Map<String,Object>> mapList = new ArrayList<>();
        // 1. entrySet遍历，在键和值都需要时使用（最常用）
        for (Map.Entry<Integer, Map<String, Long>> entry : caseTotal.entrySet()) {
            String entryKey = entry.getKey().toString();
            Map<String, Long> map = entry.getValue();
            //long count = map.

        }

    }
}
@Data
class User{
    private String userName;
    private int age;
    private String identyID;
    private Date planDate;

    public User(){}
    public User(String userName, int age, String identyID) {
        this.userName = userName;
        this.age = age;
        this.identyID = identyID;
    }
    public User(String userName, int age, String identyID,Date planDate) {
        this.userName = userName;
        this.age = age;
        this.identyID = identyID;
        this.planDate = planDate;
    }
}
@Data
class Student{
    private String userName;
    private int age;
    private String identyID;
}
