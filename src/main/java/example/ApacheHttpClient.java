package example;

import org.apache.http.HttpException;
import org.apache.log4j.Logger;

import java.io.IOException;

public class ApacheHttpClient {

    private final static Logger log = Logger.getLogger(ApacheHttpClient.class);
    private static ApacheHttpClient instance;
    private String BaseURL = "https://accounts.test.blackpearlsystems.net";
    private String accountsByEmail = "/accounts/emails/";

    public static synchronized ApacheHttpClient getInstance() {
        if (instance == null) {
            instance = new ApacheHttpClient();
        }
        return instance;
    }

    public void accountExists(String encodedEmail) throws IOException {

//        String URI = BaseURL + accountsByEmail + encodedEmail;
//
//        HttpClient httpClient = HttpClientBuilder.create().build();
//    	HttpGet request = new HttpGet(URI);
//
//        GetMethod getMethod = new GetMethod(URI);
//
//        // -H 'Content-Type: application/json' \
//        //-H  'X-BP-Envelope: EgIIARoBMQ==' \
//        getMethod.addRequestHeader("Content-Type", "application/json");
//        getMethod.addRequestHeader("X-BP-Envelope", "EgIIARoBMQ==");
//
//        try {
//            httpClient.executeMethod(getMethod);
//            String response = getMethod.getResponseBodyAsString();
//            log.info(response.toString());
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }

    }

    public static void main(String[] args) throws HttpException, IOException {

        String encodedEmail = "portal_test_01%40lyveminds.com";
        ApacheHttpClient.getInstance().accountExists(encodedEmail);

    }
}

