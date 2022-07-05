
package ar.edu.unq.desapp.grupon.backenddesappapi;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.reflections.ReflectionUtils.*;

public class ArqTest {

    @Test
    public void testAllPublicMethodsInServicesAreTransactional(){
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("ar.edu.unq.desapp.grupon.backenddesappapi"))
                .setScanners(new SubTypesScanner(),
                        new TypeAnnotationsScanner(),
                        new MethodAnnotationsScanner()));

        Set<Class<?>> services = reflections.getTypesAnnotatedWith(Service.class);

        for (Class<?> service : services) {
            
            Set<Method> transactionalMethods = getAllMethods(service, withAnnotation(Transactional.class));
            Set<Method> publicMethods = getAllMethods(service, withModifier(Modifier.PUBLIC));
            
            publicMethods.removeIf(n -> (Modifier.isAbstract(n.getModifiers())));

            assertTrue(transactionalMethods.containsAll(publicMethods));
        }
    }
}