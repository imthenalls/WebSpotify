
package com.team0n3.webspotify.config;

import com.team0n3.webspotify.dao.AdDAO;
import com.team0n3.webspotify.dao.PlaylistDAO;
import javax.sql.DataSource;
import com.team0n3.webspotify.dao.UserDAO;
import com.team0n3.webspotify.dao.SongDAO;
import com.team0n3.webspotify.dao.AlbumDAO;
import com.team0n3.webspotify.dao.ArtistDAO;
import com.team0n3.webspotify.dao.PaymentDAO;
import com.team0n3.webspotify.dao.RoyaltyPaymentDAO;
import com.team0n3.webspotify.dao.implementation.AdDAOHibernateImpl;
import com.team0n3.webspotify.dao.implementation.PlaylistDAOHibernateImpl;
import com.team0n3.webspotify.dao.implementation.UserDAOHibernateImpl;
import com.team0n3.webspotify.dao.implementation.SongDAOHibernateImpl;
import com.team0n3.webspotify.dao.implementation.AlbumDAOHibernateImpl;
import com.team0n3.webspotify.dao.implementation.ArtistDAOHibernateImpl;
import com.team0n3.webspotify.dao.implementation.PaymentDAOHibernateImpl;
import com.team0n3.webspotify.dao.implementation.RoyaltyPaymentDAOHibernateImpl;
import com.team0n3.webspotify.model.Ad;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.PaymentInfo;
import com.team0n3.webspotify.model.RoyaltyPayment;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.team0n3.webspotify")
@PropertySource("classpath:constants.properties")
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

  @Bean
  public static PropertySourcesPlaceholderConfigurer
    propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }
  @Autowired
  @Bean(name = "sessionFactory")
  public SessionFactory getSessionFactory(DataSource dataSource) {
    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    sessionBuilder.addAnnotatedClasses(User.class,Playlist.class,Song.class,Album.class,Artist.class,PaymentInfo.class,RoyaltyPayment.class, Ad.class);    
    return sessionBuilder.buildSessionFactory();
  }

  @Autowired
  @Bean(name = "transactionManager")
  public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
    return transactionManager;
  }

@Bean
public StandardServletMultipartResolver multipartResolver(){
    return new StandardServletMultipartResolver();
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
  
  @Autowired
  @Bean(name = "artistDao")
  public ArtistDAO getArtistDao(SessionFactory sessionFactory){
    return new ArtistDAOHibernateImpl(sessionFactory);
  }    
  
  @Autowired
  @Bean(name="paymentDao")
  public PaymentDAO getPaymentDao(SessionFactory sessionFactory){
    return new PaymentDAOHibernateImpl(sessionFactory);
  }

  @Autowired
  @Bean(name="royaltyPaymentDao")
  public RoyaltyPaymentDAO getRoyaltyPaymentDao(SessionFactory sessionFactory){
    return new RoyaltyPaymentDAOHibernateImpl(sessionFactory);
  }
  
  @Autowired
  @Bean(name = "adDao")
  public AdDAO getAdDao(SessionFactory sessionFactory) {
    return new AdDAOHibernateImpl(sessionFactory);
  }
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }
}