# ![alt-text][logo] Flash Study

Android App
**Por Cesar Morales Garduño**

## Definición

#### Mi proyecto de tarjetas flash

Cuando te estás preparando oara un examen, una técnica común para estudiar es usando tarjetas de estudio (Flash Cards). En estas tarjetas, la pregunta esta escrita de un lado y la respuesta del otro. En éste curso el proyecto final es "_**Realizar una aplicación que tenga esta funcionalidad**_"-

- Cada carta desplega una cadena de texto (la pregunta) y cuando tu la selecciones (tap) te mostrará otra vista (otra actividad / fragmento) con la respuesta.

![alt-text][main_activity] ![alt-text][detail_activity]

- La vista principal de la aplicación tiene que tener una lista (RecyclerView) donde presenta las tarjetas disponibles.

- La vista principal de la aplicación debe tener un botón flotante que permite agregar nuevas cartas utilizando un cuadro de diálogo.

![alt-text][dialog]

- Las preguntas y respuestas deben de almacenarce en una base de datos. Los datos de la aplicación, deberá ser compartida a otras aplicaciones vía Content Provider.

- La información inicial será leída desde un archivo Json desde los recursos.

- Es posible ajustar recordatorios periódicos de estudio, usando Job Scheduler, Notifications y Shared Preferences. Si el usuario quiere notificaciones o no, deberá estar almacenado persistentemente. La frecuencia puede ser definida en código o en el menú.

![alt-text][settings]

- El usuario puede agregar un widget a su pantalla de inicio, que le mostrará una pregunta random.

![alt-text][widget]

## Fuente

**Flash Study** es el proyecto final para entregar como parte de los requerimientos del curso [Android Development for Beginners](https://courses.edx.org/courses/course-v1:GalileoX+CAAD002X+1T2017/info) de la plataforma educativa [edX](https://www.edx.org/).

[logo]: https://raw.githubusercontent.com/ShellCore/AndroidBeginners_CesarMoralesGarduno/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png "Flash Study App Logo"

[main_activity]: https://raw.githubusercontent.com/ShellCore/AndroidBeginners_CesarMoralesGarduno/master/readmeFiles/main_activity.png "Main Activity"

[detail_activity]: https://raw.githubusercontent.com/ShellCore/AndroidBeginners_CesarMoralesGarduno/master/readmeFiles/detail.png "Detail Activity"

[dialog]: https://raw.githubusercontent.com/ShellCore/AndroidBeginners_CesarMoralesGarduno/master/readmeFiles/dialog.png "Dialog Activity"

[settings]: https://raw.githubusercontent.com/ShellCore/AndroidBeginners_CesarMoralesGarduno/master/readmeFiles/settings.png "Settings Activity"

[widget]: https://raw.githubusercontent.com/ShellCore/AndroidBeginners_CesarMoralesGarduno/master/readmeFiles/widget.png "Widget Activity"