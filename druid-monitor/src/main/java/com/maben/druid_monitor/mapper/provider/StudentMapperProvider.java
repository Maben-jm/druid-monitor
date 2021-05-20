package com.maben.druid_monitor.mapper.provider;

import com.maben.druid_monitor.pojo.query.StudentQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Slf4j(topic = "m.StudentMapperProvider")
public class StudentMapperProvider {
    public String select(StudentQuery studentQuery){
        final StringBuilder sql = new StringBuilder("select * from t_student ");
        if (Objects.nonNull(studentQuery)){
            sql.append("where 1=1 ");
            final String name = studentQuery.getName();
            if (StringUtils.isNotBlank(name)){
                sql.append(" name like ").append("%").append(name).append("%");
            }
        }
        log.info("StudentMapperProvider.select:{}",sql.toString());
        return sql.toString();
    }
}
