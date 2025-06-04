@FXML
protected Pane contenedor;

protected void showScene(String sceneName) throws IOException {
        AnchorPane pane = FXMLLoader.load(
                getClass().getResource("/ads/tiendopolis/" + sceneName + ".fxml")
        );
        contenedor.getChildren().setAll(pane);
}
**VS**


public void switchToScene1(ActionEvent event) throws IOException { 
Parent root = FXMLLoader. Load(getClass().getResource("Scene1.fxml")); 
stage = (Stage)((Node) event.getSource()).getScene().getWindow(); 
scene = new Scene(root); 
stage.setScene(scene); stage.show();
}

Ese método de “`switchToScene1(...)`” es perfectamente válido y de hecho es la forma estándar de reemplazar por completo la escena (el objeto `Scene`) de la ventana (el `Stage`) en la que estás trabajando. Hasta ahora tú venías cargando nuevos FXMLs y “pegándolos” dentro de un contenedor (un `Pane`) que ya existía en tu layout principal; en cambio, en este `switchToScene1`:

1. Cargas el FXML completo con

   ```java
   Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
   ```
2. Obtienes el `Stage` a partir del `ActionEvent` (haciendo cast de `(Stage)((Node)event.getSource()).getScene().getWindow()`).
3. Construyes una nueva `Scene` con ese “root” recién cargado y se la reasignas al `Stage`.

   ```java
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
   ```

---

## Ventajas de este enfoque (“reemplazar toda la escena”)

* **Cambio completo de interfaz**: Cada vez que llamas a `switchToScene1(...)` estás diciendo “dame tal FXML, conviértelo en Scene y ponlo entero en la ventana”. Si lo que quieres es descartar todo el contenido anterior (botones, barras, contenedores, etc.) y arrancar de nuevo con otro layout limpio, esta es la forma ideal.
* **Código más conciso**: No necesitas tener un `@FXML Pane contenedor;` en tu controlador para ir reemplazando hijos dentro de ese pane. Literalmente quitas la escena vieja y la sustituyes por otra.
* **Gestión sencilla del `Stage`**: Al obtener el `Stage` directamente (mediante `((Node)event.getSource()).getScene().getWindow()`), no dependes de campos inyectados como `contendedor` o `contenedorBorderPane` que a veces pueden quedar `null` si el FXML no está bien declarado.

---

## Desventajas o casos en que podrías preferir el enfoque de “insertar dentro de un contenedor”

* **Quiero mantener elementos comunes** (por ejemplo un menú, un toolbar, un sidebar) sin recargarlos cada vez.

  * Si tu ventana principal tiene siempre un encabezado fijo (con tu logo, barra de navegación, etc.) y solo quieres variar el “panel central”, entonces recargar toda la escena borra también ese encabezado y vuelves a cargarlo cada vez. En ese caso, te conviene tener un `BorderPane` principal y reemplazar solo su `center` (o bien tener un `AnchorPane` y cambiarle los hijos).
* **Performance/fluidez**: cambiar toda la `Scene` implica que JavaFX libere controles antiguos, cargue estilos nuevos, vuelva a renderizar todo. Si tu UI es muy pesada, a veces se nota un pequeño “parpadeo” o retardo. En cambio, si solo intercambias un `Pane` interno, el framework reutiliza muchos componentes de la ventana y el cambio se siente más suave.
* **Reutilización de controladores y estados globales**: si tienes servicios, objetos o variables que solo quieres inicializar una vez (por ejemplo un `UserSession` o un `ViewModel`), cambiar de escena completa puede requerirte volver a pasar referencias o gestionarlas desde un controlador “padre”. Con un pane interno basta con inyectar un controlador hijo sin desmontar todo.

---

## ¿Cuándo usar “cambiar escena completa”?

* Si cada “pantalla” de tu app es completamente distinta y no comparten nada visual ni de lógica en la misma ventana.
* Si, al cambiar de “login” a “dashboard” a “detalle de producto”, por ejemplo, no necesitas que el header sea el mismo (o lo cargas de nuevo).
* Si no te preocupa perder el estado de los nodos viejos (porque vas a construir todo desde cero).

