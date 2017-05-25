package com.aboutcoder.packease.utils.togglz;

import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.file.FileBasedStateRepository;
import org.togglz.core.user.UserProvider;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/21/16 2:04 PM<br />
 * @description <br />
 */
public class PETogglzConfig<T extends Feature> implements TogglzConfig {

    /**
     * Define a class model of generic type from input parameter.
     */
    private Class<T> appTogglzConfigClazz;

    /**
     * This method is the easiest to implement.
     * It is used to tell Togglz about the feature enum class you want to use.
     * You simply return the Class object of your feature enum here.
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public Class<? extends Feature> getFeatureClass() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        appTogglzConfigClazz = (Class<T>) params[0];

        return appTogglzConfigClazz;
    }

    /**
     * This method allows you to select the feature state repository you would like to use.
     * The feature state repository is responsible to persist the current state of your features.
     * It knows which features are enabled/disabled, the activation strategies selected for them and their parameters.
     * Have a look at the Activation Strategies chapter to learn more about activation strategies.
     *
     * Togglz ships with a number of default implementations. Please refer to State Repositories for details.
     *
     * The example below shows how to configure a feature state repository that reads the current feature state from an external file.
     * By default, we place the external file in the classes' path of application.
     *
     * @return
     */
    public StateRepository getStateRepository() {
        String featuresPropertiesPath = PETogglzConfig.class.getResource("/").getPath();
        return new FileBasedStateRepository(new File(featuresPropertiesPath + "/features.properties"));
    }

    /**
     * This method returns an implementation of the UserProvider interface which is responsible to tell the
     * FeatureManager who is the current user. Knowing about the current user is essential for Togglz to
     * implement access control for the admin console and for the activation strategy that allows to enable features only for specific users.
     *
     * This example uses the ServletUserProvider as the feature user provider.
     * This implementation uses the HttpServletRequest.getUserPrincipal() to obtain the current user.
     * Other strategies for user authorization will be described in a later chapter.
     *
     * @return
     */
    public UserProvider getUserProvider() {
        return null;
    }
}
