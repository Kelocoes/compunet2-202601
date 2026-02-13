# TODO

- Inyectar todos los elementos por medio de interfaces para evitar el acoplamiento entre clases. Por ejemplo, en GameService, inyectar IGameRepository en lugar de GameRepository. Esto permitirá cambiar la implementación del repositorio sin afectar el servicio.
- Asegurarse de que todas las clases de servicio y repositorio estén anotadas correctamente con @Service, @Repository o @Component según corresponda, para que Spring pueda gestionarlas como beans.
- Permitir que los servicios y repositorios interactúen entre sí para realizar operaciones completas. Por ejemplo, el UserService podría interactuar con el GameService para obtener información sobre los juegos asociados a un usuario.
- Implementar los repositorios y servicios de los roles y los permisos.
- Modificar el Servlet para mostrar más información sobre los usuarios y los juegos, utilizando los servicios para obtener los datos necesarios.
- Revisar el funcionamiento del `ServletContextListener` para asegurarse de que se están inicializando correctamente los servicios y repositorios al iniciar la aplicación.

Ej: 

```java
package com.example.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.config.AppConfig;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class SpringContextListener implements ServletContextListener {
    private AnnotationConfigApplicationContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Crear el contexto de Spring
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Guardar el contexto en el ServletContext
        sce.getServletContext().setAttribute("springContext", context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cerrar el contexto de Spring
        if (context != null) {
            context.close();
        }
    }
}
```