package com.example.plugins;

import com.example.config.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Mybatis插件，通过拦截Executor
 *
 * @author allen
 */
@Intercepts({// mybatis 执行流程
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
@Slf4j
public class DynamicPlugin implements Interceptor {
    private static final Map<String, String> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];

        String dynamicDataSource = null;

        if ((dynamicDataSource = cacheMap.get(ms.getId())) == null) {
            // 读方法
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) { // select * from user;    update insert
                // !selectKey 为自增id查询主键(SELECT LAST_INSERT_ID() )方法，使用主库
                if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    dynamicDataSource = "write";
                } else {
                    // 负载均衡，针对多个读库
                    dynamicDataSource = "read";
                }
            } else {
                dynamicDataSource = "write";
            }

            log.info("方法[{"+ms.getId()+"}] 使用了 [{"+dynamicDataSource+"}] 数据源, SqlCommandType [{"+ms.getSqlCommandType().name()+"}]..");
            // 把id(方法名)和数据源存入map，下次命中后就直接执行
            cacheMap.put(ms.getId(), dynamicDataSource);
        }
        // 设置当前线程使用的数据源
        DynamicDataSourceHolder.putDataSource(dynamicDataSource);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
