///*******************************************************************************
// * Copyright (c) 2019.
// * This  code file/snippet/block, including any other configuration files,
// * is for the sole use of the Evive Health, LLC and may contain business
// * confidential and privileged information.
// * Any unauthorized review, use, disclosure or distribution is prohibited.
// ******************************************************************************/
//
//package hello.security;
//
//import java.util.Arrays;
//import java.util.TimeZone;
//import org.eclipse.jetty.server.AsyncNCSARequestLog;
//import org.eclipse.jetty.server.ForwardedRequestCustomizer;
//import org.eclipse.jetty.server.HttpConnectionFactory;
//import org.eclipse.jetty.server.handler.HandlerCollection;
//import org.eclipse.jetty.server.handler.RequestLogHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author evivehealth on 2019-03-04.
// */
//@Configuration
//public class JettyConfig  implements EmbeddedServletContainerCustomizer{
//  @Autowired
//  private JettySettings jettySettings;
//
//  @Override
//  public void customize(ConfigurableEmbeddedServletContainer container) {
//    // checks whether the container is Jetty
//    if (container instanceof JettyEmbeddedServletContainerFactory) {
//      ((JettyEmbeddedServletContainerFactory) container)
//          .addServerCustomizers(jettyServerCustomizer());
//    }
//  }
//
//  @Bean
//  public JettyServerCustomizer jettyServerCustomizer() {
//    return server -> {
//      requestLog(server);
//      forwardedRequest(server);
//      threadPool(server);
//    };
//  }
//  public void requestLog(Server server) {
//    RequestLogHandler requestLogHandler = new RequestLogHandler();
//    AsyncNCSARequestLog requestLog = new AsyncNCSARequestLog("logs/evive-entrance-access.log");
//    requestLog.setRetainDays(Integer.MAX_VALUE);
//    requestLog.setLogTimeZone(TimeZone.getDefault().getID());
//    requestLog.setAppend(true);
//    requestLog.setLogServer(true);
//    requestLog.setLogCookies(true);
//    requestLog.setLogLatency(true);
//    requestLog.setExtended(true);
//    requestLogHandler.setRequestLog(requestLog);
//
//    HandlerCollection handlers = new HandlerCollection();
//    handlers.addHandler(server.getHandler());
//
//    requestLogHandler.setHandler(handlers);
//
//    server.setHandler(requestLogHandler);
//  }
//
//  public void forwardedRequest(Server server) {
//    Arrays.stream(server.getConnectors())
//        .flatMap(connector -> connector.getConnectionFactories().stream())
//        .filter(connectionFactory -> connectionFactory instanceof HttpConnectionFactory)
//        .findFirst()
//        .map(connectionFactory -> ((HttpConnectionFactory) connectionFactory).getHttpConfiguration())
//        .ifPresent(httpConfiguration -> {
//          httpConfiguration.addCustomizer(new ForwardedRequestCustomizer());
//        });
//  }
//
//  public void threadPool(Server server) {
//    final JettySettings.ThreadPool threadPoolSettings = jettySettings.getThreadPool();
//    if (threadPoolSettings != null) {
//      server.getBeans(QueuedThreadPool.class)
//          .forEach(threadPool -> {
//            threadPool.setMinThreads(threadPoolSettings.getMinThreads());
//            threadPool.setMaxThreads(threadPoolSettings.getMaxThreads());
//            threadPool.setIdleTimeout(threadPoolSettings.getIdleTimeout());
//          });
//    }
//  }
//}
