package cn.itcast.springboot.javaconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.jolbox.bonecp.BoneCPDataSource;

/*
 * 核心代码：用Java代码代替了xml配置文件，利用注解来实现IOC
 */
@Configuration //通过该注解来表明该类是一个Spring的配置，相当于一个xml文件
@ComponentScan(basePackages = "cn.itcast.springboot.javaconfig") //配置扫描包
@PropertySource(value= {"classpath:db.properties"},ignoreResourceNotFound = true) //读取外部的配置文件，有多个继续在后面加，找不到文件忽略掉
public class SpringConfig {
    
    @Bean // 通过该注解来表明是一个Bean对象，相当于xml中的<bean>
    public UserDAO getUserDAO(){
        return new UserDAO(); // 直接new对象做演示
    }
    
    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driverClassName}")
    private String jdbcDriverClassName;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;    
    
    @Bean(destroyMethod = "close")
    public BoneCPDataSource boneCPDataSource() { //相当于在xml中配置连接池，方法名默认是bean的id
    	BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
    	boneCPDataSource.setDriverClass(jdbcDriverClassName);
    	boneCPDataSource.setJdbcUrl(jdbcUrl);
    	boneCPDataSource.setUsername(jdbcUsername);
    	boneCPDataSource.setPassword(jdbcPassword);
    	// 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
        // 连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0
        boneCPDataSource.setIdleMaxAgeInMinutes(30);
        // 每个分区最大的连接数
        boneCPDataSource.setMaxConnectionsPerPartition(100);
        // 每个分区最小的连接数    
        boneCPDataSource.setMinConnectionsPerPartition(5);
        return boneCPDataSource;

    }
}
