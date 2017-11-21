/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.config;

/**
 *
 * @author JSCHA
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.team0n3.webspotify.dao.PlaylistDAO;
import javax.sql.DataSource;
import com.team0n3.webspotify.dao.UserDAO;
import com.team0n3.webspotify.dao.SongDAO;
import com.team0n3.webspotify.dao.AlbumDAO;
import com.team0n3.webspotify.dao.implementation.PlaylistDAOHibernateImpl;
import com.team0n3.webspotify.dao.implementation.UserDAOHibernateImpl;
import com.team0n3.webspotify.dao.implementation.SongDAOHibernateImpl;
import com.team0n3.webspotify.dao.implementation.AlbumDAOHibernateImpl;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.Album;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.team0n3.webspotify")
@EnableTransactionManagement
public class ApplicationContextConfig extends WebMvcConfigurerAdapter{
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://mysql2.cs.stonybrook.edu:3306/teamon3?zeroDateTimeBehavior=convertToNull");
        dataSource.setUsername("teamon3");
        dataSource.setPassword("KevinLovesTyping");
        return dataSource;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addAnnotatedClasses(User.class,Playlist.class,Song.class,Album.class);    
        /**
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate-sqlserver.cfg.xml").build();
        return sessionBuilder.buildSessionFactory(serviceRegistry);
        **/
        return sessionBuilder.buildSessionFactory();
    }
    
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
    
    @Autowired
    @Bean(name = "userDao")
    public UserDAO getUserDao(SessionFactory sessionFactory) {
        return new UserDAOHibernateImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "playlistDao")
    public PlaylistDAO getPlaylistDao(SessionFactory sessionFactory){
        return new PlaylistDAOHibernateImpl(sessionFactory);
    }
    @Autowired
    @Bean(name = "songDao")
    public SongDAO getSongDao(SessionFactory sessionFactory){
        return new SongDAOHibernateImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "albumDao")
    public AlbumDAO getAlbumDao(SessionFactory sessionFactory){
        return new AlbumDAOHibernateImpl(sessionFactory);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}