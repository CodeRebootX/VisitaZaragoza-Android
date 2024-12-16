# VisitaZaragoza-Android
Zaragoza Tour Guide App

Zaragoza Tour Guide es una aplicación móvil diseñada para ofrecer una visita guiada por la ciudad de Zaragoza, ideal para recibir a familiares o amigos que visiten la ciudad por primera vez.

Características principales

Pantalla principal:

🏙️ Muestra el nombre y el icono de la aplicación.

📍 Cuatro filas, cada una representando un lugar emblemático de Zaragoza.

🔗 Cada fila incluye un botón que abre la ubicación del lugar en Google Maps.

AlertDialog al acercarse a un lugar:

🚶‍♂️ La primera vez que el usuario esté a 100 metros o menos de uno de los lugares, aparece un AlertDialog preguntando si desea ver un video del lugar.

🎥 Si el usuario acepta, se agrega un nuevo botón en la fila correspondiente que permite reproducir el video en cualquier momento.

Seguimiento de lugares visitados:

✅ La aplicación guarda qué lugares han sido visitados.

📼 Permite ver todos los videos de los lugares ya visitados.

Tecnologías utilizadas

Lenguaje: 💻 Java para desarrollo nativo en Android.

Entorno de desarrollo: 🛠️ Android Studio.

APIs y bibliotecas:

🌍 Google Maps Intent para abrir ubicaciones.

📡 Geofencing API o LocationManager para detectar la proximidad a los lugares.

🗂️ SharedPreferences o base de datos local para registrar los lugares visitados.

💬 AlertDialog para interacción con el usuario.

Configuración inicial

Clona este repositorio:

git clone https://github.com/CodeRebootX/VisitaZaragoza-Android.git

Abre el proyecto en Android Studio.

Configura las claves de API necesarias para Google Maps.

Compila y ejecuta la aplicación en un dispositivo Android o emulador.

Uso

Navegación por la pantalla principal:

🔍 Selecciona cualquiera de los botones para abrir la ubicación del lugar en Google Maps.

Exploración de lugares:

📡 Acércate a 100 metros de un lugar para activar el AlertDialog.

▶️ Una vez que se muestre el video, puedes volver a verlo usando el botón adicional.

Revisar lugares visitados:

📜 La aplicación recuerda los lugares visitados para que siempre tengas acceso a sus videos.

Próximas mejoras

➕ Agregar más lugares de interés de Zaragoza.

🍽️ Integrar un sistema de recomendación de restaurantes y actividades cercanas.

🎨 Mejorar la interfaz de usuario para hacerlo más intuitivo y atractivo.

Contribución

Las contribuciones son bienvenidas. Si tienes ideas o mejoras, abre un issue o envía un pull request al repositorio.


🌟 ¡Explora Zaragoza de una manera divertida y guiada con Zaragoza Tour Guide App!

