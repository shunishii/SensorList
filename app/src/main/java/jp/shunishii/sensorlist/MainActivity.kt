package jp.shunishii.sensorlist

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.hardware.Sensor.TYPE_STEP_DETECTOR
import android.hardware.Sensor.TYPE_STEP_COUNTER
import android.hardware.Sensor.TYPE_STATIONARY_DETECT
import android.hardware.Sensor.TYPE_SIGNIFICANT_MOTION
import android.hardware.Sensor.TYPE_ROTATION_VECTOR
import android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY
import android.hardware.Sensor.TYPE_PROXIMITY
import android.hardware.Sensor.TYPE_PRESSURE
import android.hardware.Sensor.TYPE_POSE_6DOF
import android.hardware.Sensor.TYPE_MOTION_DETECT
import android.hardware.Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED
import android.hardware.Sensor.TYPE_MAGNETIC_FIELD
import android.hardware.Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT
import android.hardware.Sensor.TYPE_LINEAR_ACCELERATION
import android.hardware.Sensor.TYPE_LIGHT
import android.hardware.Sensor.TYPE_HEART_RATE
import android.hardware.Sensor.TYPE_HEART_BEAT
import android.hardware.Sensor.TYPE_GYROSCOPE_UNCALIBRATED
import android.hardware.Sensor.TYPE_GYROSCOPE
import android.hardware.Sensor.TYPE_GRAVITY
import android.hardware.Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR
import android.hardware.Sensor.TYPE_GAME_ROTATION_VECTOR
import android.hardware.Sensor.TYPE_DEVICE_PRIVATE_BASE
import android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE
import android.hardware.Sensor.TYPE_ACCELEROMETER_UNCALIBRATED
import android.hardware.Sensor.TYPE_ACCELEROMETER
import android.os.Build
import androidx.annotation.RequiresApi
import android.util.Log


class MainActivity : WearableActivity() {

    private lateinit var sensorManager: SensorManager
    private val SensorListTAG = "ShowSensorList"

    @RequiresApi(Build.VERSION_CODES.N)
    private val sensorList = intArrayOf(
        TYPE_ACCELEROMETER,
        TYPE_ACCELEROMETER_UNCALIBRATED,
        TYPE_AMBIENT_TEMPERATURE,
        TYPE_DEVICE_PRIVATE_BASE,
        TYPE_GAME_ROTATION_VECTOR,
        TYPE_GEOMAGNETIC_ROTATION_VECTOR,
        TYPE_GRAVITY,
        TYPE_GYROSCOPE,
        TYPE_GYROSCOPE_UNCALIBRATED,
        TYPE_HEART_BEAT,
        TYPE_HEART_RATE,
        TYPE_LIGHT,
        TYPE_LINEAR_ACCELERATION,
        TYPE_LOW_LATENCY_OFFBODY_DETECT,
        TYPE_MAGNETIC_FIELD,
        TYPE_MAGNETIC_FIELD_UNCALIBRATED,
        TYPE_MOTION_DETECT,
        TYPE_POSE_6DOF,
        TYPE_PRESSURE,
        TYPE_PROXIMITY,
        TYPE_RELATIVE_HUMIDITY,
        TYPE_ROTATION_VECTOR,
        TYPE_SIGNIFICANT_MOTION,
        TYPE_STATIONARY_DETECT,
        TYPE_STEP_COUNTER,
        TYPE_STEP_DETECTOR
    )

    private val sensorNameList = arrayOf(
        "ACCELEROMETER",
        "ACCELEROMETER_UNCALIBRATED",
        "AMBIENT_TEMPERATURE",
        "DEVICE_PRIVATE_BASE",
        "GAME_ROTATION_VECTOR",
        "GEOMAGNETIC_ROTATION_VECTOR",
        "GRAVITY",
        "GYROSCOPE",
        "GYROSCOPE_UNCALIBRATED",
        "HEART_BEAT",
        "HEART_RATE",
        "LIGHT",
        "LINEAR_ACCELERATION",
        "LOW_LATENCY_OFFBODY_DETECT",
        "MAGNETIC_FIELD",
        "MAGNETIC_FIELD_UNCALIBRATED",
        "MOTION_DETECT",
        "POSE_6DOF",
        "PRESSURE",
        "PROXIMITY",
        "RELATIVE_HUMIDITY",
        "ROTATION_VECTOR",
        "SIGNIFICANT_MOTION",
        "STATIONARY_DETECT",
        "STEP_COUNTER",
        "STEP_DETECTOR"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        checkSensors()
        checkSensorsEach()
    }

    private fun checkSensors() {
        val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        val strListbuf = StringBuffer("Sensor List:\n\n")
        var count = 0

        for (sensor in sensors) {
            count++
            val str = String.format(
                "%s: <name>%s, <id>%d, <type>%d\n", (count).toString(), sensor.name, sensor.id, sensor.type
            )
            strListbuf.append(str)
        }
        Log.d(SensorListTAG, strListbuf.toString())
    }

    private fun checkSensorsEach() {
        val strbuf = StringBuffer("Sensor List:\n\n")
        for (i in 0..sensorList.size) {
            val sensor = sensorManager.getDefaultSensor(sensorList[i])

            if (sensor != null) {
                val strTmp = String.format(
                    "%s: %s: usable\n",
                    (i + 1).toString(), sensorNameList[i]
                )
                strbuf.append(strTmp)
            } else {
                val strTmp = String.format(
                    "%s: %s: XXX unusable\n",
                    (i + 1).toString(), sensorNameList[i]
                )
                strbuf.append(strTmp)
            }
        }
        Log.d(SensorListTAG, strbuf.toString())
    }
}