En ese caso tu `switchToScene1(...)` (o un método similar) es perfectamente apropiado. De hecho, mucha gente acostumbra a tener un solo controlador por cada “pantalla” y, al pulsar un botón, invocar exactamente ese bloque de código que muestras.

---

## ¿Cuándo mantener el enfoque de “insertar en un contenedor”?

* Si tu ventana principal tiene:

  * Barra de menú fija arriba.
  * Sidebar o navegación lateral fija.
  * Status bar fija en la parte inferior.
    Y solo cambias lo que hay en el centro (por ejemplo, un listado o un formulario) sin tocar el header ni el footer.
* Si quieres conservar datos o estados en memoria que están asociados a la ventana principal, sin recargarla cada vez.
* Si deseas animaciones/transiciones dentro del mismo layout (un fade out/in del panel central, por ejemplo), resulta más sencillo intercambiar un `Pane` interior que toda la escena.

---

## Conclusión

* **Sí, tu método `switchToScene1(...)` puede funcionar “mejor”** si lo que pretendes es recargar la ventana por completo con otro FXML. Es más “limpio” para arrancar una pantalla totalmente nueva.
* **No es necesariamente “mejor”** si en realidad quieres conservar partes de la UI (cabeceras, pies de página, menús, etc.) y únicamente variar un panel interior.
* **Decide en función de tu diseño**:

  1. Si la aplicación tiene una sola ventana y cada botón te lleva a una interfaz completamente distinta, usa el enfoque de cargar → `stage.setScene(new Scene(root))`.
  2. Si tienes siempre un layout fijo (con BorderPane, menús, etc.) y solo cambias el “center”, sigue usando tu lógica de `contenedor.getChildren().setAll(...)` o `contenedorBorderPane.setCenter(...)`.
  3. Incluso podrías mezclar: tener un `Stage` con un `BorderPane` general y, dentro del `center` de ese `BorderPane`, usar `switchToScene(...)` para intercambiar paneles que ellos mismos sean `BorderPane` o `AnchorPane`, según convenga.


Para que todos tus controladores hijos hereden un método genérico de “cambio de escena” *sin* tener que recibir un `ActionEvent` cada vez, tienes que apoyarte en un nodo inyectado en **cada** controlador (por ejemplo, el root pane del propio FXML). De esa manera, el `BaseController` sabe de dónde sacar el `Stage` (a través de `rootPane.getScene().getWindow()`) y no necesita un parámetro `event`. El truco consiste en:

1. **En el `BaseController`** declarar un campo `@FXML` (por ejemplo un `Pane rootPane;`) que represente siempre el nodo raíz de todas las vistas (podría ser un `AnchorPane`, un `BorderPane`, un `VBox`, etc. – lo importante es que sea un tipo que extienda de `javafx.scene.layout.Pane`).
2. **En cada FXML hijo** (el de `AlgoController`, `TestController`, etc.) poner ese mismo `fx:id="rootPane"` en el nodo raíz, para que JavaFX inyecte en tu controlador hijo —que extiende `BaseController`— ese pane raíz.
3. Dentro de `BaseController.showScene(...)` ya no usarás `((Node) event.getSource()).getScene().getWindow()`, sino algo como:

   ```java
   Stage stage = (Stage) rootPane.getScene().getWindow();
   ```

   así te olvidas del parámetro `event`.

A continuación te muestro un ejemplo mínimo paso a paso:

---

## 1. Definir el `BaseController`

