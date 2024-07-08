This application is designed to collect and analyze your sports activity. The data is sourced from the Strava service. Currently, the application has limited functionality, but it is planned to expand its features in the near future.

Instructions for running the application:

To make the application work, you need to create a "Keys" file in the "constants" folder.
Inside the file, you need to create variables and fill them with the data that you can find on the website https://developers.strava.com/docs/getting-started/:
kotlin
Копировать код
object Keys {
    const val STRAVA_TOKEN_KEY = ""
    const val STRAVA_CLIENT_ID = ""
    const val STRAVA_AUTHORIZATION_CODE = ""
    const val STRAVA_CLIENT_SECRET = ""
    const val STRAVA_REFRESH_TOKEN = ""
}
After completing these steps, the data will be loaded from Strava into the application's database.
