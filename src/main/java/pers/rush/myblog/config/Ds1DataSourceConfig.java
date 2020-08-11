package pers.rush.myblog.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

/**
 * 动态数据源配置
 * @author Rush
 *
 */
@Configuration
@MapperScan(basePackages= {"pers.rush.myblog.**.dao"},
	sqlSessionFactoryRef="sqlSessionFactory-ds1")
public class Ds1DataSourceConfig {
	
	@Value("${spring.datasource.jndi-name:jndiname}")
	private String jndiName;
	
	@Profile("dev")
	@Bean("ds1")
	@ConfigurationProperties("spring.datasource.druid.ds1")
	public DataSource dataSourceJdbc() {
		return DruidDataSourceBuilder.create().build();
	}
	
	/**
	 * 事务配置
	 * @param dataSource
	 * @return
	 */
	@Primary
	@Bean(name="transactionManager-ds1")
	public DataSourceTransactionManager batchTransactionManager(@Qualifier("ds1") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	/**
	 * 使用Mybatis-Plus
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Primary
	@Bean(name= {"sqlSessionFactory-ds1", "th-default-ssf"})
	public SqlSessionFactory sqlSessionFactory(@Qualifier("ds1") DataSource dataSource) throws Exception {
		// MP全局配置
		GlobalConfig gcfg = new GlobalConfig();
		
		// MP配置
		MybatisConfiguration mcfg = new MybatisConfiguration();
		mcfg.setJdbcTypeForNull(JdbcType.NULL);
		
		// 使用MP提供的SSF，配套MP的分页插件
		final MybatisSqlSessionFactoryBean ssfb = new MybatisSqlSessionFactoryBean();
		ssfb.setDataSource(dataSource);
		ssfb.setPlugins(new Interceptor[] {new PaginationInterceptor()});
		ssfb.setGlobalConfig(gcfg);
		ssfb.setConfiguration(mcfg);
		
		return ssfb.getObject();
	}
}
