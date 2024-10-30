import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.pinpon.R
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.example.pinpon.Colores

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener el color de fondo de las preferencias
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        applyBackgroundColor(sharedPreferences)

        // Escuchar cambios en las preferencias
        sharedPreferences.registerOnSharedPreferenceChangeListener { prefs, key ->
            if (key == "background_color") {
                applyBackgroundColor(prefs)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, Colores::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Método para aplicar el color de fondo
    @SuppressLint("WrongViewCast")
    private fun applyBackgroundColor(prefs: SharedPreferences) {
        val color = prefs.getString("background_color", "azul")
        val rootLayout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.root_layout)

        // Aplicar el color de fondo basado en la preferencia seleccionada
        when (color) {
            "azul" -> rootLayout.setBackgroundColor(resources.getColor(R.color.Winter, theme))
            "verde" -> rootLayout.setBackgroundColor(resources.getColor(R.color.Summer, theme))
            "rojo" -> rootLayout.setBackgroundColor(resources.getColor(R.color.Autumn, theme))
        }
    }
}
