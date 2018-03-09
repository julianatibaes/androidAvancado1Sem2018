package com.tibaes.testedesensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.data



class MainActivity : AppCompatActivity(), SensorEventListener {

    // lateinit vai permitir que você instancie o objeto "mais tarde"
    // deve-se utilizá-lo com cuidado, pois precisará ter certeza de que dará certo
    lateinit var texto: TextView

    // essa variável já está recebendo o valor vindo do getSystemService
    val sensorManager: SensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texto = textView

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    // toda vez que o sensor receber um evento alterando o valor do sensor
    // alterará o valor do texto do textView. Nesse caso, ele mostrará os valores X, Y, Z
    override fun onSensorChanged(event: SensorEvent?) {
        texto.text = event!!.values.zip("XYZ".toList()).fold(""){
            acc, pair ->
            "$acc${pair.second}: ${pair.first}\n"
        }
    }

    override fun onResume() {
        super.onResume()
        // o registerListener lerá o sensor, no tipo setado, e jogará no sensorManager
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        // parará a leitura quando sair da tela
        sensorManager.unregisterListener(this)
    }

}