/**
 Throwing
 /Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/bin/java -Didea.launcher.port=7533 "-Didea.launcher.bin.path=/Applications/IntelliJ IDEA 14.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath "/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/lib/javafx-doclet.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/lib/tools.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/htmlconverter.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Users/mmadhusoodan/workspace/RestAPITesting/build/classes/main:/Users/mmadhusoodan/workspace/RestAPITesting/build/resources/main:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.apache.commons/commons-email/1.2/6cd5d2b5c11d2898b901e3162d825b804699187d/commons-email-1.2.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/javax.activation/activation/1.1/e6cb541461c2834bdea3eb920f1884d1eb508b50/activation-1.1.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/javax.mail/mail/1.4.1/8b7bc69010655425dabf091b51d1e90b4de36715/mail-1.4.1.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.springframework/spring-web/4.1.3.RELEASE/8e3652e8c3675a940b65460c79e9e699f530c14b/spring-web-4.1.3.RELEASE.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.springframework/spring-core/4.1.3.RELEASE/bdfdc2249afcc8a3f589e94ec99eef24e7818a5e/spring-core-4.1.3.RELEASE.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/commons-httpclient/commons-httpclient/3.1/964cd74171f427720480efdec40a7c7f6e58426a/commons-httpclient-3.1.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/log4j/log4j/1.2.17/5af35056b4d257e4b64b9e8069c0746e8b08629f/log4j-1.2.17.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/com.retailmenot/testrailsdk/0.8/b6083556de96318a7619dbfa07822a4ea939bac2/testrailsdk-0.8.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/com.googlecode.json-simple/json-simple/1.1.1/c9ad4a0850ab676c5c64461a05ca524cdfff59f1/json-simple-1.1.1.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.springframework/spring-aop/4.1.3.RELEASE/16162c5395752c0e6929d288575d206302924852/spring-aop-4.1.3.RELEASE.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.springframework/spring-beans/4.1.3.RELEASE/6f5d51f73ecbf6ec9f13999f53d45af4371c6c8a/spring-beans-4.1.3.RELEASE.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.springframework/spring-context/4.1.3.RELEASE/2c39194ad514fc4b8a13b0f2155a59248e4f05d/spring-context-4.1.3.RELEASE.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/commons-logging/commons-logging/1.2/4bfc12adfe4842bf07b657f0369c4cb522955686/commons-logging-1.2.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/1.6.6/ce53b0a0e2cfbb27e8a59d38f79a18a5c6a8d2b0/slf4j-api-1.6.6.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents/httpclient/4.2.2/ee8dfd1a0efa78a07222eda60b8892972fbb6165/httpclient-4.2.2.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/aopalliance/aopalliance/1.0/235ba8b489512805ac13a8f9ea77a1ca5ebe3e8/aopalliance-1.0.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.springframework/spring-expression/4.1.3.RELEASE/53695581a8a1860c4c23b1e712007749c41ff7f/spring-expression-4.1.3.RELEASE.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents/httpcore/4.2.2/b76bee23cd3f3ee9b98bc7c2c14670e821ddbbfd/httpcore-4.2.2.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.codehaus.jackson/jackson-mapper-asl/1.9.9/174678f285c155bea65c76ae9ac302f63b4aece1/jackson-mapper-asl-1.9.9.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.apache.commons/commons-lang3/3.1/905075e6c80f206bbe6cf1e809d2caa69f420c76/commons-lang3-3.1.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/commons-codec/commons-codec/1.6/b7f0fc8f61ecadeb3695f0b9464755eee44374d4/commons-codec-1.6.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.codehaus.jackson/jackson-core-asl/1.9.9/b198f9d20dcc5bd2fe211e10f4110639b8243d6/jackson-core-asl-1.9.9.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/junit/junit/4.10/e4f1766ce7404a08f45d859fb9c226fc9e41a861/junit-4.10.jar:/Users/mmadhusoodan/.gradle/caches/modules-2/files-2.1/org.hamcrest/hamcrest-core/1.1/860340562250678d1a344907ac75754e259cdb14/hamcrest-core-1.1.jar:/Library/Python/2.7/site-packages/web.py-0.37-py2.7.egg:/Library/Python/2.7/site-packages/selenium-2.44.0-py2.7.egg:/Library/Python/2.7/site-packages/httplib2-0.9-py2.7.egg:/Library/Python/2.7/site-packages/s3-0.1.3-py2.7.egg:/Library/Python/2.7/site-packages/xmltodict-0.9.0-py2.7.egg:/Library/Python/2.7/site-packages/futures-2.2.0-py2.7.egg:/Library/Python/2.7/site-packages/requests-2.5.0-py2.7.egg:/Library/Python/2.7/site-packages/boto-2.34.0-py2.7.egg:/Library/Python/2.7/site-packages/node-0.9.14-py2.7.egg:/Library/Python/2.7/site-packages/zope.component-4.2.1-py2.7.egg:/Library/Python/2.7/site-packages/zope.deprecation-4.1.1-py2.7.egg:/Library/Python/2.7/site-packages/zope.lifecycleevent-4.0.3-py2.7.egg:/Library/Python/2.7/site-packages/plumber-1.3-py2.7.egg:/Library/Python/2.7/site-packages/odict-1.5.1-py2.7.egg:/Library/Python/2.7/site-packages/zope.event-4.0.3-py2.7.egg:/System/Library/Frameworks/Python.framework/Versions/2.7/lib/python2.7:/System/Library/Frameworks/Python.framework/Versions/2.7/lib/python2.7/plat-darwin:/System/Library/Frameworks/Python.framework/Versions/2.7/lib/python2.7/plat-mac:/System/Library/Frameworks/Python.framework/Versions/2.7/lib/python2.7/plat-mac/lib-scriptpackages:/System/Library/Frameworks/Python.framework/Versions/2.7/Extras/lib/python:/System/Library/Frameworks/Python.framework/Versions/2.7/lib/python2.7/lib-tk:/System/Library/Frameworks/Python.framework/Versions/2.7/lib/python2.7/lib-dynload:/System/Library/Frameworks/Python.framework/Versions/2.7/Extras/lib/python/PyObjC:/Library/Python/2.7/site-packages:/Users/mmadhusoodan/Library/Application Support/IntelliJIdea14/python/helpers/python-skeletons:/Users/mmadhusoodan/Library/Caches/IntelliJIdea14/python_stubs/348993582:/Applications/IntelliJ IDEA 14.app/Contents/lib/idea_rt.jar" com.intellij.rt.execution.application.AppMain com.lyve.app.ApacheHttpClient
 javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target
 at sun.security.ssl.Alerts.getSSLException(Alerts.java:192)
 at sun.security.ssl.SSLSocketImpl.fatal(SSLSocketImpl.java:1884)
 at sun.security.ssl.Handshaker.fatalSE(Handshaker.java:276)
 at sun.security.ssl.Handshaker.fatalSE(Handshaker.java:270)
 at sun.security.ssl.ClientHandshaker.serverCertificate(ClientHandshaker.java:1439)
 at sun.security.ssl.ClientHandshaker.processMessage(ClientHandshaker.java:209)
 at sun.security.ssl.Handshaker.processLoop(Handshaker.java:878)
 at sun.security.ssl.Handshaker.process_record(Handshaker.java:814)
 at sun.security.ssl.SSLSocketImpl.readRecord(SSLSocketImpl.java:1016)
 at sun.security.ssl.SSLSocketImpl.performInitialHandshake(SSLSocketImpl.java:1312)
 at sun.security.ssl.SSLSocketImpl.writeRecord(SSLSocketImpl.java:702)
 at sun.security.ssl.AppOutputStream.write(AppOutputStream.java:122)
 at java.io.BufferedOutputStream.flushBuffer(BufferedOutputStream.java:82)
 at java.io.BufferedOutputStream.flush(BufferedOutputStream.java:140)
 at org.apache.commons.httpclient.HttpConnection.flushRequestOutputStream(HttpConnection.java:828)
 at org.apache.commons.httpclient.HttpMethodBase.writeRequest(HttpMethodBase.java:2116)
 at org.apache.commons.httpclient.HttpMethodBase.execute(HttpMethodBase.java:1096)
 at org.apache.commons.httpclient.HttpMethodDirector.executeWithRetry(HttpMethodDirector.java:398)
 at org.apache.commons.httpclient.HttpMethodDirector.executeMethod(HttpMethodDirector.java:171)
 at org.apache.commons.httpclient.HttpClient.executeMethod(HttpClient.java:397)
 at org.apache.commons.httpclient.HttpClient.executeMethod(HttpClient.java:323)
 at com.lyve.app.ApacheHttpClient.meshIDForAccount(ApacheHttpClient.java:36)
 at com.lyve.app.ApacheHttpClient.main(ApacheHttpClient.java:49)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:606)
 at com.intellij.rt.execution.application.AppMain.main(AppMain.java:134)

 */