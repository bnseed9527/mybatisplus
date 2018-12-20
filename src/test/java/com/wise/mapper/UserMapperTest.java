package com.wise.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wise.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void list(){
        /**
         * 针对某列进行查询
         * conditon为true时 column即作为查询条件
         * SELECT id,name,age,email FROM user WHERE name = ?
         * Parameters: null
         * conditon为false时
         *  SELECT id,name,age,email FROM user
         */
        List<User>list = userMapper.selectList(new QueryWrapper<User>().eq(true,"age",20));
        list.forEach(System.out::println);
    }
    @Test
    public void insert(){
        User user = new User();
        user.setName("马大哈");
        user.setAge(23);
        userMapper.insert(user);
    }
    @Test
    public void delete(){
        //删除条件
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name","马冬梅");//删除name为马冬梅的数据 如果存在重名 会一起删除..
        userMapper.deleteByMap(columnMap);
    }
    @Test
    public void deleteWapper(){

        /*
         个别参数说明:
         params : key为数据库字段名,value为字段值
         null2IsNull : 为true则在map的value为null时调用 isNull 方法
          DELETE FROM user WHERE email IS NULL
         为false时则忽略value为null的 慎用..
          DELETE FROM user
         */
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("email",null);
        userMapper.delete(new QueryWrapper<User>().allEq(columnMap,false));
    }
    @Test
    public void deleteBatchIds(){
        /**
         * 批量删除
         * Collection集合
         * DELETE FROM user WHERE id IN ( ? , ? , ? )
         * Parameters: 9(Integer), 10(Integer), 11(Integer)
         */
        List<Integer>lists = new ArrayList<>();
        lists.add(9);lists.add(10);lists.add(11);
        userMapper.deleteBatchIds(lists);
    }
    @Test
    public void select(){
        System.out.println(userMapper.selectOne(new QueryWrapper<User>().eq(true,"name","Jack")));
    }

    @Test
    public void update(){
        /**
         * UpdateWrapper<User>().set 存在SQL问题 暂不采用
         * 无condition
         * UPDATE user SET name=?, age=?, email=?, name=?
         * true
         * UPDATE user SET name=?, age=?, email=?, name=?
         * false
         * UPDATE user SET name=?, age=?, email=?
         */
        User user = userMapper.selectOne(new QueryWrapper<User>().eq(true,"name","Qa1"));
        //userMapper.update(user, new UpdateWrapper<User>().set(true,"name", "Qa"));
        userMapper.update(user, new UpdateWrapper<User>().
                setSql("where name=" + user.getName()));
    }

    @Test
    public void selectBatchIds(){
        /**
         * 批量查找
         * SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
         * Parameters: 1(Integer), 2(Integer), 3(Integer)
         */
        List<Integer>list = new ArrayList();
        list.add(1);list.add(2);list.add(3);
        List<User>usersList =  userMapper.selectBatchIds(list);
        usersList.forEach(System.out::println);
    }
    @Test
    public void  selectByMap(){

        Map<String,Object> columnMap= new HashMap<>();
        columnMap.put("age",20);
        /**
         * 查出age为20用户
         * SELECT id,name,age,email FROM user WHERE age = ?
         * Parameters: 20(Integer)
         */
        List<User>usersList =  userMapper.selectByMap(columnMap);
        usersList.forEach(System.out::println);
    }
    @Test
    public void  selectCount(){

        /**
         * 统计符合条件数据数
         * SELECT COUNT(1) FROM user WHERE age > ?
         * Parameters: 20(Integer)
         */
        System.out.println(userMapper.selectCount(new QueryWrapper<User>().gt("age",20)));
    }
    @Test
    public void  selectMaps(){
        /**
         * <p>
         * 根据 Wrapper 条件，查询全部记录
         * SELECT id,name,age,email FROM user WHERE age > ?
         * Parameters: 20(Integer)
         * @param queryWrapper 实体对象封装操作类（可以为 null）
         * @return 所有字段映射对象 Map 集合
         */
       List<Map<String,Object>>mapList = userMapper.selectMaps(new QueryWrapper<User>().gt("age",20));
        for (int i = 0; i <mapList.size() ; i++) {
            Map<String,Object> map= mapList.get(i);
            Set<String>stringSet = map.keySet();
            Iterator iterator = stringSet.iterator();
            while (iterator.hasNext()){
                String column = (String)iterator.next();
                System.out.println(column + "<<<字段  值>>>"+ map.get(column));
            }
        }
    }
    @Test
    public void selectObjs(){

        /**
         * 注意： 只返回第一个字段的值
         * SELECT id,name,age,email FROM user WHERE age > ?
         * Parameters: 20(Integer)
         */
        List<Object> user = userMapper.selectObjs(new QueryWrapper<User>().gt(true,"age",13));
        user.forEach(System.out::println);
    }
    @Test
    public void selectPage(){

        /**
         * SELECT COUNT(1) FROM user WHERE age > ?
         * 13(Integer)
         * 查出第三页 每页显示三条
         * SELECT id,name,age,email FROM user WHERE age > ? LIMIT ?,?
         */
       IPage<User> iPage = userMapper.selectPage(new Page<>(3, 3),new QueryWrapper<User>().gt(true,"age",13));
        List<User>users = iPage.getRecords();
        users.forEach(System.out::println);
        System.out.println(iPage.getTotal());//共有数据量
        System.out.println(iPage.getCurrent());//当前页码
        System.out.println(iPage.getPages());//共有几页
        System.out.println(iPage.getSize());//每页显示几条
    }

    @Test
    public void  selectMapsPage(){
        /**
         * <p>
         * 根据 Wrapper 条件，查询全部记录
         * SELECT id,name,age,email FROM user WHERE age > ?
         * Parameters: 20(Integer)
         * @param queryWrapper 实体对象封装操作类（可以为 null）
         * @return 所有字段映射对象 Map 集合
         */
        IPage<Map<String,Object>>mapIPage = userMapper.selectMapsPage(new Page<>(3, 3),new QueryWrapper<User>().gt(true,"age",13));
        List<Map<String,Object>>mapList = mapIPage.getRecords();
        for (int i = 0; i <mapList.size() ; i++) {
            Map<String,Object> map= mapList.get(i);
            Set<String>stringSet = map.keySet();
            Iterator iterator = stringSet.iterator();
            while (iterator.hasNext()){
                String column = (String)iterator.next();
                System.out.println(column + "<<<字段  值>>>"+ map.get(column));
            }
        }
    }

}