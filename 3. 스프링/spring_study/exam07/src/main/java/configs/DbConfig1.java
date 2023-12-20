package configs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration

public class DbConfig1 {

    /**
     * 개발
     */
    @Profile("dev")
    @Configuration
    @MapperScan("mapper")
    @EnableTransactionManagement
    static class DbDevConfig{
        @Bean(destroyMethod = "close")
        public DataSource dataSource(){
            System.out.println("dev profile!!!");
            DataSource ds = new DataSource();
            /* 연결 설정 : 로컬 DB */
            ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
            ds.setUsername("spring6");
            ds.setPassword("_aA123456");

            /* 커넥션 풀 설정 */
            ds.setInitialSize(2); // 2개 설정
            ds.setMaxActive(10); // 10개 이상 안되게...
            //  비활성화 커넥션을 추출할 때 커넥션이 유효한지의 여부를 검사해서 유효하지 않은 커넥션은 풀에서 제거
            ds.setTestWhileIdle(true);
            return ds;

        }

        // 트랜잭션 애너테이션 사용 가능
        @Bean
        public PlatformTransactionManager transactionManager(){
            return new DataSourceTransactionManager(dataSource());
        }

        // 데이터 소스를 통합시킴
        @Bean
        public SqlSessionFactory sqlSessionFactory() throws Exception{
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource());
            return sqlSessionFactoryBean.getObject();
        }
    }

    /**
     * 배포시에 데이트
     */
    @Profile("prod")
    @Configuration
    @MapperScan("mapper")
    @EnableTransactionManagement
    static class DbProdConfig{
        @Bean(destroyMethod = "close")
        public DataSource dataSource(){
            System.out.println("prod profile!!!");
            DataSource ds = new DataSource();

            /* 연결 설정 : 로컬 DB */
            ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
            ds.setUsername("spring6");
            ds.setPassword("_aA123456");

            /* 커넥션 풀 설정 */
            ds.setInitialSize(2); // 2개 설정
            ds.setMaxActive(10); // 10개 이상 안되게...
            //  비활성화 커넥션을 추출할 때 커넥션이 유효한지의 여부를 검사해서 유효하지 않은 커넥션은 풀에서 제거
            ds.setTestWhileIdle(true);
            return ds;
        }

        // 트랜잭션 애너테이션 사용 가능
        @Bean
        public PlatformTransactionManager transactionManager(){
            return new DataSourceTransactionManager(dataSource());
        }

        // 데이터 소스를 통합시킴
        @Bean
        public SqlSessionFactory sqlSessionFactory() throws Exception{
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource());
            return sqlSessionFactoryBean.getObject();
        }

    }
}
