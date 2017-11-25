package win.hgfdodo.springbootexample.liquibase.config;

import com.mysql.jdbc.Driver;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by guangfuhe on 2017/9/28.
 */
@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class Config {
    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource,LiquibaseProperties liquibaseProperties){
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog(liquibaseProperties.getChangeLog());
        springLiquibase.setShouldRun(liquibaseProperties.isEnabled());
        springLiquibase.setDropFirst(liquibaseProperties.isDropFirst());

        return springLiquibase;
    }
}