```java
package ads.tiendopolis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;    // Puede ser AnchorPane, BorderPane, VBox, HBox... 
import javafx.stage.Stage;
import java.io.IOException;

public abstract class BaseController {
    /**
     * Campo que representará el nodo raíz de cada FXML.
     * Todas las vistas hijas deberán tener en su FXML algo como:
     *   <AnchorPane fx:id="rootPane" ...>
     *   o <BorderPane fx:id="rootPane" ...>
     *   o <VBox fx:id="rootPane" ...>
     */
    @FXML
    protected Pane rootPane;

    /**
     * Carga un nuevo FXML y sustituye completamente la escena del Stage
     * donde esté inyectado este controlador (es decir: el Stage al que
     * pertenece rootPane).
     *
     * @param sceneName nombre del fichero FXML (sin “.fxml”) a cargar desde /ads/tiendopolis/
     */
    protected void showScene(String sceneName) throws IOException {
        // 1. Cargo el FXML que quiero mostrar:
        Parent newRoot = FXMLLoader.load(
            getClass().getResource("/ads/tiendopolis/" + sceneName + ".fxml")
        );
        // 2. Obtengo el Stage a partir del Pane raíz actual (rootPane)
        Stage currentStage = (Stage) rootPane.getScene().getWindow();
        // 3. Armo la nueva Scene y la pongo en el Stage
        currentStage.setScene(new javafx.scene.Scene(newRoot));
        currentStage.show();
    }
}
```

**Comentarios clave:**

* Usamos `protected Pane rootPane;` en lugar de un tipo concreto (`AnchorPane` o `BorderPane`), porque así cualquier layout que herede de `Pane` (como `AnchorPane`, `VBox`, `BorderPane`, etc.) servirá.
* `rootPane.getScene().getWindow()` regresa el `Window` (cast‐eado a `Stage`) donde está montada esta vista actual. De esa forma, no necesitamos recibir el `ActionEvent`.

---

## 2. Cada controlador hijo debe extender `BaseController`

Por ejemplo, digamos que tienes un `AlgoController.java`:

```java
package ads.tiendopolis.controllers;

import javafx.fxml.FXML;
import java.io.IOException;

public class AlgoController extends BaseController {

    @FXML
    private void volverAMenuPrincipal() throws IOException {
        // Para volver a la “pantalla principal” (por ejemplo “main-view”),
        // basta con llamar showScene() heredado:
        showScene("main-view");
    }

    @FXML
    private void otraAccionDiferente() {
        // aquí pones la lógica específica de AlgoController
    }
}
```

Y otro `TestController.java`:

```java
package ads.tiendopolis.controllers;

import javafx.fxml.FXML;
import java.io.IOException;

public class TestController extends BaseController {

    @FXML
    private void irAVistaDetalle() throws IOException {
        showScene("detalle-view");
    }

    @FXML
    private void otraAcciónTest() {
        // lógica específica de TestController
    }
}
```

**Observa** cómo ninguno de los dos necesita `ActionEvent event` como parámetro: todos llaman directamente a `showScene("...")`, que ya sabe “por dónde” está montado el control (gracias a `rootPane`).

---

## 3. Cada FXML debe “conectar” el fx\:id="rootPane" y el fx\:controller

### Ejemplo de `main-view.fxml` (pantalla principal)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.layout.AnchorPane?>

<AnchorPane fx:id="rootPane"
            xmlns="http://javafx.com/javafx/17.0.12"
            xmlns:fx="http://javafx.com/fxml/1"
            fx:controller="ads.tiendopolis.controllers.HelloController"
            prefWidth="600" prefHeight="400">

    <children>
        <Button layoutX="50" layoutY="50"
                text="Ir a AlgoView"
                onAction="#mostrarAlgo" />
        <Button layoutX="50" layoutY="100"
                text="Ir a TestView"
                onAction="#mostrarTest" />
    </children>
</AnchorPane>
```

Y su controlador sería `HelloController.java`, que extiende `BaseController`:

```java
package ads.tiendopolis.controllers;

import javafx.fxml.FXML;
import java.io.IOException;

public class HelloController extends BaseController {

    @FXML
    private void mostrarAlgo() throws IOException {
        showScene("algo-view");
    }

