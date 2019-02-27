## Spring 2 Security Demo 
In the process of taking a Spring 1.5 app that had working security and converting it to Spring 2. A few changes have been made...

Login  name/password: **admin/admin** , **user1/user1** or **user2/user2**


## Dependencies used
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile group: 'org.thymeleaf.extras', name: 'thymeleaf-extras-springsecurity4', version: '3.0.4.RELEASE'
    compile('nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect')
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-security")
    compile('org.springframework.boot:spring-boot-starter-data-jpa')
    runtime('com.h2database:h2')
    testCompile("junit:junit")
    testCompile("org.springframework.boot:spring-boot-starter-test")
    testCompile("org.springframework.security:spring-security-test")

## Spring 2 updates inclusions/things to be careful of
- Must give permissions to access your static resources (js, css, images, etc)
- Must give permissions for things like h2-console access and update the applications.properties file 

### What I really like?
Thymeleaf has a dialect for spring security... being brought into the build.gradle as the following dependency: `compile ('org.thymeleaf.extras:thymeleaf-extras-springsecurity4')`... Using `sec: authorize` Thymeleaf can help you control the way your html content is being displayed? Only want to offer the option to logout once logged in? This dialect does that! Only want to offer certain nav buttons like add post when logged in as admin? This dialect does that! Pretty cool stuff Thymeleaf...here are the docs! `https://www.thymeleaf.org/doc/articles/springsecurity.html`


### Reference docs utilized
- [Spring Security Guide](https://spring.io/guides/gs/securing-web/)
- [Baeldung](https://www.baeldung.com/security-spring)
- [Thymeleaf Dialect](https://www.thymeleaf.org/doc/articles/springsecurity.html)


