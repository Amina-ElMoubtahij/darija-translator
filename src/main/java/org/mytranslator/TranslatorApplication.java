package org.mytranslator;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class TranslatorApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(TranslatorResource.class);
        classes.add(WelcomeRessource.class);
        classes.add(BasicAuthFilter.class);  // <-- Ajouté
        classes.add(CORSFilter.class); // <-- ajouté
        return classes;
    }
}