    @FXML
    private void mostrarTest() throws IOException {
        showScene("test-view");
    }
}
```

* **Importante**: aquí el nodo raíz del FXML es un `<AnchorPane fx:id="rootPane">`, por lo que JavaFX inyectará ese `AnchorPane` (que es un `Pane`) en el campo `rootPane` de `BaseController`.
* Luego, al llamar a `showScene("algo-view")`, `rootPane.getScene().getWindow()` me devuelve el `Stage` donde se está mostrando “main-view\.fxml” — y se lo cambio por completo a la escena nueva.

---

### Ejemplo de `algo-view.fxml` (vista secundaria)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.control.Label?>

<AnchorPane fx:id="rootPane"
            xmlns="http://javafx.com/javafx/17.0.12"
            xmlns:fx="http://javafx.com/fxml/1"
            fx:controller="ads.tiendopolis.controllers.AlgoController"
            prefWidth="600" prefHeight="400">

    <children>
        <Label layoutX="50" layoutY="50" text="¡Bienvenido a AlgoView!" />
        <Button layoutX="50" layoutY="100" text="Volver" onAction="#volverAMenuPrincipal"/>
    </children>
</AnchorPane>
```

* Aquí el root también es un `<AnchorPane fx:id="rootPane">`. JavaFX inyectará este nodo en el campo `rootPane` de `AlgoController` (heredado de `BaseController`).
* Cuando el usuario pulse “Volver”, se invoca `volverAMenuPrincipal()`, que llama a `showScene("main-view")`. Dado que `rootPane` ya está en esta ventana, `showScene(...)` sabe qué `Stage` usar sin necesidad de recibir un `ActionEvent`.

---

### Ejemplo de `test-view.fxml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.control.Label?>

<AnchorPane fx:id="rootPane"
            xmlns="http://javafx.com/javafx/17.0.12"
            xmlns:fx="http://javafx.com/fxml/1"
            fx:controller="ads.tiendopolis.controllers.TestController"
            prefWidth="600" prefHeight="400">

    <children>
        <Label layoutX="50" layoutY="30" text="Esta es TestView" />
        <Button layoutX="50" layoutY="80" text="Volver a Main" onAction="#irAMenuPrincipal" />
    </children>
</AnchorPane>
```

Y su controlador `TestController.java`:

```java
package ads.tiendopolis.controllers;

import javafx.fxml.FXML;
import java.io.IOException;

public class TestController extends BaseController {

    @FXML
    private void irAMenuPrincipal() throws IOException {
        showScene("main-view");
    }

    @FXML
    private void otraAcciónTest() {
        // lógica propia de TestController
    }
}
```

---

## 4. Resumen rápido de la mecánica

1. **`BaseController` declara**

   ```java
   @FXML
   protected Pane rootPane;
   protected void showScene(String sceneName) { … }
   ```
2. **Cada FXML hijo** (por ejemplo, `main-view.fxml`, `algo-view.fxml`, `test-view.fxml`, etc.) debe tener *exactamente un* nodo raíz con `fx:id="rootPane"` y su propio `fx:controller="…Controller"`.
3. Gracias a ese `fx:id="rootPane"`, en tiempo de carga JavaFX inyecta:

   ```
   this.rootPane = <el AnchorPane o VBox o BorderPane que
                    esté en el FXML como nodo raíz>;
   ```

   *en la instancia concreta* de tu controlador (sea `HelloController`, `AlgoController`, `TestController`, …).
4. Cuando llamas `showScene("otra-vista")`, el `BaseController` hace:

   ```java
   Parent newRoot = FXMLLoader.load("/ads/tiendopolis/otra-vista.fxml");
   Stage stage = (Stage) rootPane.getScene().getWindow();
   stage.setScene(new Scene(newRoot));
   stage.show();
   ```

   — de este modo no necesitas pasar ningún `ActionEvent`, porque ya sabes “desde qué pane” estás trabajando y ese pane conoce su `Scene` y su `Window`.

---

### ✏️ Beneficios de este enfoque

* **Libre desacoplamiento**: cada controlador hijo tiene sus propios métodos `@FXML` y su propio FXML, sin que todos los botones terminen en un único controlador gigante.
* **Código reutilizable** en `BaseController`: todos los hijos llaman a `showScene("mi-vista")` y ni se enteran de cómo se obtiene el `Stage`.
* **Sencillez**: no hay que andar pasando `ActionEvent` por todos lados, ni castear `(Stage)((Node) event.getSource())…` en cada controlador.
* **Flexibilidad**: el nodo raíz de cada FXML puede ser `AnchorPane`, `BorderPane`, `VBox`, lo que prefieras. Mientras extienda de `Pane`, JavaFX hará la inyección en `@FXML protected Pane rootPane;`.

---

## 5. ¿Y si mi vista secundaria es un `BorderPane` en lugar de un `AnchorPane`?

No pasa nada. Como en `BaseController` definimos

```java
@FXML
protected Pane rootPane;
```

cualquier `BorderPane`, `AnchorPane`, `VBox`, etc. se convertirá de forma válida a `Pane`. Por ejemplo, si `algo-view.fxml` fuese:

```xml
<BorderPane fx:id="rootPane"
            xmlns="http://javafx.com/javafx/17.0.12"
            xmlns:fx="http://javafx.com/fxml/1"
            fx:controller="ads.tiendopolis.controllers.AlgoController">
    <!-- ... -->
