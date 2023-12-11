/**
 * 1. 작성자 : Dani
 * 2. 작성일 : 12월 11일
 * 3. DataSource 객체를 이용해서 DB연결하기
 */
package config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan("mapper") // 하위 패키지 전부
public class DbConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        dataSource.setUsername("SPRING6");
        dataSource.setPassword("_aA123456");

        // 커넥션 풀 성정
        dataSource.setInitialSize(2);
        // 유휴 상태 커넥션 객체를 체크 여부
        dataSource.setTestWhileIdle(true);
        // 3초마다 커넥션 상태 체크
        dataSource.setTimeBetweenEvictionRunsMillis(3000);
        // 30초가 최대 유휴 시간 -> 경과시 소멸
        dataSource.setMinEvictableIdleTimeMillis(30*1000);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }


    /**
     * DataSourceTransactionManager -> 트랜잭션 관리
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        DataSourceTransactionManager tm = new DataSourceTransactionManager(dataSource());
        return tm;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }
}
