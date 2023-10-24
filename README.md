# Gestión Comercial 🏭
Este es el repositorio del back-end de Gestión Comercial, enfocado en el módulo de gestión comercial (9). El objetivo de este módulo es crear estrategias comerciales para la fidelización de los usuarios, la lealtad y la retención de tus usuarios.

## Tecnologías utilizadas 💻
Para el desarrollo del back-end de Gestión Comercial, hemos utilizado las siguientes tecnologías:

- **Spring Boot**: Un framework de Java que simplifica la creación de aplicaciones web basadas en Spring. Nos permite configurar y ejecutar nuestro proyecto con facilidad y rapidez.
- **Java**: Un lenguaje de programación orientado a objetos, robusto y multiplataforma. Nos permite escribir código limpio, modular y reutilizable.

## Requisitos
Para construir y ejecutar la aplicación necesita:

- [JDK 20](https://jdk.java.net/20/)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## Ejecutando la aplicación localmente
Hay varias formas de ejecutar una aplicación Spring Boot en su máquina local. Una forma es ejecutar el método `main` en la clase `co.udea.ssmu.api.SsmuApiApplication` desde su IDE.

Alternativamente, puedes usar el [Plugin de Spring Boot Maven](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) de esta manera:
```shell
mvn spring-boot:run
```

## Otros comandos
Si desea limpiar y compilar el proyecto use el siguiente comando:
```shell
mvn clean install
```

En el caso de que quiera correr el comando sin validar los test del aplicativo ejecute:
```shell
mvn clean install -DskipTests
```