</BorderPane>
```

Entonces en `AlgoController` (que hereda de `BaseController`) se inyectará ese `BorderPane` en el campo `rootPane` (que es un `Pane`). Y a la hora de hacer

```java
Stage stage = (Stage) rootPane.getScene().getWindow();
```

¡funcionará exactamente igual!

---

## 6. Ejemplo mínimo completo

Para recapitular, pongamos un esqueleto de archivos:

1. **BaseController.java**

   ```java
   package ads.tiendopolis.controllers;

   import javafx.fxml.FXML;
   import javafx.fxml.FXMLLoader;
   import javafx.scene.Parent;
   import javafx.scene.layout.Pane;
   import javafx.stage.Stage;
   import java.io.IOException;

   public abstract class BaseController {
       @FXML
       protected Pane rootPane;

       protected void showScene(String sceneName) throws IOException {
           Parent newRoot = FXMLLoader.load(
               getClass().getResource("/ads/tiendopolis/" + sceneName + ".fxml")
           );
           Stage stage = (Stage) rootPane.getScene().getWindow();
           stage.setScene(new javafx.scene.Scene(newRoot));
           stage.show();
       }
   }
   ```

2. **HelloController.java** (pantalla principal)

   ```java
   package ads.tiendopolis.controllers;

   import javafx.fxml.FXML;
   import java.io.IOException;

   public class HelloController extends BaseController {

       @FXML
       private void mostrarAlgo() throws IOException {
           showScene("algo-view");
       }

       @FXML
       private void mostrarTest() throws IOException {
           showScene("test-view");
       }
   }
   ```

3. **AlgoController.java** (vista secundaria 1)

   ```java
   package ads.tiendopolis.controllers;

   import javafx.fxml.FXML;
   import java.io.IOException;

   public class AlgoController extends BaseController {

       @FXML
       private void volverAMain() throws IOException {
           showScene("main-view");
       }
   }
   ```

4. **TestController.java** (vista secundaria 2)

   ```java
   package ads.tiendopolis.controllers;

   import javafx.fxml.FXML;
   import java.io.IOException;

   public class TestController extends BaseController {

       @FXML
       private void volverAMain() throws IOException {
           showScene("main-view");
       }
   }
   ```

5. **main-view\.fxml** (la que arranca la aplicación)

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <?import javafx.scene.control.Button?>
   <?import javafx.scene.layout.AnchorPane?>

   <AnchorPane fx:id="rootPane"
               xmlns="http://javafx.com/javafx/17.0.12"
               xmlns:fx="http://javafx.com/fxml/1"
               fx:controller="ads.tiendopolis.controllers.HelloController"
               prefWidth="600" prefHeight="400">

       <children>
           <Button layoutX="50" layoutY="50"
                   text="Ir a AlgoView"
                   onAction="#mostrarAlgo" />
           <Button layoutX="50" layoutY="100"
                   text="Ir a TestView"
                   onAction="#mostrarTest" />
       </children>
   </AnchorPane>
   ```

