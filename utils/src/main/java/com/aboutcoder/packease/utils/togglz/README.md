PE.utils.PETogglzConfig
=========================

#### 一、简介

这是一个对Togglz框架的TogglzConfig的一种封装, 使用的案例如下:

> DemoTogglzConfig.java

```java
public class DemoTogglzConfig extends PETogglzConfig<DemoTogglzFeatures> {
    // codes inherit from parent.

    /**
     * This method returns an implementation of the UserProvider interface which is responsible to tell the
     * FeatureManager who is the current user. Knowing about the current user is essential for Togglz to
     * implement access control for the admin console and for the activation strategy that allows to enable features only for specific users.
     * <p>
     * This example uses the ServletUserProvider as the feature user provider.
     * This implementation uses the HttpServletRequest.getUserPrincipal() to obtain the current user.
     * Other strategies for user authorization will be described in a later chapter.
     *
     * @return
     */
    @Override
    public UserProvider getUserProvider() {
        return new UserProvider() {
            /**
             * For developing purpose, we simply configure a dummy UserProvider that
             * assigns administration privileges to every user.
             *
             * <::WARNING::>
             * This mustn't be released to product environment.
             *
             * @return
             */
            public FeatureUser getCurrentUser() {
                return new SimpleFeatureUser("admin", true);
            }
        };
    }
}
```

> DemoTogglzFeatures.java

```java
public enum DemoTogglzFeatures implements Feature {
    @EnabledByDefault
    @Label("First Feature")
    FEATURE_ONE,

    @Label("Second Feature")
    FEATURE_TWO
    ;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
```

#### 二、更多参考

* [https://www.togglz.org/documentation/configuration.html](https://www.togglz.org/documentation/configuration.html)