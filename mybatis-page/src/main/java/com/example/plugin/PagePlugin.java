package com.example.plugin;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

@Intercepts(@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
))
public class PagePlugin implements Interceptor {
    // 插件的核心业务
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        /**
         * 1、拿到原始的sql语句
         * 2、修改原始sql，增加分页  select * from t_user limit 0,3
         * 3、执行jdbc去查询总数
         */
        // 从invocation拿到我们StatementHandler对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        // 拿到原始的sql语句
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();

        // statementHandler 转成 metaObject
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

        // spring context.getBean("userBean")
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 获取mapper接口中的方法名称  selectUserByPage
        String mapperMethodName = mappedStatement.getId();
        if (mapperMethodName.matches(".*ByPage")) {
            Page page = PageUtil.getPaingParam();
            //  select * from user;
            String countSql = "select count(0) from (" + sql + ") a";
            System.out.println("查询总数的sql : " + countSql);

            // 执行jdbc操作
            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStatement);
            ResultSet rs = countStatement.executeQuery();
            if (rs.next()) {
                page.setTotalNumber(rs.getInt(1));
            }
            rs.close();
            countStatement.close();

            // 改造sql limit
            String pageSql = this.generaterPageSql(sql, page);
            System.out.println("分页sql：" + pageSql);

            //将改造后的sql设置回去
            metaObject.setValue("delegate.boundSql.sql", pageSql);

        }
        // 把执行流程交给mybatis
        return invocation.proceed();
    }

    // 把自定义的插件加入到mybatis中去执行
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    // 设置属性
    @Override
    public void setProperties(Properties properties) {

    }

    // 根据原始sql 生成 带limit sql
    public String generaterPageSql(String sql, Page page) {

        StringBuffer sb = new StringBuffer();
        sb.append(sql);
        sb.append(" limit " + page.getStartIndex() + " , " + page.getTotalSelect());
        return sb.toString();
    }

}
