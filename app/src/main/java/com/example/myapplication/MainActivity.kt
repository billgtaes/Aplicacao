package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Habilita o modo de tela cheia (Edge-to-Edge)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val menu = findViewById<NavigationView>(R.id.navigationView)
        val texto = findViewById<TextView>(R.id.txtConteudo)

        // Aplica o recuo superior especificamente na Toolbar para que o título fique visível
        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        // Aplica o recuo superior no NavigationView para evitar que os itens do menu fiquem sob a barra de status
        ViewCompat.setOnApplyWindowInsetsListener(menu) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        // O DrawerLayout lida com os recuos laterais e inferiores (barra de navegação)
        // Mantemos o topo em 0 para permitir que a Toolbar gerencie seu próprio preenchimento
        ViewCompat.setOnApplyWindowInsetsListener(drawer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura o ícone do menu para abrir a gaveta
        toolbar.setNavigationOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        menu.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_inicio -> {
                    texto.text = "Tela inicial"
                }
                R.id.menu_perfil -> {
                    texto.text = "Meu perfil"
                }
                R.id.menu_config -> {
                    texto.text = "Configurações"
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }
}
