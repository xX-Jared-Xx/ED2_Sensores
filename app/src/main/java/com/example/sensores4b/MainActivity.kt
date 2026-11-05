package com.example.sensores4b

import android.graphics.Color
import android.graphics.ColorSpace
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


//Ahora tambien es una clase de Sesnor
class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var tvX : TextView
    private lateinit var tvY : TextView
    private lateinit var tvZ : TextView
    private lateinit var tvEstado : TextView
    private lateinit var sensorManager: SensorManager

    private lateinit var mainbg: LinearLayout
    private lateinit var tv1 : TextView

    //Creas objeto para
    private var sensorGravedad : Sensor? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mainbg = this.findViewById(R.id.mainbg)
        tv1 = this.findViewById(R.id.tv1)

        tvEstado = this.findViewById(R.id.tvEstado)
        tvX = this.findViewById(R.id.tvX)
        tvY = this.findViewById(R.id.tvY)
        tvZ = this.findViewById(R.id.tvZ)

        //Permiti el uso de servicios de Hardware del dispositivo
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        //Seleccionar el sensor de luz
        sensorGravedad = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

        //Validar si el sensor se encuentra en el dispositivo
        if(sensorGravedad == null){
            tvEstado.text = "(i) No existe el sensor de Gravedad en este dispositivo !"
        }

    }


    //METODOS OBLIGATORIOS PARA EL USO DE SENSORES

    //METODO PARA MEDIR LOS CAMBIOS EN LOS VALORES DEL SENSOR
    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_GRAVITY){
            // variable que guarde la informacion del sensor
            val gravedadX = event.values[0]
            val gravedadY = event.values[1]
            val gravedadZ = event.values[2]

            tvX.text = "Valor de X: $gravedadX"
            tvY.text = "Valor de Y: $gravedadY"
            tvZ.text = "Valor de Z: $gravedadZ"

            if(Math.abs(gravedadY) > 7 || Math.abs(gravedadX) > 7 || Math.abs(gravedadZ) > 7) {
                if (Math.abs(gravedadZ) > 7) {
                    mainbg.setBackgroundColor(Color.GREEN)
                    tvEstado.text = "Celcular Acostado o Superficie Plana"
                }
                if (Math.abs(gravedadX) > 7) {
                    mainbg.setBackgroundColor(Color.parseColor("#f55b0f"))
                    tvEstado.text = "Celcular inclinado Hacia un lado"
                }
                if (Math.abs(gravedadY) > 7) {
                    mainbg.setBackgroundColor(Color.BLACK)
                    tvEstado.text = "Celcular Vertical o inclinado"
                }
            }else{
                tvEstado.text ="Orientacion Indefinida"
                mainbg.setBackgroundColor(Color.WHITE)
            }


        }
    }

    //METODO PARA MEDIR EL VALOR MATEMATICO DEL SENSOR
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    //METODO PARA DETENER EL USO DEL SENSOR
    override fun onResume() {
        super.onResume()
         sensorGravedad?.let {
             sensorManager.registerListener(
                 this,
                 it,
                 SensorManager.SENSOR_DELAY_NORMAL
             )
         }

    }

}