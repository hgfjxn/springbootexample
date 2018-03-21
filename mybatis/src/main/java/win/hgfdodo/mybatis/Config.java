package win.hgfdodo.mybatis;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringProperties;

import javax.sql.DataSource;

@Configuration
public class Config {
    public SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties liquibaseProperties) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog(liquibaseProperties.getChangeLog());
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setShouldRun(liquibaseProperties.isEnabled());
        springLiquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        springLiquibase.setDropFirst(liquibaseProperties.isDropFirst());

        return springLiquibase;
    }
}
