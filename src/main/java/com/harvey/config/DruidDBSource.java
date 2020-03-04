package com.harvey.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * DruidSource配置信息的定制
 * @author bell.meng
 * @date     2018.06.14
 *
 */
@Configuration
public class DruidDBSource {
	private final static Logger logger = LoggerFactory.getLogger(DruidDBSource.class);
	   	@Value("${spring.datasource.url}")  
	    private String dbUrl;  
	    @Value("${spring.datasource.username}")  
	    private String username;  
	    @Value("${spring.datasource.password}")  
	    private String password;  
	    @Value("${spring.datasource.driver-class-name}")  
	    private String driverClassName;  
	    @Value("${spring.datasource.druid.initialSize}")  
	    private int initialSize;  
	    @Value("${spring.datasource.druid.minIdle}")  
	    private int minIdle;  
	    @Value("${spring.datasource.druid.maxActive}")  
	    private int maxActive;  
	    @Value("${spring.datasource.druid.maxWait}")  
	    private int maxWait;  
	    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")  
	    private int timeBetweenEvictionRunsMillis;  
	    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")  
	    private int minEvictableIdleTimeMillis;  
	    @Value("${spring.datasource.druid.validationQuery}")  
	    private String validationQuery;  
	    @Value("${spring.datasource.druid.testWhileIdle}")  
	    private boolean testWhileIdle;  
	    @Value("${spring.datasource.druid.testOnBorrow}")  
	    private boolean testOnBorrow;  
	    @Value("${spring.datasource.druid.testOnReturn}")  
	    private boolean testOnReturn;  
	    @Value("${spring.datasource.druid.poolPreparedStatements}")  
	    private boolean poolPreparedStatements;  
	    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")  
	    private int maxPoolPreparedStatementPerConnectionSize;  
	    @Value("${spring.datasource.druid.filters}")  
	    private String filters;  
	    @Value("${spring.datasource.druid.connectionProperties}")  
	    private String connectionProperties;  
	    @Value("${spring.datasource.druid.useGlobalDataSourceStat}")  
	    private boolean useGlobalDataSourceStat;  
	  
	    @Bean     //声明其为Bean实例  
	    @Primary  //在同样的DataSource中，首先使用被标注的DataSource  
	    public DataSource dataSource(){  
	        DruidDataSource datasource = new DruidDataSource();  
	        datasource.setUrl(this.dbUrl);  
	        datasource.setUsername(username);  
	        datasource.setPassword(password);  
	        datasource.setDriverClassName(driverClassName);  
	  
	        //configuration  
	        datasource.setInitialSize(initialSize);  
	        datasource.setMinIdle(minIdle);  
	        datasource.setMaxActive(maxActive);  
	        datasource.setMaxWait(maxWait);  
	        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);  
	        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);  
	        datasource.setValidationQuery(validationQuery);  
	        datasource.setTestWhileIdle(testWhileIdle);  
	        datasource.setTestOnBorrow(testOnBorrow);  
	        datasource.setTestOnReturn(testOnReturn);  
	        datasource.setPoolPreparedStatements(poolPreparedStatements);  
	        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);  
	        datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);  
	        try {  
	            datasource.setFilters(filters);  
	        } catch (SQLException e) {  
	        	logger.info("druid configuration initialization filter: "+ e);
	        }  
	        datasource.setConnectionProperties(connectionProperties);  
	        return datasource;  
	    } 
	    
	    @Bean
	    public ServletRegistrationBean statViewServlet(){
	        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
	        servletRegistrationBean.addInitParameter("allow","127.0.0.1");  //设置ip白名单
	        servletRegistrationBean.addInitParameter("deny","192.168.0.19");//设置ip黑名单，优先级高于白名单
	        //设置控制台管理用户
	        servletRegistrationBean.addInitParameter("loginUsername","root");
	        servletRegistrationBean.addInitParameter("loginPassword","root");
	        //是否可以重置数据
	        servletRegistrationBean.addInitParameter("resetEnable","false");
	        return servletRegistrationBean;
	    }
	    
	    @Bean
	    public FilterRegistrationBean statFilter(){
	        //创建过滤器
	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
	        //设置过滤器过滤路径
	        filterRegistrationBean.addUrlPatterns("/*");
	        //忽略过滤的形式
	        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
	        return filterRegistrationBean;
	    }
	    
	    @Bean//(destroyMethod = "close",initMethod = "init")
		@ConfigurationProperties(prefix="spring.datasource") //加载时读取指定的配置信息,前缀为spring.datasource
		public DataSource druidDataSource() {
			return new DruidDataSource();
		}
}
