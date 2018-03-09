package com.tibaes.testasensor

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Point
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread
// fonte https://gist.github.com/enzoftware/87a25a790ac43433dc019b1eb43ba283

// O SensorEventListener implementará os métodos de leitura dos sensores
// O Parcerable implementará os métodos para permitir enviar objetos entre as views do app
class MainActivity() : AppCompatActivity(), SensorEventListener, Parcelable {

    private var acelerometro: Sensor? = null
    var groundView: GroundView? = null

    val sensorManager: SensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        groundView = GroundView(this)
        setContentView(groundView)

        // diz que a tela será apenas em landscape
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            View.SYSTEM_UI_FLAG_FULLSCREEN
            View.SYSTEM_UI_FLAG_IMMERSIVE
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    // se alterar algo no sensor, a terá receberá o método updateME
    // que é responsável por alterar os itens na tela
    override fun onSensorChanged(event: SensorEvent?) {
        if(event != null){
            groundView!!.updateMe(event.values[1], event.values[0])
        }
    }

    override fun onResume() {
        super.onResume()
       sensorManager.registerListener(
               this,
               acelerometro,
               SensorManager.SENSOR_DELAY_GAME
       )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
    // essa classe será responsável por desenhar os itens na tela
    // quando houver uma thread
    class DrawThread(surfaceHolder: SurfaceHolder,
                     panel: GroundView): Thread(){
        private var surfaceHolder: SurfaceHolder? = null
        private var panel: GroundView? = null
        private var run = false

        init {
            this.surfaceHolder = surfaceHolder
            this.panel = panel
        }

        fun setRunning(run:  Boolean){
            this.run = run
        }
        // enquanto a minha thread estiver rodando, vou mandar
        // apagar tudo da tela e setar tudo de novo na tela
        override fun run() {
            var c:Canvas? = null
            while (run){
                c = null
                try{
                    c = surfaceHolder!!.lockCanvas(null)
                    synchronized(surfaceHolder!!){
                        panel!!.draw(c)
                    }
                } finally {
                    if(c!=null){
                        surfaceHolder!!.unlockCanvasAndPost(c)
                    }
                }
            }
        }
    }


    // essa classe ficará responsável por implementar a thread
    // que apaga e desenha os itens da tela em cada momeno
    class GroundView(context: Context?): SurfaceView(context),
            SurfaceHolder.Callback{

        // coordenadas da bola
        var cx: Float = 10.toFloat()
        var cy: Float = 10.toFloat()

        // última posição incrementada
        var lastGx: Float = 0.toFloat()
        var lastGy: Float = 0.toFloat()

        var picAltura: Int = 0
        var picComprimento: Int = 0
        var icon: Bitmap? = null

        var windowComprimento: Int = 0
        var windowAltura: Int = 0

        // variáveis para setar as bordas e permitir vibrar o celular
        var noBorderX = false
        var noBorderY = false
        var vibratorService: Vibrator? = null
        var thread:MainActivity.DrawThread? = null

        // ao instanciar a tela
        init {
            holder.addCallback(this)

            thread = MainActivity.DrawThread(holder, this)

            val display: Display = (getContext().
                    getSystemService(Context.WINDOW_SERVICE)
                    as WindowManager).defaultDisplay

            val size = Point()
            display.getSize(size)
            windowComprimento = size.x
            windowAltura = size.y
            icon = BitmapFactory.decodeResource(resources, R.drawable.ball)
            picAltura = icon!!.height
            picComprimento = icon!!.width
            vibratorService = (getContext().getSystemService(
                    Service.VIBRATOR_SERVICE)) as Vibrator

        }

        override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

        }

        override fun surfaceDestroyed(holder: SurfaceHolder?) {

        }

        // estarta a thread
        override fun surfaceCreated(holder: SurfaceHolder?) {
            thread!!.setRunning(true)
            thread!!.start()
        }

        // desenha os itens na tela
        override fun draw(canvas: Canvas?) {
            super.draw(canvas)
            if(canvas != null){
                // setando o fundo da tela, caso seja uma imagem,
                // basta por uma imagem que ocupe a tela inteira e o
                // método drawBitMap
                canvas.drawColor(0xFFAAAAA)
                // setando dado fixo na tela
                canvas.drawBitmap(icon, 110.toFloat(), 110.toFloat(), null)
                // setado os dados atualizados na tela
                canvas.drawBitmap(icon, cx, cy, null)
            }
        }

        // desenha os itens na tela
        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            if(canvas != null){
                // setando o fundo da tela, caso seja uma imagem,
                // basta por uma imagem que ocupe a tela inteira e o
                // método drawBitMap
                canvas.drawColor(0xFFAAAAA)
                // setando dado fixo na tela
                canvas.drawBitmap(icon, 10.toFloat(), 10.toFloat(), null)

                // setado os dados atualizados na tela
                canvas.drawBitmap(icon, cx, cy, null)
            }
        }


        // atualiza os itens na tela
        // lembre-se que a posição do item inicia do ponto superior esquerdo
        fun updateMe(inx: Float, iny: Float){
            lastGx += inx
            lastGy += iny

            cx += lastGx
            cy += lastGy


            // verificando os cantos laterais
            if(cx > (windowComprimento - picComprimento)) {
                cx = (windowComprimento - picComprimento).toFloat()
                lastGx = 0F
                if (noBorderX) {
                    vibratorService!!.vibrate(100)
                    noBorderX = false
                }
            }else if (cx < 0){
                cx = 0F
                lastGx = 0F
                if(noBorderX) {
                    vibratorService!!.vibrate(100)
                    noBorderX = false
                }
            } else{
                noBorderX = true
            }
            // verificando os cantos verticais
            if (cy > (windowAltura - picAltura)) {
                cy = (windowAltura - picAltura).toFloat()
                lastGy = 0F
                if (noBorderY) {
                    vibratorService!!.vibrate(100)
                    noBorderY = false
                }
            } else if (cy < (0)) {
                cy = 0F
                lastGy = 0F
                if (noBorderY) {
                    vibratorService!!.vibrate(100)
                    noBorderY = false
                }
            } else {
                noBorderY = true
            }
            invalidate()

        }
    }
}
