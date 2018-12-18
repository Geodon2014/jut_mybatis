package com.jut.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/***
 * 执行MyBatis数据操作的工具类,使用时需要配置文件,路径如下:classpath:jut_mybatis/mybatis.cfg.xml
 *
 * @author geodon
 */
public class DbUtil {
    public static SqlSessionFactory sessionFactory;

    static{
        try {
            //使用MyBatis提供的Resources类加载mybatis的配置文件
            Reader reader = Resources.getResourceAsReader("jut_mybatis/mybatis.cfg.xml");
            //构建sqlSession的工厂
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //创建能执行映射文件中sql的sqlSession
    public static SqlSession getSession(){
        return getSession(false);
    }
    //创建能执行映射文件中sql的sqlSession
    //isAutoCommit true表示自动提交事务,false不自动提交事务
    public static SqlSession getSession(boolean isAutoCommit){
        return sessionFactory.openSession(isAutoCommit);
    }

    /***
     * 执行mapper的方法
     * @param callbackInterface 回调接口实例
     * @param tClass 调用的mapper类型
     * @param <T> 调用的mapper类型
     * @param <R> 调用的mapper方法的返回类型
     * @return mapper的执行结果
     */
    public static <T,R> R execute(CallbackInterface<T,R> callbackInterface,Class<T> tClass){
        SqlSession sqlSession = getSession();
        T mapper = sqlSession.getMapper(tClass);
        try {
            R r = callbackInterface.execute(mapper);
            sqlSession.commit();
            return  r;
        }catch (Exception e){
            sqlSession.rollback();
            throw e;
        }
    }
}