6. **algo-view\.fxml** (puede ser un AnchorPane o un BorderPane)

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <?import javafx.scene.control.Button?>
   <?import javafx.scene.layout.AnchorPane?>

   <AnchorPane fx:id="rootPane"
               xmlns="http://javafx.com/javafx/17.0.12"
               xmlns:fx="http://javafx.com/fxml/1"
               fx:controller="ads.tiendopolis.controllers.AlgoController"
               prefWidth="600" prefHeight="400">

       <children>
           <Button layoutX="50" layoutY="50"
                   text="Volver a Main"
                   onAction="#volverAMain"/>
       </children>
   </AnchorPane>
   ```

7. **test-view\.fxml** (puede ser también un BorderPane, por ejemplo)

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <?import javafx.scene.control.Button?>
   <?import javafx.scene.layout.BorderPane?>

   <BorderPane fx:id="rootPane"
               xmlns="http://javafx.com/javafx/17.0.12"
               xmlns:fx="http://javafx.com/fxml/1"
               fx:controller="ads.tiendopolis.controllers.TestController"
               prefWidth="600" prefHeight="400">

       <center>
           <Button text="Volver a Main" onAction="#volverAMain"/>
       </center>
   </BorderPane>
   ```

En cualquier FXML, el atributo `fx:id="rootPane"` debe coincidir con el campo `@FXML protected Pane rootPane;` de `BaseController`. De ese modo, **todos** los controladores que hereden de `BaseController` tendrán inyectado ese nodo raíz, y a la hora de hacer `rootPane.getScene().getWindow()` nunca dará `null`.

---

### ⚠️ Resumen de los pasos que debes comprobar

1. **En `BaseController`**:

   * Declara `@FXML protected Pane rootPane;` (no `AnchorPane` ni `BorderPane` directamente, sino el tipo común `Pane`).
   * Implementa `protected void showScene(String sceneName) { … }` usando `rootPane.getScene().getWindow()` para obtener el `Stage`.
2. **En cada FXML** (sea `main-view.fxml`, `algo-view.fxml`, `test-view.fxml`, etc.):

   * El nodo raíz debe tener exactamente `fx:id="rootPane"`.
   * Debe tener su propio `fx:controller="ads.tiendopolis.controllers.XxxController"`.
3. **Cada controlador hijo** (por ejemplo `AlgoController`, `TestController`, `HelloController`) extiende `BaseController` y **en ningún método reciben `ActionEvent event`** ni hacen `(Stage)((Node)event.getSource()).getScene().getWindow()`. Simplemente invocan:

   ```java
   @FXML
   private void algunaAcción() throws IOException {
       showScene("nombre-del-fxml-sin-.fxml");
   }
   ```

Con esta estructura, tus controladores podrán compartir el método `showScene(...)` y a la vez cada uno seguirá teniendo sus propios `@FXML` y lógica sin generar acoplamientos masivos.

En resumen, **no hay una “receta única”**, sino dos estrategias diferentes:

* **Reemplazar toda la escena** (tu `switchToScene1`): muy útil para pantallas completamente independientes.
* **Reemplazar solo un contenedor interno** (tu `showScene(...)` o `showBorderPane(...)`): ideal para mantener un layout principal e ir cambiando secciones.

Elige la que encaje con la experiencia de usuario que buscas.


básicamente todas las vistas tienen que tener por id "rootPane", además que pueden haber unas que sean BorderPane y otras que sean AnchorPane Es decir, que test-view sea un borderPane y algo-view sea un AnchorPane.
