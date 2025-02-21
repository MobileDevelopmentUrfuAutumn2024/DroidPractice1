package ru.urfu.droidpractice1

import android.util.Log
import androidx.activity.ComponentActivity

/**
 * Обертка над ComponentActivity, для логирования жизненного цикла
 */
abstract class LoggableLifeCycleComponentActivity : ComponentActivity() {

    override fun onStart() {
        Log.d(getClazz(), "onStart")
        super.onStart()
    }

    override fun onRestart() {
        Log.d(getClazz(), "onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Log.d(getClazz(), "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(getClazz(), "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(getClazz(), "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(getClazz(), "onDestroy")
        super.onDestroy()
    }

    /**
     * Получить название класса для ключа логирования
     */
    abstract fun getClazz(): String

}
